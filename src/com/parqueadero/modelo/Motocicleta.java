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
public class Motocicleta extends Vehiculo {
    private static final double TARIFA_MOTOCICLETA = 2500; // Tarifa por hora
    private int cilindraje;

    public Motocicleta(String placa, String marca, String modelo, LocalDateTime horaEntrada, int cilindraje) {
        super(placa, marca, modelo, horaEntrada);
        this.cilindraje = cilindraje;
    }

    // Getter y Setter
    public int getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(int cilindraje) {
        this.cilindraje = cilindraje;
    }

    @Override
    public double calcularCosto(LocalDateTime horaSalida) {
        long minutosParqueo = ChronoUnit.MINUTES.between(this.getHoraEntrada(), horaSalida);
        long horasParqueo = (long) Math.ceil((double) minutosParqueo / 60);
        return horasParqueo * TARIFA_MOTOCICLETA;
    }
}