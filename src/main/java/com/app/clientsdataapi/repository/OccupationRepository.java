package com.app.clientsdataapi.repository;

import com.app.clientsdataapi.entity.Gender;
import com.app.clientsdataapi.entity.Occupation;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface OccupationRepository extends MongoRepository<Occupation, String> {
    List<Occupation> findOccupationsByType(Gender type);
    boolean existsByName(String name);
}
