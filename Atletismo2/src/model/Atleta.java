package model;

public class Atleta {
 /*
  * codigoAtleta int identity,
nomeAtleta varchar(100),
sexo varchar(10),
pais varchar(4)
primary key (codigoAtleta)
  * */
	private int CodigoAtleta;
	private String NomeAtleta;
	private String SexoAltleta;
	private String codigoPais;
	
	
	public int getCodigoAtleta() {
		return CodigoAtleta;
	}
	public void setCodigoAtleta(int codigoAtleta) {
		CodigoAtleta = codigoAtleta;
	}
	public String getNomeAtleta() {
		return NomeAtleta;
	}
	public void setNomeAtleta(String nomeAtleta) {
		NomeAtleta = nomeAtleta;
	}
	public String getSexoAltleta() {
		return SexoAltleta;
	}
	public void setSexoAltleta(String sexoAltleta) {
		SexoAltleta = sexoAltleta;
	}
	public String getCodigoPais() {
		return codigoPais;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	

	
	
	
}
