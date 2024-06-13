package org.example;

public class MainTests {


    public static void main(String[] args) {

        GestorCliente gestorCliente = new GestorCliente();
        GestorProducto gestorProducto = new GestorProducto();

        gestorProducto.create("Domo antivandalico", 120, 48);
        gestorProducto.create("Bullet IR", 107, 80);
        gestorProducto.create("PTZ autotracking", 1120, 8);
        gestorProducto.create("NVR 8 canales", 250, 10);
        gestorProducto.create("Fuente multiple x10", 80, 100);
        gestorProducto.create("Monitor 32", 145, 19);
        gestorCliente.create("Juancho", "Pineda", 22697384, 22);
        gestorCliente.create("Pepa", "Armada", 28739377, 97);
    }
}
