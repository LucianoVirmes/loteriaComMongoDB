package br.edu.unoesc.main;

import java.util.Scanner;

import br.edu.unoesc.dao.ConcursoDao;
import br.edu.unoesc.dao.ConcursoMongo;

public class Main {

	public static void main(String[] args) {
		ConcursoDao c = new ConcursoMongo();
		Scanner sc = new Scanner(System.in);
		System.out.println("Selecione 1 para adicionar um curso ou 2 para verificar se há um ganhador.");
		int n = sc.nextInt();
		if(n == 1) {
			c.adicionaConcurso();
		} else if(n == 2) {
			c.validaParticipante();	
		}
		sc.close();
	}	

}
