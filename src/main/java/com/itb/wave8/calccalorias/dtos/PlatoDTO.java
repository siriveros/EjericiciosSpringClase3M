package com.itb.wave8.calccalorias.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlatoDTO {
    private String nombre;
    private List<IngredienteDTO> listaIngredientes;
}
