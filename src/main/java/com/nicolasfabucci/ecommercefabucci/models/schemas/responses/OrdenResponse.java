package com.nicolasfabucci.ecommercefabucci.models.schemas.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nicolasfabucci.ecommercefabucci.models.documents.CarritoItem;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdenResponse {
    private Double total;
    private String email;
    private List<CarritoItem> items;
    private Long orderNumber;
}
