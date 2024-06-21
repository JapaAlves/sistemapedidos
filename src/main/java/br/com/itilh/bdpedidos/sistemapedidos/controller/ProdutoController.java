package br.com.itilh.bdpedidos.sistemapedidos.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.itilh.bdpedidos.sistemapedidos.model.Produto;
import br.com.itilh.bdpedidos.sistemapedidos.repository.ProdutoRepository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class ProdutoController {

    private final ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/produtos")
    public List<Produto> getTodosProdutos() {
        return (List<Produto>) repository.findAll();
    }

    @GetMapping("/produto/{id}")
    public Produto getProdutoId(@PathVariable BigInteger id) throws Exception {
        return repository.findById(id).orElseThrow(
            () -> new Exception("ID inválido")
        );
    }

    @PostMapping("/produto")
    public Produto postProduto(@RequestBody Produto entity) throws Exception{
        try { 
            return repository.save(entity);
        }catch(Exception e) {
            throw new Exception("Erro ao salvar Estado");
        }
    }

    @PutMapping("/produto/{id}")
    public Produto putProduto(@PathVariable BigInteger id, 
                              @RequestBody Produto entity) throws Exception{
        try {
            return repository.save(entity);
        }catch (Exception ex) {
            throw new Exception("Não foi possível alterar o Produto informado " + ex.getMessage());
        }
    }

    @DeleteMapping("/produto/{id}") 
    public String deleteProduto(@PathVariable BigInteger id) throws Exception {
        try {
            repository.deleteById(id);
            return "Excluido";
        }catch (Exception ex) {
            throw new Exception("Não foi possível excluir o Produto informado " + ex.getMessage());
        }
    }
}
