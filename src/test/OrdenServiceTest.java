package com.kari.ordenes;

import com.kari.ordenes.model.OrdenCompra;
import com.kari.ordenes.repository.OrdenRepository;
import com.kari.ordenes.service.OrdenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrdenServiceTest {

    @Mock
    private OrdenRepository repository;

    @InjectMocks
    private OrdenService ordenService;

    @Test
    void testCrearOrdenCorrectamente() {
        OrdenCompra orden = new OrdenCompra();
        orden.setCliente("Kari");
        orden.setProducto("Correa");
        orden.setCantidad(1);
        orden.setPrecioUnitario(12990);
        orden.setEstado("PENDIENTE");

        String resultado = ordenService.crearOrden(orden);

        assertEquals("Orden creada correctamente", resultado);
        verify(repository, times(1)).save(orden);
    }

    @Test
    void testCrearOrdenSinCliente() {
        OrdenCompra orden = new OrdenCompra();
        orden.setCliente("");
        orden.setProducto("Correa");
        orden.setCantidad(1);
        orden.setPrecioUnitario(12990);

        String resultado = ordenService.crearOrden(orden);

        assertEquals("El cliente es obligatorio", resultado);
        verify(repository, never()).save(orden);
    }
}