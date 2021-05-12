/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.osi.modelo;

import javax.swing.JLabel;
import m_osi.JF_OSI;

/**
 *
 * @author Sora
 */
public class Move extends Thread{
    
    private JLabel etiqueta;
    private JLabel inicio;
    private JLabel medio;
    private JLabel fin;
    private JF_OSI osi;

    public Move(JLabel etiqueta, JLabel inicio, JLabel medio, JLabel fin, JF_OSI osi) {
        this.etiqueta = etiqueta;
        this.inicio = inicio;
        this.medio = medio;
        this.fin = fin;
        this.osi = osi;
    }
    
    @Override
    public void run() {
        int x, y;
        while (true) {            
            try {
                sleep(100);
                x = etiqueta.getLocation().x;
                y = etiqueta.getLocation().y;
                if (x < inicio.getLocation().x) {
                    etiqueta.setLocation(etiqueta.getLocation().x + 10, etiqueta.getLocation().y);
                    osi.jpOSI.repaint();
                }else if(y < medio.getLocation().y) {
                    etiqueta.setLocation(etiqueta.getLocation().x, etiqueta.getLocation().y + 10);
                    osi.jpOSI.repaint();
                }else {
                    break;
                }
            } catch (Exception e) {
            }
        }
        while (true) {            
            try {
                sleep(100);
                x = etiqueta.getLocation().x;
                y = etiqueta.getLocation().y;
                if(x > fin.getLocation().x){
                    etiqueta.setLocation(etiqueta.getLocation().x - 10, etiqueta.getLocation().y);
                    osi.jpOSI.repaint();
                }else {
                    break;
                }
            } catch (Exception e) {
            }
        }
        etiqueta.setVisible(false);
    }
}
