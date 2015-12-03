package br.ufc.arida.bcl.rdp20152.atv5.ex2;

import org.apache.commons.math3.linear.RealVector;

import br.ufc.arida.bcl.rdp20152.atv5.entidades.Matriz;

public class Exercicio2 {

	public static void main(String[] args) {
		
		Exercicio2Functions f = new Exercicio2Functions();
		
		/*
		 * q estipulado de acordo com o exercício 2.1
		 */
		int q = 4;
		
		
		Matriz PHI = new Matriz(f.construirPHI(q));
		System.out.println(PHI);
		
		RealVector t = f.getData_A_learning_output();
		
		RealVector w = f.w(PHI, t);
		System.out.println("w: " + w);

	}

}
