/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parqueadero.modelo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Arturo_Velásquez_G
 */
public class Camion extends Vehiculo {
    private static final double TARIFA_CAMION = 10000; // Tarifa por hora
    private double capacidadCarga;

    public Camion(String placa, String marca, String modelo, LocalDateTime horaEntrada, double capacidadCarga) {
        super(placa, marca, modelo, horaEntrada);
        this.capacidadCarga = capacidadCarga;
    }

    // Getter y Setter
    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    @Override
    public double calcularCosto(LocalDateTime horaSalida) {
        long minutosParqueo = ChronoUnit.MINUTES.between(this.getHoraEntrada(), horaSalida);
        long horasParqueo = (long) Math.ceil((double) minutosParqueo / 60);
        return horasParqueo * TARIFA_CAMION;
    }
}