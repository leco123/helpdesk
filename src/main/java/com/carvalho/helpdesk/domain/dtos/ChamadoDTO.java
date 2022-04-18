package com.carvalho.helpdesk.domain.dtos;

import com.carvalho.helpdesk.domain.Chamado;
import com.carvalho.helpdesk.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
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
    @NotNull(message = "O campo Prioridade é requerido")
    private Integer prioridade;
    @NotNull(message = "O campo Status é requerido")
    private Integer status;
    @NotNull(message = "O Campo Título é requerido")
    private String titulo;
    @NotNull(message = "O Campo Observações é requerido")
    private String observacoes;
    @NotNull(message = "O Campo Técnico é requerido")
    private Integer tecnico;
    @NotNull(message = "O Campo Cliente é requerido")
    private Integer cliente;
    private String nomeTecnico;
    private String nomeCliente;

    public ChamadoDTO(Chamado chamado) {
        this.id = chamado.getId();
        this.dataAbertura = chamado.getDataAbertura();
        this.dataFechamento = chamado.getDataFechamento();
        this.prioridade = chamado.getPrioridade().getCodigo();
        this.status = chamado.getStatus().getCodigo();
        this.titulo = chamado.getTitulo();
        this.observacoes = chamado.getObservacoes();
        this.tecnico = chamado.getTecnico().getId();
        this.nomeTecnico = chamado.getTecnico().getNome();
        this.cliente = chamado.getCliente().getId();
        this.nomeTecnico = chamado.getCliente().getNome();
    }
}
