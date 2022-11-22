package com.nicolasfabucci.ecommercefabucci.repositories;

import com.nicolasfabucci.ecommercefabucci.models.documents.DatabaseSequences;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DatabaseSequencesRepository extends MongoRepository<DatabaseSequences, String> {
}
