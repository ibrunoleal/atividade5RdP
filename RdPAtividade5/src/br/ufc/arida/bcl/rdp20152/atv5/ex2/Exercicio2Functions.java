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
	
	public double funcaoG(double x, double centroid) {
		return Math.exp(-1 * (Math.pow( Math.abs(x - centroid), 2) / 2));
	}

	public double funcaoGSomatorio(double x, RealVector centroids) {
		double sum = 0;
		
		for (int i = 0; i < centroids.getDimension(); i++) {
			double centroid = centroids.getEntry(i);
			sum += funcaoG(x, centroid);
		}
		
		return sum;
	}
	
	public double funcaoDeAtivacao(double x, double centroid, RealVector centroids) {
		return funcaoG(x, centroid) / funcaoGSomatorio(x, centroids);
	}
	
	public RealVector construirCentroids(int numClusters) {
		KMeans kmeans = new KMeans(DATA_A_LEARNING_INPUT, numClusters);
		double[] centroids = kmeans.getCentroids();
		RealVector c = new ArrayRealVector(centroids);
		return c;
	}
	
	public RealMatrix construirPHI(RealVector centroids) {
		RealMatrix PHI = new Array2DRowRealMatrix(getData_A_learning_input().getDimension(), centroids.getDimension());
		          
		for (int j = 0; j < centroids.getDimension(); j++) {
			double centroid = centroids.getEntry(j);
			for (int i = 0; i < getData_A_learning_input().getDimension(); i++) {
				double phi_i = funcaoDeAtivacao(getData_A_learning_input().getEntry(i), centroid, centroids);
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
	
	public double funcaoY(double x, RealVector w, RealVector centroids) {
		double sum = 0;
		for (int i = 0; i < centroids.getDimension(); i++) {
			sum += funcaoDeAtivacao(x, centroids.getEntry(i), centroids) * w.getEntry(i);
		}
		return sum;
	}
	
	public RealVector construirYPreditos(RealVector dataInput, RealVector w, RealVector centroids) {
		RealVector vy = new ArrayRealVector(dataInput.getDimension());
		for (int i = 0; i < getData_A_testing_input().getDimension(); i++) {
			double xi = getData_A_testing_input().getEntry(i);
			double yi = funcaoY(xi, w, centroids);
			vy.setEntry(i, yi);
		}
		return vy;
	}
	
	public double funcao_MSE(RealVector yPreditos, RealVector yOutput) {
		int n = yPreditos.getDimension();
		double sum = 0.0;
		for (int i = 0; i < n; i++) {
			sum += Math.pow(yPreditos.getEntry(i) - yOutput.getEntry(i), 2);
		}
		return sum / n;
	}
	
}
