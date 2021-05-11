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
public class controlador implements ActionListener{
    private JF_OSI view;
    private PC pc;
    private OSI osi;
    private ArrayList<PC> pcs;
    private ArrayList<PC> pcod;
    DefaultTableModel modelo = new DefaultTableModel();
    //
    private String mensaje;
    private FileInputStream entrada;
    private FileOutputStream salida;
    private File archivo;
    private JFileChooser seleccionado;
    byte[] bytes;


    public controlador(JF_OSI view) {
        this.view = view;
        iniciar();
        this.pcs = new ArrayList<>();
        this.pcod = new ArrayList<>();
        this.mensaje =  "";
        this.seleccionado = new JFileChooser();
        this.bytes = new byte[1024*100];
        //
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
        //Esto no es
        this.view.jbtnProbar.addActionListener(this);
        //
        PC pc1 = new PC("82:BE:17:DE:E1:BC", "192.168.1.100");
        PC pc2 = new PC("E8:93:87:CA:DF:3E", "192.168.1.101");
        PC pc3 = new PC("F1:66:BB:39:BE:B2", "192.168.1.102");
        pcs.add(pc1);
        pcs.add(pc2);
        pcs.add(pc3);
        //
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
            //
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
        }
    }
    
    public void agregarPC() {
        String MAC = view.jtxtMAC.getText();
        String IP = view.jtxtIP.getText();
        pc = new PC(IP, MAC);
        pcs.add(pc);        
        listarPC();
    }
    
    public void limpiarByte() {
        bytes = new byte[1024*100];
    }
    
    public void limpiar() {
        view.jtxtMAC.setText("");
        view.jtxtIP.setText("");
    }
    
    public DefaultTableModel listarPC() {        
        modelo = (DefaultTableModel)view.jtPCS.getModel();
        String[] objeto = new String[3];
        modelo.getDataVector().removeAllElements();
        for (int i = 0; i < pcs.size(); i++) {
            objeto[0] = "PC " + (i+1);
            objeto[1] = pcs.get(i).getMAC();
            objeto[2] = pcs.get(i).getIP();   
            modelo.addRow(objeto);
        }
        return modelo;
    }
    //
    public void escogerPC() {
        try {
            int index = view.jtPCSe.getSelectedRow();
            if (view.jlblMACOrigen.getText().equals("")) {
                view.jlblMACOrigen.setText(view.jtPCS.getValueAt(index, 1).toString());
                view.jlblIPOrigen.setText(view.jtPCS.getValueAt(index, 2).toString());                  
            }
            if (view.jlblMACOrigen.getText() != view.jtPCS.getValueAt(index, 1) && view.jlblMACDestino.getText().equals("")) {
                view.jlblMACDestino.setText(view.jtPCS.getValueAt(index, 1).toString());
                view.jlblIPDestino.setText(view.jtPCS.getValueAt(index, 2).toString());                  
            }           
            pcod.clear();
            //Esto ------------------------------------------------------------------------------------------------------------------------------
            for (int i = 0; i < pcs.size(); i++) {
                if (pcs.get(i).getMAC().equals(view.jlblMACOrigen.getText())) {
                    pcod.add(0, pcs.get(i));
                } else if (pcs.get(i).getMAC().equals(view.jlblMACDestino.getText())) {
                    pcod.add(1, pcs.get(i));
                }
            }
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una PC");
        }
    }
    //
    public void limpiarLblOSI() {
        view.jlblArchivoI.setIcon(null);
        view.jlblArchivoT.setText("");
        view.jlblTexto.setText("");
    }
    public void TipoDato() {
        if (view.jcbTipoDato.getSelectedIndex() == 0) {
            view.jbtnCargar.setVisible(false);
            view.jtxtDatoTexto.setVisible(true);
            view.jbtnTexto.setVisible(true);
            view.jlblArchivoT.setVisible(true);//
        }
        if (view.jcbTipoDato.getSelectedIndex() == 1) {
            view.jtxtDatoTexto.setVisible(false);
            view.jbtnTexto.setVisible(false);
            view.jlblArchivoT.setVisible(true);//
            view.jlblArchivoI.setVisible(true);//
            view.jbtnCargar.setVisible(true);
            
        }
    }
    
    public void CargarT() {
        try {
            view.jlblTexto.setText(view.jtxtDatoTexto.getText());
            view.jtxtDatoTexto.setText("");
            bytes = view.jlblTexto.getText().getBytes();
            mensaje = bytes.toString();
        } catch (Exception e) {
        }
    }
    
    public void CargarArchivoT() {
        String contenido = "";
        try {
            entrada = new FileInputStream(archivo);
            /*
            int ascci;
            while ((ascci = entrada.read()) != -1) {                
                char caracter = (char)ascci;
                contenido += caracter;
            }
            view.jlblArchivoT.setText(contenido);*/
            //
            entrada.read(bytes);
            view.jlblArchivoT.setText(new String(bytes));
            //view.jlblArchivoT.setText(bytes.toString());
            mensaje = bytes.toString();
            
            //
        } catch (Exception e) {            
        }     
    }
    
    public String GuardarArchivoT(File archivo, String contenido) {
        String respuesta = "";
        try {
            salida = new FileOutputStream(archivo);
            byte[] bytesTxt = contenido.getBytes();
            salida.write(bytesTxt);
            respuesta = "Se guardo con exito el archivo";
        } catch (Exception e) {
        }
        return respuesta;
    }
    
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
                } else {
                    if (archivo.getName().endsWith("jpg") || archivo.getName().endsWith("png") || archivo.getName().endsWith("gif")) {
                        CargarArchivoI();
                    } else {
                        JOptionPane.showMessageDialog(null, "ESCOGA OTRO");
                    }
                }
            }
        }
    }
    //
    public void OSi(){
        view.jlblMACO.setText(pcod.get(0).getMAC());
        view.jlblPCO.setText(pcod.get(0).getIP());
        view.jlblMACD.setText(pcod.get(1).getMAC());
        view.jlblPCD.setText(pcod.get(1).getIP());
        this.osi = new OSI(mensaje, pcod.get(0), pcod.get(1));
        osi.enviarMensaje();
    }
    
}
