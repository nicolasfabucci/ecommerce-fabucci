package com.nicolasfabucci.ecommercefabucci.models.documents;


import com.nicolasfabucci.ecommercefabucci.models.enums.ERole;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class RoleDocument {
    @Id
    private String id;
    private ERole name;


    public RoleDocument() {

    }

    public RoleDocument(ERole name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}
