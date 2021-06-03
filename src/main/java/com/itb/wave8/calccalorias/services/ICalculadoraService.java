package com.itb.wave8.calccalorias.services;

import com.itb.wave8.calccalorias.dtos.CaloriasTotalesPlatoDTO;
import com.itb.wave8.calccalorias.dtos.IngredienteDTO;
import com.itb.wave8.calccalorias.dtos.PlatoDTO;
import com.itb.wave8.calccalorias.dtos.PlatoProcesadoDTO;
import com.itb.wave8.calccalorias.models.entities.Food;

import java.util.List;

public interface ICalculadoraService {

    public List<Food> listarComidas();

    public CaloriasTotalesPlatoDTO calcularCaloriasTotalesPlato(PlatoDTO plato);

    public List<IngredienteDTO> calcularCaloriasPorIngrediente(PlatoDTO plato);

    public IngredienteDTO calcularIngredienteMasCalorias(PlatoDTO plato);

    public List<PlatoProcesadoDTO> procesarPlatos (List<PlatoDTO> platos);
}
