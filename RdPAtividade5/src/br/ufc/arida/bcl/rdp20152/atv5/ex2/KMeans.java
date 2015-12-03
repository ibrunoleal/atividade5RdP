package br.ufc.arida.bcl.rdp20152.atv5.ex2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import weka.clusterers.SimpleKMeans;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

public class KMeans {
	
	private Instances instanciasDeTreino;
	
	private SimpleKMeans kMeans;
	
	public KMeans(String nomeDoArquivoCSV, int numClusters) {
		instanciasDeTreino = null;
		kMeans = new SimpleKMeans();
		carregarDados(nomeDoArquivoCSV);
		executarSimpleKMeans(numClusters);
	}
	
	public double[] getCentroids() {
		Instances instances = kMeans.getClusterCentroids();
		int n = instances.numInstances();
		double[] centroids = new double[n];
		for (int i = 0; i < n; i++) {
			Instance inst = instances.instance(i);
			double valor = inst.value(0);
			centroids[i] = valor;
		}
		return centroids;
	}
	
	private void carregarDados(String nomeDoArquivo) {
		try {
			CSVLoader loader = new CSVLoader();
			String[] options = new String[1]; 
			options[0] = "-H";
			loader.setOptions(options);
		    loader.setSource(new File(nomeDoArquivo));
		    instanciasDeTreino = loader.getDataSet();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void executarSimpleKMeans(int numClusters) {
		if (instanciasDeTreino != null) {
			kMeans.setSeed(10);
			kMeans.setPreserveInstancesOrder(true);
			try {
				kMeans.setNumClusters(numClusters);
				kMeans.buildClusterer(instanciasDeTreino);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("Erro: instancias de treino não carregadas!");
		}
		
	}

}
