package com.concessionaria.dto;

import com.concessionaria.model.Status;
import com.concessionaria.model.TipoCarro;

import java.math.BigDecimal;

public record CarroResponseDto(
        Long id,
        String modelo,
        String marca,
        Integer anoFabricacao,
        Integer anoModelo,
        String cor,
        String placa,
        String chassi,
        Integer quilometragem,
        BigDecimal preco,
        Status status,
        TipoCarro tipoCarro
) {
}
