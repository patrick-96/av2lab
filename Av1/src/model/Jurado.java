package model;

public class Jurado {
	private int id_j;
	private String nome_j;
	
	public int getId_j() {
		return id_j;
	}
	public void setId_j(int id_j) {
		this.id_j = id_j;
	}
	public String getNome_j() {
		return nome_j;
	}
	public void setNome_j(String nome_j) {
		this.nome_j = nome_j;
	}
	
	@Override
	public String toString() {
		
		return nome_j;
	}
	
	

}
