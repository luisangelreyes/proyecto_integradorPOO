import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Incidente {
    private String id;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private Estado estado;
    private Tipo tipo;
    private Prioridad prioridad;
    private Equipo equipo;
    private Usuario reportador;
    private Usuario tecnico;
    private List<String> historial;
    
    public Incidente(String id, String descripcion, Tipo tipo, Prioridad prioridad, 
                    Equipo equipo, Usuario reportador) {
       this.id = id;
       this.descripcion = descripcion;
       this.tipo = tipo;
       this.prioridad = prioridad;
       this.equipo = equipo;
       this.reportador = reportador;
       this.estado = Estado.ABIERTA;
       this.fechaCreacion = LocalDateTime.now();
       this.historial = new ArrayList<>();
       agregarAlHistorial("INCIDENTE CREADO - Estado: " + estado);
    }

    public String getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public Usuario getReportador() {
        return reportador;
    }

    public Usuario getTecnico() {
        return tecnico;
    }

    public List<String> getHistorial() {
        return new ArrayList<>(historial);
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void cambiarEstado(Estado nuevoEstado, String responsable) {
        String cambio = "Estado: " + estado + " → " + nuevoEstado + " por " + responsable;
        agregarAlHistorial(cambio);
        this.estado = nuevoEstado;
    }
    
    public void asignarTecnico(Usuario tecnico) {
        this.tecnico = tecnico;
        if (this.estado == Estado.ABIERTA) {
            cambiarEstado(Estado.EN_PROGRESO, "Sistema");
        }
        agregarAlHistorial("Técnico asignado: " + tecnico.getNombre());
    }
    
    private void agregarAlHistorial(String evento) {
        String timestamp = LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        historial.add(timestamp + " - " + evento);
    }
    
    public String getInfoBasica() {
        return String.format("#%s - %s [%s]", id, descripcion, estado);
    }
    
    public String getInfoCompleta() {
        StringBuilder info = new StringBuilder();
        info.append("=== INCIDENTE #").append(id).append(" ===\n");
        info.append("Descripción: ").append(descripcion).append("\n");
        info.append("Tipo: ").append(tipo).append(" | Prioridad: ").append(prioridad).append("\n");
        info.append("Estado: ").append(estado).append("\n");
        info.append("Equipo: ").append(equipo.getInfo()).append("\n");
        info.append("Reportado por: ").append(reportador.getInfo()).append("\n");
        info.append("Técnico: ").append(tecnico != null ? tecnico.getInfo() : "No asignado").append("\n");
        info.append("Fecha: ").append(fechaCreacion.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))).append("\n");
        
        info.append("\nHistorial:\n");
        for (String evento : historial) {
            info.append("• ").append(evento).append("\n");
        }
        
        return info.toString();
    }
}