package com.carvalho.helpdesk.domain;

import com.carvalho.helpdesk.domain.dtos.ClienteDTO;
import com.carvalho.helpdesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends Pessoa {

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente(@Valid ClienteDTO clienteDTO) {
        super(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getCpf(), clienteDTO.getEmail(), clienteDTO.getSenha());
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.CLIENTE);
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
