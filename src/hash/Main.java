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
        hash.inserir("Arroz", 3);
        hash.inserir("Feijao", 12);
        hash.inserir("Carne", 30);
        hash.inserir("Abobora", 8);
        hash.inserir("Abacaxi", 4);
        hash.inserir("Chocolate", 7);
        
        hash.imprimeHash();
        
        System.out.println("Resultado da pesquisa por \"Abobora\": "  + hash.pesquisar("Abobora"));
        
        hash.remover("Arroz", 3);
        hash.imprimeHash();
        
    }

}
