package com.carvalho.helpdesk.services;

import com.carvalho.helpdesk.domain.Tecnico;
import com.carvalho.helpdesk.domain.repository.TecnicoRepository;
import com.carvalho.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
        return tecnico.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado Id: "+id));
    }
}
