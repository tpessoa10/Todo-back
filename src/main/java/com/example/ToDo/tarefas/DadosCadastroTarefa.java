package com.example.ToDo.tarefas;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroTarefa(
		@NotBlank
		String titulo,
		@NotBlank
		String descricao) {
	
}
