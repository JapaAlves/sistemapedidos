package br.com.itilh.bdpedidos.sistemapedidos.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.itilh.bdpedidos.sistemapedidos.model.Cliente;
import br.com.itilh.bdpedidos.sistemapedidos.repository.ClienteRepository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class ClienteController {

    private final ClienteRepository repository;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/clientes")
    public List<Cliente> getClientes() {
        return (List<Cliente>) repository.findAll();
    }

    @GetMapping("/cliente/{id}")
    public Cliente getClientePorId(@PathVariable BigInteger id) throws Exception {
        return repository.findById(id)
        .orElseThrow(() -> new Exception("Id não encontrado."));
    }

    @PostMapping("/cliente")
    public Cliente postCliente(@RequestBody Cliente entity) throws Exception{
        try {
            return repository.save(entity);
        }catch (Exception ex) {
            throw new Exception("Não foi possível criar novo Cliente. " + ex.getMessage());
        }
    }

    @PutMapping("cliente/{id}")
    public Cliente putCliente(@PathVariable BigInteger id, @RequestBody Cliente entity) throws Exception {
        try {
            return repository.save(entity);
        }catch (Exception ex) {
            throw new Exception("Não foi possível alterar o Cliente informado. " + ex.getMessage());
        }
    }

    @DeleteMapping("/cliente/{id}")
    public String deleteCliente(@PathVariable BigInteger id) throws Exception{
        try {
            repository.deleteById(id);
            return "Cliente Excluído";
        }catch (Exception ex) {
            throw new Exception("Não foi possível excluir o Cliente informado.");
        }
    }
}
