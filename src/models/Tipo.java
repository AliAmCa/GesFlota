package models;

public enum Tipo {
    YACIMIENTO("Y", "Yacimiento"), DEPOSITO("D", "Depósito"), REFINERIA("R", "Refinería");

    private String id;
    private String nombre;

    private Tipo(String id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    
    
}
