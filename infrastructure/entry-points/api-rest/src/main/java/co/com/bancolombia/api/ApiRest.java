package co.com.bancolombia.api; // Mantén el paquete original de tu archivo

import co.com.bancolombia.api.dto.UserRequestDTO;
import co.com.bancolombia.api.dto.UserResponseDTO;
import co.com.crediYa.model.user.user.User;
import co.com.crediYa.usecase.user.user.UserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ApiRest {

    private final UserUseCase userUseCase;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> registrarUsuario(@RequestBody UserRequestDTO request) {
        log.info("Iniciando solicitud de registro para el usuario con correo: {}", request.getCorreoElectronico());

        User userToRegister = User.builder()
                .nombres(request.getNombres())
                .apellidos(request.getApellidos())
                .fechaNacimiento(request.getFechaNacimiento())
                .direccion(request.getDireccion())
                .telefono(request.getTelefono())
                .correoElectronico(request.getCorreoElectronico())
                .salarioBase(request.getSalarioBase())
                .build();

        User savedUser = userUseCase.registerUser(userToRegister);

        log.info("Usuario registrado exitosamente con ID: {}", savedUser.getId());

        UserResponseDTO response = UserResponseDTO.builder()
                .id(savedUser.getId())
                .nombres(savedUser.getNombres())
                .apellidos(savedUser.getApellidos())
                .fechaNacimiento(savedUser.getFechaNacimiento())
                .direccion(savedUser.getDireccion())
                .telefono(savedUser.getTelefono())
                .correoElectronico(savedUser.getCorreoElectronico())
                .salarioBase(savedUser.getSalarioBase())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}