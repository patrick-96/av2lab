package persistence;

import java.sql.SQLException;

import model.Atleta;

public interface iAtletaDao {
	
	public void insereAtleta(Atleta Atl) throws SQLException;
	public void excluiAtleta(Atleta Atl) throws SQLException;
	public void consultaAtleta(Atleta Atl) throws SQLException;
	public Atleta atualizaAtleta(Atleta Atl) throws SQLException;
}
