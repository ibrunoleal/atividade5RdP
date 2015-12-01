package br.ufc.arida.bcl.rdp20152.atv5.ex1;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

public class Exercicio1 {

	public static void main(String[] args) {
		
		Exercicio1Functions f = new Exercicio1Functions();
		
		int n = f.getData_A_testing_input().getDimension();
		RealVector yPreditos = new ArrayRealVector(n);
		for (int i = 0; i < n; i++) {
			double xi = f.getData_A_testing_input().getEntry(i);
			double yi = f.funcao_y(xi, f.getData_A_learing_input(), f.getData_A_learning_output());
			yPreditos.setEntry(i, yi);
		}

		System.out.println("Valor Label(t) -- Valor Predito");
		for (int i = 0; i < yPreditos.getDimension(); i++) {
			System.out.println(f.getData_A_testing_output().getEntry(i) + " -- " + yPreditos.getEntry(i));
		}
		
		double mse = f.funcao_MSE(yPreditos, f.getData_A_testing_output());
		System.out.println("\nMean Squared Error(MSE):");
		System.out.println(mse);
	}

}
