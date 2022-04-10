package com.carvalho.helpdesk;

import com.carvalho.helpdesk.domain.Chamado;
import com.carvalho.helpdesk.domain.Cliente;
import com.carvalho.helpdesk.domain.Tecnico;
import com.carvalho.helpdesk.domain.enums.Perfil;
import com.carvalho.helpdesk.domain.enums.Prioridade;
import com.carvalho.helpdesk.domain.enums.Status;
import com.carvalho.helpdesk.domain.repository.ChamadoRepository;
import com.carvalho.helpdesk.domain.repository.ClienteRepository;
import com.carvalho.helpdesk.domain.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Tecnico tec1 = new Tecnico(null, "Silvana Bianca de Paula", "68617252502", "silvana-depaula71@marcossousa.com","TwMoWFPNrb");
		tec1.addPerfil(Perfil.ADMIN);
		Tecnico tec2 = new Tecnico(null, "Henrique Rodrigo Thiago da Rocha", "25590509602", "henrique_darocha@51hotmail.com","kZgw2oCcM6");
		tec1.addPerfil(Perfil.ADMIN);
		Tecnico tec3 = new Tecnico(null, "Olivia Andreia Isabela Drumond", "42312589613", "olivia.andreia.drumond@ieee.org","FEyFqMh6is");
		Tecnico tec4 = new Tecnico(null, "Sophia Amanda Barbosa", "29625540407", "sophia-barbosa75@fluxioneventos.com.br","YzMCyjSuPg");
		Tecnico tec5 = new Tecnico(null, "Eduarda Ana de Paula", "74651447953", "eduarda.ana.depaula@azevedoalves.com.br","3HUJ8S4d5e");
		Tecnico tec6 = new Tecnico(null, "João Manoel Gonçalves", "18854532134", "joaomanoelgoncalves@technocut.com.br","5dlPiRaZG9");

		Cliente cli1 = new Cliente(null, "Luzia Aurora Cecília Duarte", "89777316747", "luzia_duarte@henrimar.com.br", "csdeyySxgE");
		Cliente cli2 = new Cliente(null, "Camila Eliane Marcela Galvão", "75577761948", "camila_eliane_galvao@redex.com.br", "Ga0alYJM44");
		Cliente cli3 = new Cliente(null, "Mariana Lara Brito", "10989032000", "mariana_lara_brito@coldblock.com.br", "HyRwO46Szz");
		Cliente cli4 = new Cliente(null, "André Leonardo Anderson Alves", "85617767496", "landre_leonardo_alves@oliveiracontabil.com.br", "iON8Wuy2X6");
		Cliente cli5 = new Cliente(null, "Marcos Márcio Nogueira", "97471265329", "marcosmarcionogueira@dkcarmo.com", "cw0tzqskKb");

		Chamado chamado1 = new Chamado(null, Prioridade.BAIXA, Status.ABERTO, "Teste 1", "observação 01", tec1, cli1);
		Chamado chamado2 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Teste 2", "observação 02", tec2, cli2);
		Chamado chamado3 = new Chamado(null, Prioridade.ALTA, Status.ANDAMENTO, "Teste 3", "observação 03", tec3, cli3);
		Chamado chamado4 = new Chamado(null, Prioridade.BAIXA, Status.ABERTO, "Teste 4", "observação 04", tec4, cli4);
		Chamado chamado5 = new Chamado(null, Prioridade.MEDIA, Status.ENCERRADO, "Teste 5", "observação 05", tec5, cli5);
		Chamado chamado6 = new Chamado(null, Prioridade.ALTA, Status.ANDAMENTO, "Teste 6", "observação 06", tec6, cli3);

		tecnicoRepository.saveAll(Arrays.asList(tec1,tec2, tec3, tec4, tec5, tec6));
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5));
		chamadoRepository.saveAll(Arrays.asList(chamado1, chamado2, chamado3, chamado4, chamado5, chamado6));

	}
}
