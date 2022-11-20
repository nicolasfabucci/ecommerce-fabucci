package com.nicolasfabucci.ecommercefabucci.repositories;

import com.nicolasfabucci.ecommercefabucci.models.documents.OrdenDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrdenRepository extends MongoRepository<OrdenDocument, Integer> {
    public OrdenDocument findByEmail(String email);
    public OrdenDocument findByNumeroOrden(Integer numeroOrden);
}
