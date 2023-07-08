package models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Buque {
    private String identificador;
    private String nombre;
    private Puerto puerto;
    private Carga carga;
    private Calendar resumen;
    private Date fechaInicio;
    private ArrayList<Operacion> listaOperaciones;

    public Buque(String identificador, String nombre, Puerto puerto, 
            Date fechaInicio) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.puerto = puerto;
        this.carga = Carga.VACIO;
        
        this.fechaInicio = fechaInicio;
        listaOperaciones =new ArrayList<>();
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Puerto getPuerto() {
        return puerto;
    }

    public void setPuerto(Puerto puerto) {
        this.puerto = puerto;
    }

    public Carga getCarga() {
        return carga;
    }

    public void setCarga(Carga carga) {
        this.carga = carga;
    }

    public Calendar getResumen() {
        return resumen;
    }

    public void setResumen(Calendar resumen) {
        this.resumen = resumen;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String mostrarResumen(int mes, int anio){
        return "Resumen";
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
        Buque other = (Buque) obj;
        if (identificador == null) {
            if (other.identificador != null)
                return false;
        } else if (!identificador.equals(other.identificador))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "\t" + identificador + "\t" + nombre + "\t\t" + puerto.getNombre() + "\t\t\t"
                + getFecha(fechaInicio) + "\t\t\t" + carga.getCarga() ;
    }
    public String getFecha(Date fecha){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fecha);

    }
    
    public void addOperacion(Operacion o){
        listaOperaciones.add(o);
    }

    public boolean comprobarOperacion(Date fecha, Puerto pO, Carga carga,  Puerto pD){
        boolean posible = true;
        if(!comprobarFecha(fecha)){
            posible = false;
        }

        //si el puerto destino no es null (se quiere realizar trayecto)
        if(pD != null){
            if(!comprobarTrayecto(pO, pD, carga)){
                posible = false;
            }
        }

        return posible;
    }

    private boolean comprobarFecha(Date fecha){
        boolean posible = true;
        for (Operacion operacion : listaOperaciones) {
            Date fechaInicio = operacion.getFechaComienzo();
            Date fechaFin = operacion.getFechaFin();

                if (fecha.after(fechaInicio) && fecha.before(fechaFin)){
                    posible = false;
                }
                if (fecha.before(fechaInicio) && fecha.before(fechaFin)){
                    posible = false;
                }
                if (fecha.equals(fechaInicio) || fecha.equals(fechaFin)){
                    posible = false;
                }

        }

        return posible;
    }

    private boolean comprobarTrayecto(Puerto pOrigen, Puerto pDestino, Carga carga){
        boolean posible = true;
        //comprobar que origen y destino no sea el mismo
        if (pOrigen.getIdentificador()== pDestino.getIdentificador()){
            posible = false;
        }
        //comprobar que el trayecto sea lógico
        
            // si el origen y destino son del mismo tipo
            if (pOrigen.getTipo() == pDestino.getTipo()){ posible = false;}

            //si origen es yacimiento, destino solo puede ser refineria, excepto si vacio
            if (pOrigen.getTipo()== Tipo.YACIMIENTO){
                if(carga!= Carga.VACIO && pDestino.getTipo()!= Tipo.REFINERIA){
                    posible = false;

                }
            }

            //si origen es refinería, destino solo puede ser deposito, excepto si vacio
            if (pOrigen.getTipo()== Tipo.REFINERIA){
                if(carga!= Carga.VACIO && pDestino.getTipo()!= Tipo.DEPOSITO){
                    posible = false;

                }
            }

            //si origen es depósito, tiene que esta vacio
            if(pOrigen.getTipo()== Tipo.DEPOSITO && carga != Carga.VACIO){
                posible= false;

            }
        return posible;
    }

    public ArrayList<Operacion> getOperacionesMes(Date fecha1, Date fecha2){
        
        ArrayList<Operacion> lista = new ArrayList<>();
        boolean añadir = false;
        for (Operacion op : listaOperaciones) {
            if (op.getFechaComienzo() == fecha1 || op.getFechaComienzo()==fecha2){
                añadir = true;
            }
            if (op.getFechaFin() == fecha1 || op.getFechaFin() == fecha2){
                añadir = true;
            }
            if(op.getFechaComienzo().after(fecha1) && op.getFechaComienzo().before(fecha2)){
                añadir = true;
            }
            if (añadir){
                lista.add(op);
            }
        
        }
        return lista;
    }
}
