package com.nicolasfabucci.ecommercefabucci.models.schemas.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioResponse {
    private String usuario;
    private String email;
    private String token;
}
