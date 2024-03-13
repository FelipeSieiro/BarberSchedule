package br.com.fiap.barberschedule.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.barberschedule.model.Servico;
import br.com.fiap.barberschedule.repository.ServicoRepository;


@RestController
@RequestMapping("servico")
public class ServicoController {

      Logger log = LoggerFactory.getLogger(getClass());

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
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Servico> optionalServico = repository.findById(id);
        if (optionalServico.isPresent()) {
            repository.deleteById(id);
            log.info("Serviço removido com sucesso: ID {}", id);
            return ResponseEntity.noContent().build();
        } else { 
            log.error("Serviço não encontrado para o ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    } 
}

     
