package com.nicolasfabucci.ecommercefabucci.models.documents;

import com.nicolasfabucci.ecommercefabucci.models.enums.StateEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection="orden")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdenDocument {
    @Transient
    public static final String SEQUENCE_NAME = "orderNumber";
    @Id
    private Long orderNumber;
    private String userId;
    private List<CarritoItem> items;
    private Double total;
    private StateEnum estado;
    private String email;
}
