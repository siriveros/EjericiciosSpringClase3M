package com.itb.wave8.calccalorias.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredienteDTO {
    private String nombre;
    private Integer peso;
    private Integer calorias;
}
