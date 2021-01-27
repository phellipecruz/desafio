package br.tech.xpto.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotaAlunoDTO {

	private String nome;
	private Integer prova;
	private Double nota;
	
	public NotaAlunoDTO(String nome, Integer prova, Double nota) {
		this.nome = nome;
		this.prova = prova;
		this.nota = nota;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NotaAlunoDTO [nome=");
		builder.append(nome);
		builder.append(", prova=");
		builder.append(prova);
		builder.append(", nota=");
		builder.append(nota);
		builder.append("]");
		return builder.toString();
	}
	
}