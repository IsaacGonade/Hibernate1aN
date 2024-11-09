package org.example.hibernate1an_isaac_gonzalez.DAO;

import org.example.hibernate1an_isaac_gonzalez.Model.Coche;
import org.example.hibernate1an_isaac_gonzalez.Model.Multa;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MultaDAOimp implements MultaDAO{
    @Override
    public void insertarMulta(Multa multa, Session session) {
        Transaction transaction = null;
        try{
            //guardo el nuevo coche
            transaction = session.beginTransaction();
            session.save(multa);
            transaction.commit();
            session.clear();
        } catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
        }
    }

    @Override
    public void modificarMulta(Multa multaVieja, Multa multaNueva, Session session) {
        /*Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            //Selecciono el id del coche viejo
            Coche multaSeleccionada = session.get(Coche.class, multaVieja.getId());

            //Cambio los campos por los nuevos
            multaSeleccionada.setMatricula(multaNueva.getPrecio());
            multaSeleccionada.setMarca(multaNueva.getFecha());

            //actualizo el coche
            session.update(multaSeleccionada);
            transaction.commit();
            session.clear();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }*/
    }

    @Override
    public void eliminarMulta(Multa multa, Session session) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            //selecciono el id del coche seleccionado en la tabla
            Coche cocheSeleccionado = session.get(Coche.class, multa.getId());
            //elimino el coche seleccionado
            session.delete(cocheSeleccionado);
            transaction.commit();
            session.clear();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public List<Multa> obtenerMulta(Session session) {
        Transaction transaction = null;
        List<Multa> multas = null;
        try{
            //obtengo todos los coches y los muestro en la tabla
            transaction = session.beginTransaction();
            multas = session.createQuery("from Coche").list();
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
        }
        return multas;
    }
}
