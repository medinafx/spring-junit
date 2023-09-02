package com.example.springjunit.services;

import com.example.springjunit.models.Banco;
import com.example.springjunit.repositories.BancoRepository;

import java.util.List;

public class BancoServiceImpl implements BancoService {

    private BancoRepository bancoRepository;

    public BancoServiceImpl(BancoRepository bancoRepository) {
        this.bancoRepository = bancoRepository;
    }

    @Override
    public List<Banco> findAll() {
        return this.bancoRepository.findAll();
    }

    @Override
    public Banco findById(Long id) {
        return this.bancoRepository.findById(id).orElseThrow();
    }

    @Override
    public Banco update(Banco banco) {
        return this.bancoRepository.save(banco);
    }
}
