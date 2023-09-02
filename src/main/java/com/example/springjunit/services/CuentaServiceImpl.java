package com.example.springjunit.services;

import com.example.springjunit.models.Banco;
import com.example.springjunit.models.Cuenta;
import com.example.springjunit.repositories.BancoRepository;
import com.example.springjunit.repositories.CuentaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;
    private final BancoRepository bancoRepository;

    public CuentaServiceImpl(CuentaRepository cuentaRepository, BancoRepository bancoRepository) {
        this.cuentaRepository = cuentaRepository;
        this.bancoRepository = bancoRepository;
    }

    @Override
    public List<Cuenta> findAll() {
        return this.cuentaRepository.findAll();
    }

    @Override
    public Cuenta findById(Long id) {
        return this.cuentaRepository.findById(id).orElseThrow();
    }

    @Override
    public void update(Cuenta cuenta) {
        this.cuentaRepository.save(cuenta);
    }

    @Override
    public Cuenta save(Cuenta cuenta) {
        return this.cuentaRepository.save(cuenta);
    }

    @Override
    public int revisarTotalTransferencias(Long bancoId) {
        return this.bancoRepository.findById(bancoId).orElseThrow().getTotalTransferencias();
    }

    @Override
    public BigDecimal revisarSaldo(Long cuentaId) {
        return this.cuentaRepository.findById(cuentaId).orElseThrow().getSaldo();
    }

    @Override
    public void transferir(Long numCuentaOrigen, Long numCuentaDestino, BigDecimal monto, Long bancoId) {
        Cuenta cuentaDestino = this.cuentaRepository.findById(numCuentaDestino).orElseThrow();
        Cuenta cuentaOrigen = this.cuentaRepository.findById(numCuentaOrigen).orElseThrow();
        cuentaOrigen.setDebito(monto);
        cuentaDestino.setCredito(monto);

        this.cuentaRepository.save(cuentaDestino);
        this.cuentaRepository.save(cuentaOrigen);

        Banco banco = this.bancoRepository.findById(bancoId).orElseThrow();
        banco.setTotalTransferencias(banco.getTotalTransferencias() + 1);
        this.bancoRepository.save(banco);
    }
}
