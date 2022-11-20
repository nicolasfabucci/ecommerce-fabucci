package com.nicolasfabucci.ecommercefabucci.models.documents;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;

@Document(collection = "carrito")
@Data
@Builder
public class CarritoDocument {
    private String codigo;
    @Min(1)
    private Integer cantidad;
}
