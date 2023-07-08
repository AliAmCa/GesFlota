package models;
import java.util.ArrayList;
import java.util.Date;

public class Calendario {
    private int diasCarga, diasTraslados,diasDescargas, diasParada;

    public Calendario() {
        diasCarga=0;
        diasDescargas=0;
        diasParada= 0;
        diasTraslados=0;

    }

    private String getMes(int mes){
        String s_mes = "";
        switch (mes) {

            case 1:  s_mes =("\t\t\tENERO"); break;
            case 2:  s_mes =("\t\t\tFEBRERO"); break;
            case 3:  s_mes =("\t\t\tMARZO"); break;
            case 4: s_mes =("\t\t\tABRIL"); break;
            case 5:  s_mes =("\t\t\tMAYO"); break;
            case 6: s_mes =("\t\t\tJUNIO"); break;
            case 7:  s_mes =("\t\t\tJULIO"); break;
            case 8: s_mes =( "\t\t\tAGOSTO"); break;
            case 9: s_mes =( "\t\t\tSEPTIEMBRE"); break;
            case 10:s_mes =( "\t\t\tOCTUBRE"); break;
            case 11: s_mes =("\t\t\tNOVIEMBRE"); break;
            case 12: s_mes =("\t\t\tDICIEMBRE"); break;
            default:
            ;
        }
        return s_mes;
    }

    /*Calcular año bisiesto*/

    private boolean esBisiesto(int anio){
        if(anio%4==0){
        if (anio%100 !=0){
            return true;
        }else{
            if (anio%400==0){
            return true;
            }else{
            return false;
            }
        }
        }else{
        return false;
        }
    
    
    }
    
    /*Calcular los dias del mes*/

    private int calcularDias(int mes, int anio){

        if (mes==1 || mes==3 || mes==5 || mes==7 || mes==8 || mes==10 ||mes==12){
        return 31;
        }else if(mes==2){
        if (esBisiesto(anio)==true){
            return 29;
        }else{
        return 28;
        }
        }else{
        return 30;
        }
    
    }
    private int zeller(int anio, int mes, int dia){
        /*Dom 0, lun 1, mar 2, mierc 3,juev 4, vier 5, sabad 6*/
        int a = (14-mes)/12;
        int y = anio-a;
        int m = mes + 12 * a -2;
        int d = (dia + y + y/4 -y/100 + y/400 + (31*m)/12)%7;
      
        return d;
    }
      
    public Date getDiaFinMes(int mes,int anio){
        int dias = calcularDias(mes, anio);
        return new Date(anio, mes, dias);
    }

      /*Mostrar el calendario*/

    private void imprimirDias(ArrayList<Operacion> operaciones, int mes, int anio){
        int dias_mes = calcularDias(mes, anio);
        int x= zeller(anio,mes,1);
        int con= x+7;
        int con2 =x;
        boolean hayOperacion;
        

        switch(x){ //Espacios en blanco a imprimir dependiendo del dia de la semana que sea el dia 1
            case 1: System.out.print("\t\t\t  ") ;break;
            case 2: System.out.print("\t\t\t      "); break;
            case 3: System.out.print("\t\t\t          "); break;
            case 4: System.out.print("\t\t\t              "); break;
            case 5: System.out.print("\t\t\t                  "); break;
            case 6: System.out.print("\t\t\t                     | "); break;
            case 0: System.out.print("\t\t\t                     |     "); break;
        }

        for(int i=1; i<=dias_mes;i++){//Bucle para imprimir los dias de la semana
            hayOperacion = false;
            String simbolo ="";
            
            for(int j=0;j<operaciones.size(); j++){//Bucle para recorrer las operaciones
                if (operaciones.get(j).hayOperacion(new Date(anio,mes, i))){
                    hayOperacion= true;
                    simbolo = operaciones.get(j).getStringOperacion(new Date(anio,mes, i), j+1);
                    if(simbolo.equals("C ")){
                        diasCarga++;
                    }else if (simbolo.equals("D ")){
                        diasDescargas++;
                    }else if (simbolo.equals("")){
                        hayOperacion= false;
                    }else{
                        diasTraslados++;
                    }
                }
            }

            if (hayOperacion && simbolo!= ""){
                System.out.print(simbolo);
                
            }else{ //si no hay operacion, se imprime el numero de dia
                if (i<10){//Si el dia es menor a 10 se imprime un 0
                    System.out.print("0");
                }
                System.out.print(i);
                diasParada++;
            }
            if(con2==5){//Para imprimir | entre V y S
                System.out.print(" | ");
               }else {
                System.out.print("  ");
               }
               con2++;
              if(con%7==0){//Para saltar de linea tras el Domingo
                System.out.print("\n\t\t\t  ");
                con2=1;
              }
              con++;
           

        

        }
    }

    public void imprimirCalendario(ArrayList<Operacion> operaciones, int mes, int anio){
        System.out.println("");

        //imprimir mes y año
        System.out.println(getMes(mes)+ "\t\t"+ anio);
        //imprimir __
        System.out.print("\t\t\t");
        for(int i=1; i<=29; i++){

            System.out.print("_");

        }

        System.out.print("\n");

        /*Imprimir dias semana*/
        System.out.println("\t\t\t  LU  MA  MI  JU  VI | SA  DO");
        
        /*Imprimir dias*/
        imprimirDias(operaciones,mes,anio);

        System.out.println("");
        System.out.println("\nTiempo de cargas (C): "+ diasCarga +" días");
        System.out.println("Tiempo de traslados (Tn): "+ diasTraslados +" días");

        for (int i=0; i < operaciones.size(); i++) {
            System.out.println("Traslado T" +(i+1)+ ": desde "+ operaciones.get(i).getPuertoSalida().getNombre() +" a "
                + operaciones.get(i).getPuertoDestino().getNombre() + " con " + operaciones.get(i).getCarga().getCarga());
  
        }
        System.out.println("Tiempo de descargas (D): "+ diasDescargas +" días");
        System.out.println("Tiempo de parada: "+ diasParada +" días");

    }

}