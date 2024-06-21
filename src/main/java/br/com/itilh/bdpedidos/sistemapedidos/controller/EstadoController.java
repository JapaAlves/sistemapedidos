package br.com.itilh.bdpedidos.sistemapedidos.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.itilh.bdpedidos.sistemapedidos.model.Estado;
import br.com.itilh.bdpedidos.sistemapedidos.repository.EstadoRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class EstadoController {
    
    private final EstadoRepository repository;
    
    public EstadoController(EstadoRepository repository){
        this.repository = repository;
    }

    @GetMapping("/estados")
    public List<Estado> getTodos() {
        return (List<Estado>) repository.findAll(); 
    }
    
    @GetMapping("/estado/{id}")
    public Estado getPorId(@PathVariable BigInteger id) throws Exception {
        return repository.findById(id).orElseThrow(
            () -> new Exception("ID inválido")
        );
    }
    
    @PostMapping("/estado")
    public Estado criarEstado(@RequestBody Estado entity) throws Exception {
        try {
            return repository.save(entity);
        }catch(Exception e) {
            throw new Exception("Erro ao salvar estado");
        }
    }

    @PutMapping("/estado/{id}")
    public Estado alterarEstado(@PathVariable BigInteger id, 
                                @RequestBody Estado novosDados) throws Exception {
        Optional<Estado> estadoArmazenado = repository.findById(id); 
        if(estadoArmazenado.isPresent()) {
            // atribuir novo nome ao objeto ja existente no banco de dados
            estadoArmazenado.get().setNome(novosDados.getNome());
            //
            return repository.save(estadoArmazenado.get());
        }
            throw new Exception("Alteração não realizada");
    }

    @DeleteMapping("/estado/{id}")
    public String deletePorId(@PathVariable BigInteger id) throws Exception {

        Optional<Estado> estadoArmazenado = repository.findById(id); 
        if(estadoArmazenado.isPresent()) {
            repository.delete(estadoArmazenado.get());
            return "Excluído";
        }
            throw new Exception("Id não escontrado para exclusão");
    }
}
