package com.nicolasfabucci.ecommercefabucci.models.schemas.requests;
import lombok.*;

import javax.validation.constraints.Min;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarritoItemRequest {
    @Min(1)
    private Integer cantidad;
}