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
import java.util.Random;

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

    //Auxiliar de Busca

     private static List<Integer> selecionarElementosBusca(int[] dados) {
        List<Integer> elementosBusca = new ArrayList<>();
        int tamanho = dados.length;
        Random random = new Random();

        if (tamanho == 0) return elementosBusca;

        elementosBusca.add(dados[0]);
        elementosBusca.add(dados[tamanho - 1]);
        elementosBusca.add(dados[tamanho / 2]);

        for (int i = 0; i < 3; i++) {
            elementosBusca.add(dados[random.nextInt(tamanho)]);
        }

        elementosBusca.add(dados[tamanho - 1] + 1);

        return elementosBusca;
    }
    
    // BUSCA
    
    public static List<String> testarBusca() {
        List<String> resultados = new ArrayList<>();
        resultados.add("--- TEMPOS DE BUSCA (ms) ---");

        BuscaSequencial buscaSeq = new BuscaSequencial();
        BuscaBinaria buscaBin = new BuscaBinaria();
        MergeSort mergeSorter = new MergeSort();


         for (int tamanho : TAMANHOS) {
            for (String ordem : ORDENS) {

                int[] dados = GeradorDeDados.gerar(tamanho, ordem);
                List<Integer> elementosBusca = selecionarElementosBusca(dados);

                if (elementosBusca.isEmpty()) {
                    resultados.add(String.format("%d/%s | Nenhum dado gerado.", tamanho, ordem));
                    continue;
                }


                Vetor vetor = new Vetor(tamanho);
                ArvoreBinaria abb = new ArvoreBinaria();
                ArvoreAVL avl = new ArvoreAVL();

                for (int valor : dados) {
                    vetor.adicionar(valor);
                    abb.inserir(valor);
                    avl.inserir(valor);
                }

                final int[] vetorDados = vetor.copiar();

                int[] vetorOrdenado = vetorDados.clone();
                mergeSorter.ordenar(vetorOrdenado);

                double somaSeq = 0.0;
                double somaBin = 0.0;
                double somaABB = 0.0;
                double somaAVL = 0.0;

                
                for (int elemento : elementosBusca) {
                    somaSeq += medirTempoMedio(() -> buscaSeq.buscar(vetorDados, elemento));
                    somaBin += medirTempoMedio(() -> buscaBin.buscar(vetorOrdenado, elemento));
                    somaABB += medirTempoMedio(() -> abb.buscar(elemento));
                    somaAVL += medirTempoMedio(() -> avl.buscar(elemento));
                }

                int numElementos = elementosBusca.size();

                resultados.add(
                        String.format("%d/%s | Seq: %.4f | Bin: %.4f | ABB: %.4f | AVL: %.4f",
                                tamanho, ordem,
                                somaSeq / numElementos,
                                somaBin / numElementos,
                                somaABB / numElementos,
                                somaAVL / numElementos)
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
