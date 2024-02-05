package com.example.ToDo.tarefas;

import java.time.LocalDateTime;

public record DadosDetalhamentoTarefa(Long id, String titulo, String descricao, LocalDateTime data) {
	public DadosDetalhamentoTarefa(Tarefa tarefa) {
		this(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getData());
	}

}
