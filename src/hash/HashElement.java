/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash;

/**
 *
 * @author diegocruzalves
 */
public class HashElement<K, V> implements Comparable<HashElement<K, V>>{
    
    K chave;
    V valor;
    
    public HashElement(K chave, V valor){
        this.chave = chave;
        this.valor = valor;
    }

    @Override
    public int compareTo(HashElement<K, V> o) {
       return (((Comparable<K>)this.chave).compareTo(o.chave));
    }
    
}
