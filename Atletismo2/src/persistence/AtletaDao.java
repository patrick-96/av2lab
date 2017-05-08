package persistence;

import java.sql.Connection;
import java.sql.SQLException;

import model.Atleta;

public class AtletaDao implements iAtletaDao{
	private Connection c;
	
	public AtletaDao(){
		
		IGenericDao gDao = new GenericDao();
	}
	@Override
	public void insereAtleta(Atleta Atl) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluiAtleta(Atleta Atl) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void consultaAtleta(Atleta Atl) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Atleta atualizaAtleta(Atleta Atl) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	

}
