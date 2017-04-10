package model;

public class Quesito {
	private int id_q;
	private String nome_q;
	public int getId_q() {
		return id_q;
	}
	public void setId_q(int id_q) {
		this.id_q = id_q;
	}
	public String getNome_q() {
		return nome_q;
	}
	public void setNome_q(String nome_q) {
		this.nome_q = nome_q;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nome_q;
	}
	

}
