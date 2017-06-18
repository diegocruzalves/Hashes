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
public class Main {

    public static void main(String[] args) {

        Hash<String, Integer> hash = new Hash<String, Integer>(4);
        hash.inserir("Fulano", 1);
        hash.inserir("Cicrano", 3);
        
        System.out.println("Pesquisa Cicrano");
        System.out.println(hash.pesquisar("Cicrano"));
    }

}
