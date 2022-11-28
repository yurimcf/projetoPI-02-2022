package br.com.yuriCorp.projetoPi.Model.entity;

public class Aluno {
	
	private String ra;
	private String nome;
	private String rg;
	
	public Aluno() {
	}

	public Aluno(String nome, String rg) {
		super();
		this.nome = nome;
		this.rg = rg;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@Override
	public String toString() {
		return "Aluno [ra=" + ra + ", nome=" + nome + ", rg=" + rg + "]";
	}
	
	
}
