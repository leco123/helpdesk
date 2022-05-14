package com.carvalho.helpdesk.services;

import com.carvalho.helpdesk.domain.Cliente;
import com.carvalho.helpdesk.domain.Pessoa;
import com.carvalho.helpdesk.domain.dtos.ClienteDTO;
import com.carvalho.helpdesk.domain.repository.ClienteRepository;
import com.carvalho.helpdesk.domain.repository.PessoaRepository;
import com.carvalho.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.carvalho.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public Cliente findById(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado Id: "+id));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente create(ClienteDTO clienteDTO) {
        clienteDTO.setId(null);
        clienteDTO.setSenha(encoder.encode(clienteDTO.getSenha()));
        validaPorCPFEEmail(clienteDTO);
        Cliente newCliente = new Cliente(clienteDTO);
        return clienteRepository.save(newCliente);
    }

    public Cliente update(Integer id, @Valid  ClienteDTO clienteDTO) {
        clienteDTO.setId(id);
        Cliente oldCliente = findById(id);
        if (!clienteDTO.getSenha().equals(oldCliente.getSenha())) {
            clienteDTO.setSenha(encoder.encode(oldCliente.getSenha()));
        }
        validaPorCPFEEmail(clienteDTO);
        oldCliente = new Cliente(clienteDTO);
        return clienteRepository.save(oldCliente);
    }

    public void delete(Integer id) {
        Cliente cliente = findById(id);
        if(cliente.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Cliente possui ordens de serviços e não pode ser deletado!");
        }

        clienteRepository.deleteById(id);
    }

    private void validaPorCPFEEmail(ClienteDTO clienteDTO) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(clienteDTO.getCpf());

        if(pessoa.isPresent() && !pessoa.get().getId().equals(clienteDTO.getId())){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        pessoa  = pessoaRepository.findByEmail(clienteDTO.getEmail());
        if(pessoa.isPresent() && !pessoa.get().getId().equals(clienteDTO.getId())){
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }
}
