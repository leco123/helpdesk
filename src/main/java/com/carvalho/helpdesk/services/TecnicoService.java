package com.carvalho.helpdesk.services;

import com.carvalho.helpdesk.domain.Pessoa;
import com.carvalho.helpdesk.domain.Tecnico;
import com.carvalho.helpdesk.domain.dtos.TecnicoDTO;
import com.carvalho.helpdesk.domain.repository.PessoaRepository;
import com.carvalho.helpdesk.domain.repository.TecnicoRepository;
import com.carvalho.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.carvalho.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
        return tecnico.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado Id: "+id));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setId(null);
        validaPorCPFEEmail(tecnicoDTO);
        Tecnico newTecnico = new Tecnico(tecnicoDTO);
        return tecnicoRepository.save(newTecnico);
    }

    private void validaPorCPFEEmail(TecnicoDTO tecnicoDTO) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(tecnicoDTO.getCpf());

        if(pessoa.isPresent() && !pessoa.get().getId().equals(tecnicoDTO.getId())){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        pessoa  = pessoaRepository.findByEmail(tecnicoDTO.getEmail());
        if(pessoa.isPresent() && !pessoa.get().getId().equals(tecnicoDTO.getId())){
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }
}
