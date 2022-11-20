package com.nicolasfabucci.ecommercefabucci.repositories;

import com.nicolasfabucci.ecommercefabucci.models.documents.UsuarioDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UsuarioRepository extends MongoRepository<UsuarioDocument, String> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<UsuarioDocument>  findByUsername(String username);
    Optional<UsuarioDocument> findByEmail(String email);
}
