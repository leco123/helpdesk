package com.carvalho.helpdesk.services;

import com.carvalho.helpdesk.domain.Chamado;
import com.carvalho.helpdesk.domain.repository.ChamadoRepository;
import com.carvalho.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    public Chamado findById(Integer id){
        Optional<Chamado> chamado = chamadoRepository.findById(id);
        return chamado.orElseThrow(()-> new ObjectNotFoundException("Chamado não encontrado "+id));
    }

    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }
}
