package org.example.hibernate1an_isaac_gonzalez.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.hibernate1an_isaac_gonzalez.DAO.MultaDAOimp;
import org.example.hibernate1an_isaac_gonzalez.Model.Coche;
import org.example.hibernate1an_isaac_gonzalez.Model.Multa;
import org.example.hibernate1an_isaac_gonzalez.Util.Alerts;
import org.example.hibernate1an_isaac_gonzalez.Util.HibernateUtil;
import org.example.hibernate1an_isaac_gonzalez.Util.Validar;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MultaController implements Initializable {

    @FXML
    private TableColumn<Multa, LocalDate> colFecha;

    @FXML
    private TableColumn<Multa, Integer> colMulta;

    @FXML
    private TableColumn<Multa, Integer> colPrecio;

    @FXML
    private DatePicker fechaTF;

    @FXML
    private TextField idmultaTF;

    @FXML
    private TextField matriculaTF;

    @FXML
    private TextField precioTF;

    @FXML
    private TableView<Multa> tablaMultas;

    private final MultaDAOimp multaDAOimp = new MultaDAOimp();
    private Multa multaSeleccionada;

    SessionFactory factory = HibernateUtil.getSessionFactory();
    Session session = HibernateUtil.getSession();

    //metodo carga los datos desde el inicio
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarDatos();
    }

    public void cargarDatos() {
        //limpia los campos
        tablaMultas.getItems().clear();
        try {
            //cargo los datos en la tabla
            List<Multa> coches = multaDAOimp.obtenerMulta(session);
            tablaMultas.setItems(FXCollections.observableList(coches));

            //cargamos los datos en las columnas
            colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            colMulta.setCellValueFactory(new PropertyValueFactory<>("multa"));
            colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void onEliminar(ActionEvent event) {
        Multa multa = tablaMultas.getSelectionModel().getSelectedItem();
        if (multa == null){
            Alerts.mostrarError("No se ha seleccionado ninguna multa");
            return;
        }

        try {
            //alerta para confirmar la accion
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("¿Eliminar multa?");
            confirmacion.setContentText("¿Estás seguro?");
            Optional<ButtonType> respuesta = confirmacion.showAndWait();
            if (respuesta.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE){
                return;
            }

            //llamo al metodo
            multaDAOimp.eliminarMulta(multa, session);

            //cargo los datos para refrescar la tabla
            cargarDatos();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void onInsertar(ActionEvent event) {
        //valido que el campo de la matrícula no este vacio y que cumpla con los requisitos
        if (idmultaTF.getText().isEmpty()) {
            Alerts.mostrarError("La idmulta es un campo obligatorio");
            return;
        }

        //recojo los datos de los campos de texto y los guardo en un nuevo objeto
        int idmulta = Integer.parseInt(idmultaTF.getText());
        int precio = Integer.parseInt(precioTF.getText());
        LocalDate fecha = LocalDate.parse(fechaTF.getConverter().toString());
        Multa multa = new Multa(idmulta, fecha, precio);

        try {
            //alerta para confirmar la accion
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("¿Añadir multa?");
            confirmacion.setContentText("¿Estás seguro?");
            Optional<ButtonType> respuesta = confirmacion.showAndWait();
            if (respuesta.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE){
                return;
            }

            multaDAOimp.insertarMulta(multa, session);

            cargarDatos();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void onLimpiar(ActionEvent event) {

    }

    @FXML
    void onModificar(ActionEvent event) {

    }

    @FXML
    void seleccionarCoche(MouseEvent event) {

    }

}
