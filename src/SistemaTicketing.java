import java.util.ArrayList;
import java.util.List;

public class SistemaTicketing {
    private List<Usuario> usuarios;
    private List<Equipo> equipos;
    private List<Incidente> incidentes;
    private int contadorIncidentes;
    
    public SistemaTicketing() {
        this.usuarios = new ArrayList<>();
        this.equipos = new ArrayList<>();
        this.incidentes = new ArrayList<>();
        this.contadorIncidentes = 1;
        cargarDatosIniciales();
    }
    
    private void cargarDatosIniciales() {
        usuarios.add(new Usuario("U1", "Luis Angel Reyes", "zs24022197@estudiantes.uv.mx", "S24022197", "ESTUDIANTE"));
        usuarios.add(new Usuario("U2", "Rafarel Beltran", "z23017374@estudiantesuv.mx", "S23017374", "ESTUDIANTE"));
        usuarios.add(new Usuario("U3", "José Antonio Vergara", "jvergara@uv.mx", "MP12345", "MAESTRO"));
        usuarios.add(new Usuario("T1", "Irving Lopez", "Ilopez@uv.mx", "TP67890", "TECNICO"));
        usuarios.add(new Usuario("T2", "Gerardo Díaz", "gdiaz@uv.mx", "TP67891", "TECNICO"));
        
        equipos.add(new Equipo("E1", "01", "Laboratorio LIS"));
        equipos.add(new Equipo("E2", "02", "Laboratorio LIS"));
        equipos.add(new Equipo("E3", "03", "Laboratorio LIS"));
    }
    
    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public List<Equipo> getEquipos() {
        return new ArrayList<>(equipos);
    }

    public List<Incidente> getIncidentes() {
        return new ArrayList<>(incidentes);
    }
    
    public List<Usuario> getTecnicos() {
        List<Usuario> tecnicos = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if ("TECNICO".equals(usuario.getTipo())) {
                tecnicos.add(usuario);
            }
        }
        return tecnicos;
    }

    public Incidente reportarIncidente(String descripcion, Tipo tipo, Prioridad prioridad, 
                                      String equipoId, String usuarioId) {
        Equipo equipo = buscarEquipo(equipoId);
        Usuario usuario = buscarUsuario(usuarioId);
        
        if (equipo != null && usuario != null) {
            String id = "INC" + contadorIncidentes++;
            Incidente incidente = new Incidente(id, descripcion, tipo, prioridad, equipo, usuario);
            incidentes.add(incidente);
            return incidente;
        }
        return null;
    }
    
    public boolean asignarTecnico(String incidenteId, String tecnicoId) {
        Incidente incidente = buscarIncidente(incidenteId);
        Usuario tecnico = buscarUsuario(tecnicoId);
        
        if (incidente != null && tecnico != null && "TECNICO".equals(tecnico.getTipo())) {
            incidente.asignarTecnico(tecnico);
            return true;
        }
        return false;
    }
    
    public boolean cambiarEstado(String incidenteId, Estado nuevoEstado, String responsable) {
        Incidente incidente = buscarIncidente(incidenteId);
        if (incidente != null) {
            incidente.cambiarEstado(nuevoEstado, responsable);
            return true;
        }
        return false;
    }
    
    public List<Incidente> filtrarPorEstado(Estado estado) {
        List<Incidente> resultado = new ArrayList<>();
        for (Incidente inc : incidentes) {
            if (inc.getEstado() == estado) {
                resultado.add(inc);
            }
        }
        return resultado;
    }
    
    public List<Incidente> filtrarPorTipo(Tipo tipo) {
        List<Incidente> resultado = new ArrayList<>();
        for (Incidente inc : incidentes) {
            if (inc.getTipo() == tipo) {
                resultado.add(inc);
            }
        }
        return resultado;
    }
    
    public String generarReporte() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("=== REPORTE DEL SISTEMA ===\n\n");
        reporte.append("Total de incidentes: ").append(incidentes.size()).append("\n\n");
        
        reporte.append("Por estado:\n");
        for (Estado estado : Estado.values()) {
            int count = filtrarPorEstado(estado).size();
            reporte.append("- ").append(estado).append(": ").append(count).append("\n");
        }
        
        reporte.append("\nPor tipo:\n");
        for (Tipo tipo : Tipo.values()) {
            int count = filtrarPorTipo(tipo).size();
            reporte.append("- ").append(tipo).append(": ").append(count).append("\n");
        }
        
        return reporte.toString();
    }
    
    public Usuario buscarUsuario(String id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }
    
    public Equipo buscarEquipo(String id) {
        for (Equipo equipo : equipos) {
            if (equipo.getId().equals(id)) {
                return equipo;
            }
        }
        return null;
    }
    
    public Incidente buscarIncidente(String id) {
        for (Incidente incidente : incidentes) {
            if (incidente.getId().equals(id)) {
                return incidente;
            }
        }
        return null;
    }
}