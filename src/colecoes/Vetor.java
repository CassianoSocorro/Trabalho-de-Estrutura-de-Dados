package colecoes;

public class Vetor {
    private int[] dados;
    private int quantidade;

    public Vetor(int capacidadeInicial) {
        this.dados = new int[capacidadeInicial];
        this.quantidade = 0;
    }

    public void adicionar(int valor) {
        if (this.quantidade == this.dados.length) {
            expandir();
        }
        this.dados[this.quantidade] = valor;
        this.quantidade++;
    }

    public int obter(int indice) {
        if (indice < 0 || indice >= this.quantidade) {
            throw new RuntimeException("Índice inválido");
        }
        return this.dados[indice];
    }

    public int tamanho() {
        return this.quantidade;
    }

    public int[] copiar() {
        int[] copia = new int[this.quantidade];
        for (int i = 0; i < this.quantidade; i++) {
            copia[i] = this.dados[i];
        }
        return copia;
    }

    private void expandir() {
        int novoTamanho = this.dados.length * 2;
        int[] novoArray = new int[novoTamanho];

        for (int i = 0; i < this.dados.length; i++) {
            novoArray[i] = this.dados[i];
        }

        this.dados = novoArray;
    }
}
