package com.concessionaria.service;

import com.concessionaria.dto.CarroDto;
import com.concessionaria.dto.CarroResponseDto;
import com.concessionaria.exception.RecursoNaoEncontradoException;
import com.concessionaria.exception.RegistroDuplicadoException;
import com.concessionaria.model.Carro;
import com.concessionaria.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {
    @Autowired
    private CarroRepository carroRepository;

    public List<CarroResponseDto> listarTodos(String cor, Integer ano) {

        List<Carro> carros;

        if (cor != null && ano != null) {
            carros = carroRepository.findByFiltros(cor, ano);

        } else if (cor != null) {
            carros = carroRepository.findByFiltros(cor, null);

        } else if (ano != null) {
            carros = carroRepository.findByFiltros(null, ano);

        } else {
            carros = carroRepository.findByFiltros(null, null);
        }

        return carros.stream()
                .map(this::toResponse)
                .toList();
    }

    public CarroResponseDto listarPorId(Long id){
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Carro não encontrado."
                        )
                );

        return toResponse(carro);
    }

    public CarroResponseDto cadastrarCarro(CarroDto dto){

        if (carroRepository.existsByChassi(dto.chassi())) {
            throw new RegistroDuplicadoException(
                    "chassi",
                    "Este chassi já está cadastrado."
            );
        }

        if (dto.placa() != null && carroRepository.existsByPlaca(dto.placa())) {
            throw new RegistroDuplicadoException(
                    "placa",
                    "Esta placa já está cadastrada."
            );
        }

        Carro carro = Carro.builder()
                .modelo(dto.modelo())
                .marca(dto.marca())
                .anoFabricacao(dto.anoFabricacao())
                .anoModelo(dto.anoModelo())
                .cor(dto.cor())
                .placa(dto.placa())
                .chassi(dto.chassi())
                .quilometragem(dto.quilometragem())
                .preco(dto.preco())
                .status(dto.status())
                .tipoCarro(dto.tipoCarro())
                .build();

        Carro carroSalvo = carroRepository.save(carro);

        return toResponse(carroSalvo);
    }

    private CarroResponseDto toResponse(Carro carro){
        return new CarroResponseDto(
                carro.getId(),
                carro.getModelo(),
                carro.getMarca(),
                carro.getAnoFabricacao(),
                carro.getAnoModelo(),
                carro.getCor(),
                carro.getPlaca(),
                carro.getChassi(),
                carro.getQuilometragem(),
                carro.getPreco(),
                carro.getStatus(),
                carro.getTipoCarro()
        );
    }

    public void deletarCarro(Long id){
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Carro não encontrado"));

        carroRepository.delete(carro);
    }
}
