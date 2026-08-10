package com.concessionaria.controller;

import com.concessionaria.model.Carro;
import com.concessionaria.repository.CarroRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/concessionaria")
public class CarroController {
    @Autowired
    private CarroRepository carroRepository;

    @GetMapping
    @Operation(summary = "Listar todos os carros")
    public List<Carro> listarTodos(){
        return carroRepository.findAll();
    }

    @Operation(summary = "Listar carros por id")
    @GetMapping("/{id}")
    public ResponseEntity<Carro> listarPorId(@PathVariable Long id){
        Optional<Carro> carro = carroRepository.findById(id);

        return ResponseEntity.ok(carro.get());
    }

    @Operation(summary = "Cadastrar carros")
    @PostMapping
    public ResponseEntity<Carro> cadastrar(@RequestBody Carro carro){
        Carro carroSalvo = carroRepository.save(carro);

        return ResponseEntity.status(HttpStatus.CREATED).body(carroSalvo);

    }
    @Operation(summary = "Deletar carros")
    @DeleteMapping("/{id}")
    public ResponseEntity<Carro> deletar(@PathVariable Long id){
        carroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
