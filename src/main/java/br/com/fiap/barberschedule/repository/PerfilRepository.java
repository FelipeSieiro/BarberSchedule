package br.com.fiap.barberschedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.barberschedule.model.Perfil;

public interface PerfilRepository  extends JpaRepository<Perfil, Long>{
    
}
