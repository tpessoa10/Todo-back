package com.example.ToDo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.ToDo.tarefas.DadosAtualizacaoTarefa;
import com.example.ToDo.tarefas.DadosCadastroTarefa;
import com.example.ToDo.tarefas.DadosDetalhamentoTarefa;
import com.example.ToDo.tarefas.Tarefa;
import com.example.ToDo.tarefas.TarefasRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("tarefas")
public class TarefasController {
	
	@Autowired
	private TarefasRepository repository;

	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTarefa dados, UriComponentsBuilder uriBuilder) {
		var tarefa = new Tarefa(dados);
		repository.save(tarefa);
		var uri = uriBuilder.path("/tarefas/{id}").buildAndExpand(tarefa.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoTarefa(tarefa));
	}
	
	@GetMapping
	public ResponseEntity retornar() {
		var registros = repository.findAll();
		return ResponseEntity.ok(registros);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@Valid @RequestBody DadosAtualizacaoTarefa dados) {
		var tarefa = repository.getReferenceById(dados.id());
		tarefa.atualizarInformacoes(dados);
		return ResponseEntity.ok(new DadosDetalhamentoTarefa(tarefa));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity deletar(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
