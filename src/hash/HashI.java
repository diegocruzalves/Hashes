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
interface HashI<K, V> {

    public void inserir(K chave, V valor);

    public void remover(K chave, V valor);

    public V pesquisar(K chave);

    public double fatorDeCarga();

    public void resize(int tamNovo);

}
