package com.example.ToDo.tarefas;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Tarefa")
@Table(name = "tarefas")
public class Tarefa {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String descricao;
	private LocalDateTime data;
	
	public Tarefa(DadosCadastroTarefa dados) {
		this.titulo = dados.titulo();
		this.descricao = dados.descricao();
		this.data = LocalDateTime.now();
	}
	
	public void atualizarInformacoes(DadosAtualizacaoTarefa dados) {
		if(dados.titulo() != null) {
			this.titulo = dados.titulo();
		}
		if(dados.descricao() != null) {
			this.descricao = dados.descricao();
		}
	}
}
