package com.example.springjunit;

import com.example.springjunit.models.Banco;
import com.example.springjunit.models.Cuenta;

import java.math.BigDecimal;
import java.util.Optional;

public class Datos {
    public static Optional<Cuenta> crearCuenta001() {
        return Optional.of(new Cuenta(1L, "Felix", new BigDecimal("1000")));
    }

    public static Optional<Cuenta> crearCuenta002() {
        return Optional.of(new Cuenta(1L, "Jose", new BigDecimal("2000")));
    }

    public static Optional<Banco> crearBanco() {
        return Optional.of(new Banco(1L, "FICOHSA", 0));
    }
}
