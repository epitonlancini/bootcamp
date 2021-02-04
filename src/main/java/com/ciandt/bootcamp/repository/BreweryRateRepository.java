package com.ciandt.bootcamp.repository;

import com.ciandt.bootcamp.model.entity.BreweryRate;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreweryRateRepository extends PagingAndSortingRepository<BreweryRate, ObjectId> {
}
