package model;

public class Competicao {
	/*codigoProva int,
codigoAtleta int,
bateria decimal (7,1)*/
	private int codigoProva;
	private int codigoAtleta;
		private String fase;
	private String bateria;
	public int getCodigoProva() {
		return codigoProva;
	}
	public void setCodigoProva(int codigoProva) {
		this.codigoProva = codigoProva;
	}
	public int getCodigoAtleta() {
		return codigoAtleta;
	}
	public void setCodigoAtleta(int codigoAtleta) {
		this.codigoAtleta = codigoAtleta;
	}

	public String getFase() {
		return fase;
	}
	public void setFase(String fase) {
		this.fase = fase;
	}
	public String getBateria() {
		return bateria;
	}
	public void setBateria(String bateria) {
		this.bateria = bateria;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return fase;
	}
	
		
	
	
	
	
	

}
