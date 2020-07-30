package problem

import scala.collection.mutable
import scala.collection.parallel.CollectionConverters._
import scala.collection.parallel.mutable.ParSeq

/**
 * 一对数字 （a, b)
 * 可以有运算： （a+b, b) 或 (a, a+b)
 * 初始值为 (1,1)
 * 求：给出一个数n,求出变形到n这个数字的最小步骤数。
 */
object Q1 {

  val y = 99998
  def main(args: Array[String]): Unit = {
    println(s"start ")

    val start = System.currentTimeMillis()
    val lv = calc2(y)
    println(s"number $y min level is $lv")

//    (3 to 100).foreach ( (i) => {
//      val lv = calc2(i)
//      println(s"number $i min level is $lv")
//    })
    val end = System.currentTimeMillis()
    println(s"use time: ${(end-start)}")
  }

  def
  calc2(y: Int): Int = {
    var i = 0
    var lst = Seq((1, 2)).par
    var level = 0
    while(level == 0) {
      i += 1
      // println(s" level $i queue size: ${lst.length}  min=${lst.min} max=${lst.max}")
      lst = lst.flatMap { t =>
        val c = t._1 + t._2
        if (c < y) {
          List((c, t._2), (t._1, c))
        } else if (c == y) {
          level = i
          List()
        } else {
          List()
        }
      }
    }
    return level
  }

  def calc(): Int = {
    val queue: mutable.Queue[(Int, Int, Int)] = mutable.Queue[(Int, Int, Int)]()
    queue.enqueue((2,2,1))
    var c = 0
    var level = 0
    while (c < y) {
      val (l, a, b) = queue.dequeue()
      level = l
      c=a+b
      queue.enqueue((l+1, c, b)).enqueue((l+1, a, c))
    }
    return level
  }
}
