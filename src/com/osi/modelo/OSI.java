package com.osi.modelo;

import java.util.ArrayList;

public class OSI {

    private String nombre;
    private String mensaje;
    //
    private PC pcOrigen;
    private PC pcDestino;
    private String paquete;
    private String trama;
    //private ArrayList<String> segm;
    private String APDU, PPDU, SPDU, BIT;
    private ArrayList<String> segmento, TPDU, PAQUETE, TRAMA;

    public OSI(String nombre, String mensaje, PC pcOrigen, PC pcDestino) {
        this.nombre = nombre;
        this.mensaje = mensaje;
        this.pcOrigen = pcOrigen;
        this.pcDestino = pcDestino;
        this.segmento = new ArrayList<>();
        //this.segm = new ArrayList<>();
        this.APDU = "";
        this.PPDU = "";
        this.SPDU = "";
        this.BIT = "";
        this.TPDU = new ArrayList<>();
        this.PAQUETE = new ArrayList<>();
        this.TRAMA = new ArrayList<>();
    }

    public void enviarMensaje() {
        APDU = nombre;
        PPDU = mensaje;
        SPDU = "SH" + "/" + PPDU;
        segmentacion(mensaje);
        for (int i = 0; i < segmento.size(); i++) {
            TPDU.add(segmento.get(i));
            PAQUETE.add(pcOrigen.getIP() + "/" + pcDestino.getIP() + "/"+TPDU.get(i));
            TRAMA.add(pcOrigen.getMAC() + "/" + pcDestino.getMAC() + "/" + PAQUETE.get(i));
            BIT = ""+textToBinary(TRAMA.get(i));
        }
    }

    public String decimalToBinary(int decimal) {
        if (decimal <= 0) {
            return "0";
        }
        String binary = "";
        while (decimal > 0) {
            short remainder = (short) (decimal % 2);
            decimal = decimal / 2;
            binary = String.valueOf(remainder) + binary;
        }
        return binary;
    }

    public String textToBinary(String mensaje) {
        String binaryText = "";
        for (int i = 0; i < mensaje.length(); i++) {
            char currentChar = mensaje.charAt(i);
            int ascii = (int) currentChar;
            String binary = decimalToBinary(ascii);
            for (int j = 8; j > 0; j--) {
                if (binary.length() < 8) {
                    binary = "0" + binary;
                }
            }
            binaryText += binary + " ";
        }

        return binaryText;
    }

    public void segmentacion(String mensaje) {
        int partes = SPDU.length() / 5 + 1;
        if (SPDU.length() % 5 == 0) {
            partes -= 1;
        }
        int indiceMensaje = 0;
        for (int i = 0; i < partes; i++) {
            String parte = String.valueOf(i);
            int j = 0;
            while (j < 5 && indiceMensaje < SPDU.length()) {
                parte = parte + SPDU.charAt(indiceMensaje);
                j++;
                indiceMensaje++;
            }
            segmento.add(parte);
            //segm.add(parte);
        }
    }

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

    /*public ArrayList<String> getSegm() {
        return segm;
    }*/

    public String getAPDU() {
        return APDU;
    }

    public String getPPDU() {
        return PPDU;
    }

    public String getSPDU() {
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
