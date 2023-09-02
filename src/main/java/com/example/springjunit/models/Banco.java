package com.example.springjunit.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "bancos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Banco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre = "";

    @Column(name = "total_transferencias")
    private Integer totalTransferencias = Integer.valueOf("0");

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Banco)) return false;
        Banco banco = (Banco) o;
        return Objects.equals(id, banco.id) && Objects.equals(nombre, banco.nombre) && Objects.equals(totalTransferencias, banco.totalTransferencias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, totalTransferencias);
    }
}
