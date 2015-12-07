package br.ufc.arida.bcl.rdp20152.atv5.ex1;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

import br.ufc.arida.bcl.rdp20152.atv5.graficos.GraficoDePontos2D;
import br.ufc.arida.bcl.rdp20152.atv5.graficos.Ponto2D;

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

		List<Ponto2D> pontos = new ArrayList<Ponto2D>();
		List<Ponto2D> pontosReta = new ArrayList<Ponto2D>();
		System.out.println("Valor Label(t) -- Valor Predito");
		for (int i = 0; i < yPreditos.getDimension(); i++) {
			Ponto2D pi = new Ponto2D(f.getData_A_testing_input().getEntry(i), f.getData_A_testing_output().getEntry(i));
			pontos.add(pi);
			Ponto2D pr = new Ponto2D(f.getData_A_testing_input().getEntry(i), yPreditos.getEntry(i));
			pontosReta.add(pr);
		}
		
		GraficoDePontos2D g1 = new GraficoDePontos2D("Titulo");
		g1.adicionarPontos2D("labels x preditos", pontos);
		g1.adicionarPontos2D("preditos", pontosReta);
		g1.exibirGrafico();
		
		double mse = f.funcao_MSE(f.getData_A_testing_output(), yPreditos);
		System.out.println("\nMean Squared Error(MSE):");
		System.out.println(mse);
	}

}
