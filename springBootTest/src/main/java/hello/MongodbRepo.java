package hello;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by sren on 16-11-2.
 */
public interface MongodbRepo extends MongoRepository<Task, String> {

    public Task findByTaskId(String taskId);
}
