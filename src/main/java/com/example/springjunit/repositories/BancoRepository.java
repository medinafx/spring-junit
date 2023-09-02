package com.example.springjunit.repositories;

import com.example.springjunit.models.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BancoRepository extends JpaRepository<Banco, Long> {
//    List<Banco> findAll();
//
//    Optional<Banco> findById(Long id);
//
//    Banco update(Banco banco);
}
