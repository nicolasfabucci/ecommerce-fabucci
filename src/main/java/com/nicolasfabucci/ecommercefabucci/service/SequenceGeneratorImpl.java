package com.nicolasfabucci.ecommercefabucci.service;

import com.nicolasfabucci.ecommercefabucci.models.documents.DatabaseSequences;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@AllArgsConstructor
@Service
public class SequenceGeneratorImpl implements SequenceGenerator {
    private final MongoOperations mongoOperations;

    public Long generateSequence(String sequenceName) {
        DatabaseSequences counter = mongoOperations.findAndModify(query(where("_id").is(sequenceName)),
                new Update().inc("value", 1), options().returnNew(true).upsert(true), DatabaseSequences.class);
        return !Objects.isNull(counter) ? counter.getValue() : 1;
    }
}
