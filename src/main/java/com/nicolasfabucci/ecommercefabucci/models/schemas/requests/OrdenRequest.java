package com.nicolasfabucci.ecommercefabucci.models.schemas.requests;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class OrdenRequest {
    @Email
    private String email;
    @NotBlank
    @NotNull
    private String direccionEntrega;
}
