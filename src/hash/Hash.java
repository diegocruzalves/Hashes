/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash;

import java.util.LinkedList;

/**
 *
 * @author diegocruzalves
 */
public class Hash<K, V> implements HashI<K, V> {

    int size, tamTabela;
    double fatorCargaMax;
    LinkedList<HashElement<K, V>>[] listaHash;

    //Construtor da classe
    public Hash(int tamTabela) {
        this.tamTabela = tamTabela;
        this.listaHash = (LinkedList<HashElement<K, V>>[]) new LinkedList[tamTabela];
        for (int i = 0; i < tamTabela; i++) {
            this.listaHash[i] = new LinkedList<HashElement<K, V>>();

        }
        this.fatorCargaMax = 0.75;
        this.size = 0;
    }

    public void inserir(K chave, V valor) {
        //Verifica o tamanho do tabela Hash
//        if (fatorDeCarga() > fatorCargaMax) {
//            //Se ultrapassar o fato de carga máximo, dobra o tamanho da Tabela Hash
//            //resize(tamTabela * 2);
//        }
        //Cria o novo objeto a ser inserido na tabela Hash
        HashElement<K, V> novoElem = new HashElement(chave, valor);
        //Índice - busca o hashCode referente a chave inserida
        int valorHash = chave.hashCode();
        System.out.println(valorHash);

        //Transforma em positivo
        valorHash &= 0x7FFFFFFF;
        //O resto da divisão com o tamanho da tabela será o index do novo elemento
        valorHash %= tamTabela;
        //Insere o novo elemento na Lista encadeada correspondente
        listaHash[valorHash].add(novoElem);
        size++;
    }

    public void remover(K chave, V valor) {
        //Encontra o índice da chave informada
        int valorHash = chave.hashCode();
        //Transforma em positivo
        valorHash &= 0x7FFFFFFF;
        valorHash %= tamTabela;

        //Remove da Lista encadeada
        listaHash[valorHash].remove();
        size--;
    }

    public V pesquisar(K chave) {
        //Encontra o índice da chave informada
        int valorHash = chave.hashCode();
        valorHash &= 0x7FFFFFFF;
        valorHash %= tamTabela;

        //Iterador
        for (HashElement<K, V> elemHash : listaHash[valorHash]) {
            if (((Comparable<K>) chave).compareTo(elemHash.chave) == 0);
            return elemHash.valor;
        }
        return null;
    }

    public double fatorDeCarga() {
        return size / tamTabela;
    }

    public void resize(int tamNovo) {
        //Cria nova listaHash com o dobro do tamanho
        LinkedList<HashElement<K, V>>[] novaHash = (LinkedList<HashElement<K, V>>[]) new LinkedList[tamNovo];

        //Inicializa novaHash
        for (int i = 0; i < tamNovo; i++) {
            novaHash[i] = new LinkedList<HashElement<K, V>>();
        }

        //Copia os valores de listaHash antiga para a nova tabela Hash
        for (K chave : this) {
            V valor = pesquisar(chave);
            HashElement<K, V> elemHash = new HashElement<K, V>(chave, valor);
            int valorHash = (chave.hashCode() & 0x7FFFFFFF) % tamNovo;
            novaHash[valorHash].add(elemHash);
        }
        //A tabela Hash antiga é atualizada para a nova tabela Hash
        listaHash = novaHash;
        //O tamanho da tabela recebe o tamanho novo
        tamTabela = tamNovo;
    }

    public void imprimeHash() {
        System.out.println("Tabela Hash:");
        for (int i = 0; i < tamTabela; i++) {
            if (listaHash[i] != null) {
                System.out.println(listaHash[i].get(i).chave + "\t" + listaHash[i].get(i).valor);
            }
        }
    }

}
