package com.nicolasfabucci.ecommercefabucci.repositories;

import com.nicolasfabucci.ecommercefabucci.models.documents.CarritoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface CarritoRepository  extends MongoRepository<CarritoDocument, String> {
      Optional<CarritoDocument> findByUserId(String userId);
      void deleteByUserId(String userId);
}
