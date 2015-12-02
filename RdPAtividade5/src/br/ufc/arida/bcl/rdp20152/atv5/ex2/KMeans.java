package br.ufc.arida.bcl.rdp20152.atv5.ex2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

public class KMeans {
	
	private Instances instanciasDeTreino;
	
	public KMeans() {
		instanciasDeTreino = null;
	}
	
	public void carregarDados(String nomeDoArquivo) {
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
	
	public void executarSimpleKMeans(int numClusters) {
		if (instanciasDeTreino != null) {
			SimpleKMeans kMeans = new SimpleKMeans();
	        kMeans.setSeed(10);
	        kMeans.setPreserveInstancesOrder(true);
	        try {
				kMeans.setNumClusters(numClusters);
				kMeans.buildClusterer(instanciasDeTreino);
				 int[] assignments = kMeans.getAssignments();
			     int i = 0;
			     for (int clusterNum : assignments) {
			    	 System.out.printf("Instance %d -> Cluster %d", i, clusterNum);
			         i++;
			     }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("Erro: instancias de treino não carregadas!");
		}
		
        
       
	}

}
