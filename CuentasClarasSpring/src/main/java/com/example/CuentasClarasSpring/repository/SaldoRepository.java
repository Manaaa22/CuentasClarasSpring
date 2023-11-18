package com.example.CuentasClarasSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CuentasClarasSpring.componentes.Saldo;
@Repository
public interface SaldoRepository  extends JpaRepository<Saldo, Long>{

}
