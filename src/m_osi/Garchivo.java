package m_osi;

import java.io.*;

public class Garchivo {
    FileInputStream entrada;
    File archivo;

    public Garchivo() {
    }
    
    public byte[] ConvertirImagen(File archivo) {
        byte[] byteimg = new byte[1024*100];
        try{
            entrada = new FileInputStream(archivo);
            entrada.read(byteimg);
        }catch (Exception e){            
        }
        return byteimg;
    }
}
