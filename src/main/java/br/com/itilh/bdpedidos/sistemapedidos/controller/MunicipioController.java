package br.com.itilh.bdpedidos.sistemapedidos.controller;

import org.springframework.web.bind.annotation.RestController;
import br.com.itilh.bdpedidos.sistemapedidos.repository.MunicipioRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import br.com.itilh.bdpedidos.sistemapedidos.model.Municipio;
import java.math.BigInteger;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class MunicipioController {

    private final MunicipioRepository repository;

    public MunicipioController(MunicipioRepository repository){
        this.repository = repository;
    }

    @GetMapping("/municipios")
    public List<Municipio> getMunicipios( ) {
        return (List<Municipio>) repository.findAll();
    }
    
     @GetMapping("/municipio/{id}")
    public Municipio getMunicipioPorId(@PathVariable BigInteger id) throws Exception {
        return repository.findById(id)
        .orElseThrow(()-> new Exception("Id não encontrado."));
    }
    
    @PostMapping("/municipio")
    public Municipio postMunicipio(@RequestBody Municipio entity) throws Exception{
        try {
            return repository.save(entity);
        }catch (Exception ex) {
            throw new Exception("Não foi possível criar o Município. " + ex.getMessage());
        }
    }

    @PutMapping("municipio/{id}")
    public Municipio putMunicipio(@PathVariable BigInteger id, 
                                  @RequestBody Municipio entity) throws Exception{
        try {
            return repository.save(entity);
        }catch (Exception ex) {
            throw new Exception("Não foi possível alterar o Município informado. " + ex.getMessage());
        }
    }

    @DeleteMapping("/municipio/{id}")
    public String deleteMunicipio(@PathVariable BigInteger id) throws Exception{
        try {
            repository.deleteById(id);
            return "Excluído";
        }catch (Exception ex) {
            throw new Exception("Não foi possível excluir o Município id informado. " + ex.getMessage());
        }
    }
    
}
