package com.nicolasfabucci.ecommercefabucci.repositories;

import com.nicolasfabucci.ecommercefabucci.models.documents.OrdenDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface OrdenRepository extends MongoRepository<OrdenDocument, Long> {
    Optional<OrdenDocument> findByOrderNumber(Long orderNumber);
}
