package org.aprendendojboss.decisiontable.model;

public class Candidato {

	private String nome;
	private int idade;
	private double fundos;
	private String analise;

	public Candidato() {
	}

	public Candidato(String nome, int idade) {
		this.nome = nome;
		this.idade = idade;
	}
	
	public Candidato(String nome, int idade, double fundos) {
		super();
		this.nome = nome;
		this.idade = idade;
		this.fundos = fundos;
	}
	
	public Candidato(String nome, int idade, double fundos, String analise) {
		super();
		this.nome = nome;
		this.idade = idade;
		this.fundos = fundos;
		this.analise = analise;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public double getFundos() {
		return fundos;
	}

	public void setFundos(double fundos) {
		this.fundos = fundos;
	}

	public String getAnalise() {
		return analise;
	}

	public void setAnalise(String analise) {
		this.analise = analise;
	}

	@Override
	public String toString() {
		return "Candidato [nome=" + nome + ", idade=" + idade + ", fundos="
				+ fundos + ", analise=" + analise + "]";
	}

}