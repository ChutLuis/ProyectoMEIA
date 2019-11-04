/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomeia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Luis Emilio
 */
public class ArchivoSecuencialIndizado {
    
    public DescriptorIndiceDonaciones DID = new DescriptorIndiceDonaciones();
    public DescriptorBloqueDonaciones DBD = new DescriptorBloqueDonaciones();
    
    public void ObtenerDatos_Desc(){
        int NumeroBloque=ComprobacionArchivoSiguiente();
        try{
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/desc_Bloque_Donaciones_"+(NumeroBloque)+".txt"));
            String linea=Archivo.readLine();
            String[] datos;
            while(linea != null){
                datos = linea.split("\\|");
                if(datos[0].equals("Nombre de Archivo")){
                    DBD.setNombreArchivo(datos[1]);
                }
                if(datos[0].equals("Usuario Creador")){
                    DBD.setUsuarioCreacion(datos[1]);
                }
                if(datos[0].equals("Fecha Creacion")){
                    DBD.setFechaCreacion2(datos[1]);
                }
                if(datos[0].equals("TotalRegistros")){
                    DBD.setNumRegistros(Integer.parseInt(datos[1]));
                }
                if(datos[0].equals("RegistrosActivos")){
                    DBD.setRegistrosActivos(Integer.parseInt(datos[1]));
                }
                if(datos[0].equals("RegistrosInactivos")){
                    DBD.setRegistrosInactivos(Integer.parseInt(datos[1]));
                }
                linea = Archivo.readLine();
            }
            Archivo.close();
            BufferedReader Archivo2 = new BufferedReader(new FileReader("C:/MEIA/desc_Indice_Donaciones.txt"));
            String linea2=Archivo2.readLine();
            String[] datos2;
            while(linea2 != null){
                datos2 = linea2.split("\\|");
                if(datos2[0].equals("Nombre del Archivo")){
                    DID.setNombreArchivo(datos2[1]);
                }
                if(datos2[0].equals("Usuario Creador")){
                    DID.setUsuarioCreacion(datos2[1]);
                }
                if(datos2[0].equals("Fecha Creacion")){
                    DID.setFechaCreacion2(datos2[1]);
                }
                if(datos2[0].equals("RegistroInicial")){
                    DID.setRegistroInicial(Integer.parseInt(datos2[1]));
                }
                if(datos2[0].equals("NoBloques")){
                    DID.setNoBloques(Integer.parseInt(datos2[1]));
                }
                linea2 = Archivo2.readLine();
            }
            Archivo2.close();
        }
        catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void EscribirArchivos(String[] Campos, String datos){
        try {
                    //Crear Bloque 1
                    FileWriter Escribir = new FileWriter("C:/MEIA/Bloque_Donaciones_1.txt",false);
                    BufferedWriter bw1 = new BufferedWriter(Escribir);
                    bw1.write(datos + System.getProperty("line.separator"));
                    bw1.close();
                    Escribir.close();
                    //Crear Desc_Bloque 1
                    FileWriter Escribir2 = new FileWriter("C:/MEIA/desc_Bloque_Donaciones_1.txt",false);
                    BufferedWriter bw = new BufferedWriter(Escribir2);
                    bw.write("Nombre de Archivo|Bloque_Donaciones_1.txt"+System.getProperty("line.separator"));
                    bw.write("Usuario Creador|"+Campos[0]+System.getProperty("line.separator"));
                    bw.write("Fecha Creacion|"+Campos[7]+System.getProperty("line.separator"));
                    bw.write("TotalRegistros|1"+System.getProperty("line.separator"));
                    bw.write("RegistrosActivos|1"+System.getProperty("line.separator"));
                    bw.write("RegistrosInactivos|0"+System.getProperty("line.separator"));
                    bw.write("MaxRegistros|" + DBD.getMaxRegistros() +System.getProperty("line.separator"));
                    bw.write("Atributos|Usuario, nombre_material, Fecha,Peso, Descripción, Evento, Usuario_transacción, Fecha_creacion,Estatus");
                    bw.close();
                    Escribir2.close();
                    //Crear Indice Donaciones
                    FileWriter EscribirIndice = new FileWriter("C:/MEIA/Indice_Donaciones.txt",true);
                    BufferedWriter bw2 = new BufferedWriter(EscribirIndice);
                    bw2.write("1|1.1|" + Campos[0] + "|" + Campos[1] + "|"+Campos[2] + "|0|1" + System.getProperty("line.separator"));
                    bw2.close();
                    EscribirIndice.close();
                    //Crear Descriptor Indice
                    FileWriter EscribirDescIndice = new FileWriter("C:/MEIA/desc_Indice_Donaciones.txt",false);
                    BufferedWriter bw3 = new BufferedWriter(EscribirDescIndice);
                    bw3.write("Nombre del Archivo|Indice_Donaciones.txt"+System.getProperty("line.separator"));
                    bw3.write("Usuario Creador|"+Campos[0]+System.getProperty("line.separator"));
                    bw3.write("Fecha Creacion|"+Campos[7]+System.getProperty("line.separator"));
                    bw3.write("RegistroInicial|1"+System.getProperty("line.separator"));
                    bw3.write("NoBloques|1"+System.getProperty("line.separator"));
                    bw3.write("Usuario,Nombre_Material,fecha");
                    bw3.close();
                    EscribirDescIndice.close();
                    JOptionPane.showMessageDialog(null, "Material Donado correctamente");
                } catch (IOException ex) {
                    Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Material no donado");
                }
    }
    
    
    
    public void Insertar(String datos)
    {
        String[]Campos=datos.split("\\|");
        if (Campos.length==9)
        {
            int NumeroBloque=ComprobacionArchivoSiguiente();
            if (NumeroBloque==0) {
                //--------------------------------------Crear Archivos y primer bloque
                EscribirArchivos(Campos, datos);
            }
            else
            {   
                ObtenerDatos_Desc();
                int NumeroBloqueAEscribir = VerificarDescriptorBloque(NumeroBloque);
                if (DBD.getMaxRegistros() == DBD.getNumRegistros()) {
                    try {
                        DBD.setNombreArchivo("Bloque_Donaciones_"+(NumeroBloqueAEscribir+1)+".txt");
                        DBD.setFechaCreacion();
                        DBD.setUsuarioCreacion("Admin");
                        DBD.setNumRegistros(1);
                        DBD.setRegistrosActivos(1);
                        DBD.setRegistrosInactivos(0);
                        //----------------------------------------------Se crea un nuevo Bloque
                        //Crear Nuevo Bloque
                        FileWriter Escribir = new FileWriter("C:/MEIA/Bloque_Donaciones_"+(NumeroBloqueAEscribir+1)+".txt",true);
                        BufferedWriter bw1 = new BufferedWriter(Escribir);
                        bw1.write(datos);
                        bw1.close();
                        Escribir.close();
                        //Actualizar descriptor
                        FileWriter Escribir2 = new FileWriter("C:/MEIA/desc_Bloque_Donaciones_"+(NumeroBloqueAEscribir+1)+".txt",false);
                        BufferedWriter bw = new BufferedWriter(Escribir2);
                        bw.write("Nombre del Archivo|" + DBD.getNombreArchivo() +System.getProperty("line.separator"));
                        bw.write("Usuario Creador|" + "Admin" + System.getProperty("line.separator"));
                        bw.write("Fecha Creacion|" + DBD.getFechaCreacion() + System.getProperty("line.separator"));
                        bw.write("TotalRegistros|"+ DBD.getNumRegistros() + System.getProperty("line.separator"));
                        bw.write("RegistrosActivos|"+ DBD.getRegistrosActivos() + System.getProperty("line.separator"));
                        bw.write("RegistrosInactivos|"+ DBD.getRegistrosInactivos() +System.getProperty("line.separator"));
                        bw.write("MaxRegistros|" + DBD.getMaxRegistros() +System.getProperty("line.separator"));
                        bw.write("Atributos|Usuario, Nombre_material, Fecha, Peso, Descripción, Evento, Usuario_transacción, Fecha_creacion,Estatus");
                        bw.close();
                        Escribir2.close();
                        //Actualizar Indice
                        String registro[] = {String.valueOf(VerificarCantRegistrosIndice(NumeroBloqueAEscribir)+1), (NumeroBloqueAEscribir+"."+VerificarSiguiente(NumeroBloqueAEscribir)), Campos[0], Campos[1], Campos[2], "0", "1"};
                        ObtenerSiguiente(registro);
                        //Actualizar Descriptor Indice
                        FileWriter EscribirDescIndice = new FileWriter("C:/MEIA/desc_Indice_Donaciones.txt",false);
                        BufferedWriter bw4 = new BufferedWriter(EscribirDescIndice);
                        bw4.write("Nombre del Archivo|Indice_Donaciones.txt"+System.getProperty("line.separator"));
                        bw4.write("Usuario Creador|"+ "Admin" +System.getProperty("line.separator"));
                        bw4.write("Fecha Creacion|"+ DID.getFechaCreacion() +System.getProperty("line.separator"));
                        bw4.write("RegistroInicial|"+ DID.getRegistroInicial() +System.getProperty("line.separator"));
                        bw4.write("NoBloques|"+ DID.getNoBloques()+1 +System.getProperty("line.separator"));
                        bw4.write("Usuario,Nombre_Material,fecha");
                        bw4.close();
                        EscribirDescIndice.close();
                        JOptionPane.showMessageDialog(null, "Bloque creado correctamente");
                        JOptionPane.showMessageDialog(null, "Material Donado correctamente");
                    } catch (IOException ex) {
                        Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, "Bloque no creado correctamente");
                        JOptionPane.showMessageDialog(null, "Material no donado");
                    } 
                }
                else{
               try {
                   //---------------------------Insertar nueva donacion en un bloque existente
                    FileWriter Escribir = new FileWriter("C:/MEIA/Bloque_Donaciones_"+NumeroBloqueAEscribir+".txt",true);
                    BufferedWriter bw=new BufferedWriter(Escribir);
                    bw.write(datos+System.getProperty("line.separator"));
                    bw.close();
                    Escribir.close();
                    //Actualizar descriptor
                    FileWriter Escribir2 = new FileWriter("C:/MEIA/desc_Bloque_Donaciones_"+NumeroBloqueAEscribir+".txt",false);
                    BufferedWriter bw2 = new BufferedWriter(Escribir2);
                    bw2.write("Nombre del Archivo|" + DBD.getNombreArchivo() +System.getProperty("line.separator"));
                    bw2.write("Usuario Creador|" + "Admin" + System.getProperty("line.separator"));
                    bw2.write("Fecha Creacion|" + DBD.getFechaCreacion() + System.getProperty("line.separator"));
                    bw2.write("TotalRegistros|"+ (DBD.getNumRegistros()+1)  + System.getProperty("line.separator"));
                    bw2.write("RegistrosActivos|"+ (DBD.getRegistrosActivos()+1) + System.getProperty("line.separator"));
                    bw2.write("RegistrosInactivos|"+ DBD.getRegistrosInactivos() +System.getProperty("line.separator"));
                    bw2.write("MaxRegistros|" + DBD.getMaxRegistros() +System.getProperty("line.separator"));
                    bw2.write("Atributos|Usuario, Nombre_material, Fecha, Peso, Descripción, Evento, Usuario_transacción, Fecha_creacion,Estatus");                        
                    bw2.close();
                    Escribir2.close();
                    //Actualizar Indice
                    String reg = (VerificarCantRegistrosIndice(NumeroBloqueAEscribir)+1) + "|" + NumeroBloqueAEscribir+"."+VerificarSiguiente(NumeroBloqueAEscribir)+ "|" + Campos[0] + "|" + Campos[1]+ "|" + Campos[2] + "|0|1";
                    String registro[] = reg.split("\\|");
                    ObtenerSiguiente(registro);
                    //Actualizar Descriptor Indice
                    FileWriter EscribirDescIndice = new FileWriter("C:/MEIA/desc_Indice_Donaciones.txt",false);
                    BufferedWriter bw4 = new BufferedWriter(EscribirDescIndice);
                    bw4.write("Nombre del Archivo|Indice_Donaciones.txt"+System.getProperty("line.separator"));
                    bw4.write("Usuario Creador|"+ "Admin" +System.getProperty("line.separator"));
                    bw4.write("Fecha Creacion|"+ DID.getFechaCreacion() +System.getProperty("line.separator"));
                    bw4.write("RegistroInicial|"+ DID.getRegistroInicial() +System.getProperty("line.separator"));
                    bw4.write("NoBloques|"+ DID.getNoBloques()+1 +System.getProperty("line.separator"));
                    bw4.write("Usuario,Nombre_Material,fecha");
                    bw4.close();
                    EscribirDescIndice.close();
                    OrganizarIndice();
                    JOptionPane.showMessageDialog(null, "Material Donado correctamente");
                } catch (IOException ex) {
                    Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Material no donado");
                }  
            }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Los campos ingresados no tienen el formato requerido");
        }
    }
    
    public void ObtenerSiguiente(String[] Campos){
        //Formato: Registro|Posicion|Usuario|NombreMaterial|Fecha|Siguiente|Estatus
        try{         
            List<IndiceDonaciones> Indice_Donaciones = new ArrayList<IndiceDonaciones>();
            IndiceDonaciones Temporal = new IndiceDonaciones();
            IndiceDonaciones Anterior = new IndiceDonaciones();
            IndiceDonaciones Nuevo = new IndiceDonaciones();
            Nuevo.ObtenerDatos(Campos);
            
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/Indice_Donaciones.txt"));
            String linea=Archivo.readLine();
            String Datos[];
            while(linea != null){
                Datos = linea.split("\\|");                
                Temporal.ObtenerDatos(Datos);
                Indice_Donaciones.add(Temporal);
                if(Datos[0] == String.valueOf(DID.getRegistroInicial())){
                    Anterior.ObtenerDatos(Datos);
                }
                linea = Archivo.readLine();
            }
            
            boolean salir = true;
            while(salir){
                if(Anterior.siguiente == 0){
                    Anterior.siguiente = Nuevo.Registro;
                    Indice_Donaciones.add(Nuevo);
                    salir = false;
                }
                else{
                    
                }
            }
            Archivo.close();            
            FileWriter EscribirIndice = new FileWriter("C:/MEIA/Indice_Donaciones.txt",true);
            BufferedWriter bw3 = new BufferedWriter(EscribirIndice);
            for(int i=0; i < Indice_Donaciones.size(); i++){
                bw3.write(Indice_Donaciones.get(i).Registro + "|" + Indice_Donaciones.get(i).posicion + "|" + Indice_Donaciones.get(i).usuario + "|" + Indice_Donaciones.get(i).nombre_Material + "|" + Indice_Donaciones.get(i).fecha + "|" + Indice_Donaciones.get(i).siguiente + "|" + Indice_Donaciones.get(i).estatus + System.getProperty("line.separator"));
            }
            bw3.close();
            EscribirIndice.close();
            
        }catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    //Retorna un 1 si la variable1 es menor, 2 si la variable2 es menor, 3 si son iguales
    public int CompararLLave(String[] Variable1, String[] Variable2){
        if(Variable1[2].compareTo(Variable2[2]) == 0){
            if(Variable1[3].compareTo(Variable2[3]) == 0){
                if(Variable1[4].compareTo(Variable2[4]) == 0){
                    return 3;
                }
                else if(Variable1[4].compareTo(Variable2[4]) < 0){
                    return 1;
                }
                else{
                    return 2;
                }
            }
            else if(Variable1[3].compareTo(Variable2[3]) < 0){
                return 1;
            }
            else{
                return 2;
            }
        }
        else if(Variable1[2].compareTo(Variable2[2]) < 0){
            return 1;
        }
        else{
            return 2;
        }
    }
    
    public int ComprobacionArchivoSiguiente(){
		int NoBloque = 0;
                File folder = new File("C:/MEIA");
                File[] listOfFiles = folder.listFiles();                 
		if(listOfFiles.length >0){
			for(int i = 0; i < listOfFiles.length;i++){
				if(listOfFiles[i].getAbsolutePath().contains("C:\\MEIA\\Bloque_Donaciones_")){
					NoBloque++;
				}
			}
                }
                return NoBloque;
    }
    
    
   	
    public void OrganizarIndice()
    {
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/Indice_Donaciones.txt"));
            String linea=Archivo.readLine();
            List<String> elementosArchivo = new ArrayList<String>();
            int aux =0;
            while(linea!=null){
                String[] campos=linea.split("\\|");
                String llave=campos[2]+campos[3]+campos[4]+"|"+aux;
                elementosArchivo.add(llave);
                linea=Archivo.readLine();
                aux++;
            }
            Archivo.close();
            Collections.sort(elementosArchivo);
            int auxiliar=0;
            BufferedReader Archivo2 = new BufferedReader(new FileReader("C:/MEIA/Indice_Donaciones.txt"));
            linea=Archivo2.readLine(); 
            List<String> ListaEscribir = new ArrayList<String>();
            while(linea!=null){
                ListaEscribir.add(linea);
                linea=Archivo2.readLine();
            }
            List<String> ListaFinal = new ArrayList<String>();

            for (int i = 0; i < ListaEscribir.size(); i++) {
                for (int j = 0; j < elementosArchivo.size(); j++) {
                    String Recorriendo=elementosArchivo.get(j);
                    if (ListaEscribir.get(i).contains(Recorriendo)) {
                        try{
                        String siguiente= elementosArchivo.get(j+1);
                            for (int k = 0; k < ListaEscribir.size(); k++) {
                                if (ListaEscribir.get(k).contains(siguiente)) {
                                    String[] Siguiente=ListaEscribir.get(k).split("\\|");
                                    String Escribir =ListaEscribir.get(i);
                                    String[] Arreglo = Escribir.split("\\|");
                                    ListaFinal.add(Arreglo[0]+"|"+Arreglo[1]+"|"+Arreglo[2]+"|"+Arreglo[3]+"|"+Arreglo[4]+"|"+Siguiente[0]+"|"+Arreglo[6]);
                                }
                            }
                        }finally{
                           String Escribir =ListaEscribir.get(i);
                           String[] Arreglo = Escribir.split("\\|"); 
                           ListaFinal.add(Arreglo[0]+"|"+Arreglo[1]+"|"+Arreglo[2]+"|"+Arreglo[3]+"|"+Arreglo[4]+"|null|"+Arreglo[6]);                       
                        }
                    }
                }
            }
            Archivo2.close();
            FileWriter Escribir = new FileWriter("C:/MEIA/Indice_Donaciones.txt",true);
            for (int i = 0; i < ListaFinal.size(); i++) {
                Escribir.write(ListaFinal.get(i));
            }
            Escribir.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    //Verificar Valores
     public int VerificarDescriptorBloque(int BloqueAVerificar)
    {
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/desc_Bloque_Donaciones_"+BloqueAVerificar+".txt"));
            String numeroReg=(Archivo.readLine()).split("\\|")[1];
            String numeroMax=(Archivo.readLine()).split("\\|")[1];
            Archivo.close();
            if (numeroReg.equals(numeroMax)) {
                //CrearNuevoBloque
                FileWriter Escribir = new FileWriter("C:/MEIA/Bloque_Donaciones_"+(BloqueAVerificar+1)+".txt",false);
                Escribir.close();
                FileWriter Escribir2 = new FileWriter("C:/MEIA/desc_Bloque_Donaciones_"+(BloqueAVerificar+1)+".txt",false);
                    BufferedWriter bw = new BufferedWriter(Escribir2);
                    bw.write("TotalRegistros|1"+System.getProperty("line.separator"));
                    bw.write("MaxRegistros|10"+System.getProperty("line.separator"));
                    bw.close();
                    Escribir2.close();          
                    return BloqueAVerificar+1;
            }           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return BloqueAVerificar;
    }
       
    public int VerificarCantRegistrosIndice(int NumBloque)
    {
    int numeroReg=0;
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/Indice_Donaciones.txt"));
            while(Archivo.readLine()!=null)   {
                numeroReg++;
            }
            Archivo.close();
    } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return (numeroReg);  
    }    
    public int VerificarSiguiente(int NumBloque)
    {        
    int numeroReg=0;
        try {
            String path = "C:/MEIA/Bloque_Donaciones_"+NumBloque+".txt";
            BufferedReader Archivo = new BufferedReader(new FileReader(path));
            while(Archivo.readLine()!=null)   {
                numeroReg++;
            }
            Archivo.close();
            
    } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return (numeroReg);     
    }
       
}
