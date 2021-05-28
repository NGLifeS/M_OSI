package com.osi.modelo;

public class PC {
    private String IP;
    private String MAC;

    public PC() {
    }

    public PC(String MAC, String IP) {
        this.MAC = MAC;
        this.IP = IP;
    }

    public String getIP() {
        return IP;
    }

    public String getMAC() {
        return MAC;
    }
}
