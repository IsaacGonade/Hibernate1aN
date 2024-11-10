package org.example.hibernate1an_isaac_gonzalez.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.hibernate1an_isaac_gonzalez.App;
import org.example.hibernate1an_isaac_gonzalez.DAO.CocheDAOImp;
import org.example.hibernate1an_isaac_gonzalez.Model.Coche;
import org.example.hibernate1an_isaac_gonzalez.Util.Alerts;
import org.example.hibernate1an_isaac_gonzalez.Util.HibernateUtil;
import org.example.hibernate1an_isaac_gonzalez.Util.Validar;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CocheController implements Initializable {

    @FXML
    private ComboBox<String> cbTipo;

    @FXML
    private TableColumn<Coche, String> clTipo;

    @FXML
    private TableColumn<Coche, String> colMarca;

    @FXML
    private TableColumn<Coche, String> colMatricula;

    @FXML
    private TableColumn<Coche, String> colModelo;

    @FXML
    private TextField marcaTF;

    @FXML
    private TextField matriculaTF;

    @FXML
    private TextField modeloTF;

    @FXML
    private TableView<Coche> tablaCoches;

    @FXML
    private AnchorPane ventanaGestion;

    private final CocheDAOImp cocheDAOImp = new CocheDAOImp();
    private Coche cocheseleccionado;

    SessionFactory factory = HibernateUtil.getSessionFactory();
    Session session = HibernateUtil.getSession();

    //metodo carga los datos desde el inicio
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarDatos();
    }

    //cargo los datos en la tabla
    public void cargarDatos() {
        //limpia los campos
        tablaCoches.getItems().clear();
        try {
            //cargo los datos en la tabla
            List<Coche> coches = cocheDAOImp.obtenerCoche(session);
            tablaCoches.setItems(FXCollections.observableList(coches));

            //cargamos los datos en las columnas
            colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
            colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
            colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
            clTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

            //relleno el combobox
            String[] tipos = new String[]{"Familiar","SUV","Compacto","Deportivo"};
            cbTipo.setItems(FXCollections.observableArrayList(tipos));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void onEliminar(ActionEvent event) {
        Coche coche = tablaCoches.getSelectionModel().getSelectedItem();
        if (coche == null){
            Alerts.mostrarError("No se ha seleccionado ningun coche");
            return;
        }

        try {
            //alerta para confirmar la accion
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("¿Eliminar coche?");
            confirmacion.setContentText("¿Estás seguro?");
            Optional<ButtonType> respuesta = confirmacion.showAndWait();
            if (respuesta.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE){
                return;
            }

            //llamo al metodo
            cocheDAOImp.eliminarCoche(coche, session);

            //cargo los datos para refrescar la tabla
            cargarDatos();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void onInsertar(ActionEvent event) {
        String matricula = matriculaTF.getText();
        //valido que el campo de la matrícula no este vacio y que cumpla con los requisitos
        if (matricula.isEmpty()) {
            Alerts.mostrarError("La matricula es un campo obligatorio");
            return;
        }else if (!Validar.comprobarMatricula(matriculaTF.getText())){
            Alerts.mostrarError("La matricula no cumple los requisitos");
            return;
        }

        //recojo los datos de los campos de texto y los guardo en un nuevo objeto
        String marca = marcaTF.getText();
        String modelo = modeloTF.getText();
        String tipo = cbTipo.getSelectionModel().getSelectedItem();
        Coche coche = new Coche(matricula, marca, modelo, tipo);

        try {
            //alerta para confirmar la accion
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("¿Añadir coche?");
            confirmacion.setContentText("¿Estás seguro?");
            Optional<ButtonType> respuesta = confirmacion.showAndWait();
            if (respuesta.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE){
                return;
            }

            //llamo al metodo
            cocheDAOImp.insertarCoche(coche, session);

            //refresco la tabla
            cargarDatos();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //boton para reiniciar los campos
    @FXML
    void onLimpiar(ActionEvent event) {
        marcaTF.setText("");
        matriculaTF.setText("");
        modeloTF.setText("");
        cbTipo.setValue("Selecciona tipo");
        matriculaTF.requestFocus();
    }

    @FXML
    void onModificar(ActionEvent event) {
        String matricula = matriculaTF.getText();

        //valido que el campo de la matrícula no este vacio y que cumpla con los requisitos
        if (matricula.isEmpty()) {
            Alerts.mostrarError("La matricula es un campo obligatorio");
            return;
        }else if (!Validar.comprobarMatricula(matriculaTF.getText())){
            Alerts.mostrarError("La matricula no cumple los requisitos");
            return;
        }

        //recojo los datos de los campos de texto y los guardo en un nuevo objeto
        String marca = marcaTF.getText();
        String modelo = modeloTF.getText();
        String tipo = cbTipo.getSelectionModel().getSelectedItem();
        Coche coche = new Coche(matricula, marca, modelo, tipo);

        try {
            //alerta para confirmar la accion
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("¿Modificar coche?");
            confirmacion.setContentText("¿Estás seguro?");
            Optional<ButtonType> respuesta = confirmacion.showAndWait();
            if (respuesta.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE){
                return;
            }

            //llamo al metodo
            cocheDAOImp.modificarCoche(cocheseleccionado, coche, session);

            //refresco la tabla
            cargarDatos();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //boton para cambiar el formulario
    @FXML
    void onVerMultas(ActionEvent event) throws IOException {
        if (matriculaTF.getText().isEmpty()){
            Alerts.mostrarError("Debes seleccionar una matricula");
        } else {
            Coche coche = cocheseleccionado;
            //carga el siguiente formulario
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("Multa.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            MultaController multaController = loader.getController();
            multaController.cargarDatos(coche);
            newStage.sizeToScene();
            newStage.show();
            Stage currentStage = (Stage) ventanaGestion.getScene().getWindow();
            currentStage.close();
        }
    }

    //metodo para meter los datos de un coche
    private void cargarCoche(Coche coche) {
        marcaTF.setText(coche.getMarca());
        matriculaTF.setText(coche.getMatricula());
        modeloTF.setText(coche.getModelo());
        cbTipo.setValue(coche.getTipo());
    }

    @FXML
    void seleccionarCoche(MouseEvent event) {
        cocheseleccionado = tablaCoches.getSelectionModel().getSelectedItem();
        cargarCoche(cocheseleccionado);
    }
}