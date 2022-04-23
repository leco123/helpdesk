package com.carvalho.helpdesk.resources;

import com.carvalho.helpdesk.domain.Cliente;
import com.carvalho.helpdesk.domain.dtos.ClienteDTO;
import com.carvalho.helpdesk.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.ok().body(new ClienteDTO(cliente));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<Cliente> clientes = clienteService.findAll();
        List<ClienteDTO> clienteDTOS = clientes.stream().map(ClienteDTO::new).collect(Collectors.toList());
        return  ResponseEntity
                .ok().body(clienteDTOS);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO clienteDTO){
        Cliente cliente = clienteService.create(clienteDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = clienteService.update(id, clienteDTO);
        return  ResponseEntity.ok().body(new ClienteDTO(cliente));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
