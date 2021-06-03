package demo;


import demo.model.UserInn;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InnRepository extends MongoRepository<UserInn, String> {
}