package com.carvalho.helpdesk.domain;

import com.carvalho.helpdesk.domain.enums.Perfil;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public abstract class Pessoa {

    protected Integer id;
    protected String nome;
    protected String cpf;
    protected String email;
    protected String senha;
    protected Set<Integer> perfis = new HashSet<>();
    protected LocalDateTime dataCriacao = LocalDateTime.now();

    public Pessoa(){
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        addPerfil(Perfil.CLIENTE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return id.equals(pessoa.id) && cpf.equals(pessoa.cpf);
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(perfilcodigo -> Perfil.toEnum(perfilcodigo)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil){
        this.perfis.add(perfil.getCodigo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf);
    }
}
