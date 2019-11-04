/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomeia;

import javax.swing.JProgressBar;

/**
 *
 * @author luise
 */
public class cargar extends Thread{
    
    JProgressBar progreso;
    
    public cargar(JProgressBar progreso){
        super();
        this.progreso = progreso;
    }
    
    @Override
    public void run(){
        for (int i = 0; i <= 100; i++) {
            progreso.setValue(i);
            pausa(15);
        }
    }
    
    public void pausa(int seg){
        try {
            Thread.sleep(seg);
        } catch (InterruptedException ex) {
            
        }
    }
    
}
