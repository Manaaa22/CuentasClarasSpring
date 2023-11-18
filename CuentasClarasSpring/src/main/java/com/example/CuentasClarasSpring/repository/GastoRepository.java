package com.example.CuentasClarasSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CuentasClarasSpring.componentes.Gasto;
@Repository
public interface GastoRepository  extends JpaRepository<Gasto, Long>{

}
