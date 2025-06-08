package grapefrui.xyz.backend;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IntelRepository extends MongoRepository<Intel, String> {
}