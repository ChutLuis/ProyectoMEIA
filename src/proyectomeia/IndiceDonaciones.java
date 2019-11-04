 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomeia;

/**
 *
 * @author Luis Emilio
 */
public class IndiceDonaciones {
    public int Registro;
    public double posicion;
    public String usuario;
    public String nombre_Material;
    public String fecha;
    public int siguiente;
    public int estatus;
    
    
    public int getRegistro() {
        return Registro;
    }

    public void setRegistro(int numeroRegistro) {
        this.Registro = numeroRegistro;
    }

    public double getPosicion() {
        return posicion;
    }

    public void setPosicion(double posicion) {
        this.posicion = posicion;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getNombreMaterial() {
        return nombre_Material;
    }

    public void setNombreMaterial(String Material) {
        this.nombre_Material = Material;
    }
    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
        public int getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(int siguiente) {
        this.siguiente = siguiente;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }
    
    public void ObtenerDatos(String[] Datos){
        this.Registro = Integer.parseInt(Datos[0]);
        this.posicion = Double.parseDouble(Datos[1]);
        this.usuario = Datos[2];
        this.nombre_Material = Datos[3];
        this.fecha = Datos[4];
        this.siguiente = Integer.parseInt(Datos[5]);
        this.estatus = Integer.parseInt(Datos[6]);
    }
}
