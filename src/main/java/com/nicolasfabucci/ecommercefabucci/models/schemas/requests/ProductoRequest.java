package com.nicolasfabucci.ecommercefabucci.models.schemas.requests;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@Builder
public class ProductoRequest {
    private Long codigo;
    @NotNull(message = "Debe ingresar un precio")
    private Double precio;
    @NotBlank(message = "Debe ingresar una descripcion")
    @NotNull(message = "Debe ingresar una descripcion")
    private String descripcion;
    @NotBlank(message = "Debe ingresar una categoria")
    @NotNull(message = "Debe ingresar una categoria")
    private String categoria;
}
