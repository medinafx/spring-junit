package com.example.springjunit;

import com.example.springjunit.models.Cuenta;
import com.example.springjunit.repositories.CuentaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class IntegrationJpaTest {

    @Autowired
    CuentaRepository cuentaRepository;

    @Test
    void findById() {
        Optional<Cuenta> cuenta = cuentaRepository.findById(1L);
        assertTrue(cuenta.isPresent());
        assertEquals("Felix", cuenta.orElseThrow().getNombre());
    }
}
