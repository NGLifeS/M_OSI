package com.osi.modelo;

import java.util.ArrayList;
import java.util.Collections;

public class OSI {
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
    
    public OSI(String mensaje, PC pcOrigen, PC pcDestino) {
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
        //
        System.out.println("----------");
        segmentacion(mensaje);
        System.out.println("----------");
        System.out.println("Multiplexando mensaje");
        multiplex();
        System.out.println("----------");
        capaDeRed();
        System.out.println("----------");
        capaDeEnlace();
        System.out.println("----------");
        System.out.println("Enviando datos al switch");
        dispositivoSwitch();
        System.out.println("----------");
        System.out.println("Desencriptando mensajes recibidos");
        desencriptarMensaje();
        
        System.out.println("Mensaje desencriptado correctamente");
        System.out.println("Host Origen: " + pcOrigen.getIP());
        //
        /*
        segmentacion(mensaje);
        multiplex();
        capaDeRed();
        capaDeEnlace();
        dispositivoSwitch();
        desencriptarMensaje();
        */
    }
    
    public void segmentacion(String mensaje) {
        //
        System.out.println("Segmentando mensaje ........");
        //
        int partes = mensaje.length() / 5 + 1;
        if (mensaje.length()%5 == 0) {
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
        //
    }
    
    public void multiplex() {
        Collections.shuffle(segmento);
        //
        for (String seg : segmento) {
            System.out.println(seg);
        }
        System.out.println("Mensaje multiplexado correctamente");
        //
    }
    
    public void capaDeRed() {
        //
        System.out.println("Mensaje a enviar: " + mensaje);
        System.out.println("Host de origen: " + pcOrigen.getIP());
        System.out.println("Host de Destino: " + pcDestino.getIP());
        //
        paquete = pcOrigen.getIP() + "/" + pcDestino.getIP();
    }
    
    public void capaDeEnlace() {
        //
        System.out.println("MAC de origen: " + pcOrigen.getMAC());
        System.out.println("MAC de Destino: " + pcDestino.getMAC());
        //
        trama = pcOrigen.getMAC() + "/" + pcDestino.getMAC();
    }
    
    public void dispositivoSwitch() {        
        int bandera = 0;
        int bandera2 = 0;
        
        //
        System.out.println("Buscando ip de origen y destino: ");
        System.out.println("Paquete: " + paquete);
        //
        
        for (int i = 0; i < paquete.length(); i++) {
            if (paquete.charAt(i) == '/') {
                bandera = 1;
                i += 1;
            }
            if (bandera == 0) {
                ipOrigen = ipOrigen + paquete.charAt(i);
            } else {
                ipDestino = ipDestino + paquete.charAt(i);
            }
        }        
        //
        System.out.println("IP Origen: " + ipOrigen);
        System.out.println("IP Destino: " + ipDestino);
        
        System.out.println("Buscando MAC de orige y destino: ");
        System.out.println("Trama: " + trama);
        //
        for (int i = 0; i < trama.length(); i++) {
            if (trama.charAt(i) == '/') {
                bandera2 = 1;
                i += 1;
            }
            if (bandera2 == 0) {
                macOrigen = macOrigen + trama.charAt(i);
            } else {
                macDestino = macDestino + trama.charAt(i);
            }
        }
        //
        System.out.println("MAC Origen: " + macOrigen);
        System.out.println("MAC Destino: " + macDestino);
            
        System.out.println("Enlace realizado correctamente");
        System.out.println("Enviando mensaje al destino: ");
        //
        macDestino = macDestino.substring(1);
        ipDestino = ipDestino.substring(1);
        //
        System.out.println("IP Destino: " + ipDestino);
        System.out.println("MAC Destino: " + macDestino);
        //
        for (String men : segmento) {
            pcDestino.addMensaje(men);
        }
        //
        System.out.println("Mensaje enviado correctamente");
        //
    }
    
    public void desencriptarMensaje() {
        //
        String hey;
        hey = pcDestino.mensajeFinal;
        System.out.println("Desencriptando mensaje......");
        //
        pcDestino.desencriptarMensajes();
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
    
    //
    public void mensaje2() {
        
        
        
        System.out.println("-----------------------------------");
        System.out.println("Capa de Aplicacion");
        APDU = "protocolo" + "/" + mensaje;
        System.out.println(APDU);
        System.out.println("-----------------------------------");
        System.out.println("Capa de Presentacion");
        PPDU = "txt" + "/" + APDU;
        System.out.println(PPDU);
        System.out.println("-----------------------------------");
        System.out.println("Capa de Sesion");
        SPDU = "SH" + "/" + PPDU;
        System.out.println(SPDU);
        System.out.println("-----------------------------------");
        System.out.println("Capa de Transporte");
        segmentacion(mensaje);
        for (int i = 0; i < segmento.size(); i++) {
            TPDU.add("TH"+ "/" + segmento.get(i));
            System.out.println(TPDU.get(i));        
        }   /*
        System.out.println("-----------------------------------");
        System.out.println("Capa de Red");
        PAQUETE = pcOrigen.getIP() + "/" + pcDestino.getIP() + "/" + TPDU;
        System.out.println(PAQUETE);
        System.out.println("-----------------------------------");
        System.out.println("Capa de Enlace de Datos");
        TRAMA =  pcOrigen.getMAC() + "/" + pcDestino.getMAC() + "/" + PAQUETE;
        System.out.println(TRAMA);
        System.out.println("-----------------------------------");
        System.out.println("Capa Fisica");
        BIT = textToBinary(TRAMA);
        System.out.println(BIT);*/
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
}
