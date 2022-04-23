package com.carvalho.helpdesk.domain.dtos;

import com.carvalho.helpdesk.domain.Cliente;
import com.carvalho.helpdesk.domain.enums.Perfil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class ClienteDTO {
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

    public ClienteDTO() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public ClienteDTO(Cliente cliente) {
        super();
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.email = cliente.getEmail();
        this.senha = cliente.getSenha();
        this.perfis = cliente.getPerfis().stream().map(Perfil::getCodigo).collect(Collectors.toSet());
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
