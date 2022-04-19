package com.carvalho.helpdesk.services;

import com.carvalho.helpdesk.domain.Chamado;
import com.carvalho.helpdesk.domain.Cliente;
import com.carvalho.helpdesk.domain.Tecnico;
import com.carvalho.helpdesk.domain.dtos.ChamadoDTO;
import com.carvalho.helpdesk.domain.enums.Prioridade;
import com.carvalho.helpdesk.domain.enums.Status;
import com.carvalho.helpdesk.domain.repository.ChamadoRepository;
import com.carvalho.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private TecnicoService tecnicoService;

    public Chamado findById(Integer id){
        Optional<Chamado> chamado = chamadoRepository.findById(id);
        return chamado.orElseThrow(()-> new ObjectNotFoundException("Chamado não encontrado "+id));
    }

    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }

    public Chamado create(@Valid ChamadoDTO chamadoDTO) {
        return  chamadoRepository.save(newChamado(chamadoDTO));
    }

    public Chamado update(Integer id, ChamadoDTO chamadoDTO) {
        chamadoDTO.setId(id);
        Chamado oldChamado = findById(id);
        oldChamado = newChamado(chamadoDTO);
        return chamadoRepository.save(oldChamado);
    }

    private Chamado newChamado(ChamadoDTO chamadoDTO){
        Tecnico tecnico = tecnicoService.findById(chamadoDTO.getTecnico());
        Cliente cliente = clienteService.findById(chamadoDTO.getCliente());

        Chamado chamado = new Chamado();
        if(chamadoDTO.getId() != null) {
            chamado.setId(chamadoDTO.getId());
        }

        if(chamadoDTO.getStatus().equals(2)){
            chamado.setDataFechamento(LocalDateTime.now());
        }

        chamado.setCliente(cliente);
        chamado.setTecnico(tecnico);
        chamado.setPrioridade(Prioridade.toEnum(chamadoDTO.getPrioridade()));
        chamado.setStatus(Status.toEnum(chamadoDTO.getStatus()));
        chamado.setTitulo(chamadoDTO.getTitulo());
        chamado.setObservacoes(chamadoDTO.getObservacoes());
        return chamado;
    }

}
