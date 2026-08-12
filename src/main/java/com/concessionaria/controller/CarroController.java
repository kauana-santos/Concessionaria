package com.concessionaria.controller;

import com.concessionaria.dto.CarroDto;
import com.concessionaria.dto.CarroResponseDto;
import com.concessionaria.service.CarroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Veículos", description = "Cadastre, liste e delete veículos do banco de dados.")
@RestController
@RequestMapping("/api/concessionaria")
public class CarroController {
    @Autowired
    private CarroService carroService;

    @GetMapping
    @Operation(summary = "Listar todos os carros")
    public ResponseEntity<List<CarroResponseDto>> listarTodos(){
        return ResponseEntity.ok(carroService.listarTodos());
    }

    @Operation(summary = "Listar carros por id")
    @GetMapping("/{id}")
    public ResponseEntity<CarroResponseDto> listarPorId(@PathVariable Long id){
        return ResponseEntity.ok(carroService.listarPorId(id));
    }

    @ApiResponse(responseCode = "201", description = "Carro cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @Operation(summary = "Cadastrar carros")
    @PostMapping
    public ResponseEntity<CarroResponseDto> cadastrar(@Valid @RequestBody CarroDto dto) {
        CarroResponseDto carroSalvo = carroService.cadastrarCarro(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(carroSalvo);

    }
    @Operation(summary = "Deletar carros")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        carroService.deletarCarro(id);
        return ResponseEntity.noContent().build();
    }
}
