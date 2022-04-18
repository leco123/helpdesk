package com.carvalho.helpdesk.domain.dtos;

import com.carvalho.helpdesk.domain.Chamado;
import com.carvalho.helpdesk.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChamadoDTO implements Serializable {

    private Integer id;
    private LocalDateTime dataAbertura = LocalDateTime.now();
    private LocalDateTime dataFechamento;
    private Integer prioridade;
    private Status status;
    private String titulo;
    private String observacoes;
    private Integer tecnico;
    private String nomeTecnico;
    private Integer cliente;
    private String nomeCliente;

    public ChamadoDTO(Chamado chamado) {
        this.id = chamado.getId();
        this.dataAbertura = chamado.getDataAbertura();
        this.dataFechamento = chamado.getDataFechamento();
        this.prioridade = chamado.getPrioridade().getCodigo();
        this.status = chamado.getStatus();
        this.titulo = chamado.getTitulo();
        this.observacoes = chamado.getObservacoes();
        this.tecnico = chamado.getTecnico().getId();
        this.nomeTecnico = chamado.getTecnico().getNome();
        this.cliente = chamado.getCliente().getId();
        this.nomeTecnico = chamado.getCliente().getNome();
    }
}
