/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomeia;

import java.util.Comparator;

/**
 *
 * @author luise
 */
public class Usuario {
    private String usuario,nombre,apellido,contraseña,correo,
            pathFoto,fechaNacimiento;  
    
    private int rol,telefono,estatus, FixedSize;  

    public static int getFixedSize() {
        return 393;
    }

    public void setFixedSize(int FixedSize) {
        this.FixedSize = 393;
    }

    @Override
    public String toString() {
        return usuario + "|" + nombre + "|" + apellido + "|" + contraseña + "|" + fechaNacimiento + "|" + correo + "|" + pathFoto + "|" + telefono + "|" + rol + "|" + estatus;
    }     
    
    public String toFixedSizeString(){
        return String.format("%-20s", usuario) + "|" + String.format("%-30s", nombre) + "|" + String.format("%-30s", apellido) + "|" + 
               String.format("%-40s", contraseña) + "|" + String.format("%-10s", fechaNacimiento) + "|" + String.format("%-40s", correo) + "|" + 
               String.format("%-200s", pathFoto) + "|" + String.format("%-12s", telefono) + "|" + rol + "|" + estatus;                
    }
    

    public Usuario(String usuario, String nombre, String apellido, String contraseña, String fechaNacimiento,String correo, 
            String pathFoto, int telefono, int estatus) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contraseña = contraseña;
        this.correo = correo;
        this.pathFoto = pathFoto;
        this.telefono = telefono;
        this.estatus = estatus;
        this.fechaNacimiento=fechaNacimiento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPathFoto() {
        return pathFoto;
    }

    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }
     public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fecha) {
        this.fechaNacimiento = fecha;
    }

}
class CompareByName implements Comparator<Usuario> {
    @Override
    public int compare(Usuario o1, Usuario o2) {
          return o1.getUsuario().compareTo(o2.getUsuario());
    }

}
