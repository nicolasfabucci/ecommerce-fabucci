package com.nicolasfabucci.ecommercefabucci.repositories;

import com.nicolasfabucci.ecommercefabucci.models.documents.RoleDocument;
import com.nicolasfabucci.ecommercefabucci.models.enums.ERole;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<RoleDocument, String> {
  Optional<RoleDocument> findByName(ERole name);
}
