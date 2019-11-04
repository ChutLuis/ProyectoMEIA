/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomeia;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Luis Emilio
 */
public class DescriptorIndiceDonaciones {
    
    public String nombreArchivo;
    public String fechaCreacion;
    public String usuarioCreacion;
    public int NoBloques;
    public int registroInicial;
    
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchvio) {
        this.nombreArchivo = nombreArchivo ;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' hh:mm");
        String FechaActual=ft.format(date);
        this.fechaCreacion = FechaActual;
    }
    
    public void setFechaCreacion2(String FechaActual) {
        this.fechaCreacion = FechaActual;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public int getNoBloques() {
        return NoBloques;
    }

    public void setNoBloques(int noBloques) {
        this.NoBloques = noBloques;
    }


    public int getRegistroInicial() {
        return registroInicial;
    }

    public void setRegistroInicial(int registroInicial) {
        this.registroInicial = registroInicial;
    }
}
