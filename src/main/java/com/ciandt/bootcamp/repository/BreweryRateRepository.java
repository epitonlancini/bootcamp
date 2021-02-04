package com.ciandt.bootcamp.repository;

import com.ciandt.bootcamp.model.entity.BreweryRate;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreweryRateRepository extends MongoRepository<BreweryRate, ObjectId> {
}
