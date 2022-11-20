package com.nicolasfabucci.ecommercefabucci.models.schemas.requests;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@Builder
public class ProductoRequest {
    @NotBlank
    @NotNull
    private String codigo;
    @NotNull
    private Double precio;
    @NotBlank
    @NotNull
    private String descripcion;
    @NotBlank
    @NotNull
    private String categoria;
}
