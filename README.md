Feito por Cassiano Socorro

Este repositório contém o código-fonte Java utilizado para comparar o desempenho prático de diferentes estruturas de dados e algoritmos de ordenação, validando as complexidades teóricas (Notação Big O).

1. 🚀 Visão Geral do ProjetoO objetivo principal é medir e contrastar o tempo de execução (em milissegundos) para operações cruciais como Inserção, Busca e Ordenação em conjuntos de dados de $10.000$ elementos.

Estruturas Testadas: Vetores, Árvore Binária de Busca (ABB) e Árvore AVL.

Algoritmos Testados: Bubble Sort ($O(N^2)$) e Merge Sort ($O(N \log N)$).

Cenários Chave: Pior Caso (Dados Ordenados/Inversos) vs. Caso Médio (Dados Aleatórios).

2. 📋 Pré-requisitos
Para compilar e executar o projeto, você precisará ter o Java Development Kit (JDK) versão 8 ou superior instalado.

3. 📂 Estrutura do Repositório
O projeto é organizado com a seguinte estrutura de arquivos. O arquivo Main.java é o ponto de execução principal para iniciar os testes.

📦 src
 ├── 📦 algoritmos
 │    ├── 📄 BubbleSort.java
 │    ├── 📄 BuscaBinaria.java
 │    ├── 📄 BuscaSequencial.java
 │    └── 📄 MergeSort.java
 ├── 📦 colecoes
 │    ├── 📄 ArvoreAVL.java
 │    ├── 📄 ArvoreBinaria.java
 │    ├── 📄 No.java
 │    └── 📄 Vetor.java
 └── 📦 testes
      ├── 📄 BenchmarkRunner.java
      ├── 📄 GeradorDeDados.java
      └── 📄 Main.java
 
 📄 README.md

4. 🚀 Como Compilar e Executar
Este guia detalha os passos para compilar e executar o programa, pressupondo o uso do terminal.

💻 A. Compilação
Para compilar os arquivos Java, navegue até o diretório src/main/java/ no seu terminal e utilize o comando javac:


javac Main.java Estruturas/*.java
O que o comando faz: Ele compila o arquivo principal (Main.java) e todos os arquivos Java dentro do subdiretório Estruturas/.

⚙️ B. Execução
Após a compilação, execute a classe principal (Main) para iniciar a coleta de dados de tempo. O programa executará todos os testes de forma automática e imprimirá os resultados no console. (java.main)

5; 🔬 Análise de Saída
Ao executar o Main.java, a saída mostrará os dados de tempo que comprovam as complexidades:

6. ℹ️ Mais Informações
Consulte o arquivo Relatorio_Tecnico.pdf para a análise completa, incluindo a metodologia detalhada, as tabelas de dados brutos e a interpretação visual dos gráficos.