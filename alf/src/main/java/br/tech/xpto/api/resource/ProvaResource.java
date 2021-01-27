package br.tech.xpto.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.tech.xpto.api.model.Prova;
import br.tech.xpto.api.service.ProvaService;

@RestController
@RequestMapping("/prova")
public class ProvaResource {

	@Autowired
	private ProvaService service;
	
	@GetMapping
	public List<Prova> listar() {
		return this.service.listar();
	}
	
	@PostMapping("/cadastrar_gabarito")
	public ResponseEntity<Object> salvar(@RequestBody Prova prova) {
		Object resposta = this.service.salvar(prova);
		return resposta instanceof Prova ? ResponseEntity.status(HttpStatus.CREATED).body(resposta) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
	}
	
}