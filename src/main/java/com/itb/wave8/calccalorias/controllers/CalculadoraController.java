package com.itb.wave8.calccalorias.controllers;

import com.itb.wave8.calccalorias.dtos.CaloriasTotalesPlatoDTO;
import com.itb.wave8.calccalorias.dtos.IngredienteDTO;
import com.itb.wave8.calccalorias.dtos.PlatoDTO;
import com.itb.wave8.calccalorias.dtos.PlatoProcesadoDTO;
import com.itb.wave8.calccalorias.models.entities.Food;
import com.itb.wave8.calccalorias.services.ICalculadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CalculadoraController {

    @Autowired
    private ICalculadoraService calculadoraService;

    @GetMapping("/listarComidas")
    public ResponseEntity<List<Food>> listaComidas(){
        return ResponseEntity.ok(this.calculadoraService.listarComidas());
    }

    @PostMapping("/calcularCaloriasTotalesPlato")
    public ResponseEntity<CaloriasTotalesPlatoDTO> calcularCaloriasTotalesPlato(@RequestBody PlatoDTO plato){
        return ResponseEntity.ok(this.calculadoraService.calcularCaloriasTotalesPlato(plato));
    }

    @PostMapping("/calcularCaloriasPorIngrediente")
    public ResponseEntity<List<IngredienteDTO>> calcularCaloriasPorIngrediente(@RequestBody PlatoDTO plato){
        return ResponseEntity.status(HttpStatus.OK).body(this.calculadoraService.calcularCaloriasPorIngrediente(plato));
    }

    @PostMapping("/calcularIngredienteMasCalorias")
    public ResponseEntity<IngredienteDTO> calcularIngredienteMasCalorias(@RequestBody PlatoDTO plato){
        return ResponseEntity.ok(this.calculadoraService.calcularIngredienteMasCalorias(plato));
    }

    @PostMapping("/procesarPlatos")
    public ResponseEntity<List<PlatoProcesadoDTO>> procesarPlatos (@RequestBody List<PlatoDTO> platos){
        return ResponseEntity.ok(this.calculadoraService.procesarPlatos(platos));
    }

}
