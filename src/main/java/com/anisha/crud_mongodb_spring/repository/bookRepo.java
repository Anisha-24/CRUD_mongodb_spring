package com.anisha.crud_mongodb_spring.repository;

import com.anisha.crud_mongodb_spring.model.book_data;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface bookRepo extends MongoRepository<book_data, String> {
    //Iterable<Object> findByTitleContaining(String title);

    List<book_data> findByTitleContaining(String title); //returns all Tutorials which title contains input title.
    List<book_data> findByPublished(boolean published); //returns all Tutorials with published having value as input published.
}

//Now we can use MongoRepository’s methods: save(), findOne(), findById(), findAll(), count(), delete(), deleteById()…
// without implementing these methods.

//The implementation is plugged in by Spring Data MongoDB automatically.