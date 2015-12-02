package br.ufc.arida.bcl.rdp20152.atv5.ex2;

public class Exercicio2 {

	public static void main(String[] args) {
		
		KMeans kmeans = new KMeans();
		kmeans.carregarDados("data/data_A_learning_input.csv");
		kmeans.executarSimpleKMeans(3);

	}

}
