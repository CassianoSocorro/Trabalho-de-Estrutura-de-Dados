package testes;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("BENCHMARK ALGORITMOS E ESTRUTURAS \n");

        // Inserção
        List<String> ins = BenchmarkRunner.testarInsercao();
        ins.forEach(System.out::println);

        System.out.println();
        
        // Busca
        List<String> busca = BenchmarkRunner.testarBusca();
        busca.forEach(System.out::println);

        System.out.println();

        // Ordenação
        List<String> ord = BenchmarkRunner.testarOrdenacao();
        ord.forEach(System.out::println);

        System.out.println("\n====== FIM DOS TESTES ======");
    }
}
