package com.nicolasfabucci.ecommercefabucci.repositories;

import com.nicolasfabucci.ecommercefabucci.models.documents.CarritoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CarritoRepository  extends MongoRepository<CarritoDocument, String> {
    CarritoDocument findByCodigo(String codigo);
}
