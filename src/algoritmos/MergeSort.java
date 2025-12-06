package algoritmos;

public class MergeSort {

    
    public void ordenar(int[] vetor) {
        if (vetor == null || vetor.length < 2) {
            return;
        }
        mergeSortRecursivo(vetor, new int[vetor.length], 0, vetor.length - 1);
    }

    private void mergeSortRecursivo(int[] vetor, int[] auxiliar, int inicio, int fim) {
        if (inicio < fim) {
            
            int meio = inicio + (fim - inicio) / 2;

            mergeSortRecursivo(vetor, auxiliar, inicio, meio);
            mergeSortRecursivo(vetor, auxiliar, meio + 1, fim);

            fundeSubArrays(vetor, auxiliar, inicio, meio, fim);
        }
    }

  
    private void fundeSubArrays(int[] vetor, int[] auxiliar, int inicio, int meio, int fim) {
        for (int k = inicio; k <= fim; k++) {
            auxiliar[k] = vetor[k];
        }

        int i = inicio; 
        int j = meio + 1; 
        int k = inicio; 

        while (i <= meio && j <= fim) {
            if (auxiliar[i] <= auxiliar[j]) {
                vetor[k] = auxiliar[i];
                i++;
            } else {
                vetor[k] = auxiliar[j];
                j++;
            }
            k++;
        }

        while (i <= meio) {
            vetor[k] = auxiliar[i];
            k++;
            i++;
        }
    }
}