/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parqueadero.principal;

import com.parqueadero.modelo.*;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 *
 * @author Arturo_Velásquez_G
 */
public class Main {
    public static void main(String[] args) {
        Parqueadero parqueadero = new Parqueadero();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    registrarEntrada(parqueadero, scanner);
                    break;
                case 2:
                    registrarSalida(parqueadero, scanner);
                    break;
                case 3:
                    parqueadero.mostrarVehiculosParqueados();
                    break;
                case 4:
                    System.out.println("Saliendo del sistema. ¡Gracias!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        } while (opcion != 4);

        scanner.close();
    }

    public static void mostrarMenu() {
        System.out.println("\n--- Sistema de Gestión de Parqueadero ---");
        System.out.println("1. Registrar entrada de vehículo");
        System.out.println("2. Registrar salida de vehículo");
        System.out.println("3. Consultar vehículos en el parqueadero");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public static void registrarEntrada(Parqueadero parqueadero, Scanner scanner) {
        System.out.print("Ingrese el tipo de vehículo (1: Automóvil, 2: Motocicleta, 3: Camión): ");
        int tipoVehiculo = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.print("Placa: ");
        String placa = scanner.nextLine();
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        LocalDateTime horaEntrada = LocalDateTime.now();
        Vehiculo nuevoVehiculo = null;

        switch (tipoVehiculo) {
            case 1:
                System.out.print("Tipo de combustible: ");
                String tipoCombustible = scanner.nextLine();
                nuevoVehiculo = new Automovil(placa, marca, modelo, horaEntrada, tipoCombustible);
                break;
            case 2:
                System.out.print("Cilindraje: ");
                int cilindraje = scanner.nextInt();
                nuevoVehiculo = new Motocicleta(placa, marca, modelo, horaEntrada, cilindraje);
                break;
            case 3:
                System.out.print("Capacidad de carga (toneladas): ");
                double capacidadCarga = scanner.nextDouble();
                nuevoVehiculo = new Camion(placa, marca, modelo, horaEntrada, capacidadCarga);
                break;
            default:
                System.out.println("Tipo de vehículo no válido. No se pudo registrar.");
                return;
        }

        if (nuevoVehiculo != null) {
            parqueadero.registrarEntrada(nuevoVehiculo);
        }
    }

    public static void registrarSalida(Parqueadero parqueadero, Scanner scanner) {
        System.out.print("Ingrese la placa del vehículo a salir: ");
        String placa = scanner.nextLine();
        parqueadero.registrarSalida(placa);
    }
}
