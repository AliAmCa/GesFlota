package models;

public class Refineria extends Puerto{

   

    public Refineria(Integer identificador, String nombre) {
        super(identificador, nombre);
        super.setTipo(Tipo.REFINERIA);
        super.setProductosACargar(Carga.FUEL);
        super.setProductosACargar(Carga.GASOIL);
        super.setProductosACargar(Carga.GASOLINA);
        super.setProductosADescargar(Carga.CRUDO);

    }
    
    
}
