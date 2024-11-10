package org.example.hibernate1an_isaac_gonzalez.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "multas")
public class Multa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_multa")
    private int id;
    @Column(name = "precio")
    private int precio;
    @Column(name = "fecha")
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "matricula", referencedColumnName = "matricula")
    private Coche coche;

    public Multa() {
    }

    public Multa(int id, Coche coche, LocalDate fecha, int precio) {
        this.id = id;
        this.coche = coche;
        this.fecha = fecha;
        this.precio = precio;
    }

    public Multa(int precio, LocalDate fecha, Coche coche) {
        this.precio = precio;
        this.fecha = fecha;
        this.coche = coche;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Coche getCoche() {
        return coche;
    }

    public void setCoche(Coche coche) {
        this.coche = coche;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Multa{" +
                "id=" + id +
                ", precio=" + precio +
                ", fecha=" + fecha +
                ", coche=" + coche +
                '}';
    }
}
