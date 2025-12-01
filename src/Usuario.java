
public class Usuario {
    private String id;
    private String nombre;
    private String correo;
    private String identificacion;
    private String tipo;
    
    public Usuario(String id, String nombre, String correo, String identificacion, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.identificacion = identificacion;
        this.tipo = tipo;
    }
    
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getTipo() {
        return tipo;
    }
    
    public String getInfo() {
        return nombre + " (" + tipo + ") - " + identificacion;
    }
}