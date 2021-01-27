package br.tech.xpto.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "GABARITO")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Gabarito implements Serializable {

	private static final long serialVersionUID = 859534430179409490L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "RESPOSTA")
	@NotNull
	private String resposta;

	public Gabarito(Integer id, String resposta) {
		this.id = id;
		this.resposta = resposta;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Gabarito [id=");
		builder.append(id);
		builder.append(", resposta=");
		builder.append(resposta);
		builder.append("]");
		return builder.toString();
	}
	
}
