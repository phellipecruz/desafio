package br.tech.xpto.api.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PROVA_ALUNO")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"prova", "aluno", "questao"})
public class ProvaAluno implements Serializable {

	private static final long serialVersionUID = 8564109433884407247L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ALUNO_ID")
	@JsonBackReference
	private Aluno aluno;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PROVA_ID")
	private Prova prova;
	
	@OneToOne
	@JoinColumn(name = "QUESTAO_ID", referencedColumnName = "ID")
	private Questao questao;
	
	@Column(name = "RESPOSTA")
	private String resposta;

	public ProvaAluno(Integer id, Aluno aluno, Prova prova, Questao questao, String resposta) {
		this.id = id;
		this.aluno = aluno;
		this.prova = prova;
		this.questao = questao;
		this.resposta = resposta;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProvaAluno [id=");
		builder.append(id);
		builder.append(", aluno=");
		builder.append(aluno);
		builder.append(", prova=");
		builder.append(prova);
		builder.append(", questao=");
		builder.append(questao);
		builder.append(", resposta=");
		builder.append(resposta);
		builder.append("]");
		return builder.toString();
	}

}