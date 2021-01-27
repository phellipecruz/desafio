package br.tech.xpto.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "NOTA_ALUNO")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"aluno"})
public class NotaAluno {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ALUNO_ID")
	@JsonBackReference
	private Aluno aluno;
	
	@Column(name = "NOTA")
	private Double nota;

	public NotaAluno(Aluno aluno, Double nota) {
		this.aluno = aluno;
		this.nota = nota;
	}
	
	public NotaAluno(Integer id, Aluno aluno, Double nota) {
		this.id = id;
		this.aluno = aluno;
		this.nota = nota;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NotaAluno [id=");
		builder.append(id);
		builder.append(", aluno=");
		builder.append(aluno);
		builder.append(", nota=");
		builder.append(nota);
		builder.append("]");
		return builder.toString();
	}
	
}