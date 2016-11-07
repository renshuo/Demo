package hello.data;

import hello.MongodbRepo;
import hello.Task;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sren on 16-11-3.
 */

@RestController
@RequestMapping("/task")
public class TaskService {

    Logger log = LoggerFactory.getLogger(this.getClass());



    @Autowired
    MongodbRepo taskRepo;



    @ApiOperation(value="获取所有任务", notes="")
    @RequestMapping(value="/", method = RequestMethod.GET)
    @ResponseBody
    public List<Task> getAllTask(){
        return  taskRepo.findAll();
    }


    @ApiOperation("添加任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name="task", value="任务数据", dataType = "hello.Task"),
            @ApiImplicitParam(name="taskId", value="没用的takId，用于将url和别的对齐")
    })
    @RequestMapping(value="/{taskId}", method=RequestMethod.POST)
    public String addTask(@RequestBody Task task, @PathVariable String taskId) {
        /* for RequestBody, should post a json like {"id":"q","taskId":"q","planId":"q4","startTime":null,"endTime":null}
         * from client. and set the content type to application/json */
        log.info("save task("+taskId+"): "+task);
        taskRepo.save(task);
        return "success: "+task;
    }


    @RequestMapping(value = "/{taskId}", method = RequestMethod.GET)
    @ResponseBody
    public Task getTaskByTaskId(@PathVariable("taskId") String taskId){
        Task task = taskRepo.findByTaskId(taskId);
        log.debug("get the task: "+task);
        return task;
    }

    @RequestMapping(value = "/{taskId}", method = RequestMethod.PUT)
    @ResponseBody
    public void updateTaskByTaskId(@PathVariable("taskId") String taskId,@ModelAttribute Task task){
        Task t = getTaskByTaskId(taskId);
        t.setPlanId(task.getPlanId());
        t.setEndTime(task.getEndTime());
        t.setStartTime(task.getStartTime());
        taskRepo.save(t);
    }

    @RequestMapping(value="/{taskId}", method=RequestMethod.DELETE)
    public String deleteTaskByTaskId(@PathVariable String taskId) {
        log.info("delet task: taskId= "+taskId);
        Task task = taskRepo.findByTaskId(taskId);
        log.info("find the task: "+task);
        taskRepo.delete(task);

        return "success";
    }

}
