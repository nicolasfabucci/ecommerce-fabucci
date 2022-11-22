package com.nicolasfabucci.ecommercefabucci.models.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="database_sequences")
@Data
public class DatabaseSequences {
    @Id
    private String id;
    private Long value;
}
