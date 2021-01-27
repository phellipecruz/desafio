package br.tech.xpto.api.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ALUNO")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Aluno implements Serializable {

	private static final long serialVersionUID = 721961468498992649L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "NOME")
	private String nome;
	
	@OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<ProvaAluno> provas;
	
	@OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<NotaAluno> notas;

	public Aluno(Integer id, String nome, List<ProvaAluno> provas, List<NotaAluno> notas) {
		this.id = id;
		this.nome = nome;
		this.provas = provas;
		this.notas = notas;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Aluno [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", provas=");
		builder.append(provas);
		builder.append(", notas=");
		builder.append(notas);
		builder.append("]");
		return builder.toString();
	}

}