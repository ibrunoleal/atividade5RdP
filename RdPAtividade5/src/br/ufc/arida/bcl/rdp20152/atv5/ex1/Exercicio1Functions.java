package br.ufc.arida.bcl.rdp20152.atv5.ex1;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

import br.ufc.arida.bcl.rdp20152.atv5.file.FileHandler;

public class Exercicio1Functions {
	
	private RealVector data_A_learing_input;
	
	private RealVector data_A_learning_output;
	
	private RealVector data_A_testing_input;
	
	private RealVector data_A_testing_output;
	
	public Exercicio1Functions() {
		FileHandler fileHandlerIn = new FileHandler("data/data_A_learning_input.csv", ";");
		data_A_learing_input = new ArrayRealVector(fileHandlerIn.getVetor(0));
		
		FileHandler fileHandlerOut = new FileHandler("data/data_A_learning_output.csv", ";");
		data_A_learning_output = new ArrayRealVector(fileHandlerOut.getVetor(0));
		
		FileHandler fileHandlerInTesting = new FileHandler("data/data_A_testing_input.csv", ";");
		data_A_testing_input = new ArrayRealVector(fileHandlerInTesting.getVetor(0));
		
		FileHandler fileHandlerOutTesting = new FileHandler("data/data_A_testing_output.csv", ";");
		data_A_testing_output = new ArrayRealVector(fileHandlerOutTesting.getVetor(0));
	}
	
	public RealVector getData_A_learing_input() {
		return data_A_learing_input;
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
	
	public double funcao_g(double x, double x1) {
		return Math.exp(-1 * (Math.pow( Math.abs(x - x1), 2) / 2));
	}
	
	public double funcao_y(double x, RealVector vX, RealVector vT) {
		int n = vX.getDimension();
		double sumN = 0.0;
		double sumM = 0.0;
		for (int i = 0; i < n; i++) {
			sumN += funcao_g(x, vX.getEntry(i)) * vT.getEntry(i);
			sumM += funcao_g(x, vX.getEntry(i));
		}
		return sumN / sumM;
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
