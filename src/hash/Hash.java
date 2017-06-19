/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash;

import java.util.LinkedList;
import java.lang.Object;

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
        //Como não se pode criar uma nova Lista Ligada genérica, utiliza-se um cast
        this.listaHash = (LinkedList<HashElement<K, V>>[]) new LinkedList[tamTabela];
        for (int i = 0; i < tamTabela; i++) {
            this.listaHash[i] = new LinkedList<HashElement<K, V>>();
        }
        this.fatorCargaMax = 0.75;
        this.size = 0;
    }

    public void inserir(K chave, V valor) {
        //Verifica o tamanho do tabela Hash
        if (fatorDeCarga() > fatorCargaMax) {
            //Se ultrapassar o fato de carga máximo, dobra o tamanho da Tabela Hash
            resize(tamTabela * 2);
        }
        //Cria o novo objeto a ser inserido na tabela Hash
        HashElement<K, V> novoElem = new HashElement(chave, valor);
        //Índice - busca o hashCode referente a chave inserida. "Hash Function".
        int valorHash = chave.hashCode();
        //Transforma em positivo
        if (valorHash < 0) {
            valorHash *= -1;
        }
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
        if (valorHash < 0) {
            valorHash *= -1;
        }
        valorHash %= tamTabela;

        //Remove da Lista encadeada
        listaHash[valorHash].remove();
        size--;
    }

    public V pesquisar(K chave) {
        //Encontra o índice da chave informada
        int valorHash = chave.hashCode();
        if (valorHash < 0) {
            valorHash *= -1;
        }
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
        K[] chaves = (K[]) new Object[size];
        int p = 0;
        for (int i = 0; i < tamTabela; i++) {
            LinkedList<HashElement<K, V>> lista = listaHash[i];
            for (HashElement<K, V> h : lista) {
                V valor = pesquisar(h.chave);
                HashElement<K, V> elemHash = new HashElement<K, V>(h.chave, h.valor);
                int valorHash = h.chave.hashCode();
                if (valorHash < 0) {
                    valorHash *= -1;
                }
                valorHash %= tamTabela;
                novaHash[valorHash].add(elemHash);
                chaves[p++] = h.chave;
            }
        }
        //A tabela Hash antiga é atualizada para a nova tabela Hash
        listaHash = novaHash;
        //O tamanho da tabela recebe o tamanho novo
        tamTabela = tamNovo;
    }

    public void imprimeHash() {
        System.out.printf("%-6s || %-10s || %s\n", "Índice", "Chave", "Valor");
        System.out.println("----------------------------");

        K[] chaves = (K[]) new Object[size];
        int p = 0;
        for (int i = 0; i < tamTabela; i++) {
            LinkedList<HashElement<K, V>> lista = listaHash[i];
            for (HashElement<K, V> h : lista) {
                chaves[p++] = h.chave;
                System.out.printf("%-6s || %-10s || %s\n", listaHash[i].indexOf(h), h.chave, h.valor);
            }
        }
    }

}
