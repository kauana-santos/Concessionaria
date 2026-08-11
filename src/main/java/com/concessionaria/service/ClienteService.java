package com.concessionaria.service;

import com.concessionaria.dto.CarroResponseDto;
import com.concessionaria.dto.ClienteDto;
import com.concessionaria.dto.ClienteResponseDto;
import com.concessionaria.exception.RegistroDuplicadoException;
import com.concessionaria.model.Cliente;
import com.concessionaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteResponseDto> listarTodos(){
        return clienteRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ClienteResponseDto listarPorId(Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        return toResponse(cliente);
    }

    public ClienteResponseDto cadastrarCliente(ClienteDto dto){

        if (clienteRepository.existsByCpf(dto.cpf())) {
            throw new RegistroDuplicadoException(
                    "cpf",
                    "Este CPF já está cadastrado."
            );
        }

        Cliente cliente = Cliente.builder()
                .nome(dto.nome())
                .cpf(dto.cpf())
                .email(dto.email())
                .telefone(dto.telefone())
                .build();

        Cliente clienteSalvo = clienteRepository.save(cliente);
        return toResponse(clienteSalvo);
    }

    public void deletar(Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        clienteRepository.delete(cliente);
    }

    private ClienteResponseDto toResponse(Cliente cliente){
        return new ClienteResponseDto(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getTelefone()
        );
    }
}
