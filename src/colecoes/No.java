package colecoes;

public class No {
   public int valor;
   public int altura;
   public No direita;
   public No esquerda;
   
    public No(int valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
        this.altura = 1;
    } 
}