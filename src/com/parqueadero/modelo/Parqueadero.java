/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parqueadero.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Arturo_Velásquez_G
 */
public class Parqueadero {
    private final List<Vehiculo> vehiculos;

    public Parqueadero() {
        this.vehiculos = new ArrayList<>();
    }

    public void registrarEntrada(Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
        System.out.println("Vehículo con placa " + vehiculo.getPlaca() + " ha ingresado al parqueadero.");
    }

    public void registrarSalida(String placa) {
        Iterator<Vehiculo> iterator = vehiculos.iterator();
        while (iterator.hasNext()) {
            Vehiculo vehiculo = iterator.next();
            if (vehiculo.getPlaca().equalsIgnoreCase(placa)) {
                LocalDateTime horaSalida = LocalDateTime.now();
                double costo = vehiculo.calcularCosto(horaSalida);
                iterator.remove();
                System.out.println("\n--- Salida de Vehículo ---");
                System.out.println("Placa: " + vehiculo.getPlaca());
                System.out.println("Hora de entrada: " + vehiculo.getHoraEntrada());
                System.out.println("Hora de salida: " + horaSalida);
                System.out.printf("Costo total a pagar: $%,.2f\n", costo);
                return;
            }
        }
        System.out.println("Vehículo con placa " + placa + " no se encuentra en el parqueadero.");
    }

    public void mostrarVehiculosParqueados() {
        if (vehiculos.isEmpty()) {
            System.out.println("El parqueadero está vacío.");
            return;
        }

        System.out.println("\n--- Vehículos en el parqueadero ---");
        for (Vehiculo v : vehiculos) {
            String tipo = "";
            if (v instanceof Automovil) {
                tipo = "Automóvil";
            } else if (v instanceof Motocicleta) {
                tipo = "Motocicleta";
            } else if (v instanceof Camion) {
                tipo = "Camión";
            }
            System.out.println("Placa: " + v.getPlaca() + " | Tipo: " + tipo + " | Marca: " + v.getMarca() + " | Hora de entrada: " + v.getHoraEntrada());
        }
    }
}