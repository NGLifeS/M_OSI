package com.osi.modelo;

import java.util.ArrayList;

public class PC {
    private String IP;
    private String MAC;
    private ArrayList<String> mensaje;
    ArrayList<String> mensajeOrdenado;
    String mensajeFinal;

    public PC() {
    }

    public PC(String MAC, String IP) {
        this.MAC = MAC;
        this.IP = IP;
        this.mensaje = new ArrayList<>();
        this.mensajeOrdenado = new ArrayList<>();
        this.mensajeFinal = " ";
    }

    public String getIP() {
        return IP;
    }

    public String getMAC() {
        return MAC;
    }
    
    public String getMensaje(int index) {
        return mensaje.get(index);
    }

    public boolean addMensaje(String m) {
        return mensaje.add(m);
    }
    
    public void desencriptarMensajes() {
        for (String string : mensaje) {
            mensajeOrdenado.add(string.substring(1));
        }
        for (String st1 : mensaje) {
            mensajeOrdenado.set(Integer.parseInt(Character.toString(st1.charAt(0))), st1.substring(1));
        }
        
        for (String mOrdenado : mensajeOrdenado) {
            mensajeFinal = mensajeFinal + mOrdenado;
        }
    }

    public ArrayList<String> getMensaje() {
        return mensaje;
    }

    public String getMensajeFinal() {
        return mensajeFinal;
    }
}
