package com.nicolasfabucci.ecommercefabucci.models.schemas.requests;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
@Builder
public class UsuarioRequest {
    @NotBlank
    @NotNull
    @Length(min = 3, max = 15)
    private String usuario;
    @NotBlank
    @NotNull
    private String nombre;
    @NotNull(message = "Campo celular requerido")
    @Pattern(regexp = "^[0-9]*$", message = "se requiere solo numeros para el celular")
    private String celular;
    @Min(value = 18, message = "La edad mínima es 18")
    @Max(value = 100, message = "La edad máxima es 100")
    private Integer edad;
    @NotBlank(message = "Campo password requerido")
    @NotNull(message = "Campo password requerido")
    private String password;
    @NotBlank
    @NotNull
    @Email
    private String email;
}

