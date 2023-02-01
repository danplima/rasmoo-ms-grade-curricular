package com.rasmoo.cliente.escola.gradecurricular.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class MateriaDto extends RepresentationModel<MateriaDto> {

    private Long id;

    @NotBlank(message = "Informe o nome da Matéria.")
    private String nome;

    @Min(value = 34, message = "Permitido o mínimo de 34 horas por Matéria.")
    @Max(value = 120, message = "Permitido o mínimo de 120 horas por Matéria.")
    private int horas;

    @NotBlank(message = "Informe o código da Matéria.")
    private String codigo;

    @Min(value = 1, message = "Permitido o mínimo de 1 vez ao ano.")
    @Max(value = 2, message = "Permitido o mínimo de 2 vez ao ano.")
    private int frequencia;

}
