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
    private OSI oSi;

    public Move(JLabel etiqueta, JLabel inicio, JLabel medio, JLabel fin, JF_OSI osi, OSI oSi) {
        this.etiqueta = etiqueta;
        this.inicio = inicio;
        this.medio = medio;
        this.fin = fin;
        this.osi = osi;
        this.oSi = oSi;
    }
    
    @Override
    public void run() {
        
        
        int x, y;
        int xD = 307;
        int xI = 160;
        int yB = 224;
        for (int i = 0; i < 7; i++) {
            if (i == 0) {
                osi.jlblMove.setText(oSi.getAPDU());
                etiqueta.setLocation(0, 0);
            } else if (i == 1) {
                osi.jlblMove.setText(oSi.getPPDU());
                etiqueta.setLocation(160, 224);
            } else if (i == 2) {
                osi.jlblMove.setText(oSi.getSPDU());
                etiqueta.setLocation(160, 281);
            } else if (i == 3) {
               // osi.jlblMove.setText(oSi.getSegmento());
                etiqueta.setLocation(160, 338);
            } else if (i == 4) {
                osi.jlblMove.setText(oSi.getPAQUETE());
                etiqueta.setLocation(160, 395);
            } else if (i == 5) {
                osi.jlblMove.setText(oSi.getTRAMA());
                etiqueta.setLocation(160, 452);
            } else if (i == 6) {
                osi.jlblMove.setText(oSi.getTRAMA());
                etiqueta.setLocation(160, 504);
            }
                
            while (true) {            
                try {
                    sleep(100);
                    x = etiqueta.getLocation().x;
                    y = etiqueta.getLocation().y;
                    if (x < xD) {
                        etiqueta.setLocation(x + 10, y);
                        osi.jpOSI.repaint();
                    }else if(y < yB) {
                        etiqueta.setLocation(x, y + 10);
                        osi.jpOSI.repaint();
                    }else {
                        break;
                    }
                } catch (Exception e) {
                }
            }
            
            yB += 57;
            while (true) {            
                try {
                    sleep(100);
                    x = etiqueta.getLocation().x;
                    if(x > xI){
                        etiqueta.setLocation(x - 10, y);
                        osi.jpOSI.repaint();
                    }else {
                        break;
                    }
                } catch (Exception e) {
                }
            }
        }
    }
}
