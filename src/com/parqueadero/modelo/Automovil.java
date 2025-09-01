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
public class Automovil extends Vehiculo {
    private static final double TARIFA_AUTOMOVIL = 5000; // Tarifa por hora
    private String tipoCombustible;

    public Automovil(String placa, String marca, String modelo, LocalDateTime horaEntrada, String tipoCombustible) {
        super(placa, marca, modelo, horaEntrada);
        this.tipoCombustible = tipoCombustible;
    }

    // Getter y Setter
    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    @Override
    public double calcularCosto(LocalDateTime horaSalida) {
        long minutosParqueo = ChronoUnit.MINUTES.between(this.getHoraEntrada(), horaSalida);
        long horasParqueo = (long) Math.ceil((double) minutosParqueo / 60);
        return horasParqueo * TARIFA_AUTOMOVIL;
    }
}
