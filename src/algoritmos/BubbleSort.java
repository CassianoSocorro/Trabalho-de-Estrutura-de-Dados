package algoritmos;

public class BubbleSort {

  
    public void ordenar(int[] vetor) {
        int n = vetor.length;
        
        for (int i = 0; i < n - 1; i++) {
            boolean houveTroca = false; 
            for (int j = 0; j < n - 1 - i; j++) {
                
                if (vetor[j] > vetor[j + 1]) {
                    
                    int temp = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temp;
                    houveTroca = true;
                }
            }
            if (!houveTroca) {
                break;
            }
        }
    }
}