package com.nicolasfabucci.ecommercefabucci.models.schemas.requests;

import com.nicolasfabucci.ecommercefabucci.models.documents.ProductoItem;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarritoRequest {
    @NotBlank
    @NotNull
    private String direccionEntrega;
    private String userId;
}
