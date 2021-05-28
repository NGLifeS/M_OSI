/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.osi.controlador;

import com.osi.modelo.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import m_osi.JF_OSI;

/**
 *
 * @author Sora
 */
public class controlador implements ActionListener {

    //CREACION DE VARIABLES
    private JF_OSI view;
    private PC pc;
    private OSI osi;
    private ArrayList<PC> pcs;
    private ArrayList<PC> pcod;
    DefaultTableModel modelo = new DefaultTableModel();
    //
    private String nombre;
    private String mensaje;
    private FileInputStream entrada;
    private FileOutputStream salida;
    private File archivo;
    private JFileChooser seleccionado;
    byte[] bytes;

    //CREACION DE CONSTRUCTOR
    public controlador(JF_OSI view) {
        //CREACION DEL FRAME
        this.view = view;
        //INICIAR FRAME CON LO NECESESARIO
        iniciar();
        //INICIALIZACION DE VARIABLES
        this.pcs = new ArrayList<>();
        this.pcod = new ArrayList<>();
        this.mensaje = "";
        this.seleccionado = new JFileChooser();
        this.bytes = new byte[1024 * 100];
        //CREACION DE ACCIONES CLICK
        this.view.jmiIngresarPC.addActionListener(this);
        this.view.jbtnEnviar.addActionListener(this);
        //
        this.view.jmiIngresarPC.addActionListener(this);
        this.view.jbtnLimpiar.addActionListener(this);
        //
        this.view.jmiEscogerPCOD.addActionListener(this);
        this.view.jbtnAgregar.addActionListener(this);
        this.view.jcbTipoDato.addActionListener(this);
        //
        this.view.jmiDatos.addActionListener(this);
        this.view.jcbTipoDato.addActionListener(this);
        this.view.jbtnCargar.addActionListener(this);
        this.view.jbtnTexto.addActionListener(this);
        //
        this.view.jmiEnviar.addActionListener(this);
        //
        this.view.jbtnProbar.addActionListener(this);
        //
        this.view.jbntGuardar.addActionListener(this);
        //CREACION DE PCS DE INICIO
        PC pc1 = new PC("82:BE:17:DE:E1:BC", "192.168.1.100");
        PC pc2 = new PC("E8:93:87:CA:DF:3E", "192.168.1.101");
        PC pc3 = new PC("F1:66:BB:39:BE:B2", "192.168.1.102");
        //AGREGAR PC A UN ARRAY
        pcs.add(pc1);
        pcs.add(pc2);
        pcs.add(pc3);
    }

    public void iniciar() {
        view.setLocationRelativeTo(null);
        view.jpIngresoPC.setVisible(false);
        view.jpEscogerPC.setVisible(false);
        view.jpDatos.setVisible(false);
        view.jpEnviar.setVisible(false);
        view.jpPrincipal.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.jmiIngresarPC) {
            view.jpPrincipal.setVisible(false);
            view.jpEscogerPC.setVisible(false);
            view.jpDatos.setVisible(false);
            view.jpEnviar.setVisible(false);
            view.jpIngresoPC.setVisible(true);
            view.jtPCS.setModel(listarPC());
        }
        if (e.getSource() == view.jbtnEnviar) {
            agregarPC();
        }
        if (e.getSource() == view.jbtnLimpiar) {
            limpiar();
        }
        //
        if (e.getSource() == view.jmiEscogerPCOD) {
            view.jpPrincipal.setVisible(false);
            view.jpIngresoPC.setVisible(false);
            view.jpDatos.setVisible(false);
            view.jpEnviar.setVisible(false);
            view.jpEscogerPC.setVisible(true);
            view.jtPCSe.setModel(listarPC());
            view.jlblMACOrigen.setText("");
            view.jlblIPOrigen.setText("");
            view.jlblMACDestino.setText("");
            view.jlblIPDestino.setText("");
        }
        if (e.getSource() == view.jbtnAgregar) {
            escogerPC();
        }
        //
        if (e.getSource() == view.jmiDatos) {
            if (pcod.size() == 2) {
                view.jpPrincipal.setVisible(false);
                view.jpIngresoPC.setVisible(false);
                view.jpEscogerPC.setVisible(false);
                view.jpEnviar.setVisible(false);
                view.jbtnCargar.setVisible(false);
                limpiarLblOSI();
                view.jlblArchivoT.setVisible(false);
                view.jlblArchivoI.setVisible(false);
                view.jpDatos.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Necesita escoger PCs");
            }
        }
        if (e.getSource() == view.jcbTipoDato) {
            limpiarLblOSI();
            TipoDato();
        }
        if (e.getSource() == view.jbtnCargar) {
            limpiarLblOSI();
            limpiarByte();
            CargarArchivo();
        }
        if (e.getSource() == view.jbtnTexto) {
            if (!view.jtxtDatoTexto.getText().equals("")) {
                limpiarLblOSI();
                CargarT();
            } else {
                JOptionPane.showMessageDialog(null, "Nesecita ingresar texto");
            }

        }
        //
        if (e.getSource() == view.jmiEnviar) {
            if (pcod.size() == 2) {
                if (mensaje != "") {
                    view.jpPrincipal.setVisible(false);
                    view.jpIngresoPC.setVisible(false);
                    view.jpEscogerPC.setVisible(false);
                    view.jpDatos.setVisible(false);
                    view.jpOSI.setVisible(false);
                    view.jbntGuardar.setVisible(false);
                    view.jpEnviar.setVisible(true);
                    view.jbtnProbar.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Necesita agregar algun dato");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Necesita escoger PCs");
            }
        }
        //
        //Probar
        if (e.getSource() == view.jbtnProbar) {
            view.jbtnProbar.setVisible(false);
            view.jpOSI.setVisible(true);
            OSi();
            Move mover = new Move(view, this.osi);
            mover.start();
        }

        if (e.getSource() == view.jbntGuardar) {
            GuardarArchivo();
        }
    }

    public void agregarPC() {
        //LEER Y GUARDAR DEL LABEL MAC E IP
        String MAC = view.jtxtMAC.getText();
        String IP = view.jtxtIP.getText();
        //CREACION DE PC Y GUARDARLO EN EL ARRAY
        pc = new PC(IP, MAC);
        pcs.add(pc);
        //AGREGAR PC A LA TABLA
        listarPC();
        //LIMPIAR LABEL DE MAC E IP
        limpiar();
    }

    public void limpiarByte() {
        bytes = new byte[1024 * 100];
    }

    public void limpiar() {
        //LIMPIAR LABEL DE MAC E IP DE INGRESO DE PC
        view.jtxtMAC.setText("");
        view.jtxtIP.setText("");
    }

    public DefaultTableModel listarPC() {
        //CREACION DE UN MODELO TIPO TABLA CON LOS DATOS DE LA TABLA
        modelo = (DefaultTableModel) view.jtPCS.getModel();
        //CREACION DE UN OBJETO DONDE GUARDAR LAS PCS
        String[] objeto = new String[3];
        //LIMPIAMOS LA TABLA
        modelo.getDataVector().removeAllElements();
        //AGREGAMOS DATOS DE LAS PCS A LA TABLA
        for (int i = 0; i < pcs.size(); i++) {
            objeto[0] = "PC " + (i + 1);
            objeto[1] = pcs.get(i).getMAC();
            objeto[2] = pcs.get(i).getIP();
            //AGREGAR FILAS A LA TABLA
            modelo.addRow(objeto);
        }
        //RETORNAR TABLA
        return modelo;
    }

    public void escogerPC() {
        try {
            //INDICE DE LA FILA SELECCIONADA
            int index = view.jtPCSe.getSelectedRow();
            //SI EL PC DE ORIGEN ESTA VACION AGREGAMOS EL PRIMERO A ESTE
            if (view.jlblMACOrigen.getText().equals("")) {
                view.jlblMACOrigen.setText(view.jtPCS.getValueAt(index, 1).toString());
                view.jlblIPOrigen.setText(view.jtPCS.getValueAt(index, 2).toString());
            }
            //SI EL PC DE ORIGEN ESTA LLENO Y LA MAC DE ORIGEN ES DISTINTA A LA QUE ESTAMOS ESCOGIENDO, AGREGAMOS PC DE DESTINO
            if (view.jlblMACOrigen.getText() != view.jtPCS.getValueAt(index, 1) && view.jlblMACDestino.getText().equals("")) {
                view.jlblMACDestino.setText(view.jtPCS.getValueAt(index, 1).toString());
                view.jlblIPDestino.setText(view.jtPCS.getValueAt(index, 2).toString());
            }
            //ARRAY DONDE GUARDO PC DE ORIGEN Y DESTINO, LA LIMPIAMOS
            pcod.clear();
            //RECORREMOS ARRAY DONDE ESTAN TODAS LAS PCS
            for (int i = 0; i < pcs.size(); i++) {
                //COMPARAMOS EL LABEL DE LA MAC DE ORIGEN CON LA MAC DE CADA UNA DE LAS PCS
                if (pcs.get(i).getMAC().equals(view.jlblMACOrigen.getText())) {
                    //GUARDAMOS PC DE ORIGEN QUE COINCIDE EN INDICE 0
                    pcod.add(0, pcs.get(i));
                }
            }
            //RECORREMOS ARRAY DONDE ESTAN TODAS LAS PCS
            for (int i = 0; i < pcs.size(); i++) {
                //COMPARAMOS EL LABEL DE LA MAC DE ORIGEN CON LA MAC DE CADA UNA DE LAS PCS
                if (pcs.get(i).getMAC().equals(view.jlblMACDestino.getText())) {
                    //GUARDAMOS PC DE DESTINO QUE COINCIDE EN INDICE 1
                    pcod.add(1, pcs.get(i));
                }
            }
        } catch (Exception e) {
            //EN CASO DE DARLE CLICK A BOTON "+" PARA AGREGAR Y NO HEMOS SELECCIONADO NINGUNA FILA
            JOptionPane.showMessageDialog(null, "Debe seleccionar una PC");
        }
    }

    public void limpiarLblOSI() {
        view.jlblArchivoI.setIcon(null);
        view.jlblArchivoT.setText("");
        view.jlblTexto.setText("");
    }

    //ESCOGER TIPO DE DATO A ENVIAR Y MOSTRAR PANELES QUE HAGAN FALTA
    public void TipoDato() {
        //SI ES 0 ES INGRESAR UN TEXTO
        if (view.jcbTipoDato.getSelectedIndex() == 0) {
            view.jbtnCargar.setVisible(false);
            view.jtxtDatoTexto.setVisible(true);
            view.jbtnTexto.setVisible(true);
            view.jlblArchivoT.setVisible(true);
        }
        //SI ES 1 ES INGRESAR UN ARCHIVO
        if (view.jcbTipoDato.getSelectedIndex() == 1) {
            view.jtxtDatoTexto.setVisible(false);
            view.jbtnTexto.setVisible(false);
            view.jlblArchivoT.setVisible(true);
            view.jlblArchivoI.setVisible(true);
            view.jbtnCargar.setVisible(true);

        }
    }

    //GUARGADO DE TEXTO INGRESADO
    public void CargarT() {
        try {
            view.jlblTexto.setText(view.jtxtDatoTexto.getText());
            view.jtxtDatoTexto.setText("");

            bytes = view.jlblTexto.getText().getBytes();
            mensaje = bytes.toString();

            nombre = view.jlblTexto.getText();
        } catch (Exception e) {
        }
    }

    //GUARDADO DE ARCHIVO DE TEXTO
    public void CargarArchivoT() {
        try {
            entrada = new FileInputStream(archivo);
            entrada.read(bytes);
            view.jlblArchivoT.setText(new String(bytes));
            mensaje = bytes.toString();
        } catch (Exception e) {
        }
    }

    //DESCARGAR ARCHIVO DE TEXTO O TEXTO INGRESADO
    public String GuardarArchivoT(File archivo, byte[] bytesTxt) {
        String respuesta = "";
        try {
            salida = new FileOutputStream(archivo);
            salida.write(bytesTxt);
            respuesta = "Se guardo con exito el archivo";
        } catch (Exception e) {
        }
        return respuesta;
    }

    //GUARDADO DE ARCHIVO DE IMAGEN
    public void CargarArchivoI() {
        //byte[] bytesImg = new byte[1024*100];
        try {
            entrada = new FileInputStream(archivo);
            entrada.read(bytes);
            view.jlblArchivoI.setIcon(new ImageIcon(bytes));
            //view.jlblArchivoI.setText(bytes.toString());
            mensaje = bytes.toString();
        } catch (Exception e) {
        }
        //return bytesImg;
    }

    //DESCARGAR ARCHIVO DE IMAGEN
    public String GuardarArchivoI(File archivo, byte[] bytesImg) {
        String respuesta = "";
        try {
            salida = new FileOutputStream(archivo);
            salida.write(bytesImg);
            respuesta = "La imagen se guardo con exito";
        } catch (Exception e) {
        }
        return respuesta;
    }

    public void CargarArchivo() {
        if (seleccionado.showDialog(null, "Cargar Archivo") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionado.getSelectedFile();
            if (archivo.canRead()) {
                if (archivo.getName().endsWith("txt")) {
                    CargarArchivoT();
                    nombre = archivo.getName();
                } else {
                    if (archivo.getName().endsWith("jpg") || archivo.getName().endsWith("png") || archivo.getName().endsWith("gif")) {
                        CargarArchivoI();
                        nombre = archivo.getName();
                    } else {
                        JOptionPane.showMessageDialog(null, "ESCOGA OTRO");
                    }
                }
            }
        }
    }

    public void GuardarArchivo() {
        if (seleccionado.showDialog(null, "Guardar Archivo") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionado.getSelectedFile();
            if (archivo.getName().endsWith("jpg") || archivo.getName().endsWith("png") || archivo.getName().endsWith("gif")) {
                JOptionPane.showMessageDialog(null, GuardarArchivoI(archivo, bytes));
            } else {
                JOptionPane.showMessageDialog(null, GuardarArchivoT(archivo, bytes));
            }
        }
    }

    public void OSi() {
        view.jlblMACO.setText(pcod.get(0).getMAC());
        view.jlblPCO.setText(pcod.get(0).getIP());
        view.jlblMACD.setText(pcod.get(1).getMAC());
        view.jlblPCD.setText(pcod.get(1).getIP());
        String pcnd = "";
        for (int i = 0; i < pcs.size(); i++) {
            if (pcod.get(1).getMAC().equals(pcs.get(i).getMAC())) {
                pcnd = pcnd + (i + 1);
            }
        }
        this.osi = new OSI(nombre, mensaje, pcod.get(0), pcod.get(1), pcnd);

        osi.enviarMensaje();
    }
}
