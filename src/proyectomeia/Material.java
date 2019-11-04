/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomeia;

import java.util.Comparator;

/**
 *
 * @author carlo
 */
public class Material {
    public String Nombre,tipo,path_imagen,usuario_transaccion,fecha_creacion;
    public int tiempo_degradarse,estatus;
    
    public Material(String Nombre, String tipo, String path_imagen,String usuario_transaccion, String fecha_creacion, int estatus, int tiempo_degradarse){
        this.Nombre =Nombre;
        this.tipo = tipo;
        this.path_imagen = path_imagen;
        this.usuario_transaccion = usuario_transaccion;
        this.fecha_creacion = fecha_creacion;
        this.estatus = estatus;
        this.tiempo_degradarse = tiempo_degradarse;
    }
    
    
        public String toFixedSizeString(){
        return String.format("%-20s", Nombre) + "|" + String.format("%-30s", tipo) + "|" + String.format("%-30s", path_imagen) + "|" + 
               String.format("%-40s", usuario_transaccion) + "|" + String.format("%-10s", fecha_creacion) + "|" + String.format("%-40s", estatus) + "|" + 
               String.format("%-200s", tiempo_degradarse); //+ "|" + String.format("%-12s", telefono) + "|" + rol + "|" + estatus;                
    }
        
    @Override
    public String toString() {
        return Nombre + "|" + tipo + "|" + path_imagen + "|" + usuario_transaccion + "|" + fecha_creacion + "|" + estatus + "|" + tiempo_degradarse;
    }   

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPath_imagen(String path_imagen) {
        this.path_imagen = path_imagen;
    }

    public void setUsuario_transaccion(String usuario_transaccion) {
        this.usuario_transaccion = usuario_transaccion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public void setTiempo_degradarse(int tiempo_degradarse) {
        this.tiempo_degradarse = tiempo_degradarse;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getPath_imagen() {
        return path_imagen;
    }

    public String getUsuario_transaccion() {
        return usuario_transaccion;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public int getEstatus() {
        return estatus;
    }

    public int getTiempo_degradarse() {
        return tiempo_degradarse;
    }
    
            class CompareByNme implements Comparator<Material> {
    @Override
    public int compare(Material o1, Material o2) {
          return o1.getNombre().compareTo(o2.getNombre());
    }
    
}
}
