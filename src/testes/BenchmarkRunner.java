package testes;

import colecoes.Vetor;
import colecoes.ArvoreBinaria;
import colecoes.ArvoreAVL;

import algoritmos.BuscaSequencial;
import algoritmos.BuscaBinaria;
import algoritmos.BubbleSort;
import algoritmos.MergeSort;

import java.util.ArrayList;
import java.util.List;

public class BenchmarkRunner {

    private static final int REPETICOES = 5;
    private static final int[] TAMANHOS = {100, 1000, 10000};
    private static final String[] ORDENS = {"Ordenada", "Inversa", "Aleatoria"};

    
    // MÉTODO GERAL DE MEDIÇÃO
    
    public static double medirTempoMedio(Runnable runnable) {
        long total = 0;

        for (int i = 0; i < REPETICOES; i++) {
            if (i == 0) runnable.run(); 

            long inicio = System.nanoTime();
            runnable.run();
            long fim = System.nanoTime();

            total += (fim - inicio);
        }

        return (total / (double) REPETICOES) / 1_000_000.0;
    }

    
    // INSERÇÃO
    
    public static List<String> testarInsercao() {
        List<String> resultados = new ArrayList<>();
        resultados.add("--- TEMPOS DE INSERÇÃO (ms) ---");

        for (int tamanho : TAMANHOS) {
            for (String ordem : ORDENS) {

                int[] dados = GeradorDeDados.gerar(tamanho, ordem);

                double tempoVetor = medirTempoMedio(() -> {
                    Vetor v = new Vetor(tamanho);
                    for (int x : dados) v.adicionar(x);
                });

                double tempoABB = medirTempoMedio(() -> {
                    ArvoreBinaria abb = new ArvoreBinaria();
                    for (int x : dados) abb.inserir(x);
                });

                double tempoAVL = medirTempoMedio(() -> {
                    ArvoreAVL avl = new ArvoreAVL();
                    for (int x : dados) avl.inserir(x);
                });

                resultados.add(
                    String.format("%d/%s | Vetor: %.4f | ABB: %.4f | AVL: %.4f",
                            tamanho, ordem, tempoVetor, tempoABB, tempoAVL)
                );
            }
        }
        return resultados;
    }

    
    // BUSCA
    
    public static List<String> testarBusca() {
        List<String> resultados = new ArrayList<>();
        resultados.add("--- TEMPOS DE BUSCA (ms) ---");

        BuscaSequencial seq = new BuscaSequencial();
        BuscaBinaria bin = new BuscaBinaria();

        for (int tamanho : TAMANHOS) {

            int alvo = tamanho;

            for (String ordem : ORDENS) {

                int[] dados = GeradorDeDados.gerar(tamanho, ordem);

                //Vetor sequencial 
                double tempoSeq = medirTempoMedio(() -> {
                    seq.buscar(dados, alvo);
                });

               // Vetor binária 
                int[] dadosOrdenados = dados.clone();
                java.util.Arrays.sort(dadosOrdenados);

                double tempoBin = medirTempoMedio(() -> {
                    bin.buscar(dadosOrdenados, alvo);
                });

                //ABB 
                double tempoABB = medirTempoMedio(() -> {
                    ArvoreBinaria abb = new ArvoreBinaria();
                    for (int x : dados) abb.inserir(x);
                    abb.buscar(alvo);
                });

                //AVL 
                double tempoAVL = medirTempoMedio(() -> {
                    ArvoreAVL avl = new ArvoreAVL();
                    for (int x : dados) avl.inserir(x);
                    avl.buscar(alvo);
                });

                resultados.add(
                    String.format(
                        "%d/%s | Seq: %.4f | Bin: %.4f | ABB: %.4f | AVL: %.4f",
                        tamanho, ordem, tempoSeq, tempoBin, tempoABB, tempoAVL
                    )
                );
            }
        }
        return resultados;
    }

     
    // ORDENAÇÃO
    
    public static List<String> testarOrdenacao() {
        List<String> resultados = new ArrayList<>();
        resultados.add("--- TEMPOS DE ORDENAÇÃO (ms) ---");

        BubbleSort bubble = new BubbleSort();
        MergeSort merge = new MergeSort();

        for (int tamanho : TAMANHOS) {
            for (String ordem : ORDENS) {

                int[] dados = GeradorDeDados.gerar(tamanho, ordem);

                // BubbleSort
                double tempoBubble = medirTempoMedio(() -> {
                    int[] copia = dados.clone();
                    bubble.ordenar(copia);
                });

                // MergeSort
                double tempoMerge = medirTempoMedio(() -> {
                    int[] copia = dados.clone();
                    merge.ordenar(copia);
                });

                resultados.add(
                    String.format(
                        "%d/%s | Bubble: %.4f | Merge: %.4f",
                        tamanho, ordem, tempoBubble, tempoMerge
                    )
                );
            }
        }

        return resultados;
    }
    
}
