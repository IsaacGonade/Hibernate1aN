package org.example.hibernate1an_isaac_gonzalez.DAO;

import org.example.hibernate1an_isaac_gonzalez.Model.Coche;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CocheDAOImp implements CocheDAO{
    @Override
    public void insertarCoche(Coche coche, Session session) {
        Transaction transaction = null;
        try{
            //guardo el nuevo coche
            transaction = session.beginTransaction();
            session.save(coche);
            transaction.commit();
            session.clear();
        } catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
        }
    }

    @Override
    public void modificarCoche(Coche cocheViejo, Coche cocheNuevo, Session session) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            //Selecciono el id del coche viejo
            Coche cocheSeleccionado = session.get(Coche.class, cocheViejo.getId());

            //Cambio los campos por los nuevos
            cocheSeleccionado.setMatricula(cocheNuevo.getMatricula());
            cocheSeleccionado.setMarca(cocheNuevo.getMarca());
            cocheSeleccionado.setModelo(cocheNuevo.getModelo());
            cocheSeleccionado.setTipo(cocheNuevo.getTipo());

            //actualizo el coche
            session.update(cocheSeleccionado);
            transaction.commit();
            session.clear();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void eliminarCoche(Coche coche, Session session) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            //selecciono el id del coche seleccionado en la tabla
            Coche cocheSeleccionado = session.get(Coche.class, coche.getId());
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
    public List<Coche> obtenerCoche(Session session) {
        Transaction transaction = null;
        List<Coche> coches = null;
        try{
            //obtengo todos los coches y los muestro en la tabla
            transaction = session.beginTransaction();
            coches = session.createQuery("from Coche").list();
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
        }
        return coches;
    }
}
