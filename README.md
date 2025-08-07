import java.time.LocalDateTime;

public class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private LocalDateTime horaEntrada;

    public Vehiculo(String placa, String marca, String modelo) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.horaEntrada = LocalDateTime.now();
    }

    // Getters y Setters
    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    // ... (Puedes añadir setters si son necesarios)
}
public class Automovil extends Vehiculo {
    private String tipoCombustible;
    private static final double TARIFA_POR_HORA = 5000; // Ejemplo de tarifa

    public Automovil(String placa, String marca, String modelo, String tipoCombustible) {
        super(placa, marca, modelo);
        this.tipoCombustible = tipoCombustible;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }
    
    public static double getTarifaPorHora() {
        return TARIFA_POR_HORA;
    }
}
public class Motocicleta extends Vehiculo {
    private int cilindraje;
    private static final double TARIFA_POR_HORA = 2500; // Ejemplo de tarifa

    public Motocicleta(String placa, String marca, String modelo, int cilindraje) {
        super(placa, marca, modelo);
        this.cilindraje = cilindraje;
    }

    public int getCilindraje() {
        return cilindraje;
    }
    
    public static double getTarifaPorHora() {
        return TARIFA_POR_HORA;
    }
}
public class Camion extends Vehiculo {
    private double capacidadCarga;
    private static final double TARIFA_POR_HORA = 10000; // Ejemplo de tarifa

    public Camion(String placa, String marca, String modelo, double capacidadCarga) {
        super(placa, marca, modelo);
        this.capacidadCarga = capacidadCarga;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }
    
    public static double getTarifaPorHora() {
        return TARIFA_POR_HORA;
    }
}
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Parqueadero {
    private List<Vehiculo> vehiculosParqueados;

    public Parqueadero() {
        this.vehiculosParqueados = new ArrayList<>();
    }

    public void registrarEntrada(Vehiculo vehiculo) {
        vehiculosParqueados.add(vehiculo);
        System.out.println("Vehículo con placa " + vehiculo.getPlaca() + " registrado. Hora de entrada: " + vehiculo.getHoraEntrada());
    }

    public double registrarSalida(String placa) {
        Vehiculo vehiculoSaliente = null;
        for (Vehiculo v : vehiculosParqueados) {
            if (v.getPlaca().equals(placa)) {
                vehiculoSaliente = v;
                break;
            }
        }

        if (vehiculoSaliente == null) {
            System.out.println("Vehículo con placa " + placa + " no encontrado en el parqueadero.");
            return 0.0;
        }

        // Remover el vehículo de la lista
        vehiculosParqueados.remove(vehiculoSaliente);

        // Calcular el costo
        LocalDateTime horaSalida = LocalDateTime.now();
        Duration duracion = Duration.between(vehiculoSaliente.getHoraEntrada(), horaSalida);
        long horasParqueo = duracion.toHours();

        // Considerar fracciones de hora como una hora completa
        if (duracion.toMinutes() % 60 != 0) {
            horasParqueo++;
        }

        double costoTotal = 0.0;
        if (vehiculoSaliente instanceof Automovil) {
            costoTotal = horasParqueo * Automovil.getTarifaPorHora();
        } else if (vehiculoSaliente instanceof Motocicleta) {
            costoTotal = horasParqueo * Motocicleta.getTarifaPorHora();
        } else if (vehiculoSaliente instanceof Camion) {
            costoTotal = horasParqueo * Camion.getTarifaPorHora();
        }

        System.out.println("Vehículo con placa " + placa + " ha salido.");
        System.out.println("Tiempo de estadía: " + horasParqueo + " horas.");
        System.out.println("Costo total: $" + costoTotal);

        return costoTotal;
    }
    
    public void mostrarVehiculosParqueados() {
        if (vehiculosParqueados.isEmpty()) {
            System.out.println("El parqueadero está vacío.");
            return;
        }
        
        System.out.println("--- Vehículos en el parqueadero ---");
        for (Vehiculo v : vehiculosParqueados) {
            System.out.println("Placa: " + v.getPlaca() + ", Marca: " + v.getMarca() + ", Modelo: " + v.getModelo());
        }
    }
}
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Parqueadero parqueadero = new Parqueadero();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Sistema de Gestión de Parqueadero ---");
            System.out.println("1. Ingresar vehículo");
            System.out.println("2. Registrar salida de vehículo");
            System.out.println("3. Consultar vehículos parqueados");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    ingresarVehiculo(parqueadero, scanner);
                    break;
                case 2:
                    registrarSalida(parqueadero, scanner);
                    break;
                case 3:
                    parqueadero.mostrarVehiculosParqueados();
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 4);

        scanner.close();
    }

    private static void ingresarVehiculo(Parqueadero parqueadero, Scanner scanner) {
        System.out.println("\n--- Ingresar Vehículo ---");
        System.out.print("Tipo de vehículo (Automovil, Motocicleta, Camion): ");
        String tipo = scanner.nextLine();

        System.out.print("Placa: ");
        String placa = scanner.nextLine();

        System.out.print("Marca: ");
        String marca = scanner.nextLine();

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        Vehiculo nuevoVehiculo = null;

        switch (tipo.toLowerCase()) {
            case "automovil":
                System.out.print("Tipo de combustible: ");
                String combustible = scanner.nextLine();
                nuevoVehiculo = new Automovil(placa, marca, modelo, combustible);
                break;
            case "motocicleta":
                System.out.print("Cilindraje: ");
                int cilindraje = scanner.nextInt();
                scanner.nextLine();
                nuevoVehiculo = new Motocicleta(placa, marca, modelo, cilindraje);
                break;
            case "camion":
                System.out.print("Capacidad de carga (en toneladas): ");
                double capacidadCarga = scanner.nextDouble();
                scanner.nextLine();
                nuevoVehiculo = new Camion(placa, marca, modelo, capacidadCarga);
                break;
            default:
                System.out.println("Tipo de vehículo no reconocido.");
                return;
        }

        parqueadero.registrarEntrada(nuevoVehiculo);
    }
    
    private static void registrarSalida(Parqueadero parqueadero, Scanner scanner) {
        System.out.println("\n--- Registrar Salida ---");
        System.out.print("Ingrese la placa del vehículo a salir: ");
        String placa = scanner.nextLine();
        parqueadero.registrarSalida(placa);
    }
}
