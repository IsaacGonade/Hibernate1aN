package org.example.hibernate1an_isaac_gonzalez.DAO;

import org.example.hibernate1an_isaac_gonzalez.Model.Coche;
import org.hibernate.Session;

import java.util.List;

public interface CocheDAO {
    //metodo para guardar un nuevo coche
    void insertarCoche(Coche coche, Session session);

    //metodo para modificar un coche
    void modificarCoche(Coche cocheViejo, Coche cocheNuevo, Session session);

    //metodo para eliminar un coche
    void eliminarCoche(Coche coche, Session session);

    //metodo para listar todos los coches de la base de datos
    List<Coche> obtenerCoche(Session session);
}
