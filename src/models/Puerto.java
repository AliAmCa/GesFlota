package models;

import java.util.ArrayList;

public class Puerto {
    private Integer identificador;
    private String nombre;
    private Tipo tipo;
    private ArrayList<Carga> productosACargar;
    private ArrayList<Carga> productosADescargar;
  

    /**
     * 
     * @param identificador identificador del puerto
     * @param nombre
     * @param tipos
     */
    public Puerto(Integer identificador, String nombre) {
        this.identificador = identificador;
        this.nombre = nombre;
        productosACargar = new ArrayList<>();
        productosADescargar= new ArrayList<>();
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((identificador == null) ? 0 : identificador.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Puerto other = (Puerto) obj;
        if (identificador == null) {
            if (other.identificador != null)
                return false;
        } else if (!identificador.equals(other.identificador))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "\t\t"+ identificador + "\t-" + nombre + "\t\tTipo:" + tipo ;
    }
    public void setProductosACargar(Carga carga){
        productosACargar.add(carga);
    }
    public void setProductosADescargar(Carga carga){
        productosADescargar.add(carga);
    }


    public ArrayList<Carga> sePuedeCargar(){
        return productosACargar;
    }
    public ArrayList<Carga> sePuedeDescargar(){
        return productosADescargar;
    }
    public String getProductosACargar(){
        String productos = "";
        for (int i=0; i<productosACargar.size();i++) {
            if(i==0){
                productos = productos + productosACargar.get(i).getCarga();
            }else if(i==productosACargar.size()-1){
                productos = productos +" o "+ productosACargar.get(i).getCarga();
            }else{
                productos = productos + ", "+ productosACargar.get(i).getCarga();
            }
            
            
        }
        return productos;
    }
}
