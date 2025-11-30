package colecoes;

public class ArvoreAVL {
    private No raiz;
    private int quantidade;

    public ArvoreAVL() {
        this.raiz = null;
        this.quantidade = 0;
    }

    public void inserir(int valor) {
        this.raiz = insereBalanceado(this.raiz, valor);
    }

    public boolean buscar(int valor) {
        return buscaBST(this.raiz, valor);
    }

    public int tamanho() {
        return this.quantidade;
    }

    private int pegarAltura(No no) {
        if (no == null) {
            return 0; 
        }
        return no.altura;
    }

    private void atualizaAltura(No no) {
        int altEsquerda = pegarAltura(no.esquerda);
        int altDireita = pegarAltura(no.direita);
        
        no.altura = 1 + Math.max(altEsquerda, altDireita);
    }

    private int calcularFatorBalanceamento(No no) {
        if (no == null) {
            return 0;
        }
        return pegarAltura(no.esquerda) - pegarAltura(no.direita);
    }

    private No rotacaoDireita(No y) {
        No x = y.esquerda;
        No T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        atualizaAltura(y);
        atualizaAltura(x);

        return x;
    }

    private No rotacaoEsquerda(No x) {
        No y = x.direita;
        No T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        atualizaAltura(x);
        atualizaAltura(y);

        return y;
    }

    private No insereBalanceado(No noAtual, int valor) {
        if (noAtual == null) {
            this.quantidade++;
            return new No(valor); 
        }
        
        if (valor < noAtual.valor) {
            noAtual.esquerda = insereBalanceado(noAtual.esquerda, valor);
        } else if (valor > noAtual.valor) {
            noAtual.direita = insereBalanceado(noAtual.direita, valor);
        } else {
            return noAtual; 
        }

        atualizaAltura(noAtual);
        int fatorBalanceamento = calcularFatorBalanceamento(noAtual);

        if (fatorBalanceamento > 1 && valor < noAtual.esquerda.valor) {
            return rotacaoDireita(noAtual);
        }

        if (fatorBalanceamento < -1 && valor > noAtual.direita.valor) {
            return rotacaoEsquerda(noAtual);
        }

        if (fatorBalanceamento > 1 && valor > noAtual.esquerda.valor) {
            noAtual.esquerda = rotacaoEsquerda(noAtual.esquerda);
            return rotacaoDireita(noAtual);
        }

        if (fatorBalanceamento < -1 && valor < noAtual.direita.valor) {
            noAtual.direita = rotacaoDireita(noAtual.direita);
            return rotacaoEsquerda(noAtual);
        }

        return noAtual;
    }

    private boolean buscaBST(No noAtual, int valor) {
        if (noAtual == null) {
            return false;
        }
        
        if (valor == noAtual.valor) {
            return true;
        }

        if (valor < noAtual.valor) {
            return buscaBST(noAtual.esquerda, valor);
        } else { 
            return buscaBST(noAtual.direita, valor);
        }
    }
}