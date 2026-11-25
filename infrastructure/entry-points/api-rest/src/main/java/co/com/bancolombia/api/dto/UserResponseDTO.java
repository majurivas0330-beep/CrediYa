package co.com.bancolombia.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String nombres;
    private String apellidos;

    @JsonProperty("fecha_nacimiento")
    private LocalDate fechaNacimiento;

    private String direccion;
    private String telefono;

    @JsonProperty("correo_electronico")
    private String correoElectronico;

    @JsonProperty("salario_base")
    private BigDecimal salarioBase;
}