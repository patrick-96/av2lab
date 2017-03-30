package model;

public class Escola {
	private int id_e;
	private String nome_e;
	private double total_pontos;
	public int getId_e() {
		return id_e;
	}
	public void setId_e(int id_e) {
		this.id_e = id_e;
	}
	public String getNome_e() {
		return nome_e;
	}
	public void setNome_e(String nome_e) {
		this.nome_e = nome_e;
	}
	public double getTotal_pontos() {
		return total_pontos;
	}
	public void setTotal_pontos(double total_pontos) {
		this.total_pontos = total_pontos;
	}
	@Override
	public String toString() {
		
		return this.nome_e;
	}
}
