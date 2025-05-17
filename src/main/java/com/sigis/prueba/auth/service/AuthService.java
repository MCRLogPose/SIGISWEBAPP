package com.sigis.prueba.auth.service;

import com.sigis.prueba.auth.model.*;
import com.sigis.prueba.auth.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.sigis.prueba.auth.security.JWTService;
import com.sigis.prueba.auth.dto.LoginResponse;
import com.sigis.prueba.auth.dto.RegisterRequest;
import com.sigis.prueba.auth.dto.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class AuthService {
    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Autowired
    private User2FARepository user2FARepository;

    @Autowired
    private UserTokensRepository userTokensRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public LoginResponse register(RegisterRequest request){
        if (userRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("El username ya esta en uso");
        }
        if (userRepository.existsByCorreo(request.getCorreo())){
            throw new RuntimeException("El username ya esta en uso");
        }

        UserModel user = new UserModel();

        user.setNombre(request.getNombre());
        user.setApellidos(request.getApellidos());
        user.setTelefono(request.getTelefono());
        user.setDni(request.getDni());
        user.setUsername(request.getUsername());
        user.setCorreo(request.getCorreo());

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);

        RolModel rolDefect = rolRepository.findByTipoRol("usuario")
                .orElseThrow(()-> new RuntimeException("Rol no encontrado"));
        user.setRol(rolDefect);
        user.setEstado("Active");

        UserModel savedUser = userRepository.save(user);

        CredentialsModel credentials = new CredentialsModel();
        credentials.setUser(savedUser);
        credentials.setPasswordHash(encodedPassword);
        credentials.setSalt(null);
        userCredentialsRepository.save(credentials);

        TwoFAModel twoFA = new TwoFAModel();
        twoFA.setUser(savedUser);
        twoFA.setSecretKey("DEFAULT");
        twoFA.setEnabled(false);

        user2FARepository.save(twoFA);

        String jwt=jwtService.generateToken(savedUser);
        return new LoginResponse(jwt);
    }

    public LoginResponse login(LoginRequest request, HttpServletRequest httpRequest){
        UserModel user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        CredentialsModel credentials = userCredentialsRepository.findByUser(user);

        if (credentials == null) {
            throw new RuntimeException("Credenciales no encontradas");
        }

        if (!passwordEncoder.matches(request.getPassword(), credentials.getPasswordHash())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        if (user.getUser2FA() != null && user.getUser2FA().getEnabled()) {
            throw new RuntimeException("2FA requerido");
        }
        String ipAddress = httpRequest.getRemoteAddr();
        String userAgent = httpRequest.getHeader("User-Agent");

        String jwt = jwtService.generateToken(user);
        TokenModel tokenRecord = new TokenModel();
        tokenRecord.setUser(user);
        tokenRecord.setRefreshToken(jwt);
        tokenRecord.setRevoked(false);
        tokenRecord.setExpired(false);
        tokenRecord.setIpAddress(ipAddress);
        tokenRecord.setUserAgent(userAgent);
        userTokensRepository.save(tokenRecord);

        return new LoginResponse(jwt);
    }
}
