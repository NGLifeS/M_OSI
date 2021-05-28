package com.osi.modelo;

import java.util.ArrayList;

public class OSI {

    //CREACION DE NOMBRE DEL ARCHIVO O TEXTO
    private String nombre;
    //ARCHIVO O TEXTO CODIGICADO
    private String mensaje;
    //PC DE ORIGEN Y DESTINO
    private PC pcOrigen;
    private PC pcDestino;
    //NOMBRE DE LA PC DE DESTINO
    private String NPCD;
    //VARIABLES DE LAS CAPAS
    private String paquete;
    private String trama;
    private String APDU, PPDU, BIT;
    private ArrayList<String> segmento, SPDU, TPDU, PAQUETE, TRAMA;

    //CONSTRUCTOR DEL MODELO OSI
    public OSI(String nombre, String mensaje, PC pcOrigen, PC pcDestino, String NPCD) {
        this.nombre = nombre;
        this.mensaje = mensaje;
        this.pcOrigen = pcOrigen;
        this.pcDestino = pcDestino;
        this.NPCD = NPCD;
        this.segmento = new ArrayList<>();
        this.APDU = "";
        this.PPDU = "";
        this.BIT = "";
        this.SPDU = new ArrayList<>();
        this.TPDU = new ArrayList<>();
        this.PAQUETE = new ArrayList<>();
        this.TRAMA = new ArrayList<>();
    }

    //ENVIO DEL MENSAJE
    public void enviarMensaje() {
        //CAPA DE APLICACION - NOMBRE DEL ARCHIVO O TEXTO
        APDU = nombre;
        //CAPA DE PRESENTACION - ARCHIVO O TEXTO CODIFICADO
        PPDU = mensaje;
        //LIMPIAMOS EL ARRAY
        SPDU.clear();
        //EN EL INDICE "0" HACEMOS LA CONEXION CON LA PC DE DESTINO
        SPDU.add("Conxion con PC " + NPCD + " realizada con exito");
        //EN EL INDICE "1" ENVIAMOS EL PPDU A LA CAPA DE TRANSPORTE
        SPDU.add(PPDU);
        //LA CAPA DE TRASNPORTE SEGMENTA EL MENSAJE
        segmentacion(mensaje); // IR AHI
        //RECORREMOS EL ARRAY SEGMENTO
        for (int i = 0; i < segmento.size(); i++) {
            //AGREGAMOS EL SEGMENTO AL TPDU DE LA CAPA DE TRANSPORTE
            TPDU.add(segmento.get(i));
            //AGREGAMOS LA IP DE ORIGEN Y DESTINO + LOS DATOS DEL TPDU AL PAQUETE DE LA CAPA DE RED
            PAQUETE.add(pcOrigen.getIP() + "/" + pcDestino.getIP() + "/" + TPDU.get(i));
            //AGREGAMOS LA MAC DE ORIGEN Y DESTINO + LOS DATOS DEL PAQUETE A LA TRAMA DE LA CAPA DE ENLACE DE DATOS
            TRAMA.add(pcOrigen.getMAC() + "/" + pcDestino.getMAC() + "/" + PAQUETE.get(i));
            //CONVERTIMOS LA TRAMA A O Y 1 Y LO CONCATENAMOS AL BIT DE LA CAPA FISICA
            BIT = "" + textToBinary(TRAMA.get(i)); // IR AHI
        }
    }

    //CONVERSIR DECIMAL A BINARIO
    public String decimalToBinary(int decimal) {
        if (decimal <= 0) {
            return "0";
        }
        String binary = "";
        while (decimal > 0) {
            //GUARDAMOS EL RESTO
            short remainder = (short) (decimal % 2);
            //DIVIDIMOS ENTRE 2 Y OBTENEMOS EL NUEVO DECIMAL
            decimal = decimal / 2;
            //CONTATENAMOS LOS RESTOS OBTENIDO Y LOS VAMOS GUARDANDO EN BINARY
            binary = String.valueOf(remainder) + binary;
        }
        //RETORNAMOS EL BINARIO OBTENIDO
        return binary;
    }

    //CONVERTIR TEXTO A BINARIO
    public String textToBinary(String mensaje) {
        String binaryText = "";
        //RECORREMOS HASTA COMPLETAR TODOS LOS CARACTERES DE LA TRAMA
        for (int i = 0; i < mensaje.length(); i++) {
            //GUARDAMOS EL CARACTER DEL MENSAJE EN EL INDICE DADO Y LO GUARDAMOS EN CURRENTCHART
            char currentChar = mensaje.charAt(i);
            //CONVERTIMOS EL CARACTER OBTENIDO EN ENTERO Y LO GUARDAMOS EN ASCCI
            int ascii = (int) currentChar;
            //CONVERTIMOS EL DECIMAL OBTENIDO A BINARIO Y LO GUARDAMOS EN BINARY
            String binary = decimalToBinary(ascii); // IR AHI
            //RECORREMOS LOS OCTENOS
            for (int j = 8; j > 0; j--) {
                //SI COMENZABA CON 0 ME LOS COMIA, ASI QUE AGREGABA LOS 0 AL PRINCIPIO HASTA LLEGAR A LOS 8 BITS
                if (binary.length() < 8) {
                    binary = "0" + binary;
                }
            }
            //CONCATENAMOS TODOS LOS BINARIOS OBTENIDOS
            binaryText += binary + " ";
        }
        //RETORNAMOS TODO LOS BITS OBTENIDOS
        return binaryText;
    }

    //SEGMENTACION DEL MENSAJE
    public void segmentacion(String mensaje) {
        //DIVIDE LA LONGITUD TOTAL DEL MENSAJE EN 5 CARACTERES DE LONGITUD Y CREA LAS PARTES
        int partes = SPDU.get(1).length() / 5 + 1;
        //SI EL RESTO SALE 0 DISMINUIMOS UNA PARTE PARA QUE NO SE CREE UNA PARTE VACIA
        if (SPDU.get(1).length() % 5 == 0) {
            partes -= 1;
        }
        //RECORREMOS LAS PARTES SACADAS ANTERIORMENTE
        int indiceMensaje = 0;
        for (int i = 0; i < partes; i++) {
            //CONVERTIRMOS EL INCIDE A STRING PARA AGREGARLO
            String parte = String.valueOf(i);
            int j = 0;
            //AGREGAMOS 5 CARACTERES A PARTE
            while (j < 5 && indiceMensaje < SPDU.get(1).length()) {
                parte = parte + SPDU.get(1).charAt(indiceMensaje);
                j++;
                indiceMensaje++;
            }
            //AGREGAMOS PARTE AL SEGMENTO
            segmento.add(parte);
            //VOLVEMOS A RECORRER HASTA ACABAR LAS PARTES OBTENIDAS ANTES
        }
    }

    //GETTER
    public String getMensaje() {
        return mensaje;
    }

    public PC getPcOrigen() {
        return pcOrigen;
    }

    public PC getPcDestino() {
        return pcDestino;
    }

    public ArrayList<String> getSegmento() {
        return segmento;
    }

    public String getPaquete() {
        return paquete;
    }

    public String getTrama() {
        return trama;
    }

    public String getAPDU() {
        return APDU;
    }

    public String getPPDU() {
        return PPDU;
    }

    public String getNPCD() {
        return NPCD;
    }

    public ArrayList<String> getSPDU() {
        return SPDU;
    }

    public String getBIT() {
        return BIT;
    }

    public ArrayList<String> getTPDU() {
        return TPDU;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<String> getPAQUETE() {
        return PAQUETE;
    }

    public ArrayList<String> getTRAMA() {
        return TRAMA;
    }

}
