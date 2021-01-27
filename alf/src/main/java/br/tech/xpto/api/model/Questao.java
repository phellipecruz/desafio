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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "QUESTAO")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "prova")
public class Questao implements Serializable {

	private static final long serialVersionUID = -1832087072007887792L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "ENUNCIADO")
	@NotNull
	private String enunciado;
	
	@Column(name = "PESO")
	private Integer peso;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PROVA_ID", referencedColumnName = "ID")
	@JsonBackReference
	private Prova prova;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "GABARITO_ID", referencedColumnName = "ID")
	private Gabarito gabarito;

	public Questao(Integer id) {
		this.id = id;
	}
	
	public Questao(Integer id, String enunciado, Integer peso, Prova prova, Gabarito gabarito) {
		this.id = id;
		this.enunciado = enunciado;
		this.peso = peso;
		this.prova = prova;
		this.gabarito = gabarito;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Questao [id=");
		builder.append(id);
		builder.append(", enunciado=");
		builder.append(enunciado);
		builder.append(", peso=");
		builder.append(peso);
		builder.append(", prova=");
		builder.append(prova);
		builder.append(", gabarito=");
		builder.append(gabarito);
		builder.append("]");
		return builder.toString();
	}
	
}