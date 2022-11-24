package com.nicolasfabucci.ecommercefabucci.models.documents;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="producto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProductoDocument {
    @Id
    private String id;
    private Long codigo;
    private Double precio;
    private String categoria;
    private String descripcion;

}