package models;

public class Deposito extends Puerto {

    

    public Deposito(Integer identificador, String nombre) {
        super(identificador, nombre);
        super.setTipo(Tipo.DEPOSITO);
        super.setProductosACargar(Carga.FUEL);
        super.setProductosACargar(Carga.GASOIL);
        super.setProductosACargar(Carga.GASOLINA);
        super.setProductosADescargar(Carga.FUEL);
        super.setProductosADescargar(Carga.GASOIL);
        super.setProductosADescargar(Carga.GASOLINA);

    }
    
}
