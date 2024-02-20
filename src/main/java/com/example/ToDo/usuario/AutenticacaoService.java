package com.example.ToDo.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class AutenticacaoService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByLogin(username);
	}
	
	@Transactional
	public Usuario criarUsuario(Usuario usuario) {
		String hashPassword = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(hashPassword);
		return repository.save(usuario);
	}

}
