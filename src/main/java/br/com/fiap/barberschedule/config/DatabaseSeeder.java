package br.com.fiap.barberschedule.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.barberschedule.model.Perfil;
import br.com.fiap.barberschedule.model.Servico;
import br.com.fiap.barberschedule.repository.PerfilRepository;
import br.com.fiap.barberschedule.repository.ServicoRepository;


@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    ServicoRepository servicoRepository;

    @Autowired
    PerfilRepository perfilRepository;

    @Override
    public void run(String... args) throws Exception {
        perfilRepository.saveAll(List.of(
            Perfil.builder().id(1L).nome("Felipe Sieiro").email("felipe@fiap").build(),
            Perfil.builder().id(2L).nome("João Carlos").email("Joao@fiap").build(),
            Perfil.builder().id(3L).nome("Ellon Musk").email("Ellon@fiap").build(),
            Perfil.builder().id(4L).nome("Boninho").email("boninho@fiap").build()
        ));

        servicoRepository.saveAll(
            List.of(
                Servico.builder()
                    .id(1L)
                    .nome("Corte de Cabelo")
                    .icone("scissors")
                    .perfil(perfilRepository.findById(1L).get())
                    .build(),
                Servico.builder()
                    .id(2L)
                    .nome("Corte de Cabelo")
                    .icone("scissors")
                    .perfil(perfilRepository.findById(1L).get())
                    .build(),
                Servico.builder()
                    .id(3L)
                    .nome("Pintura de Cabelo")
                    .icone("sprycan")
                    .perfil(perfilRepository.findById(1L).get())
                    .build(),
                Servico.builder()
                    .id(4L)
                    .nome("Pintura de Cabelo")
                    .icone("sprycan")
                    .perfil(perfilRepository.findById(1L).get())
                    .build(),
                Servico.builder()
                    .id(5L)
                    .nome("Penteado")
                    .icone("ruler")
                    .perfil(perfilRepository.findById(1L).get())
                    .build(),
                Servico.builder()
                    .id(6L)
                    .nome("Penteado")
                    .icone("ruler")
                    .perfil(perfilRepository.findById(1L).get())
                    .build(),
                Servico.builder()
                    .id(7L)
                    .nome("Penteado")
                    .icone("ruler")
                    .perfil(perfilRepository.findById(1L).get())
                    .build(),
                Servico.builder()
                    .id(8L)
                    .nome("Pintura de Cabelo")
                    .icone("sprycan")
                    .perfil(perfilRepository.findById(1L).get())
                    .build()        
            )
            
        );
    }
    
}