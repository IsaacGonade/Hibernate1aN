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
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            //Selecciono el id del coche viejo
            Multa multaSeleccionada = session.get(Multa.class, multaVieja.getId());

            //Cambio los campos por los nuevos
            multaSeleccionada.setPrecio(multaNueva.getPrecio());
            multaSeleccionada.setFecha(multaNueva.getFecha());

            //actualizo el coche
            session.update(multaSeleccionada);
            transaction.commit();
            session.clear();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void eliminarMulta(Multa multa, Session session) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            //selecciono el id de la multa seleccionada en la tabla
            Multa multaSeleccionada = session.get(Multa.class, multa.getId());
            //elimino la multa seleccionado
            session.delete(multaSeleccionada);
            transaction.commit();
            session.clear();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public List<Multa> obtenerMulta(Session session, String matricula) {
        Transaction transaction = null;
        List<Multa> multas = null;
        try{
            //obtengo todos los coches y los muestro en la tabla
            transaction = session.beginTransaction();
            multas = session.createQuery("from Multa where matricula= '" + matricula + "'", Multa.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
        }
        return multas;
    }
}
