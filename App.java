import java.util.Scanner;

public class App {
    private static SistemaTicketing sistema = new SistemaTicketing();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("SISTEMA DE REPORTE LAB LIS: ");
        
        boolean ejecutando = true;
        while (ejecutando) {
            mostrarMenu();
            int opcion = leerNumero();
            
            switch (opcion) {
                case 1 -> reportarIncidencia();
                case 2 -> verIncidentes();
                case 3 -> asignarTecnico();
                case 4 -> cambiarEstado();
                case 5 -> verReporte();
                case 6 -> verDetalles();
                case 0 -> ejecutando = false;
                default -> System.out.println("Opción no válida");
            }
        }
        
        System.out.println("Nos olemos luego");
        scanner.close();
    }
    
    private static void mostrarMenu() {
        System.out.println("\nMENÚ DE OPCIONES: ");
        System.out.println("1. Reportar incidencia");
        System.out.println("2. Ver todas las incidencias");
        System.out.println("3. Asignar técnico");
        System.out.println("4. Cambiar estado");
        System.out.println("5. Ver reporte");
        System.out.println("6. Ver detalles de incidencia");
        System.out.println("0. Salir");
        System.out.print("Seleccione: ");
    }
    
    private static int leerNumero() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    private static void reportarIncidencia() {
        System.out.println("\n--- REPORTAR INCIDENCIA ---");

        System.out.println("Seleccione usuario:");
        System.out.println("1. Luis Angel Reyes (Estudiante)");
        System.out.println("2. Rafarel Beltran (Estudiante)");
        System.out.println("3. José Antonio Vergara (Maestro)");
        System.out.print("Opción: ");
        int userOp = leerNumero();
        String usuarioId = switch (userOp) {
            case 1 -> "U1";
            case 2 -> "U2";
            case 3 -> "U3";
            default -> "U1";
        };

        System.out.println("\nSeleccione equipo:");
        System.out.println("1. PC-01 (Lab LIS)");
        System.out.println("2. PC-02 (Lab LIS)");
        System.out.println("3. PC-03 (Lab LIS)");
        System.out.print("Opción: ");
        int eqOp = leerNumero();
        String equipoId = switch (eqOp) {
            case 1 -> "E1";
            case 2 -> "E2";
            case 3 -> "E3";
            default -> "E1";
        };
        
        System.out.print("\nDescripción del problema: ");
        String descripcion = scanner.nextLine();
        
        System.out.println("\nTipo de problema:");
        System.out.println("1. Hardware");
        System.out.println("2. Software");
        System.out.println("3. Red");
        System.out.print("Opción: ");
        int tipoOp = leerNumero();
        Tipo tipo = switch (tipoOp) {
            case 1 -> Tipo.HARDWARE;
            case 2 -> Tipo.SOFTWARE;
            case 3 -> Tipo.RED;
            default -> Tipo.HARDWARE;
        };
        
        System.out.println("\nPrioridad:");
        System.out.println("1. Alta");
        System.out.println("2. Media");
        System.out.println("3. Baja");
        System.out.print("Opción: ");
        int priOp = leerNumero();
        Prioridad prioridad = switch (priOp) {
            case 1 -> Prioridad.ALTA;
            case 2 -> Prioridad.MEDIA;
            case 3 -> Prioridad.BAJA;
            default -> Prioridad.MEDIA;
        };
        
        var incidente = sistema.reportarIncidente(descripcion, tipo, prioridad, equipoId, usuarioId);
        
        if (incidente != null) {
            System.out.println("\nIncidencia #" + incidente.getId() + " creada");
        } else {
            System.out.println("\nError al crear incidencia");
        }
    }
    
    private static void verIncidentes() {
        System.out.println("\n--- TODAS LAS INCIDENCIAS ---");
        var incidentes = sistema.getIncidentes();
        
        if (incidentes.isEmpty()) {
            System.out.println("No hay incidencias");
            return;
        }
        
        for (var inc : incidentes) {
            System.out.println("• " + inc.getInfoBasica());
        }
    }
    
    private static void asignarTecnico() {
        System.out.println("\n--- ASIGNAR TÉCNICO ---");
        
        System.out.print("ID de incidencia: ");
        String incId = scanner.nextLine();
        
        System.out.println("\nTécnicos disponibles:");
        var tecnicos = sistema.getTecnicos();
        for (int i = 0; i < tecnicos.size(); i++) {
            System.out.println((i + 1) + ". " + tecnicos.get(i).getInfo());
        }
        
        System.out.print("Seleccione técnico: ");
        int tecOp = leerNumero();
        
        if (tecOp >= 1 && tecOp <= tecnicos.size()) {
            String tecnicoId = tecnicos.get(tecOp - 1).getId();
            if (sistema.asignarTecnico(incId, tecnicoId)) {
                System.out.println("Técnico asignado");
            } else {
                System.out.println("Error al asignar");
            }
        } else {
            System.out.println("Selección inválida");
        }
    }
    
    private static void cambiarEstado() {
        System.out.println("\n--- CAMBIAR ESTADO ---");
        
        System.out.print("ID de incidencia: ");
        String incId = scanner.nextLine();
        
        System.out.println("\nNuevo estado:");
        System.out.println("1. Abierta");
        System.out.println("2. En progreso");
        System.out.println("3. Resuelta");
        System.out.println("4. Cerrada");
        System.out.print("Opción: ");
        int estOp = leerNumero();
        
        Estado nuevoEstado = switch (estOp) {
            case 1 -> Estado.ABIERTA;
            case 2 -> Estado.EN_PROGRESO;
            case 3 -> Estado.RESUELTA;
            case 4 -> Estado.CERRADA;
            default -> null;
        };
        
        if (nuevoEstado != null) {
            System.out.print("Nombre de quien cambia el estado: ");
            String responsable = scanner.nextLine();
            
            if (sistema.cambiarEstado(incId, nuevoEstado, responsable)) {
                System.out.println("Estado cambiado a " + nuevoEstado);
            } else {
                System.out.println("Error al cambiar estado");
            }
        } else {
            System.out.println(" Estado inválido");
        }
    }

    private static void verReporte() {
        System.out.println("\n--- REPORTE DEL SISTEMA ---");
        System.out.println(sistema.generarReporte());
    }
    

    private static void verDetalles() {
        System.out.println("\n--- DETALLES DE INCIDENCIA ---");
        
        System.out.print("ID de incidencia: ");
        String incId = scanner.nextLine();
        
        var incidente = sistema.buscarIncidente(incId);
        if (incidente != null) {
            System.out.println("\n" + incidente.getInfoCompleta());
        } else {
            System.out.println("Incidencia no encontrada");
        }
    }
}