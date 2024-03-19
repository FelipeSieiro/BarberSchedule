package br.com.fiap.barberschedule.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

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

import br.com.fiap.barberschedule.model.Perfil;
import br.com.fiap.barberschedule.repository.PerfilRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("perfil")
@Slf4j
@SuppressWarnings("null")
public class PerfilController {
    
    @Autowired
    PerfilRepository repository;

    @GetMapping
    public List<Perfil> index() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Perfil create(@RequestBody Perfil perfil) { 
        log.info("cadastrando perfil {} ", perfil);
        return repository.save(perfil);
    }

    @GetMapping("{id}")
    public ResponseEntity<Perfil> show(@PathVariable Long id) {
        log.info("buscando perfil por id {}", id);

        return repository
                .findById(id)
                .map(ResponseEntity::ok) 
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("apagando perfil");

        verificarSeExistePerfil(id);
        repository.deleteById(id);
    }

    @PutMapping("{id}")
    public Perfil update(@PathVariable Long id, @RequestBody Perfil perfil) {
        log.info("atualizando perfil com id {} para {}", id, perfil);

        verificarSeExistePerfil(id);
        perfil.setId(id);
        return repository.save(perfil);
    }

    private void verificarSeExistePerfil(Long id) {
        repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Não existe servico com o id informado. Consulte lista em /servico"));
    }
}
