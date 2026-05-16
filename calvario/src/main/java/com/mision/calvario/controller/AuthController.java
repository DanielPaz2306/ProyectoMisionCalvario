package com.mision.calvario.controller;

import com.mision.calvario.dto.LoginRequestDTO;
import com.mision.calvario.dto.LoginResponseDTO;
import com.mision.calvario.entity.UsuarioEntity;
import com.mision.calvario.repository.UsuarioRepository;
import com.mision.calvario.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {
        
        // Autenticar usuario
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // Buscar usuario con pastor y distrito precargados
        UsuarioEntity usuario = usuarioRepository.findByUsernameWithPastorAndDistrito(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Generar token
        Long pastorId = usuario.getPastor() != null ? usuario.getPastor().getId() : null;
        Long distritoId = null;
        if (usuario.getPastor() != null && usuario.getPastor().getDistrito() != null) {
            distritoId = usuario.getPastor().getDistrito().getId();
        }
        String token = jwtUtil.generarToken(usuario.getUsername(), usuario.getRol(), pastorId, distritoId);
        return new LoginResponseDTO(token, usuario.getUsername(), usuario.getRol(), pastorId);
    }

    @PostMapping("/registro")
    public String registro(@RequestBody UsuarioEntity usuario) {
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new RuntimeException("Ya existe un usuario con ese username");
        }
        // Encriptar password antes de guardar
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setActivo(true);
        usuarioRepository.save(usuario);
        return "Usuario creado exitosamente";
    }
}