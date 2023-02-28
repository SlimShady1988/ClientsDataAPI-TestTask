package com.app.clientsdataapi.repository;

import com.app.clientsdataapi.entity.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, String> {}