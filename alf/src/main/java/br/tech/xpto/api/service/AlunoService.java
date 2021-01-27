package br.tech.xpto.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.tech.xpto.api.dto.NotaAlunoDTO;
import br.tech.xpto.api.dto.NotaFinalAlunoDTO;
import br.tech.xpto.api.model.Aluno;
import br.tech.xpto.api.model.NotaAluno;
import br.tech.xpto.api.model.Prova;
import br.tech.xpto.api.model.ProvaAluno;
import br.tech.xpto.api.model.Questao;
import br.tech.xpto.api.repository.AlunoRepository;
import br.tech.xpto.api.repository.NotaAlunoRepository;
import br.tech.xpto.api.repository.ProvaRepository;
import br.tech.xpto.api.repository.QuestaoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository repository;

	@Autowired
	private ProvaRepository provaRepository;

	@Autowired
	private QuestaoRepository questaoRepository;
	
	@Autowired
	private NotaAlunoRepository notaAlunoRepository;

	public List<Aluno> listar() {
		return this.repository.findAll();
	}

	public Aluno buscar(Integer id) {
		return this.repository.findOne(id);
	}

	public List<NotaAlunoDTO> listarNotasAluno(Integer aluno) {
		return this.notaAlunoRepository.listarNotasAluno(aluno);
	}
	
	public NotaFinalAlunoDTO calcularNotaFinalAluno(Integer aluno) {
		List<NotaAlunoDTO> notas = this.notaAlunoRepository.listarNotasAluno(aluno);
		NotaFinalAlunoDTO notaFinal = null;
		if (!notas.isEmpty()) {
			double nota = notas.stream().mapToDouble(NotaAlunoDTO::getNota).average().getAsDouble();
			notaFinal = new NotaFinalAlunoDTO(notas.get(0).getNome(), nota);
		}
		return notaFinal;
	}
	
	
	public List<NotaFinalAlunoDTO> listarAlunosAprovados() {
		return this.notaAlunoRepository.listarAlunosAprovados();
	}
	
	public Object salvar(Aluno aluno) {
		Aluno alunoSalvo = null;
		Prova provaSalva = null;
		String validar = validar(aluno);
		if (validar == null) {			
			if (aluno.getId() != null) {
				alunoSalvo = this.buscar(aluno.getId());
				if (alunoSalvo != null && aluno.getProvas() != null && !aluno.getProvas().isEmpty()) {
					for (ProvaAluno prova : aluno.getProvas()) {
						provaSalva = this.provaRepository.findOne(prova.getProva().getId());
						prova.setProva(provaSalva);
						prova.setAluno(alunoSalvo);
					}
					BeanUtils.copyProperties(aluno, alunoSalvo, "id", "nome");					
					List<NotaAluno> notas = new ArrayList<>();

					double nota = this.calcularNota(aluno.getProvas());

					if (aluno.getProvas() != null && !aluno.getProvas().isEmpty()) {
						if (nota < 0 || nota > 9.99)
							return "O nota do aluno deve ser maior que 0 e menor que 10";
					}

					notas.add(new NotaAluno(alunoSalvo, nota));					
					alunoSalvo.setNotas(notas);  
					alunoSalvo = this.repository.save(alunoSalvo);					
					return alunoSalvo;
				}
			} else {
				alunoSalvo = this.repository.save(aluno);
				return alunoSalvo;
			}				
			return alunoSalvo;
		} else {
			return validar;
		}
	}

	private String validar(Aluno aluno) {
		int size = this.repository.findAll().size();
		size++;
		if (size > 100)
			return "A quantidade máxima de alunos cadastrados (" +(size -1) +") já foi atingida.";
		if (aluno.getId() == null && (aluno.getNome() == null || aluno.getNome().equals("")))
			return "O nome do aluno é obrigatório";
		return null;
	}

	private double calcularNota(List<ProvaAluno> provas) {		
		double nota = 0.0;
		int somaPesos = 0;
		int somaPontos = 0;

		if (provas != null && !provas.isEmpty()) {
			for (ProvaAluno prova : provas) {
				for (Questao questao : prova.getProva().getQuestoes()) {
					somaPesos += questao.getPeso();
				}
				break;
			}

			for (ProvaAluno prova : provas) {
				prova.setQuestao(this.questaoRepository.findOne(prova.getQuestao().getId()));
				if (prova.getQuestao().getGabarito().getResposta().equals(prova.getResposta())) {
					somaPontos += (1 * prova.getQuestao().getPeso());
				}
			}

			nota = (double) somaPontos / somaPesos;
		}

		return nota;
	}

}