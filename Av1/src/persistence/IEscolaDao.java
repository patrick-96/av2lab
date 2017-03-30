package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Escola;

public interface IEscolaDao {
	public List<Escola> consultaEscolas() throws SQLException;

}
