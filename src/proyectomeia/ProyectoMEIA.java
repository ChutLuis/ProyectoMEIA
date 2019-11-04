/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomeia;

import java.io.IOException;

/**
 *
 * @author garya
 */
public class ProyectoMEIA {

    /**
     * @param args the command line arguments
     */
    public static Usuario usuarioEnUso;
    public static void main(String[] args) throws IOException {
        usuarioEnUso = null;
        PantallaCarga pantalla = new PantallaCarga();
        pantalla.setVisible(true);
        // TODO code application logic here        
    }
    
}
