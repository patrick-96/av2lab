package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Jurado;

public interface IJuradoDao {
	public List<Jurado> consultaJurados() throws SQLException;
}
