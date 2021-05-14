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
    private JF_OSI osi;
    private OSI oSi;

    public Move(JLabel etiqueta, JF_OSI osi, OSI oSi) {
        this.etiqueta = etiqueta;
        this.osi = osi;
        this.oSi = oSi;
    }
    
    @Override
    public void run() {
        
        
        int x, y;
        int xD = 307;
        int xI = 160;
        int yB = 224;
        for (int i = 0; i < 8; i++) {
            
            for (int j = 0; j < oSi.getSegmento().size(); j++) {
                
                switch(i) {
                    case 0:
                        osi.jlblMove.setText(oSi.getAPDU());
                        etiqueta.setLocation(130, 171);
                        etiqueta.getLocation();
                        break;
                    case 1:
                        osi.jlblMove.setText(oSi.getPPDU());
                        etiqueta.setLocation(130, 224);
                        etiqueta.getLocation();
                        break;
                    case 2:
                        osi.jlblMove.setText(oSi.getSPDU());
                        etiqueta.setLocation(130, 281);
                        etiqueta.getLocation();
                        break;
                    case 3:
                        osi.jlblMove.setText(oSi.getSegmento().get(j));
                        etiqueta.setLocation(130, 338);
                        etiqueta.getLocation();
                        break;
                    case 4:
                        osi.jlblMove.setText(oSi.getPAQUETE()+oSi.getSegmento().get(j));
                        etiqueta.setLocation(130, 395);
                        etiqueta.getLocation();
                        break;
                    case 5:
                        osi.jlblMove.setText(oSi.getTRAMA()+oSi.getSegmento().get(j));
                        etiqueta.setLocation(130, 452);
                        etiqueta.getLocation();
                        break;
                    case 6:
                        osi.jlblMove.setText(oSi.getBIT());
                        etiqueta.setLocation(130, 504);
                        etiqueta.getLocation();
                        break;/*
                    case 7:
                        osi.jlblMove.setText(oSi.getTRAMA());
                        etiqueta.setLocation(160, 564);
                        etiqueta.getLocation();
                        break;*/
                }

                while (true) {            
                    try {
                        sleep(100);
                        x = etiqueta.getLocation().x;
                        y = etiqueta.getLocation().y;
                        if (x < xD) {
                            etiqueta.setLocation(x + 10, y);
                            //osi.jpOSI.repaint();
                        }else if(y < yB) {
                            etiqueta.setLocation(x, y + 10);
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
            yB += 57; 
       }
    }
}
