package com.concessionaria.controller;

import com.concessionaria.dto.ClienteDto;
import com.concessionaria.dto.ClienteResponseDto;
import com.concessionaria.model.Carro;
import com.concessionaria.model.Cliente;
import com.concessionaria.repository.ClienteRepository;
import com.concessionaria.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Listar clientes")
    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> listarTodos(){
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @Operation(summary = "Listar clientes por id")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> listarPorId(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.listarPorId(id));
    }

    @Operation(summary = "Cadastrar clientes")
    @PostMapping
    public ResponseEntity<ClienteResponseDto> cadastrar(@Valid @RequestBody ClienteDto dto){
        ClienteResponseDto ClienteSalvo = clienteService.cadastrarCliente(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteSalvo);

    }
    @Operation(summary = "Deletar clientes")
    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> deletar(@PathVariable Long id){
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
