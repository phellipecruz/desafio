package br.tech.xpto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.tech.xpto.api.model.NotaAluno;

@Repository
public interface NotaAlunoRepository extends JpaRepository<NotaAluno, Integer>, NotaAlunoRepositoryQuery {

}
