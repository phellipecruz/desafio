package br.tech.xpto.api.model;

import java.io.Serializable;
import java.util.Set;

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
@Table(name = "PROVA")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Prova implements Serializable {

	private static final long serialVersionUID = 4350671682817189926L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;

	@OneToMany(mappedBy = "prova", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	private Set<Questao> questoes;

	public Prova(Integer id) {
		this.id = id;
	}
	
	public Prova(Integer id, Set<Questao> questoes) {
		this.id = id;
		this.questoes = questoes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Prova [id=");
		builder.append(id);
		builder.append(", questoes=");
		builder.append(questoes);
		builder.append("]");
		return builder.toString();
	}
	
}