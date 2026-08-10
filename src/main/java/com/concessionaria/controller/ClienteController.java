package com.concessionaria.controller;

import com.concessionaria.model.Carro;
import com.concessionaria.model.Cliente;
import com.concessionaria.repository.ClienteRepository;
import io.swagger.v3.oas.annotations.Operation;
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
    private ClienteRepository clienteRepository;

    @Operation(summary = "Listar clientes")
    @GetMapping
    public List<Cliente> listarTodos(){
        return clienteRepository.findAll();
    }

    @Operation(summary = "Listar clientes por id")
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> listarPorId(@PathVariable Long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);

        return ResponseEntity.ok(cliente.get());
    }

    @Operation(summary = "Cadastrar clientes")
    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente){
        Cliente ClienteSalvo = clienteRepository.save(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteSalvo);

    }
    @Operation(summary = "Deletar clientes")
    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> deletar(@PathVariable Long id){
        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
