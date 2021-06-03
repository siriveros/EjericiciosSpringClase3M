package com.itb.wave8.calccalorias.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itb.wave8.calccalorias.dtos.CaloriasTotalesPlatoDTO;
import com.itb.wave8.calccalorias.dtos.IngredienteDTO;
import com.itb.wave8.calccalorias.dtos.PlatoDTO;
import com.itb.wave8.calccalorias.dtos.PlatoProcesadoDTO;
import com.itb.wave8.calccalorias.models.entities.Food;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalculadoraService implements ICalculadoraService {

    public static final int CALORIAS_X_100G = 100;

    @Override
    public List<Food> listarComidas() {
        return this.cargarComidaArchivoJson();
    }

    @Override
    public CaloriasTotalesPlatoDTO calcularCaloriasTotalesPlato(PlatoDTO plato) {
        Integer caloriasTotales = 0;
        for (IngredienteDTO ingrediente : plato.getListaIngredientes()) {
            caloriasTotales += this.calcularCalorias(ingrediente);
        }
        return new CaloriasTotalesPlatoDTO(caloriasTotales);
    }

    @Override
    public List<IngredienteDTO> calcularCaloriasPorIngrediente(PlatoDTO plato) {
        List<IngredienteDTO> listaIngredientes = plato.getListaIngredientes();
        for (IngredienteDTO ingrediente : plato.getListaIngredientes()) {
            ingrediente.setCalorias(this.calcularCalorias(ingrediente));
        }
        return listaIngredientes;
    }

    @Override
    public IngredienteDTO calcularIngredienteMasCalorias(PlatoDTO plato) {
        List<IngredienteDTO> listaIngredientesConCalorias = this.calcularCaloriasPorIngrediente(plato);
        IngredienteDTO ingredienteMasCalorico = listaIngredientesConCalorias.get(0);
        for (int i = 1; i < plato.getListaIngredientes().size(); i++) {
            if (ingredienteMasCalorico.getCalorias() < plato.getListaIngredientes().get(i).getCalorias()) {
                ingredienteMasCalorico = plato.getListaIngredientes().get(i);
            }
        }
        return ingredienteMasCalorico;
    }

    @Override
    public List<PlatoProcesadoDTO> procesarPlatos(List<PlatoDTO> platos) {
        List<PlatoProcesadoDTO> listaPlatosProcesados = new ArrayList<>();
        for (PlatoDTO plato : platos) {
            listaPlatosProcesados.add(
                    new PlatoProcesadoDTO(
                            plato.getNombre(),
                            calcularCaloriasPorIngrediente(plato),
                            calcularCaloriasTotalesPlato(plato))
            );
        }
        return listaPlatosProcesados;
    }

    private Integer calcularCalorias(IngredienteDTO ingrediente) {
        return getFoodByNombre(ingrediente.getNombre()).getCalories() * ingrediente.getPeso() / CALORIAS_X_100G;
    }

    private List<Food> cargarComidaArchivoJson() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:food.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Food>> typeRef = new TypeReference<>() {
        };
        List<Food> foods = null;
        try {
            foods = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return foods;
    }

    private Food getFoodByNombre(String nombreIngrediente) {
        for (Food comida : this.cargarComidaArchivoJson()) {
            if (nombreIngrediente.equals(comida.getName())) {
                return comida;
            }
        }
        throw new RuntimeException("No se encontro el ingrediente en la base de datos de comidas");
    }
}
