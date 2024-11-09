package org.example.hibernate1an_isaac_gonzalez.Model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "multas")
public class Multa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "precio")
    private int precio;
    @Column(name = "fecha")
    private LocalDate fecha;


    public Multa() {
    }

    public Multa(int id, LocalDate fecha, int precio) {
        this.id = id;
        this.fecha = fecha;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Multa{" +
                "id=" + id +
                ", precio=" + precio +
                ", fecha=" + fecha +
                '}';
    }
}
