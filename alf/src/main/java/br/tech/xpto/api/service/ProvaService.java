package br.tech.xpto.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.tech.xpto.api.model.Prova;
import br.tech.xpto.api.model.Questao;
import br.tech.xpto.api.repository.ProvaRepository;

@Service
public class ProvaService {

	@Autowired
	private ProvaRepository repository;
	
	public Object salvar(Prova prova) {
		String validar = validar(prova);
		if (validar == null) {
			Prova provaSalva = this.repository.save(prova);
			return provaSalva;
		} else {
			return validar;
		}
	}
	
	public List<Prova> listar() {
		return this.repository.findAll();
	}
	
	private String validar(Prova prova) {		
		if (prova.getQuestoes() == null || prova.getQuestoes().isEmpty())
			return "Não é possível cadastrar uma prova sem questões";
		if (prova.getQuestoes() != null && !prova.getQuestoes().isEmpty()) {
			for(Questao questao : prova.getQuestoes()) {
				questao.setProva(prova);				
				if (questao.getGabarito() == null || questao.getGabarito().getResposta() == null || questao.getGabarito().getResposta().equals(""))
					return "Não é possível cadastrar uma questão para o gabarito da prova sem uma resposta";
				if (questao.getPeso() < 1)
					return "A peso da questão não pode ser inferior a 1";
			}
		}
		return null;
	}
	
}