package br.com.fiap.barberschedule.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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
}

     
