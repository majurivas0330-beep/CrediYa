package co.com.crediYa.usecase.user.user;

import co.com.crediYa.model.user.user.User;
import co.com.crediYa.model.user.user.exceptions.BusinessException;
import co.com.crediYa.model.user.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;

    private static final BigDecimal MIN_SALARY = BigDecimal.ZERO;
    private static final BigDecimal MAX_SALARY = new BigDecimal("15000000");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public User registerUser(User user) {
        validateRequiredFields(user);
        validateFormatsAndRanges(user);

        if (userRepository.existsByCorreoElectronico(user.getCorreoElectronico())) {
            throw new BusinessException("El correo electrónico ya se encuentra registrado.");
        }

        return userRepository.save(user);
    }

    private void validateRequiredFields(User user) {
        if (user == null) {
            throw new BusinessException("Los datos del usuario son obligatorios.");
        }
        if (isNullOrBlank(user.getNombres())) {
            throw new BusinessException("El campo nombres es obligatorio.");
        }
        if (isNullOrBlank(user.getApellidos())) {
            throw new BusinessException("El campo apellidos es obligatorio.");
        }
        if (isNullOrBlank(user.getCorreoElectronico())) {
            throw new BusinessException("El campo correo_electronico es obligatorio.");
        }
        if (user.getSalarioBase() == null) {
            throw new BusinessException("El campo salario_base es obligatorio.");
        }
    }

    private void validateFormatsAndRanges(User user) {
        if (!EMAIL_PATTERN.matcher(user.getCorreoElectronico()).matches()) {
            throw new BusinessException("El formato del correo_electronico no es válido.");
        }
        if (user.getSalarioBase().compareTo(MIN_SALARY) < 0 || user.getSalarioBase().compareTo(MAX_SALARY) > 0) {
            throw new BusinessException("El salario_base debe estar entre 0 y 15000000.");
        }
    }

    private boolean isNullOrBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}