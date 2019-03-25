package br.edu.unoesc.model;


import java.util.Date;
import java.util.List;

public class Concurso {
	private Long numero;
	private Date data;
	private List<Integer> numeros;
	private Ganhadores ganhadores;
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public List<Integer> getNumeros() {
		return numeros;
	}
	public void setNumeros(List<Integer> numeros) {
		this.numeros = numeros;
	}
	public Ganhadores getGanhadores() {
		return ganhadores;
	}
	public void setGanhadores(Ganhadores ganhadores) {
		this.ganhadores = ganhadores;
	}
	
}
