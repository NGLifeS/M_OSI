package com.osi.modelo;

import java.util.ArrayList;

public class OSI {

    private String nombre;
    private String mensaje;
    //
    private PC pcOrigen;
    private PC pcDestino;
    private ArrayList<String> segmento;
    private String paquete;
    private String trama;
    private ArrayList<String> segm;
    private String ipOrigen;
    private String ipDestino;
    private String macOrigen;
    private String macDestino;
    private String APDU, PPDU, SPDU, PAQUETE, TRAMA, BIT;
    private ArrayList<String> TPDU = new ArrayList<>();

    public OSI() {
    }

    public OSI(String nombre, String mensaje, PC pcOrigen, PC pcDestino) {
        this.nombre = nombre;
        this.mensaje = mensaje;
        this.pcOrigen = pcOrigen;
        this.pcDestino = pcDestino;
        this.segmento = new ArrayList<>();
        this.segm = new ArrayList<>();
        this.ipOrigen = " ";
        this.ipDestino = " ";
        this.macOrigen = " ";
        this.macDestino = " ";
    }

    public void enviarMensaje() {

        System.out.println("-----------------------------------");
        System.out.println("Capa de Aplicacion");
        APDU = nombre;
        System.out.println(APDU);
        System.out.println("-----------------------------------");
        System.out.println("Capa de Presentacion");
        PPDU = mensaje;
        System.out.println(PPDU);
        System.out.println("-----------------------------------");
        System.out.println("Capa de Sesion");
        SPDU = "SH" + "/" + PPDU;
        System.out.println(SPDU);
        System.out.println("-----------------------------------");
        System.out.println("Capa de Transporte");
        segmentacion(mensaje);
        for (int i = 0; i < segmento.size(); i++) {
            TPDU.add("TH" + "/" + segmento.get(i));
            System.out.println(TPDU.get(i));
        }
        System.out.println("-----------------------------------");
        System.out.println("Capa de Red");
        PAQUETE = pcOrigen.getIP() + "/" + pcDestino.getIP() + "/";
        System.out.println(PAQUETE);
        System.out.println("-----------------------------------");
        System.out.println("Capa de Enlace de Datos");
        TRAMA = pcOrigen.getMAC() + "/" + pcDestino.getMAC() + "/" + PAQUETE;
        System.out.println(TRAMA);
        System.out.println("-----------------------------------");
        System.out.println("Capa Fisica");
        BIT = textToBinary(TRAMA);
        System.out.println(BIT);
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

    //
    public void segmentacion(String mensaje) {
        //
        System.out.println("Segmentando mensaje ........");
        //
        int partes = mensaje.length() / 5 + 1;
        if (mensaje.length() % 5 == 0) {
            partes -= 1;
        }
        int indiceMensaje = 0;
        for (int i = 0; i < partes; i++) {
            String parte = String.valueOf(i);
            int j = 0;
            while (j < 5 && indiceMensaje < mensaje.length()) {
                parte = parte + mensaje.charAt(indiceMensaje);
                j++;
                indiceMensaje++;
            }
            segmento.add(parte);
            segm.add(parte);
        }
        //
        for (String mensajeString : segmento) {
            System.out.println(mensajeString);
        }
        System.out.println("Mensaje segmentado correctamente");
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

    public ArrayList<String> getSegm() {
        return segm;
    }

    public String getIpOrigen() {
        return ipOrigen;
    }

    public String getIpDestino() {
        return ipDestino;
    }

    public String getMacOrigen() {
        return macOrigen;
    }

    public String getMacDestino() {
        return macDestino;
    }

    public String getAPDU() {
        return APDU;
    }

    public String getPPDU() {
        return PPDU;
    }

    public String getSPDU() {
        return SPDU;
    }

    public String getPAQUETE() {
        return PAQUETE;
    }

    public String getTRAMA() {
        return TRAMA;
    }

    public String getBIT() {
        return BIT;
    }

    public ArrayList<String> getTPDU() {
        return TPDU;
    }
}
