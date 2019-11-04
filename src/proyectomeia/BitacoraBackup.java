/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomeia;

import java.io.File;

/**
 *
 * @author garya
 */
public class BitacoraBackup {
    private String rutaAbsoluta;
    private String usuario;
    private String fecha;

    public BitacoraBackup(String rutaAbsoluta, String usuario, String fecha) {
        this.rutaAbsoluta = rutaAbsoluta;
        this.usuario = usuario;
        this.fecha = fecha;
    }
    @Override
    public String toString() {
        return "rutaAbsoluta=" + rutaAbsoluta + "| usuario=" + usuario + "| fecha=" + fecha +",";
    }

    public String getRutaAbsoluta() {
        return rutaAbsoluta;
    }

    public void setRutaAbsoluta(String rutaAbsoluta) {
        this.rutaAbsoluta = rutaAbsoluta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
