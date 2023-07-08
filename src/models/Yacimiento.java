package models;

import java.util.ArrayList;

public class Yacimiento extends Puerto{
    

    public Yacimiento(Integer identificador, String nombre) {
        super(identificador, nombre);
        super.setTipo(Tipo.YACIMIENTO);
        
        super.setProductosACargar(Carga.CRUDO);
    }
    
    

    


   
}
