package com.example.ToDo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ToDo.securityConfigurations.DadosTokenJWT;
import com.example.ToDo.securityConfigurations.TokenService;
import com.example.ToDo.usuario.DadosAutenticacao;
import com.example.ToDo.usuario.Usuario;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService service;

	@PostMapping
	public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
		var AuthenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
		var authentication = manager.authenticate(AuthenticationToken);
	
		var tokenJWT = service.gerarToken((Usuario) authentication.getPrincipal());
		
		return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
	}
}
