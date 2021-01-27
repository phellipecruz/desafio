package br.tech.xpto.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotaFinalAlunoDTO {

	private String nome;
	private Double notaFinal;
	
	public NotaFinalAlunoDTO(String nome, Double notaFinal) {
		this.nome = nome;
		this.notaFinal = notaFinal;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NotaFinalAlunoDTO [nome=");
		builder.append(nome);
		builder.append(", notaFinal=");
		builder.append(notaFinal);
		builder.append("]");
		return builder.toString();
	}
	
}