package br.ufc.arida.bcl.rdp20152.atv5.ex2;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.linear.RealVector;

import br.ufc.arida.bcl.rdp20152.atv5.entidades.Matriz;
import br.ufc.arida.bcl.rdp20152.atv5.graficos.GraficoDePontos2D;
import br.ufc.arida.bcl.rdp20152.atv5.graficos.Ponto2D;

public class Exercicio2 {

	public static void main(String[] args) {
		
		Exercicio2Functions f = new Exercicio2Functions();
		
		/*
		 * q estipulado de acordo com o exercício 2.1
		 */
		System.out.println("Exercicio 2.1)");
		int q = 6;
		System.out.println("valor de q(numero de basis functions): " + q);
		
		System.out.println("\nExercicio 2.2)");
		RealVector centroids = f.construirCentroids(q);
		System.out.println("Valor dos centroids (utilizado o K-Means): " + centroids);
		
		System.out.println("\nExercicio 2.3, 2.4 e 2.5)");
		
		Matriz PHI = new Matriz(f.construirPHI(centroids));
		System.out.println("Matriz PHI:\n" + PHI);
		
		
		System.out.println("\nExercicio 2.6)");
		RealVector t = f.getData_A_learning_output();
		double lambda = 0.1;
		RealVector w = f.w(PHI, t, lambda);
		System.out.println("w(lambda = " + lambda + "): " + w);
		
		RealVector ysPreditos = f.construirYPreditos(f.getData_A_testing_input(), w, centroids);
		System.out.println("ysPreditos: " + ysPreditos);
		
		List<Ponto2D> pontos = new ArrayList<Ponto2D>();
		List<Ponto2D> pontosReta = new ArrayList<Ponto2D>();
		for (int i = 0; i < ysPreditos.getDimension(); i++) {
			Ponto2D pi = new Ponto2D(f.getData_A_testing_input().getEntry(i), f.getData_A_testing_output().getEntry(i));
			pontos.add(pi);
			Ponto2D pr = new Ponto2D(f.getData_A_testing_input().getEntry(i), ysPreditos.getEntry(i));
			pontosReta.add(pr);
		}
		GraficoDePontos2D g1 = new GraficoDePontos2D("Titulo");
		g1.adicionarPontos2D("labels x preditos", pontos);
		g1.adicionarPontos2D("preditos", pontosReta);
		g1.exibirGrafico();
		
		double mse = f.funcao_MSE(ysPreditos, f.getData_A_testing_output());
		System.out.println("MSE: " + mse);

	}

}
