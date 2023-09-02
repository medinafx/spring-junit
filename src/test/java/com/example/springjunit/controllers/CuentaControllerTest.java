package com.example.springjunit.controllers;

import com.example.springjunit.Datos;
import com.example.springjunit.models.TransaccionDto;
import com.example.springjunit.services.CuentaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import static java.nio.charset.StandardCharsets.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CuentaController.class)
@AutoConfigureMockMvc
class CuentaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CuentaService cuentaService;

    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        this.mapper = new ObjectMapper();
    }

    @Test
    void testDetalle() throws Exception {
        when(this.cuentaService.findById(1L)).thenReturn(Datos.crearCuenta001().get());

        mvc.perform(get("/api/cuentas/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("Felix"))
                .andExpect(jsonPath("$.saldo").value("1000"));
    }


    @Test
    void testTransferir() throws Exception {

        // Given
        TransaccionDto transferencia = new TransaccionDto();
        transferencia.setCuentaOrigenId(1L);
        transferencia.setCuentaDestinoId(2L);
        transferencia.setMonto(new BigDecimal("100"));
        transferencia.setBancoId(1L);

        String jsonContent = readFileFromClasspath(new ClassPathResource("transferir.json"));

        //then
        mvc.perform(post("/api/cuentas/transferir").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(transferencia)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.date").value(LocalDate.now().toString()))
                .andExpect(jsonPath("$.transaccion.cuentaOrigenId").value(1L))
                .andExpect(content().json(jsonContent,false));
    }

    private static String readFileFromClasspath(ClassPathResource classPathResource) throws Exception {
        try (Reader reader = new InputStreamReader(classPathResource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        }
    }
}