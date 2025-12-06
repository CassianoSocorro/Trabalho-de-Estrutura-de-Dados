package testes;

import java.util.Random;

public class GeradorDeDados {

    public static int[] gerar(int tamanho, String ordem) {
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = i + 1; 
        }

        if (ordem.equalsIgnoreCase("Inversa")) {
            for (int i = 0; i < tamanho / 2; i++) {
                int temp = vetor[i];
                vetor[i] = vetor[tamanho - 1 - i];
                vetor[tamanho - 1 - i] = temp;
            }
        } else if (ordem.equalsIgnoreCase("Aleatoria")) {
            Random rand = new Random();
            for (int i = 0; i < tamanho; i++) {
                int indiceTroca = rand.nextInt(tamanho);
                int temp = vetor[i];
                vetor[i] = vetor[indiceTroca];
                vetor[indiceTroca] = temp;
            }
        }
        
        return vetor;
    }
}