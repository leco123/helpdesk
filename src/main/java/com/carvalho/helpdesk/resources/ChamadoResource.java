package com.carvalho.helpdesk.resources;

import com.carvalho.helpdesk.domain.Chamado;
import com.carvalho.helpdesk.domain.dtos.ChamadoDTO;
import com.carvalho.helpdesk.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {

    @Autowired
    private ChamadoService chamadoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
        Chamado chamado = chamadoService.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll() {
        List<Chamado> chamados = chamadoService.findAll();
        List<ChamadoDTO> chamadoDTOS = chamados.stream()
                .map(chamado -> new ChamadoDTO(chamado))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(chamadoDTOS);
    }
}
