package com.example.ToDo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.ToDo.usuario.AutenticacaoService;
import com.example.ToDo.usuario.DadosAutenticacao;
import com.example.ToDo.usuario.Usuario;
import com.example.ToDo.usuario.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private AutenticacaoService autenticacaoService;

	@PostMapping
	public ResponseEntity cadastrarUsuario(@RequestBody @Valid DadosAutenticacao dados, UriComponentsBuilder uriBuilder ) {
		var usuario = new Usuario(dados);
		autenticacaoService.criarUsuario(usuario);
		var uri = uriBuilder.path("/tarefas/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.ok("Usuario registrado com sucesso");
	}
}
