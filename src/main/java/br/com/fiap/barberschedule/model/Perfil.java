package br.com.fiap.barberschedule.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Perfil {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "{perfil.nome.notblank}")
    private String nome;

    @NotBlank(message = "{perfil.email.notblank}")
    private String email;

    @NotBlank(message = "{perfil.senha.notblank}")
    @Size(min = 6, max = 255, message = "{perfil.senha.size}")
    private String senha;


    private String imagem;
}

