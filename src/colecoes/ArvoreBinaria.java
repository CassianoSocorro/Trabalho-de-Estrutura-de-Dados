package colecoes;

public class ArvoreBinaria {
    private No raiz;
    private int quantidade; 
    public ArvoreBinaria() {
        this.raiz = null;
        this.quantidade = 0;
    }
   
    public void inserir(int valor) {
        this.raiz = inserirRecursivo(this.raiz, valor);
    }

    public boolean buscar(int valor) {
        return buscarRecursivo(this.raiz, valor);
    }
   
    public int tamanho() {
        return this.quantidade;
    }
    
    private No inserirRecursivo(No noAtual, int valor) {
        if (noAtual == null) {
            this.quantidade++;
            return new No(valor); 
        }

        if (valor < noAtual.valor) {
            noAtual.esquerda = inserirRecursivo(noAtual.esquerda, valor);
        } 
        else if (valor > noAtual.valor) {
            noAtual.direita = inserirRecursivo(noAtual.direita, valor);
        } 

        return noAtual;
    }
    
    private boolean buscarRecursivo(No noAtual, int valor) {
        if (noAtual == null) {
            return false;
        }
        
        if (valor == noAtual.valor) {
            return true;
        }

        if (valor < noAtual.valor) {
            return buscarRecursivo(noAtual.esquerda, valor);
        } 
        else { 
            return buscarRecursivo(noAtual.direita, valor);
        }
    }
}