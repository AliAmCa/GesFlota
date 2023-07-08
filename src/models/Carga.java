package models;

public enum Carga {
       VACIO("v","vacio"), CRUDO("c", "crudo"),
        FUEL("f", "fuel"), GASOIL("gl", "gasoil"),GASOLINA("ga","gasolina");
    private String id;
    private String carga;
    
    private Carga(String id, String carga) {
        this.id = id;
        this.carga = carga;
    }

    public String getId() {
        return id;
    }

    public String getCarga() {
        return carga;
    }

    
}
