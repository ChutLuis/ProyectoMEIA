/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomeia;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author luise
 */
class LimitarCaracteres extends PlainDocument {
    private int limite;

    public LimitarCaracteres(int i) {
        super();
        this.limite = i;
    }
    
    public void insertString(int compensacion, String cadena, AttributeSet attributeset)throws BadLocationException{
        if(cadena == null) return;
        if(getLength()+cadena.length() <= limite){
            super.insertString(compensacion, cadena, attributeset);
        }
    }
    
}
