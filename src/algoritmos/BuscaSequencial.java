package algoritmos;

public class BuscaSequencial {

 
    public int buscar(int[] vetor, int valor) {
        
        int n = vetor.length;
        
        for (int i = 0; i < n; i++) {
            
            if (vetor[i] == valor) {
                return i; 
            }
        }
        return -1;
    }
}