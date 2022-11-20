package com.nicolasfabucci.ecommercefabucci.repositories;

import com.nicolasfabucci.ecommercefabucci.models.documents.ProductoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends MongoRepository<ProductoDocument, String> {
    ProductoDocument findByCodigo(String codigo);
    List<ProductoDocument> findByCategoria(String categoria);
}
