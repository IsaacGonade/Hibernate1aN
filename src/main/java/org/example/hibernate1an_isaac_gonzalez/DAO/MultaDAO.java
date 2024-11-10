package org.example.hibernate1an_isaac_gonzalez.DAO;

import org.example.hibernate1an_isaac_gonzalez.Model.Multa;
import org.hibernate.Session;

import java.util.List;

public interface MultaDAO {
    void insertarMulta(Multa multa, Session session);

    static void modificarMulta(Multa multaVieja, Multa multaNueva, Session session) {}

    void eliminarMulta(Multa multa, Session session);

    List<Multa> obtenerMulta(Session session, String matricula);
}
