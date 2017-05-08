package model;

public class Provas {
	/*
	 * create table provas(
codigoProva int,
NomeProva varchar(100),
sexoProva varchar(10)
primary key (codigoProva)
)
	 * 
	 * */
	
	private int codigoProva;
	private String nomeProva;
	private String tipoProva;
	private String sexoProva;
	public int getCodigoProva() {
		return codigoProva;
	}
	public void setCodigoProva(int codigoProva) {
		this.codigoProva = codigoProva;
	}
	public String getnomeProva() {
		return nomeProva;
	}
	public void setNomeProva(String nomeProva) {
		this.nomeProva = nomeProva;
	}
	public String getSexoProva() {
		return sexoProva;
	}
	public void setSexoProva(String sexoProva) {
		this.sexoProva = sexoProva;
	}
	public String getTipoProva() {
		return tipoProva;
	}
	public void setTipoProva(String tipoProva) {
		this.tipoProva = tipoProva;
	}
	@Override
	public String toString() {
		return nomeProva;
	}
	
	

}
