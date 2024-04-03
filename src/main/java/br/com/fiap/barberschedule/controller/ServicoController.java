package br.com.fiap.barberschedule.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import org.springframework.ai.openai.OpenAiChatClient;
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
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("servico")
@Slf4j
@SuppressWarnings("null")
public class ServicoController {

    @Autowired
    ServicoRepository repository;

    @Autowired
    OpenAiChatClient gpt;

    @GetMapping
    public List<Servico> index() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Servico create(@RequestBody @Valid Servico servico) { // binding
        log.info("cadastrando servico {} ", servico);
        var icone = gpt.call("Sugira um icone do Material Icons para uma servico chamada " + servico.getNome()
                + ". Retorne apenas o nome do ícone");
        servico.setIcone(icone);
        return repository.save(servico);
    }

    @GetMapping("{id}")
    public ResponseEntity<Servico> show(@PathVariable Long id) {
        log.info("buscando servico por id {}", id);

        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("apagando servico");

        verificarSeExisteServico(id);
        repository.deleteById(id);
    }

    @PutMapping("{id}")
    public Servico update(@PathVariable Long id, @RequestBody Servico servico) {
        log.info("atualizando servico com id {} para {}", id, servico);

        verificarSeExisteServico(id);
        servico.setId(id);
        return repository.save(servico);
    }

    private void verificarSeExisteServico(Long id) {
        repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Não existe servico com o id informado. Consulte lista em /servico"));
    }

}