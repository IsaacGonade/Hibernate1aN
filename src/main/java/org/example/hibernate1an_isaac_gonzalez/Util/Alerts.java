package org.example.hibernate1an_isaac_gonzalez.Util;

import javafx.scene.control.Alert;
//CLASE PARA MOSTRAR ALERTAS
public class Alerts {
    public static void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setContentText(mensaje);
        alerta.show();
    }
}
