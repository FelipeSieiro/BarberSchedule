package br.com.fiap.barberschedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.barberschedule.model.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}