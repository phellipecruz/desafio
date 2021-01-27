package br.tech.xpto.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.tech.xpto.api.dto.NotaAlunoDTO;
import br.tech.xpto.api.dto.NotaFinalAlunoDTO;
import br.tech.xpto.api.model.Aluno;
import br.tech.xpto.api.service.AlunoService;

@RestController
@RequestMapping("/aluno")
public class AlunoResource {

	@Autowired
	private AlunoService service;
	
	@GetMapping("/{aluno}")
	public List<NotaAlunoDTO> listar(@PathVariable Integer aluno) {		
		return this.service.listarNotasAluno(aluno);
	}
	
	@GetMapping("/lista_aprovados")
	public List<NotaFinalAlunoDTO> listarAprovados() {		
		return this.service.listarAlunosAprovados();
	}
	
	@GetMapping("/nota_final/{aluno}")
	public NotaFinalAlunoDTO calcularNotaFinalAluno(@PathVariable Integer aluno) {		
		return this.service.calcularNotaFinalAluno(aluno);
	}
	
	@PostMapping("/cadastrar_aluno")
	public ResponseEntity<Object> salvar(@RequestBody Aluno aluno) {
		Object resposta = this.service.salvar(aluno);
		return resposta instanceof Aluno ? ResponseEntity.status(HttpStatus.CREATED).body(resposta) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
	}
	
	@PutMapping("/cadastrar_resposta_aluno")
	public ResponseEntity<Object> salvarProva(@RequestBody Aluno aluno) {
		Object resposta = this.service.salvar(aluno);
		return resposta instanceof Aluno ? ResponseEntity.status(HttpStatus.OK).body(resposta) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
	}
	
}