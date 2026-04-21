package com.kari.ordenes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kari.ordenes.model.OrdenCompra;

public interface OrdenRepository extends JpaRepository<OrdenCompra, Integer> {
}