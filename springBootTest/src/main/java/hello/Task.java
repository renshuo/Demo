package hello;





import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by sren on 16-10-20.
 */


public class Task {


    @Id
    String id;

    String taskId;
    String planId;
    Date startTime;
    Date endTime;

    public Task() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", taskId='" + taskId + '\'' +
                ", planId='" + planId + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
