(defun make-cd (title artist rating ripped)
  (list :title title :art artist :rat rating :rip ripped))


(defvar *db* nil)

(defun add-record (cd) 
  (push cd *db*))

(add-record (make-cd "stille" "lacrimosa" 5 t))


(defun dump-db ()
  (dolist (cd *db*)
	(format t "~{~a: ~10t~a~%~}~%" cd)))

(defun cread (prompt)
  (format *query-io* "~a: " prompt)
;;  (force-output query-io*)
  (read-line *query-io*))

(defun prompt-cd ()
  (make-cd
   (cread "Title")
   (cread "art")
   (or (parse-integer (cread "rating") :junk-allowed t) 0)
   (y-or-n-p "Ripped [y/n]")))

(defun add-cds ()
  (loop (add-record (prompt-cd))
	 (if (not (y-or-n-p "Another? [y/n]: "))
	 (return))))


(defun save-db (filename)
  (with-open-file (out filename
					   :direction :output :if-exists :supersede)
	(with-standard-io-syntax
	  (print *db* out))))

(defun load-db (filename)
  (with-open-file (in filename)
	(with-standard-io-syntax
	  (setf *db* (read in)))))

(load-db "/home/db")

;; query func

(defun select-by-art (art)
  (remove-if-not 
   #'(lambda (cd) 
	   (equal (getf cd :art) art))
   *db*)
)

(defun select-by (select-fn)
  (remove-if-not select-fn *db*))

(defun art-selector (art)
  #'(lambda (cd) 
	  (equal (getf cd :art) art)))


(defun where (&key title art rat (rip nil rip-p))
  #'(lambda (cd)
	  (and 
	   (if title (equal (getf cd :title) title) t)
	   (if art (equal (getf cd :art) art) t)
	   (if rat (equal (getf cd :rat) rat) t)
	   (if rip-p (equal (getf cd :rip) rip) t))))

(defun update (selector-f &key title art rat (rip nil rip-p))
  (setf *db*
  (mapcar 
   #'(lambda (row)
  (when (funcall selector-f row)
	(if title (setf (getf row :title) title))
	(if art (setf (getf row :art) art))
	(if rat (setf (getf row :rat) rat))
	(if rip-p (setf (getf rip :rip) rip)))
               row) *db*)))
  

