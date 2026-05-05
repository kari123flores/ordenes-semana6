package com.kari.ordenes.controller;

import com.kari.ordenes.model.OrdenCompra;
import com.kari.ordenes.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @GetMapping
    public CollectionModel<EntityModel<OrdenCompra>> obtenerTodas() {
        List<EntityModel<OrdenCompra>> ordenes = ordenService.obtenerTodas().stream()
                .map(orden -> EntityModel.of(orden,
                        linkTo(methodOn(OrdenController.class).obtenerPorId(orden.getId())).withSelfRel()
                ))
                .toList();

        return CollectionModel.of(ordenes,
                linkTo(methodOn(OrdenController.class).obtenerTodas()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<OrdenCompra> obtenerPorId(@PathVariable Integer id) {
        OrdenCompra orden = ordenService.obtenerPorId(id);

        return EntityModel.of(orden,
                linkTo(methodOn(OrdenController.class).obtenerPorId(id)).withSelfRel(),
                linkTo(methodOn(OrdenController.class).obtenerTodas()).withRel("todas"));
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
    public EntityModel<OrdenCompra> actualizar(@PathVariable Integer id, @RequestBody OrdenCompra orden) {
        OrdenCompra actualizada = ordenService.actualizar(id, orden);

        return EntityModel.of(actualizada,
                linkTo(methodOn(OrdenController.class).obtenerPorId(id)).withSelfRel(),
                linkTo(methodOn(OrdenController.class).obtenerTodas()).withRel("todas"));
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Integer id) {
        ordenService.eliminar(id);
        return "Orden eliminada correctamente";
    }
}