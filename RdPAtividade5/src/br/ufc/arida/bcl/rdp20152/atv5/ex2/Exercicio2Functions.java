package br.ufc.arida.bcl.rdp20152.atv5.ex2;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import br.ufc.arida.bcl.rdp20152.atv5.file.FileHandler;

public class Exercicio2Functions {
	
	public static final String DATA_A_LEARNING_INPUT = "data/data_A_learning_input.csv";
	
	public static final String DATA_A_LEARNING_OUTPUT = "data/data_A_learning_output.csv";
	
	public static final String DATA_A_TESTING_INPUT = "data/data_A_testing_input.csv";
	
	public static final String DATA_A_TESTING_OUTPUT = "data/data_A_testing_output.csv";
	
	private RealVector data_A_learning_input;
	
	private RealVector data_A_learning_output;
	
	private RealVector data_A_testing_input;
	
	private RealVector data_A_testing_output;
	
	public Exercicio2Functions() {
		FileHandler fileHandlerIn = new FileHandler(DATA_A_LEARNING_INPUT, ";");
		data_A_learning_input = new ArrayRealVector(fileHandlerIn.getVetor(0));
		
		FileHandler fileHandlerOut = new FileHandler(DATA_A_LEARNING_OUTPUT, ";");
		data_A_learning_output = new ArrayRealVector(fileHandlerOut.getVetor(0));
		
		FileHandler fileHandlerInTesting = new FileHandler(DATA_A_TESTING_INPUT, ";");
		data_A_testing_input = new ArrayRealVector(fileHandlerInTesting.getVetor(0));
		
		FileHandler fileHandlerOutTesting = new FileHandler(DATA_A_TESTING_OUTPUT, ";");
		data_A_testing_output = new ArrayRealVector(fileHandlerOutTesting.getVetor(0));
	}

	public RealVector getData_A_learning_input() {
		return data_A_learning_input;
	}

	public RealVector getData_A_learning_output() {
		return data_A_learning_output;
	}
	
	public RealVector getData_A_testing_input() {
		return data_A_testing_input;
	}

	public RealVector getData_A_testing_output() {
		return data_A_testing_output;
	}

	public double funcaoDeAtivacao(double x, double centroid) {
		return Math.exp(-1 * (Math.pow( Math.abs(x - centroid), 2) / 2));
	}
	
	public RealMatrix construirPHI(int numClusters) {
		RealMatrix PHI = new Array2DRowRealMatrix(getData_A_learning_input().getDimension(), numClusters);
		           
		KMeans kmeans = new KMeans(DATA_A_LEARNING_INPUT, numClusters);
		double[] centroids = kmeans.getCentroids();
		RealVector c = new ArrayRealVector(centroids);
		for (int j = 0; j < numClusters; j++) {
			double centroid = c.getEntry(j);
			for (int i = 0; i < getData_A_learning_input().getDimension(); i++) {
				double phi_i = funcaoDeAtivacao(getData_A_learning_input().getEntry(i), centroid);
				PHI.setEntry(i, j, phi_i);
			}
		}
		
		return PHI;
	}
	

	public RealVector w(RealMatrix PHI, RealVector t) {
		
		RealMatrix PHI_t = PHI.transpose();
		RealMatrix A = PHI_t.multiply(PHI);
		
		double lambda = 0.5;
		int dimensao = A.getRowDimension();
		RealMatrix I = MatrixUtils.createRealIdentityMatrix(dimensao);
		RealMatrix Ilambda = I.scalarMultiply(lambda);
		
		RealMatrix Aplus = Ilambda.add(A);
		
		RealMatrix A_I = MatrixUtils.inverse(Aplus);
		RealMatrix B = A_I.multiply(PHI_t);
		RealVector r = B.operate(t);
		return r;
	}
	
}
