package models;

import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class Operacion {
    private Buque buque;
    private Date fechaComienzo, fechaFin;
    private Puerto puertoSalida, puertoDestino;
    private Carga carga;
    private int duracionCarga, duracionTraslado, duracionDescarga;

    public Operacion(Buque buque, Date fechaComienzo, Puerto puertoSalida, Puerto puertoDestino, Carga carga,
            int duracionCarga, int duracionTraslado, int duracionDescarga) {
        this.buque = buque;
        this.fechaComienzo = fechaComienzo;
        this.puertoSalida = puertoSalida;
        this.puertoDestino = puertoDestino;
        this.carga = carga;
        this.duracionCarga = duracionCarga;
        this.duracionTraslado = duracionTraslado;
        this.duracionDescarga = duracionDescarga;
        this.fechaFin = setFechafin(fechaComienzo);
    }

    private Date setFechafin(Date fechaInicio){
        Calendar calendar = Calendar.getInstance();
        int dias = getTotalDias();
        calendar.setTime(fechaInicio);
        calendar.add(Calendar.DAY_OF_YEAR,dias);
        return   calendar.getTime();
    }
    
    public Buque getBuque() {
        return buque;
    }

    public String getStringFechaComienzo() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fechaComienzo);

    }

    public Date getFechaComienzo() {
        return fechaComienzo;
    }

    public Puerto getPuertoSalida() {
        return puertoSalida;
    }

    public Puerto getPuertoDestino() {
        return puertoDestino;
    }

    public Carga getCarga() {
        return carga;
    }

    public int getDuracionCarga() {
        return duracionCarga;
    }

    public int getDuracionTraslado() {
        return duracionTraslado;
    }

    public int getDuracionDescarga() {
        return duracionDescarga;
    }

    public void resumenOperacion() {
        System.out.println("");
        System.out.println("\t\t\tResumen de la operación:");
        System.out.println("\t\tFecha comienzo: " + getStringFechaComienzo());
        System.out.println("\t\tPuerto Origen: " + getPuertoSalida().getNombre());
        System.out.println("\t\tTipo de carga: " + getCarga().getCarga());
        if (carga != Carga.VACIO) {
            System.out.println("\t\tDuración carga: " + getDuracionCarga() + " días");
        }
        if (puertoDestino != null) {
            System.out.println("\t\tPuerto destino: " + getPuertoDestino().getNombre());
            System.out.println("\t\tDuración del traslado: " + getDuracionTraslado()+ " días");
        }
        if (duracionDescarga > 0) {
            System.out.println("\t\tDuración de la descarga: " + getDuracionDescarga()+ " días");
        }
        System.out.println("");
    }

    public int getTotalDias() {
        return duracionCarga + duracionTraslado + duracionDescarga;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    //Comprobar si en una fecha dada tiene alguna operacion
    public boolean hayOperacion(Date fecha){
        if (fechaComienzo.equals(fecha)){ return true;}
        else if(fechaComienzo.before(fecha) && fechaFin.after(fecha)){return true;}
        else if(fechaComienzo.before(fecha) && fechaFin.equals(fecha)){return true;}
        else{
            return false;
        }
    }

    public String getStringOperacion(Date fecha, int posicion){
        if (fechaComienzo.equals(fecha)){
            if (duracionCarga>0){
                return "C ";
            }else if (duracionTraslado>0){
                return "T"+posicion;
            }else {
                return "D ";
            }
        }else if (fechaComienzo.before(fecha)){
            // contar los dias desde que comenzo la operacion hasta la fecha
            int numDias = (int)ChronoUnit.DAYS.between(fechaComienzo.toInstant(), fecha.toInstant());
            if (duracionCarga>numDias ){
                return "C ";
            }else if((duracionCarga+duracionTraslado)>numDias){
                return "T"+posicion;
            } else if((duracionCarga+duracionTraslado+duracionDescarga)>numDias){
                return "D ";
            }else{return "";}

        }else{
            return "";
        }
    }
}
