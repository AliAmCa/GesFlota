package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Date;

public class GesFlota {
    private Puerto p1, p2, p3, p4, p5, p6;
    private Buque b1, b2, b3, b4, b5;
    private Operacion o1, o2;
    private ArrayList<Buque> buques;
    private ArrayList<Puerto> listaPuertos;
    private HashMap<Integer, Puerto> puertos;
    private Scanner sc = new Scanner(System.in);

    public GesFlota() {
        p1 = new Yacimiento(1, "Honduras");
        p2 = new Refineria(2, "Isla de Gracia");
        p3 = new Deposito(3, "Pozo Grande");
        p4 = new Yacimiento(4, "Maldivas");
        p5 = new Refineria(5, "Piedras Altas");
        p6 = new Deposito(6, "Vegas Altas");
        puertos = new HashMap<Integer, Puerto>();
        puertos.put(1, p1);
        puertos.put(2, p2);
        puertos.put(3, p3);
        puertos.put(4, p4);
        puertos.put(5, p5);
        puertos.put(6, p6);
        listaPuertos = new ArrayList<>();
        listaPuertos.add(p1);
        listaPuertos.add(p2);
        listaPuertos.add(p3);
        listaPuertos.add(p4);
        listaPuertos.add(p5);
        listaPuertos.add(p6);

        b1 = new Buque("A", "Alondra", p1, new Date(2023, 5, 7));
        b2 = new Buque("B", "Buendía", p2, new Date(2023, 5, 7));
        b3 = new Buque("C", "Carcamusa", p3, new Date(2023, 5, 7));
        b4 = new Buque("D", "Duende", p1, new Date(2023, 5, 7));
        b5 = new Buque("E", "Espigón", p1, new Date(2023, 5, 7));
        buques = new ArrayList<>();
        buques.add(b1);
        buques.add(b2);
        buques.add(b3);
        buques.add(b4);
        buques.add(b5);
        o1 = new Operacion(b1, new Date(2023, 2, 2), p1, p2, Carga.CRUDO, 1, 5, 1);
        o2 = new Operacion(b1, new Date(2023, 2, 12), p2, p3, Carga.FUEL, 1, 3, 1);
        b1.addOperacion(o1);
        b1.addOperacion(o2);
        printInicio();
    }

    public GesFlota(ArrayList<Buque> buques, HashMap<Integer, Puerto> puertos) {
        this.buques = buques;
        this.puertos = puertos;
    }

    public void printInicio() {
        System.out.println("\n\n\tGesFlota : Gestión de Movimientos de una Flota");
        System.out.println("");
        System.out.println("\t\tEditar Puerto\t\t\t(Pulsar P)");
        System.out.println("\t\tEditar Buque\t\t\t(Pulsar B)");
        System.out.println("\t\tEstado Buques\t\t\t(Pulsar E)");
        System.out.println("\t\tOperar Buque\t\t\t(Pulsar O)");
        System.out.println("\t\tResumen Mensual Buque\t\t(Pulsar R)");
        System.out.println("\t\tSalir\t\t\t\t(Pulsar S)");
        System.out.print("\tTeclear una opción válida (P|B|E|O|R|S): ");
        String opcion = sc.nextLine();

        switch (opcion) {
            case "P":
                printEdPuerto();
                break;
            case "M": // Hago esta opcion para comprobar modificaciones de puertos
                mostrarListaPuertos();
                printInicio();
                break;
            case "B":
                printEdBuque();
                break;
            case "E":
                printEstadoBuques();
                break;
            case "O":
                printOpBuque();
                break;
            case "R":
                printResMensual();
                break;
            case "S":
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida");
                printInicio();
                break;

        }
    }

    public void printEdPuerto() {
        System.out.println("");
        System.out.println("Editar Puerto:");
        System.out.println("");
        System.out.print("\t\tIdentificador (número entre 1 y 10): ");
        int id = sc.nextInt();
        // Comprobar que es un id correcto
        if (id < 1 || id > 10) {
            System.out.println("\t\tIdentificador no válido ");
            printEdPuerto();
        }

        sc.nextLine();
        boolean nombre_correcto = false;
        String nombre = "";
        while (!nombre_correcto) {
            System.out.print("\t\tNombre (entre 1 y 20 caracteres): ");
            nombre = sc.nextLine();
            if (nombre.length() <= 20 && nombre.length() > 0) {
                nombre_correcto = true;
            } else {
                System.out.println("\t\tNombre no válido ");
            }
        }
        Tipo t = Tipo.YACIMIENTO;
        boolean tipo_establecido = false;
        while (tipo_establecido == false) {
            System.out.print("\t\tTipo (Y-Yacimiento, R-Refinería, D-Depósito): ");
            String id_tipo = sc.nextLine();
            switch (id_tipo) {
                case "Y":
                    t = Tipo.YACIMIENTO;
                    tipo_establecido = true;
                    break;
                case "D":
                    t = Tipo.DEPOSITO;
                    tipo_establecido = true;
                    break;
                case "R":
                    t = Tipo.REFINERIA;
                    tipo_establecido = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }

        boolean valido = false;
        System.out.print("IMPORTANTE: Esta opción borra los datos anteriores.");
        while (valido != true) {
            System.out.print(" Son correctos los nuevos datos (S/N): ");
            String opcion = sc.nextLine();

            if (opcion.equals("S")) {
                valido = true;

                // cambiar datos
                boolean encontrado = false;
                if (puertos.containsKey(id)) {
                    puertos.get(id).setNombre(nombre);
                    puertos.get(id).setTipo(t);
                    System.out.println("Datos de puerto modificados");
                    printInicio();
                } else {
                    System.out.println("Identificador no existe ");
                    printEdPuerto();
                }
                /*
                 * for (int i=0; i< puertos.size(); i++){
                 * if (puertos.get(i).getIdentificador()== id){
                 * puertos.get(i).setNombre(nombre);
                 * puertos.get(i).setTipo(t);
                 * encontrado = true;
                 * System.out.println("Datos de puerto modificados");
                 * printInicio();
                 * 
                 * }
                 * }
                 * if (!encontrado){
                 * System.out.println("Identificador no existe ");
                 * printEdPuerto();
                 * }
                 */

            } else if (opcion.equals("N")) {
                valido = true;
                System.out.println("Datos de puerto no modificados");

                printInicio();
            } else {
                System.out.println("Opción no válida ");
            }
        }

    }

    public void printEdBuque() {

        System.out.println("");
        System.out.println("Editar Buque:");
        System.out.println("");

        // Identificador
        System.out.print("\t\tIdentificador (letra entre A y E): ");
        String id = sc.nextLine();
        // Comprobar que es un id correcto
        if (!id.equals("A") && !id.contains("B") && !id.contains("C") && !id.contains("D") && !id.contains("E")) {
            System.out.println("\t\tIdentificador no válido ");
            printEdBuque();
        }

        // Nombre
        boolean nombre_correcto = false;
        String nombre = "";
        while (!nombre_correcto) {
            System.out.print("\t\tNombre (entre 1 y 20 caracteres): ");
            nombre = sc.nextLine();
            if (nombre.length() <= 20 && nombre.length() > 0) {
                nombre_correcto = true;
            } else {
                System.out.println("Nombre no válido ");
            }
        }

        // Fecha
        System.out.print("\t\tFecha de inicio: Día: ");
        int dia = sc.nextInt();
        sc.nextLine();
        if (dia < 1 || dia > 31) {
            System.out.println("Día no válido ");
            printEdBuque();
        }

        System.out.print("\t\tFecha de inicio: Mes: ");
        int mes = sc.nextInt();
        sc.nextLine();
        if (mes < 1 || mes > 12) {
            System.out.println("Mes no válido ");
            printEdBuque();
        }

        System.out.print("\t\tFecha de inicio: Año: ");
        int anio = sc.nextInt();
        sc.nextLine();
        if (anio < 1990) {
            System.out.println("Año no válido ");
            printEdBuque();
        }
        Date fecha = new Date(anio, mes, dia);

        // Puerto
        System.out.println("\n\tPuertos posibles para la ubicación inicial del buque:");
        mostrarListaPuertos();
        boolean id_correcto = false;
        int id_puerto = 0;
        while (!id_correcto) {
            System.out.print("\n\t\tIdentificador de puerto inicio: ");
            id_puerto = sc.nextInt();
            sc.nextLine();
            if (puertos.containsKey(id_puerto)) {
                id_correcto = true;
            } else {
                System.out.println("Identificador no válido ");
            }
        }

        // Validar datos
        boolean valido = false;
        System.out.print("\nIMPORTANTE: Esta opción borra los datos anteriores.");
        while (valido != true) {
            System.out.print(" Son correctos los nuevos datos (S/N): ");
            String opcion = sc.nextLine();

            if (opcion.equals("S")) {
                valido = true;

                // cambiar datos
                boolean encontrado = false;

                for (int i = 0; i < buques.size(); i++) {
                    if (buques.get(i).getIdentificador().equals(id)) {
                        buques.get(i).setNombre(nombre);
                        buques.get(i).setFechaInicio(fecha);
                        buques.get(i).setPuerto(puertos.get(id_puerto));
                        encontrado = true;
                        System.out.println("\nDatos de buque modificados");
                        printInicio();

                    }
                }
                if (!encontrado) {
                    System.out.println("Identificador no existe ");
                    printEdPuerto();
                }

            } else if (opcion.equals("N")) {
                valido = true;
                System.out.println("Datos de puerto no modificados");

                printInicio();
            } else {
                System.out.println("Opción no válida ");
            }
        }

    }

    public void printOpBuque() {
        System.out.println("");
        System.out.println("\tOperar Buque:");
        System.out.println("");

        // Fecha comienzo
        System.out.println("");
        System.out.print("\t\tFecha comienzo operación: Día? ");
        int dia = sc.nextInt();
        sc.nextLine();
        if (dia < 1 || dia > 31) {
            System.out.println("Día no válido ");
            printOpBuque();
        }

        System.out.print("\t\tFecha comienzo operación: Mes? ");
        int mes = sc.nextInt();
        sc.nextLine();
        if (mes < 1 || mes > 12) {
            System.out.println("Mes no válido ");
            printOpBuque();
        }

        System.out.print("\t\tFecha comienzo operación: Año? ");
        int anio = sc.nextInt();
        sc.nextLine();
        if (anio < 1990) {
            System.out.println("Año no válido ");
            printOpBuque();
        }
        Date fecha = new Date(anio, mes, dia);
        System.out.println("");

        // Identificador buque
        System.out.print("\t\tIdentificador del Buque(letra entre A y E): ");
        String id = sc.nextLine();
        // Comprobar que es un id correcto y obtener la posicion en lista
        if (!id.equals("A") && !id.contains("B") && !id.contains("C") && !id.contains("D") && !id.contains("E")) {
            System.out.println("\t\tIdentificador no válido ");
            printOpBuque();
        }

        System.out.println("");
        Buque b = getBuque(id);

        // Carga

        if (b.getCarga() == Carga.VACIO) {
            System.out.println("\t\tEl buque " + b.getNombre() + " está vacío en " + b.getPuerto().getNombre());
        } else {
            System.out.println("\t\tEl buque " + b.getNombre() + " está cargado con " + b.getCarga().getCarga() + " en "
                    + b.getPuerto().getNombre());
        }
        System.out.println("\t\tSe puede cargar: " + b.getPuerto().getProductosACargar());
        System.out.print("\t\tQuiere realizar la carga (S/N)? ");
        String quiere_cargar = sc.nextLine();
        Carga productoACargar = null;
        int duracionCarga = 0;
        if (quiere_cargar.equals("S")) {
            ArrayList<Carga> listaProductos = b.getPuerto().sePuedeCargar();
            if (listaProductos.size() > 1) {
                System.out.print("\t\tProducto a cargar (1 – Fuel, 2 – Gasoil, 3 – Gasolina)? ");
                int producto = sc.nextInt();
                sc.nextLine();
                switch (producto) {
                    case 1:
                        productoACargar = Carga.FUEL;
                        break;
                    case 2:
                        productoACargar = Carga.GASOIL;
                        break;
                    default:
                        productoACargar = Carga.GASOLINA;
                        break;

                }
            } else {
                productoACargar = Carga.CRUDO;
            }
            System.out.print("\t\tDuración de la carga en días: ");
            duracionCarga = sc.nextInt();
            sc.nextLine();
        } else {
            productoACargar = Carga.VACIO;
        }

        // Traslado
        System.out.println("");
        System.out.print("\t\tQuiere realizar el traslado (S/N)? ");
        String quiere_traslado = sc.nextLine();
        int idPuertoDestino = 0;
        int duracionTraslado = 0;
        Puerto puertoDestino = null;
        if (quiere_traslado.equals("S")) {
            System.out.println("\t\tPuertos de posible destino del buque:");
            for (Puerto puerto : listaPuertos) {
                if (puerto.getIdentificador() != b.getPuerto().getIdentificador()
                        && puerto.getTipo() != b.getPuerto().getTipo()) {
                    System.out.println(puerto.toString());
                }
            }
            System.out.print("\t\tIdentificador del puerto destino: ");
            idPuertoDestino = sc.nextInt();
            sc.nextLine();
            System.out.print("\t\tDuración del traslado en días: ");
            duracionTraslado = sc.nextInt();
            sc.nextLine();
            for (Puerto puerto : listaPuertos) {
                if (idPuertoDestino == puerto.getIdentificador()) {
                    puertoDestino = puerto;
                }
            }
        }

        // Descarga
        int duracionDescarga = 0;
        if (productoACargar != Carga.VACIO || b.getCarga()!= Carga.VACIO) {
            System.out.println("");
            System.out.print("\t\tQuiere realizar la descarga (S/N)? ");
            String quiereDescarga = sc.nextLine();
            
            if (quiereDescarga.equals("S")) {
                System.out.print("\t\tDuración de la descarga en días: ");
                duracionDescarga = sc.nextInt();
                sc.nextLine();

            }
        }

        // Comprobar operacion
        if (comprobarOperacion(b, fecha, b.getPuerto(), productoACargar, puertoDestino)) {
            Operacion op = new Operacion(b, fecha, b.getPuerto(), puertoDestino, productoACargar, duracionCarga,
                    duracionTraslado, duracionDescarga);
            // Resumen operacion
            op.resumenOperacion();
            System.out.print("");
            System.out.print("\t\tEs correcta la operación (S/N)? ");
            String correcta = sc.nextLine();
            if (correcta.equals("S")) { // Se guarda la operacion
                guardarOperacion(op, id);
                System.out.println("\n\tOperación guardada correctamente");
                printInicio();
            } else {
                System.out.println("\n\tOperación no guardada");

                printInicio();
            }

        } else {
            System.out.println("\n\tOperación no válida");
            printOpBuque();
        }

    }

    public void printEstadoBuques() {
        System.out.println("\n\t\t\t\tEstado buques ");
        System.out.println("");
        System.out.println("\tId\tNombre\t\tPuerto\t\t\tÚltima fecha\t\t\t\tCarga");
        System.out.println("");

        for (Buque buque : buques) {
            System.out.println(buque.toString());
        }
        System.out.println("");
        printInicio();
    }

    public void printResMensual() {
        System.out.println("\n\tResumen mensual Buque:");
        System.out.print("\tIdentificador Buque: ");
        String id = sc.nextLine();
        // Comprobar que es un id correcto y obtener la posicion en lista
        if (!id.equals("A") && !id.contains("B") && !id.contains("C") && !id.contains("D") && !id.contains("E")) {
            System.out.println("\t\tIdentificador no válido ");
            printResMensual();
        }
        Buque b = getBuque(id);

        System.out.print("\t\tSelección Mes: ");
        int mes = sc.nextInt();
        sc.nextLine();
        if (mes < 1 || mes > 12) {
            System.out.println("Mes no válido ");
            printResMensual();
        }

        System.out.print("\t\tSelección Año: ");
        int anio = sc.nextInt();
        sc.nextLine();
        if (anio < 1990) {
            System.out.println("Año no válido ");
            printResMensual();
        }

        System.out.println("\n\n\tResumen Buque: " + b.getNombre());
        System.out.println("");

        // mostrar calendario

        Calendario calendario = new Calendario();
        Date fecha1 = new Date(anio, mes, 1);
        Date fecha2 = calendario.getDiaFinMes(mes, anio);
        calendario.imprimirCalendario(b.getOperacionesMes(fecha1, fecha2), mes, anio);

        // volver a inicio

        printInicio();
    }

    public void mostrarListaPuertos() {
        Iterator<Integer> it = puertos.keySet().iterator();
        while (it.hasNext()) {
            System.out.println(puertos.get(it.next()).toString());
        }
            // volver a inicio

            printInicio();
    }

    public boolean comprobarOperacion(Buque b, Date fecha, Puerto pO, Carga carga, Puerto pD) {
        return b.comprobarOperacion(fecha, pO, carga, pD);
    }

    public Buque getBuque(String id) {
        int posicion = 0;

        for (int i = 0; i < buques.size(); i++) {
            if (buques.get(i).getIdentificador().equals(id)) {
                posicion = i;
            }
        }
        return buques.get(posicion);
    }

    public void guardarOperacion(Operacion op, String id_buque) {

        for (int i = 0; i < buques.size(); i++) {
            if (buques.get(i).getIdentificador().equals(id_buque)) {
                buques.get(i).addOperacion(op);
            }
        }

    }
}
