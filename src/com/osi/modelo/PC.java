package com.osi.modelo;

public class PC {

    //VARIABLES DE PC
    private String IP;
    private String MAC;

    //CONSTRUCTORES
    public PC() {
    }

    public PC(String MAC, String IP) {
        this.MAC = MAC;
        this.IP = IP;
    }

    //GETTER
    public String getIP() {
        return IP;
    }

    public String getMAC() {
        return MAC;
    }
}
