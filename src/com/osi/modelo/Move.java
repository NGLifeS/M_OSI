/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.osi.modelo;

import m_osi.JF_OSI;

/**
 *
 * @author Sora
 */
public class Move extends Thread {

    private JF_OSI osi;
    private OSI oSi;

    public Move(JF_OSI osi, OSI oSi) {
        this.osi = osi;
        this.oSi = oSi;
    }

    @Override
    public void run() {
        int x, y;

        for (int i = 0; i < 15; i++) {

            switch (i) {
                case 0:
                    osi.jlblMoveAO.setText(oSi.getAPDU());
                    osi.jlblMoveAO.setLocation(130, 167);
                    while (true) {
                        try {
                            sleep(100);
                            x = osi.jlblMoveAO.getLocation().x;
                            y = osi.jlblMoveAO.getLocation().y;
                            if (x < 307) {
                                osi.jlblMoveAO.setLocation(x + 10, y);
                                osi.jpOSI.repaint();
                            } else if (y < 224) {
                                osi.jlblMoveAO.setLocation(x, y + 10);
                                osi.jpOSI.repaint();
                            } else {
                                break;
                            }
                        } catch (Exception e) {
                        }
                    }

                    while (true) {
                        try {
                            sleep(100);
                            x = osi.jlblMoveAO.getLocation().x;
                            if (x > 130) {
                                osi.jlblMoveAO.setLocation(x - 10, y);
                                osi.jpOSI.repaint();
                            } else {
                                break;
                            }
                        } catch (Exception e) {
                        }
                    }
                    osi.jlblMoveAO.setText("");
                    break;
                case 1:
                    osi.jlblMovePO.setText(oSi.getPPDU());
                    osi.jlblMovePO.setLocation(130, 224);
                    while (true) {
                        try {
                            sleep(100);
                            x = osi.jlblMovePO.getLocation().x;
                            y = osi.jlblMovePO.getLocation().y;
                            if (x < 307) {
                                osi.jlblMovePO.setLocation(x + 10, y);
                                osi.jpOSI.repaint();
                            } else if (y < 283) {
                                osi.jlblMovePO.setLocation(x, y + 10);
                                osi.jpOSI.repaint();
                            } else {
                                break;
                            }
                        } catch (Exception e) {
                        }
                    }
                    while (true) {
                        try {
                            sleep(100);
                            x = osi.jlblMovePO.getLocation().x;
                            if (x > 130) {
                                osi.jlblMovePO.setLocation(x - 10, y);
                                osi.jpOSI.repaint();
                            } else {
                                break;
                            }
                        } catch (Exception e) {
                        }
                    }
                    osi.jlblMovePO.setText("");
                    break;
                case 2:
                    osi.jlblMoveSO.setText(oSi.getSPDU());
                    osi.jlblMoveSO.setLocation(130, 283);
                    while (true) {
                        try {
                            sleep(100);
                            x = osi.jlblMoveSO.getLocation().x;
                            y = osi.jlblMoveSO.getLocation().y;
                            if (x < 307) {
                                osi.jlblMoveSO.setLocation(x + 10, y);
                                osi.jpOSI.repaint();
                            } else if (y < 338) {
                                osi.jlblMoveSO.setLocation(x, y + 10);
                                osi.jpOSI.repaint();
                            } else {
                                break;
                            }
                        } catch (Exception e) {
                        }
                    }
                    while (true) {
                        try {
                            sleep(100);
                            x = osi.jlblMoveSO.getLocation().x;
                            if (x > 130) {
                                osi.jlblMoveSO.setLocation(x - 10, y);
                                osi.jpOSI.repaint();

                            } else {
                                break;
                            }
                        } catch (Exception e) {
                        }
                    }
                    osi.jlblMoveSO.setText("");
                    break;
                case 3:
                    for (int j = 0; j < oSi.getSegmento().size(); j++) {
                        osi.jlblMoveTO.setText(oSi.getSegmento().get(j));
                        osi.jlblMoveTO.setLocation(130, 338);
                        while (true) {
                            try {
                                sleep(100);
                                x = osi.jlblMoveTO.getLocation().x;
                                y = osi.jlblMoveTO.getLocation().y;
                                if (x < 307) {
                                    osi.jlblMoveTO.setLocation(x + 10, y);
                                    osi.jpOSI.repaint();
                                } else if (y < 395) {
                                    osi.jlblMoveTO.setLocation(x, y + 10);
                                    osi.jpOSI.repaint();
                                } else {
                                    break;
                                }
                            } catch (Exception e) {
                            }
                        }

                        while (true) {
                            try {
                                sleep(100);
                                x = osi.jlblMoveTO.getLocation().x;
                                if (x > 130) {
                                    osi.jlblMoveTO.setLocation(x - 10, y);
                                    osi.jpOSI.repaint();
                                } else {
                                    break;
                                }
                            } catch (Exception e) {
                            }
                        }
                        osi.jlblMoveTO.setText("");
                    }
                    break;
                case 4:
                    for (int j = 0; j < oSi.getSegmento().size(); j++) {
                        osi.jlblMoveRO.setText(oSi.getPAQUETE().get(j));
                        osi.jlblMoveRO.setLocation(130, 395);
                        while (true) {
                            try {
                                sleep(100);
                                x = osi.jlblMoveRO.getLocation().x;
                                y = osi.jlblMoveRO.getLocation().y;
                                if (x < 307) {
                                    osi.jlblMoveRO.setLocation(x + 10, y);
                                    osi.jpOSI.repaint();
                                } else if (y < 452) {
                                    osi.jlblMoveRO.setLocation(x, y + 10);
                                    osi.jpOSI.repaint();
                                } else {
                                    break;
                                }
                            } catch (Exception e) {
                            }
                        }
                        while (true) {
                            try {
                                sleep(100);
                                x = osi.jlblMoveRO.getLocation().x;
                                if (x > 130) {
                                    osi.jlblMoveRO.setLocation(x - 10, y);
                                    osi.jpOSI.repaint();
                                } else {
                                    break;
                                }
                            } catch (Exception e) {
                            }
                        }
                        osi.jlblMoveRO.setText("");
                    }
                    break;
                case 5:
                    for (int j = 0; j < oSi.getSegmento().size(); j++) {
                        osi.jlblMoveEO.setText(oSi.getTRAMA().get(j));
                        osi.jlblMoveEO.setLocation(130, 452);
                        while (true) {
                            try {
                                sleep(100);
                                x = osi.jlblMoveEO.getLocation().x;
                                y = osi.jlblMoveEO.getLocation().y;
                                if (x < 307) {
                                    osi.jlblMoveEO.setLocation(x + 10, y);
                                    osi.jpOSI.repaint();
                                } else if (y < 509) {
                                    osi.jlblMoveEO.setLocation(x, y + 10);
                                    osi.jpOSI.repaint();
                                } else {
                                    break;
                                }
                            } catch (Exception e) {
                            }
                        }
                        while (true) {
                            try {
                                sleep(100);
                                x = osi.jlblMoveEO.getLocation().x;
                                if (x > 130) {
                                    osi.jlblMoveEO.setLocation(x - 10, y);
                                    osi.jpOSI.repaint();
                                } else {
                                    break;
                                }
                            } catch (Exception e) {
                            }
                        }
                        osi.jlblMoveEO.setText("");
                    }
                    break;/*
                case 6:
                    for (int j = 0; j < oSi.getSegmento().size(); j++) {
                        osi.jlblMoveFO.setText(oSi.getBIT());
                        osi.jlblMoveFO.setLocation(130, 509);
                        while (true) {
                            try {
                                sleep(100);
                                x = osi.jlblMoveFO.getLocation().x;
                                y = osi.jlblMoveFO.getLocation().y;
                                if (x < 619) {
                                    osi.jlblMoveFO.setLocation(x + 10, y);
                                    osi.jpOSI.repaint();
                                } else {
                                    break;
                                }
                            } catch (Exception e) {
                            }
                        }
                        osi.jlblMoveFO.setText("");
                    }
                    break;*/
                case 7:
                    int xg = 35;
                    int flag = 0;
                    int b = 0;
                    String bit;
                        for (int k = 0; k < oSi.getBIT().length(); k++) {
                            try {
                                sleep(25);
                                if (oSi.getBIT().charAt(k) == '0') {
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg, 64, xg + 10, 64);
                                } else if (k == 0 && oSi.getBIT().charAt(k) == '1' && oSi.getBIT().charAt(k + 1) == '1') {
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg, 64, xg, 0);
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg, 0, xg + 10, 0);
                                } else if (k == 0 && oSi.getBIT().charAt(k) == '1' && oSi.getBIT().charAt(k + 1) == '0') {
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg, 64, xg, 0);
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg, 0, xg + 10, 0);
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg + 10, 0, xg + 10, 64);
                                } else if (oSi.getBIT().charAt(k - 1) == '1' && oSi.getBIT().charAt(k) == '1' && oSi.getBIT().charAt(k + 1) == '1') {
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg, 0, xg + 10, 0);
                                } else if (oSi.getBIT().charAt(k - 1) == '1' && oSi.getBIT().charAt(k) == '1' && oSi.getBIT().charAt(k + 1) == '0') {
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg, 0, xg + 10, 0);
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg + 10, 0, xg + 10, 64);
                                } else if (oSi.getBIT().charAt(k - 1) == '1' && oSi.getBIT().charAt(k) == '1' && oSi.getBIT().charAt(k + 1) == ' ') {
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg, 0, xg + 10, 0);
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg + 10, 0, xg + 10, 64);
                                } else if (oSi.getBIT().charAt(k - 1) == '0' && oSi.getBIT().charAt(k) == '1' && oSi.getBIT().charAt(k + 1) == '1') {
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg, 64, xg, 0);
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg, 0, xg + 10, 0);
                                } else if (oSi.getBIT().charAt(k - 1) == '0' && oSi.getBIT().charAt(k) == '1' && oSi.getBIT().charAt(k + 1) == '0') {
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg, 64, xg, 0);
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg, 0, xg + 10, 0);
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg + 10, 0, xg + 10, 64);
                                } else if (oSi.getBIT().charAt(k - 1) == '0' && oSi.getBIT().charAt(k) == '1' && oSi.getBIT().charAt(k + 1) == ' ') {
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg, 64, xg, 0);
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg, 0, xg + 10, 0);
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg + 10, 0, xg + 10, 64);
                                } else if (oSi.getBIT().charAt(k - 1) == ' ' && oSi.getBIT().charAt(k) == '1' && oSi.getBIT().charAt(k + 1) == '1') {
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg, 64, xg, 0);
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg, 0, xg + 10, 0);
                                } else if (oSi.getBIT().charAt(k - 1) == ' ' && oSi.getBIT().charAt(k) == '1' && oSi.getBIT().charAt(k + 1) == '0') {
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg, 64, xg, 0);
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg, 0, xg + 10, 0);
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg + 10, 0, xg + 10, 64);
                                } else if (oSi.getBIT().charAt(k - 1) == '1' && oSi.getBIT().charAt(k) == '1' && k + 1 == oSi.getBIT().length()) {
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg, 0, xg + 10, 0);
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg, 64, xg, 0);
                                } else if (oSi.getBIT().charAt(k - 1) == '0' && oSi.getBIT().charAt(k) == '1' && k + 1 == oSi.getBIT().length()) {
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg, 64, xg, 0);
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg, 0, xg + 10, 0);
                                    Grafic.Linea(osi.jpDiagrama.getGraphics(), xg + 10, 0, xg + 10, 64);
                                }
                                if (flag == 0) {
                                    bit = "";
                                    if (b+80<=oSi.getBIT().length()) {
                                        for (int l = b; l <= b+80; l++) {
                                            bit = bit+oSi.getBIT().charAt(l);
                                        }                                        
                                    }else {
                                        for (int l = b; l < oSi.getBIT().length(); l++) {
                                            bit = bit+oSi.getBIT().charAt(l);
                                        }
                                    }
                                    b += 81;
                                    osi.jlblBit.setText(bit);
                                }
                                if (flag == 80) {
                                    sleep(2500);
                                    osi.jlblBit.setText("");
                                    osi.jpDiagrama.removeAll();
                                    osi.jpDiagrama.repaint();
                                    flag = -1;
                                    xg = 25;
                                }
                                xg += 10;
                                flag++;
                            } catch (Exception e) {
                            }
                        }
                    try {
                        sleep(2500);
                        osi.jlblBit.setText("");
                        osi.jpDiagrama.removeAll();
                    } catch (Exception e) {
                    }
                    break;
                case 8:
                    for (int j = 0; j < oSi.getSegmento().size(); j++) {
                        osi.jlblMoveFD.setText(oSi.getTRAMA().get(j));
                        osi.jlblMoveFD.setLocation(700, 515);
                        while (true) {
                            try {
                                sleep(100);
                                x = osi.jlblMoveFD.getLocation().x;
                                y = osi.jlblMoveFD.getLocation().y;
                                if (x > 442) {
                                    osi.jlblMoveFD.setLocation(x - 10, y);
                                    osi.jpOSI.repaint();
                                } else if (y > 457) {
                                    osi.jlblMoveFD.setLocation(x, y - 10);
                                    osi.jpOSI.repaint();
                                } else {
                                    break;
                                }
                            } catch (Exception e) {
                            }
                        }
                        while (true) {
                            try {
                                sleep(100);
                                x = osi.jlblMoveFD.getLocation().x;
                                if (x < 693) {
                                    osi.jlblMoveFD.setLocation(x + 10, y);
                                    osi.jpOSI.repaint();
                                } else {
                                    break;
                                }
                            } catch (Exception e) {
                            }
                        }
                        osi.jlblMoveFD.setText("");
                    }
                    break;
                case 9:
                    for (int j = 0; j < oSi.getSegmento().size(); j++) {
                        osi.jlblMoveED.setText(oSi.getPAQUETE().get(j));
                        osi.jlblMoveED.setLocation(700, 458);
                        while (true) {
                            try {

                                sleep(100);
                                x = osi.jlblMoveED.getLocation().x;
                                y = osi.jlblMoveED.getLocation().y;
                                if (x > 442) {
                                    osi.jlblMoveED.setLocation(x - 10, y);
                                    osi.jpOSI.repaint();
                                } else if (y > 398) {
                                    osi.jlblMoveED.setLocation(x, y - 10);
                                    osi.jpOSI.repaint();
                                } else {
                                    break;
                                }
                            } catch (Exception e) {
                            }
                        }
                        while (true) {
                            try {
                                sleep(100);
                                x = osi.jlblMoveED.getLocation().x;
                                if (x < 700) {
                                    osi.jlblMoveED.setLocation(x + 10, y);
                                    osi.jpOSI.repaint();
                                } else {
                                    break;
                                }
                            } catch (Exception e) {
                            }
                        }
                        osi.jlblMoveED.setText("");
                    }
                    break;
                case 10:
                    for (int j = 0; j < oSi.getSegmento().size(); j++) {
                        osi.jlblMoveRD.setText(oSi.getSegmento().get(j));
                        osi.jlblMoveRD.setLocation(700, 398);
                        while (true) {
                            try {
                                sleep(100);
                                x = osi.jlblMoveRD.getLocation().x;
                                y = osi.jlblMoveRD.getLocation().y;
                                if (x > 442) {
                                    osi.jlblMoveRD.setLocation(x - 10, y);
                                    osi.jpOSI.repaint();
                                } else if (y > 341) {
                                    osi.jlblMoveRD.setLocation(x, y - 10);
                                    osi.jpOSI.repaint();
                                } else {
                                    break;
                                }
                            } catch (Exception e) {
                            }
                        }
                        while (true) {
                            try {
                                sleep(100);
                                x = osi.jlblMoveRD.getLocation().x;
                                if (x < 700) {
                                    osi.jlblMoveRD.setLocation(x + 10, y);
                                    osi.jpOSI.repaint();
                                } else {
                                    break;
                                }
                            } catch (Exception e) {
                            }
                        }
                        osi.jlblMoveRD.setText("");
                    }
                    break;
                case 11:
                    osi.jlblMoveTD.setText(oSi.getSPDU());
                    osi.jlblMoveTD.setLocation(700, 341);
                    while (true) {
                        try {
                            sleep(100);
                            x = osi.jlblMoveTD.getLocation().x;
                            y = osi.jlblMoveTD.getLocation().y;
                            if (x > 442) {
                                osi.jlblMoveTD.setLocation(x - 10, y);
                                osi.jpOSI.repaint();
                            } else if (y > 289) {
                                osi.jlblMoveTD.setLocation(x, y - 10);
                                osi.jpOSI.repaint();
                            } else {
                                break;
                            }
                        } catch (Exception e) {
                        }
                    }
                    while (true) {
                        try {
                            sleep(100);
                            x = osi.jlblMoveTD.getLocation().x;
                            if (x < 700) {
                                osi.jlblMoveTD.setLocation(x + 10, y);
                                osi.jpOSI.repaint();
                            } else {
                                break;
                            }
                        } catch (Exception e) {
                        }
                    }
                    osi.jlblMoveTD.setText("");
                    break;

                case 12:
                    osi.jlblMoveSD.setText(oSi.getPPDU());
                    osi.jlblMoveSD.setLocation(700, 289);
                    while (true) {
                        try {
                            sleep(100);
                            x = osi.jlblMoveSD.getLocation().x;
                            y = osi.jlblMoveSD.getLocation().y;
                            if (x > 442) {
                                osi.jlblMoveSD.setLocation(x - 10, y);
                                osi.jpOSI.repaint();
                            } else if (y > 224) {
                                osi.jlblMoveSD.setLocation(x, y - 10);
                                osi.jpOSI.repaint();
                            } else {
                                break;
                            }
                        } catch (Exception e) {
                        }
                    }
                    while (true) {
                        try {
                            sleep(100);
                            x = osi.jlblMoveSD.getLocation().x;
                            if (x < 700) {
                                osi.jlblMoveSD.setLocation(x + 10, y);
                                osi.jpOSI.repaint();
                            } else {
                                break;
                            }
                        } catch (Exception e) {
                        }
                    }
                    osi.jlblMoveSD.setText("");
                    break;
                case 13:
                    osi.jlblMovePD.setText(oSi.getAPDU());
                    osi.jlblMovePD.setLocation(700, 224);
                    while (true) {
                        try {
                            sleep(100);
                            x = osi.jlblMovePD.getLocation().x;
                            y = osi.jlblMovePD.getLocation().y;
                            if (x > 442) {
                                osi.jlblMovePD.setLocation(x - 10, y);
                                osi.jpOSI.repaint();
                            } else if (y > 167) {
                                osi.jlblMovePD.setLocation(x, y - 10);
                                osi.jpOSI.repaint();
                            } else {
                                break;
                            }
                        } catch (Exception e) {
                        }
                    }
                    while (true) {
                        try {
                            sleep(100);
                            x = osi.jlblMovePD.getLocation().x;
                            if (x < 700) {
                                osi.jlblMovePD.setLocation(x + 10, y);
                                osi.jpOSI.repaint();
                            } else {
                                break;
                            }
                        } catch (Exception e) {
                        }
                    }
                    osi.jlblMovePD.setText("");
                    break;
                case 14:
                    osi.jlblMoveAD.setText(oSi.getAPDU());
                    osi.jlblMoveAD.setLocation(700, 167);
                    while (true) {
                        try {
                            sleep(100);
                            x = osi.jlblMoveAD.getLocation().x;
                            y = osi.jlblMoveAD.getLocation().y;
                            if (x > 537) {
                                osi.jlblMoveAD.setLocation(x - 10, y);
                                osi.jpOSI.repaint();
                            } else if (y > 47) {
                                osi.jlblMoveAD.setLocation(x, y - 10);
                                osi.jpOSI.repaint();
                            } else {
                                break;
                            }
                        } catch (Exception e) {
                        }
                    }
                    osi.jbntGuardar.setVisible(true);
                    osi.jlblMoveAD.setText("");
                    break;
            }
        }
    }
}
