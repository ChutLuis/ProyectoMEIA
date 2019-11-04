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
public class DescriptorBloqueDonaciones {
    
    public String nombreArchivo;
    public String fechaCreacion;
    public String usuarioCreacion;
    public int numRegistros;
    public int registrosActivos;
    public int registrosInactivos;
    public int MaxRegistros = 10;
    
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String NombreArchivo) {
        this.nombreArchivo = NombreArchivo ;
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

    public int getNumRegistros() {
        return numRegistros;
    }

    public void setNumRegistros(int numRegistros) {
        this.numRegistros = numRegistros;
    }

    public int getRegistrosActivos() {
        return registrosActivos;
    }

    public void setRegistrosActivos(int registrosActivos) {
        this.registrosActivos = registrosActivos;
    }

    public int getRegistrosInactivos() {
        return registrosInactivos;
    }

    public void setRegistrosInactivos(int registrosInactivos) {
        this.registrosInactivos = registrosInactivos;
    }

    public int getMaxRegistros() {
        return MaxRegistros;
    }
}
