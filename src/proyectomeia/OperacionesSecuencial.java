/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomeia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;

/**
 *
 * @author garya
 */
public class OperacionesSecuencial {
    

        public static LinkedList<Usuario> obtenerUsuarios(int OpcionObtener){
        String contenido="";
        if(OpcionObtener==1){
             contenido=Lector.Obtener("C:/MEIA/bitacora_usuario.txt");
        }
        else if(OpcionObtener==2){
            contenido=Lector.Obtener("C:/MEIA/usuario.txt");
        }
        
        if(!contenido.isEmpty()){
            String[] usuarios=contenido.split(",");
            LinkedList<Usuario> users=new LinkedList<>();
            for (int i = 0; i < usuarios.length; i++){
                String[] camposUsuario=usuarios[i].split("\\|");
                Usuario usuarioObtenido=new Usuario(camposUsuario[0].trim(),camposUsuario[1].trim(),camposUsuario[2].trim(),camposUsuario[3].trim(),camposUsuario[4].trim(),camposUsuario[5].trim(),camposUsuario[6].trim(),Integer.parseInt(camposUsuario[7].trim()),Integer.parseInt(camposUsuario[9].trim()));
                usuarioObtenido.setRol(Integer.parseInt(camposUsuario[8].trim()));
                users.add(usuarioObtenido);
            }
            return users;            
        }   
        else{
            return null;
        }
    }
    public static void rellenarUsuarios(LinkedList<Usuario> usuarios){
        String textoEscribir="";
        for(Usuario user:usuarios){
            String contraseña=user.getContraseña();
            user.setContraseña(getMD5(contraseña));
            textoEscribir+=user.toString()+",";
        }
        Escritor.Escribir("C:/MEIA/usuario.txt", textoEscribir);
    }
    public static void rellenarUsuariosMaestro(LinkedList<Usuario> usuarios){
        String textoEscribir="";
        for(Usuario user:usuarios){
            textoEscribir+=user.toFixedSizeString()+",";
        }
        Escritor.Escribir("C:/MEIA/usuario.txt", textoEscribir);
    }
    public static DescriptorUsuario obtenerDescriptorUsuario(int OpcionObtener){
          String contenido="";
        if(OpcionObtener==1){
            contenido=Lector.Obtener("C:/MEIA/desc_usuario.txt");
        }
        else if(OpcionObtener==2){
            contenido=Lector.Obtener("C:/MEIA/desc_usuariobitacora.txt");
        }
        String[] campos=contenido.split(",");
        String[] st1 = campos[0].split(":");
        String[] st2 = campos[1].split(":");
        String[] st3 = campos[2].split(":");
        String[] st4 = campos[3].split(":");
        String[] st5 = campos[4].split(":");
        
        
        String[] num = campos[5].split(":");
        String[] num2 = campos[6].split(":");
        String[] num3 = campos[7].split(":");
        String[] num4 = campos[8].split(":");
         
        return new DescriptorUsuario(st1[1],st2[1],st3[1],st4[1],st5[1],Integer.parseInt(num[1]),Integer.parseInt(num2[1]),Integer.parseInt(num3[1]),Integer.parseInt(num4[1]));
      
    }
    public static void rellenarDescriptorUsuario(DescriptorUsuario descriptor,int OpcionObtener){
        if(OpcionObtener==1){
            Escritor.Escribir("C:/MEIA/desc_usuario.txt", descriptor.toString());
        }
        else if(OpcionObtener==2){
           Escritor.Escribir("C:/MEIA/desc_usuariobitacora.txt", descriptor.toString()); 
        }
        
    }
    public static DescriptorBitacoraBackup obtenerDescriptorBB(){
        String contenido="";
            contenido=Lector.Obtener("C:/MEIA/desc_bitacora_backup.txt");
        
        String[] campos=contenido.split(",");
       DescriptorBitacoraBackup desc=new DescriptorBitacoraBackup();
       desc.setNombre(campos[0]);
       desc.setFechaCreacion(campos[1]);
       desc.setUsuarioCreacion(campos[2]);
       desc.setFechaModificacion(campos[3]);
       desc.setUsuarioModificacion(campos[4]);
       desc.setNumRegistros(Integer.parseInt(campos[5]));
        return desc;
    }
    public static void LlenarUsuariosMaestro() throws IOException{
        LinkedList<Usuario> listaBitacora=obtenerUsuarios(1);
        for (int i = 0; i < listaBitacora.size(); i++) {
            if(listaBitacora.get(i).getEstatus() == 0){
                listaBitacora.remove(i);
            }
        }
        LinkedList<Usuario> listaUsuarios=obtenerUsuarios(2);
        if(listaUsuarios!=null){
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if(listaUsuarios.get(i).getEstatus() == 0){
                listaUsuarios.remove(i);
            }
        }
        }
        DescriptorUsuario descUser=obtenerDescriptorUsuario(1);
        DescriptorUsuario descBitacora=obtenerDescriptorUsuario(2);
        LinkedList<Usuario> nuevaLista=new LinkedList<>();
        if(descUser.getNumRegistros()!=0){
            listaBitacora.forEach((user) -> {nuevaLista.add(user);});
            if(listaUsuarios!=null){
            listaUsuarios.forEach((user) -> {nuevaLista.add(user);});
            }
            } 
        else{
            listaBitacora.forEach((user) -> {nuevaLista.add(user);});
        }
        Collections.sort(nuevaLista, new CompareByName());
        rellenarUsuariosMaestro(nuevaLista);
        Date fecha=new Date();
        descBitacora.setFechaModificacion(fecha.toString());
        descBitacora.setNumRegistros(0);
        descBitacora.setRegistrosActivos(0);
        descBitacora.setRegistrosInactivos(0);
        File bitacora=new File("C:/MEIA/bitacora_usuario.txt");
        bitacora.delete();
        bitacora.createNewFile();
        descUser.setFechaModificacion(fecha.toString());
        descUser.setNumRegistros(nuevaLista.size());
        descUser.setRegistrosActivos(contarActivos(nuevaLista,1));
        descUser.setRegistrosInactivos(contarActivos(nuevaLista,2));
        rellenarDescriptorUsuario(descUser,1);
        rellenarDescriptorUsuario(descBitacora,2); 
        
    }
    public static int contarActivos(LinkedList<Usuario> lista,int retorno){
        int contActivos=0;
        int contInactivos=0;
        int contretorno=0;
        for(Usuario user:lista){
            if(user.getEstatus()==1){
                contActivos++;
            }      
            else if(user.getEstatus()==0){
                contInactivos++;
            }
                
        }
        if(retorno==1){
            contretorno=contActivos;
        }
        else if(retorno==2){
            contretorno=contInactivos;
        }
        return contretorno;
    }
    public static String getMD5(String input) {
        try {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        String hashtext = number.toString(16);

        while (hashtext.length() < 32) {
        hashtext = "0" + hashtext;
        }
        return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
        }
 }
}
