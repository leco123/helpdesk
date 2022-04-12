package com.carvalho.helpdesk.services;

import com.carvalho.helpdesk.domain.Tecnico;
import com.carvalho.helpdesk.domain.dtos.TecnicoDTO;
import com.carvalho.helpdesk.domain.repository.TecnicoRepository;
import com.carvalho.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
        return tecnico.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado Id: "+id));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setId(null);
        Tecnico newTecnico = new Tecnico(tecnicoDTO);
        return tecnicoRepository.save(newTecnico);
    }
}
