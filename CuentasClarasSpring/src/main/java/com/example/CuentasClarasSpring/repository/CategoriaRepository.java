package com.example.CuentasClarasSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CuentasClarasSpring.componentes.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>  {

}
