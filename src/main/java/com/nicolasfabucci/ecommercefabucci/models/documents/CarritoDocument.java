package com.nicolasfabucci.ecommercefabucci.models.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "carrito")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarritoDocument {

    @Id
    private String id;
    private String userId;
    private List<CarritoItem> items;
    private LocalDate fechaHora;
    private String direccionEntrega;

}
