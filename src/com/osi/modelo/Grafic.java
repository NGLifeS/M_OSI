package com.osi.modelo;

import java.awt.Graphics;

/**
 *
 * @author Sora
 */
public class Grafic {

    //CREACION DE LAS LINEAS PARA EL GRAFICO
    public static void Linea(Graphics g, int x, int y, int x1, int y1) {
        g.drawLine(x, y, x1, y1);
    }
}
