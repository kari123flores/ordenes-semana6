package com.kari.ordenes.controller;

import com.kari.ordenes.model.OrdenCompra;
import com.kari.ordenes.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @GetMapping
    public List<OrdenCompra> obtenerTodas() {
        return ordenService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Object obtenerPorId(@PathVariable Integer id) {
        OrdenCompra orden = ordenService.obtenerPorId(id);
        if (orden == null) {
            return "Orden no encontrada";
        }
        return orden;
    }

    @GetMapping("/{id}/estado")
    public String obtenerEstado(@PathVariable Integer id) {
        String estado = ordenService.obtenerEstado(id);
        if (estado == null) {
            return "Orden no encontrada";
        }
        return estado;
    }

    @PostMapping
    public String crearOrden(@RequestBody OrdenCompra orden) {
        return ordenService.crearOrden(orden);
    }

    @PutMapping("/{id}")
    public Object actualizar(@PathVariable Integer id, @RequestBody OrdenCompra orden) {
        OrdenCompra actualizada = ordenService.actualizar(id, orden);
        if (actualizada == null) {
            return "Orden no encontrada";
        }
        return actualizada;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Integer id) {
        ordenService.eliminar(id);
        return "Orden eliminada correctamente";
    }
}