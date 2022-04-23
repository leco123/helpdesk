package com.carvalho.helpdesk.domain.dtos;

import com.carvalho.helpdesk.domain.Tecnico;
import com.carvalho.helpdesk.domain.enums.Perfil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class TecnicoDTO {
    protected Integer id;
    @NotNull(message = "O campo Nome é requerido")
    protected String nome;
    @NotNull(message = "O campo CPF é requerido")
    protected String cpf;
    @NotNull(message = "O campo Senha é requerido")
    protected String email;
    protected String senha;
    protected Set<Integer> perfis = new HashSet<>();
    protected LocalDateTime dataCriacao;

    public TecnicoDTO() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public TecnicoDTO(Tecnico tecnico) {
        super();
        this.id = tecnico.getId();
        this.nome = tecnico.getNome();
        this.cpf = tecnico.getCpf();
        this.email = tecnico.getEmail();
        this.senha = tecnico.getSenha();
        this.perfis = tecnico.getPerfis().stream().map(Perfil::getCodigo).collect(Collectors.toSet());
        this.dataCriacao = LocalDateTime.now();
        addPerfil(Perfil.CLIENTE);
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }
}
