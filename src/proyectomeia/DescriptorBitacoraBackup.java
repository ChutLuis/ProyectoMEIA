/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomeia;

/**
 *
 * @author garya
 */
public class DescriptorBitacoraBackup {
    String nombre=" ",fechaCreacion=" ",usuarioCreacion=" ",fechaModificacion=" ",usuarioModificacion=" ";
        int numRegistros=0;

    @Override
    public String toString() {
        return "Nombre:"+nombre + "," + "FechaCreacion:"+fechaCreacion + "," +"UsuarioCreacion:"+ usuarioCreacion + "," +"FechaModificacion:"+ fechaModificacion + "," + "UsuarioMod:"+ usuarioModificacion + "," +"TotalRegistros:"+ numRegistros;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public int getNumRegistros() {
        return numRegistros;
    }

    public void setNumRegistros(int numRegistros) {
        this.numRegistros = numRegistros;
    }

}
