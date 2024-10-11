package com.electron.controllers;

import com.electron.domain.Usuario;
import com.electron.domain.dtos.LoginDTO;
import com.electron.domain.dtos.LoginReponseDTO;
import com.electron.domain.dtos.RegistroDTO;
import com.electron.infra.security.TokenService;
import com.electron.repositories.UsuarioRepository;
import jakarta.validation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/autenticaco")
public class AutenticacaoController {

    private AuthenticationManager authenticationManager;
    private UsuarioRepository usuarioRepository;
    private TokenService tokenService;

    public AutenticacaoController(AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO autenticacaoDTO) {
        var senha = new UsernamePasswordAuthenticationToken(autenticacaoDTO.email(), autenticacaoDTO.senha());
        var auth = this.authenticationManager.authenticate(senha);

        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginReponseDTO(token));
    }

    @PostMapping("/registrar")
    public ResponseEntity register(@RequestBody @Valid RegistroDTO registroDTO) {
        // Validação do RegistroDTO
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<RegistroDTO>> violations = validator.validate(registroDTO);

        if (!violations.isEmpty()) {
            // Se houver violações, retorne um erro de validação
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro de validação: " + violations.toString());
        }

        // Verifica se a senha atende aos requisitos de tamanho
        if (registroDTO.senha().length() < 6 || registroDTO.senha().length() > 14) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro de validação: A senha deve ter entre 6 e 14 caracteres.");
        }

        String senhaEncriptada = new BCryptPasswordEncoder().encode(registroDTO.senha());
        Usuario novoUsuario = new Usuario(registroDTO.email(), senhaEncriptada, registroDTO.nome(), registroDTO.cargo());
        this.usuarioRepository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }

}
