package br.com.yuriCorp.projetoPi.Model.entity;

public class Professor {
	private String rgf;
	private String nome;
	private String rg;
	
	public Professor() {
	}

	public Professor(String nome, String rg) {
		super();
		this.nome = nome;
		this.rg = rg;
	}

	public String getRgf() {
		return rgf;
	}
	
	public int getRgfInt() {
		return Integer.parseInt(getRgf());
	}

	public void setRgf(String rgf) {
		this.rgf = rgf;
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
		return "Professor [rgf=" + rgf + ", nome=" + nome + ", rg=" + rg + "]";
	}
	
	
}
