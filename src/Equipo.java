public class Equipo {
    private String id;
    private String numero;
    private String ubicacion;
    
    public Equipo(String id, String numero, String ubicacion) {
        this.id = id;
        this.numero = numero;
        this.ubicacion = ubicacion;
    }
    
    public String getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public String getUbicacion() {
        return ubicacion;
    }
    
    public String getInfo() {
        return "PC-" + numero + " (" + ubicacion + ")";
    }
}