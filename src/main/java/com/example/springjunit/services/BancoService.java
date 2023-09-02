package com.example.springjunit.services;

import com.example.springjunit.models.Banco;

import java.util.List;

public interface BancoService {
    List<Banco> findAll();

    Banco findById(Long id);

    Banco update(Banco banco);
}
