package com.concessionaria.dto;

import com.concessionaria.model.Status;
import com.concessionaria.model.TipoCarro;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.math.BigDecimal;


public record CarroDto (
        @NotBlank(message = "O modelo é obrigatório")
        String modelo,

        @NotBlank(message = "A marca é obrigatória")
        String marca,

        @NotNull(message = "O ano de fabricação é obrigatório")
        @Min(value = 1950, message = "O ano de fabricação deve ser no mínimo 1950")
        @Max(value = 2027, message = "O ano de fabricação deve ser no máximo 2027")
        Integer anoFabricacao,

        @NotNull(message = "O ano do modelo é obrigatório")
        @Min(value = 1950, message = "O ano do modelo deve ser no mínimo 1950")
        @Max(value = 2027, message = "O ano do modelo deve ser no máximo 2027")
        Integer anoModelo,

        @NotBlank(message = "A cor é obrigatória")
        String cor,

        String placa,

        @NotBlank(message = "O chassi é obrigatório")
        String chassi,

        @NotNull(message = "A quilometragem é obrigatória")
        @Min(value = 0, message = "A quilometragem não pode ser negativa")
        Integer quilometragem,

        @NotNull(message = "O preço é obrigatório")
        @DecimalMin(value = "0.0", inclusive = true, message = "O preço não pode ser negativo")
        BigDecimal preco,

        @NotNull(message = "O status é obrigatório")
        Status status,

        @NotNull(message = "O tipo do carro é obrigatório")
        TipoCarro tipoCarro
){

}
