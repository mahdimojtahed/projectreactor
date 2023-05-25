package com.dotin.projectreactor.java_techie_utube.reactive_mongo.repository;

import com.dotin.projectreactor.java_techie_utube.reactive_mongo.domain.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

}
