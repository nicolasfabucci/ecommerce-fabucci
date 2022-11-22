package com.nicolasfabucci.ecommercefabucci.models.schemas.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nicolasfabucci.ecommercefabucci.models.documents.CarritoItem;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarritoResponse {
    private List<CarritoItem> items;
    private LocalDate fechaHora;
    private String direccionEntrega;
    private String userId;
}
