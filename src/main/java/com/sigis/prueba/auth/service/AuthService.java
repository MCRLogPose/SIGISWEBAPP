package com.sigis.prueba.auth.service;

import com.sigis.prueba.auth.dto.*;
import com.sigis.prueba.auth.mapper.UserMapper;
import com.sigis.prueba.auth.model.*;
import com.sigis.prueba.auth.repository.*;
import com.sigis.prueba.common.exception.BadRequestException;
import com.sigis.prueba.incidency.dto.IncidencyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.sigis.prueba.auth.security.JWTService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private UserMapper userMapper;

    public RegisterResponse register(RegisterRequest request){
        if (userRepository.existsByUsername(request.getUsername())){
            throw new BadRequestException("El username ya esta en uso");
        }
        if (userRepository.existsByCorreo(request.getCorreo())){
            throw new BadRequestException("El correo ya esta en uso");
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

        String tipoRol = request.getTipoRol() != null ? request.getTipoRol() : "usuario";
        RolModel rol = rolRepository.findByTipoRol(tipoRol.toLowerCase())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        user.setRol(rol);
        user.setEstado("Active");


        if ("operario".equalsIgnoreCase(rol.getTipoRol())) {
            if (request.getEspecialidad() == null || request.getEspecialidad().isBlank()) {
                throw new BadRequestException("La especialidad es requerida para el rol OPERARIO.");
            }

            OperarioDetails detalles = new OperarioDetails();
            detalles.setUser(user);
            detalles.setEspecialidad(request.getEspecialidad());
            user.setOperarioDetails(detalles);
        }

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
        return new RegisterResponse(jwt);
    }

    public LoginResponse login(LoginRequest request, HttpServletRequest httpRequest){
        UserModel user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (user.getUser2FA() != null && user.getUser2FA().getEnabled()) {
            throw new RuntimeException("2FA requerido");
        }

        String jwt = jwtService.generateToken(user);
        TokenModel tokenRecord = new TokenModel();
        tokenRecord.setUser(user);
        tokenRecord.setRefreshToken(jwt);
        tokenRecord.setRevoked(false);
        tokenRecord.setExpired(false);
        tokenRecord.setIpAddress(httpRequest.getRemoteAddr());
        tokenRecord.setUserAgent(httpRequest.getHeader("User-Agent"));
        userTokensRepository.save(tokenRecord);

        List<String> modulos = user.getRol().getModulos()
                .stream().map(ModuloModel::getNombre).toList();

        return new LoginResponse(jwt, user.getUsername(), user.getRol().getTipoRol(), modulos);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(user -> {
            UserResponse dto = new UserResponse();
            dto.setId(user.getId());
            dto.setNombre(user.getNombre());
            dto.setApellidos(user.getApellidos());
            dto.setTelefono(user.getTelefono());
            dto.setDni(user.getDni());
            dto.setUsername(user.getUsername());
            dto.setCorreo(user.getCorreo());
            dto.setRol(user.getRol().getTipoRol());
            dto.setModulos(
                    user.getRol().getModulos().stream()
                            .map(ModuloModel::getNombre)
                            .toList()
            );
            return dto;
        }).toList();
    }
    public List<UserResponse> getAllOperarios() {
        List<UserModel> operarios = userRepository.findByRol_TipoRolIgnoreCase("operario");
        return operarios.stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

}
