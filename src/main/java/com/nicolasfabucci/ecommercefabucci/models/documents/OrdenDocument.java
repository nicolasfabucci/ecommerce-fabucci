package com.nicolasfabucci.ecommercefabucci.models.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "ordenes")
@Data
@Builder
public class OrdenDocument {
    @Id
    private String id;
    private Integer numeroOrden;
    private String email;
    private LocalDateTime createDate;
    private List<CarritoDocument> productos;
    private String estado;
    private String direccionEntrega;
}
