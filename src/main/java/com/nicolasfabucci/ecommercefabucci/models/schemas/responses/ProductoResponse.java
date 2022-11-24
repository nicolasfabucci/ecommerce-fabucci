package com.nicolasfabucci.ecommercefabucci.models.schemas.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoResponse {
    private Long codigo;
    private Double precio;
    private String categoria;
    private String descripcion;
}
