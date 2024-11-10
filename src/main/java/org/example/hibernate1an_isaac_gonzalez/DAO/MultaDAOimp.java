package org.example.hibernate1an_isaac_gonzalez.DAO;

import org.example.hibernate1an_isaac_gonzalez.Model.Coche;
import org.example.hibernate1an_isaac_gonzalez.Model.Multa;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MultaDAOimp implements MultaDAO{

    //metodo para insertar una multa
    @Override
    public void insertarMulta(Multa multa, Session session) {
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            //guardo la nueva multa
            session.save(multa);
            transaction.commit();
            session.clear();
        } catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
        }
    }

    public static void modificarMulta(Multa multaVieja, Multa multaNueva, Session session) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            //Selecciono el id de la multa anterior
            Multa multaSeleccionada = session.get(Multa.class, multaVieja.getId());

            //Cambio los campos por los nuevos
            multaSeleccionada.setPrecio(multaNueva.getPrecio());
            multaSeleccionada.setFecha(multaNueva.getFecha());

            //actualizo la multa
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
            //elimino la multa seleccionada
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
            //obtengo todos los coches y devuelvo una lista que mostrare en la tabla
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
