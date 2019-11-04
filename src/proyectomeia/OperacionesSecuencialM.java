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
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;

/**
 *
 * @author garya
 */
public class OperacionesSecuencialM {
    

        public static LinkedList<Material> obtenerMateriales(int OpcionObtener){
        String contenido="";
        if(OpcionObtener==1){
             contenido=Lector.Obtener("C:/MEIA/Materiales/bitacora_Material.txt");
        }
        else if(OpcionObtener==2){
            contenido=Lector.Obtener("C:/MEIA/Materiales/Material.txt");
        }
        
        if(!contenido.isEmpty()){
            String[] Materiales=contenido.split(",");
            LinkedList<Material> Mat=new LinkedList<>();
            for (int i = 0; i < Materiales.length; i++){
                String[] camposMaterial=Materiales[i].split("\\|");
                Material MaterialObtenido=new Material(camposMaterial[0].trim(),camposMaterial[1].trim(),camposMaterial[2].trim(),camposMaterial[3].trim(),camposMaterial[4].trim(),Integer.parseInt(camposMaterial[5].trim()),Integer.parseInt(camposMaterial[6].trim()));               
                Mat.add(MaterialObtenido);
            }
            return Mat;            
        }   
        else{
            return null;
        }
    }
    public static void rellenarMateriales(LinkedList<Material> materiales){
        String textoEscribir="";
        for(Material mat:materiales){
           // String contraseña=user.getContraseña();
           // user.setContraseña(getMD5(contraseña));
            textoEscribir+=mat.toString()+",";
        }
        Escritor.Escribir("C:/MEIA/Materiales/Material.txt", textoEscribir);
    }
    public static void rellenarMaterialesMaestro(LinkedList<Material> materiales){
        String textoEscribir="";
        for(Material mat:materiales){
            textoEscribir+=mat.toFixedSizeString()+",";
        }
        Escritor.Escribir("C:/MEIA/Materiales/Material.txt", textoEscribir);
    }
    public static DescriptorMaterial obtenerDescriptorMaterial(int OpcionObtener){
          String contenido="";
        if(OpcionObtener==1){
            contenido=Lector.Obtener("C:/MEIA/Materiales/desc_Material.txt");
        }
        else if(OpcionObtener==2){
            contenido=Lector.Obtener("C:/MEIA/Materiales/desc_Materialbitacora.txt");
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
         
        return new DescriptorMaterial(st1[1],st2[1],st3[1],st4[1],st5[1],Integer.parseInt(num[1]),Integer.parseInt(num2[1]),Integer.parseInt(num3[1]),Integer.parseInt(num4[1]));
      
    }
    public static void rellenarDescriptorMaterial(DescriptorMaterial descriptor,int OpcionObtener){
        if(OpcionObtener==1){
            Escritor.Escribir("C:/MEIA/Materiales/desc_Material.txt", descriptor.toString());
        }
        else if(OpcionObtener==2){
           Escritor.Escribir("C:/MEIA/Materiales/desc_Materialbitacora.txt", descriptor.toString()); 
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
    public static void LlenarMaterialMaestro() throws IOException{
        LinkedList<Material> listaBitacora=obtenerMateriales(1);
        for (int i = 0; i < listaBitacora.size(); i++) {
            if(listaBitacora.get(i).getEstatus() == 0){
                listaBitacora.remove(i);
            }
            else{
                
            }
        }
        LinkedList<Material> listaUsuarios=obtenerMateriales(2);
        if(listaUsuarios!=null){
            for (int i = 0; i < listaUsuarios.size(); i++) {
            if(listaUsuarios.get(i).getEstatus() == 0){
                listaUsuarios.remove(i);
            }
        }
        }       
        DescriptorMaterial descUser=obtenerDescriptorMaterial(1);
        DescriptorMaterial descBitacora=obtenerDescriptorMaterial(2);
        LinkedList<Material> nuevaLista=new LinkedList<>();
        if(descUser.getNumRegistros()!=0){
            listaBitacora.forEach((material) -> {nuevaLista.add(material);});
            if(listaUsuarios!=null){
            listaUsuarios.forEach((material) -> {nuevaLista.add(material);}); 
            }
            }
        else{
            listaBitacora.forEach((material) -> {nuevaLista.add(material);});
        }
       // Collections.sort(nuevaLista, new CompareByName());
                 Collections.sort(nuevaLista, new Comparator<Material>() {

            @Override
            public int compare(Material o1, Material o2) {
                return o1.getNombre().compareTo(o2.getNombre());
            }
        });
        rellenarMaterialesMaestro(nuevaLista);
        Date fecha=new Date();
        descBitacora.setFechaModificacion(fecha.toString());
        descBitacora.setNumRegistros(0);
        descBitacora.setRegistrosActivos(0);
        descBitacora.setRegistrosInactivos(0);
        File bitacora=new File("C:/MEIA/Materiales/bitacora_Material.txt");
        bitacora.delete();
        bitacora.createNewFile();
        descUser.setFechaModificacion(fecha.toString());
        descUser.setNumRegistros(nuevaLista.size());
        descUser.setRegistrosActivos(contarActivos(nuevaLista,1));
        descUser.setRegistrosInactivos(contarActivos(nuevaLista,2));
        rellenarDescriptorMaterial(descUser,1);
        rellenarDescriptorMaterial(descBitacora,2); 
        
    }
    public static int contarActivos(LinkedList<Material> lista,int retorno){
        int contActivos=0;
        int contInactivos=0;
        int contretorno=0;
        for(Material user:lista){
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
