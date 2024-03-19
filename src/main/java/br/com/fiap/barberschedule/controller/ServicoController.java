package br.com.fiap.barberschedule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.barberschedule.model.Servico;
import br.com.fiap.barberschedule.repository.ServicoRepository;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("servico")
@Slf4j
@SuppressWarnings("null")
public class ServicoController {

    @Autowired 
    ServicoRepository repository;
    

    @GetMapping
    public List<Servico> index() {
        return repository.findAll() ; 
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Servico create(@RequestBody Servico servico) { 
        log.info("cadastrando servico {} ", servico);
        repository.save(servico);
        return servico;
    }

    @GetMapping("{id}")
    public ResponseEntity<Servico> show(@PathVariable Long id) {
        log.info("buscando serviço por id {}", id);

        return repository
            .findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        log.info("apagando serviço");

        verificarSeExisteServico(id);
        
        repository.deleteById(id);
        return ResponseEntity.noContent().build();

    }
    @PutMapping("{id}")
    public ResponseEntity<Servico> update(@PathVariable Long id, @RequestBody Servico servico){
        log.info("atualizando Servico com id {} para {}", id, servico);
        
        verificarSeExisteServico(id);
                        
        servico.setId(id);
        repository.save(servico);
        return ResponseEntity.ok(servico);
    }


    private void verificarSeExisteServico(Long id) {
        repository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Não existe categoria com o `id` informado. Consulte lista em /categoria"
            ));
    }
}

     
