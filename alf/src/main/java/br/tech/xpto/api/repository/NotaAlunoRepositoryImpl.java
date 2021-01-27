package br.tech.xpto.api.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.tech.xpto.api.dto.NotaAlunoDTO;
import br.tech.xpto.api.dto.NotaFinalAlunoDTO;

public class NotaAlunoRepositoryImpl implements NotaAlunoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	public List<NotaAlunoDTO> listarNotasAluno(Integer aluno) {
		StringBuilder builder = new StringBuilder();

		builder.append("SELECT a.NOME, p.PROVA_ID, na.NOTA ");
		builder.append("FROM NOTA_ALUNO na ");
		builder.append("INNER JOIN ALUNO a ON na.ALUNO_ID = a.ID ");
		builder.append("INNER JOIN PROVA_ALUNO p ON na.ALUNO_ID = p.ALUNO_ID ");
		builder.append("WHERE 1 = 1 ");
		
		if (aluno != null)
			builder.append("AND na.ALUNO_ID = :aluno ");
		
		builder.append("GROUP BY a.NOME, p.PROVA_ID, na.NOTA ");
		builder.append("ORDER BY a.NOME, p.PROVA_ID, na.NOTA");
		
		Query query = this.manager.createNativeQuery(builder.toString());
		
		if (aluno != null)		
			query.setParameter("aluno", aluno);
		
		List<Object []> resultado = query.getResultList();
		List<NotaAlunoDTO> notas = new ArrayList<>();
		if (!resultado.isEmpty()) {
			resultado.stream().forEach(nota -> {
				notas.add(new NotaAlunoDTO((String) nota[0], (Integer) nota[1], (double) nota[2]));
			});
		}
		return notas;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NotaFinalAlunoDTO> listarAlunosAprovados() {
		StringBuilder builder = new StringBuilder();

		builder.append("SELECT a.NOME, AVG(na.NOTA) MEDIA ");
		builder.append("FROM NOTA_ALUNO na ");
		builder.append("INNER JOIN ALUNO a ON na.ALUNO_ID = a.ID ");
		builder.append("GROUP BY a.NOME ");
		builder.append("HAVING AVG(na.NOTA) > 7 ");
		builder.append("ORDER BY a.NOME ");
				
		Query query = this.manager.createNativeQuery(builder.toString());
		
		List<Object []> resultado = query.getResultList();
		List<NotaFinalAlunoDTO> aprovados = new ArrayList<>();
		if (!resultado.isEmpty()) {
			resultado.stream().forEach(nota -> {
				aprovados.add(new NotaFinalAlunoDTO((String) nota[0], (double) nota[1]));
			});
		}
		return aprovados;
	}
	
}