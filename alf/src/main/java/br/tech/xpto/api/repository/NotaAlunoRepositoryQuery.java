package br.tech.xpto.api.repository;

import java.util.List;

import br.tech.xpto.api.dto.NotaAlunoDTO;
import br.tech.xpto.api.dto.NotaFinalAlunoDTO;

public interface NotaAlunoRepositoryQuery {
	
	public List<NotaFinalAlunoDTO> listarAlunosAprovados();
	public List<NotaAlunoDTO> listarNotasAluno(Integer aluno);
	
}