package com.kari.ordenes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kari.ordenes.model.OrdenCompra;
import com.kari.ordenes.repository.OrdenRepository;

@Service
public class OrdenService {

    @Autowired
    private OrdenRepository repository;

    public List<OrdenCompra> obtenerTodas() {
        return repository.findAll();
    }

    public OrdenCompra obtenerPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public String obtenerEstado(Integer id) {
        OrdenCompra orden = obtenerPorId(id);
        if (orden != null) {
            return orden.getEstado();
        }
        return null;
    }

    public String crearOrden(OrdenCompra orden) {
        if (orden.getCliente() == null || orden.getCliente().trim().isEmpty()) {
            return "El cliente es obligatorio";
        }
        if (orden.getProducto() == null || orden.getProducto().trim().isEmpty()) {
            return "El producto es obligatorio";
        }
        if (orden.getCantidad() <= 0) {
            return "La cantidad debe ser mayor a 0";
        }
        if (orden.getPrecioUnitario() <= 0) {
            return "El precio unitario debe ser mayor a 0";
        }

        if (orden.getEstado() == null || orden.getEstado().trim().isEmpty()) {
            orden.setEstado("PENDIENTE");
        }

        repository.save(orden);
        return "Orden creada correctamente";
    }

    public OrdenCompra actualizar(Integer id, OrdenCompra nueva) {
        OrdenCompra orden = obtenerPorId(id);

        if (orden != null) {
            orden.setCliente(nueva.getCliente());
            orden.setProducto(nueva.getProducto());
            orden.setCantidad(nueva.getCantidad());
            orden.setPrecioUnitario(nueva.getPrecioUnitario());
            orden.setEstado(nueva.getEstado());
            return repository.save(orden);
        }

        return null;
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}