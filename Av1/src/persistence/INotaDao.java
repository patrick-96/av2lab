package persistence;

import java.sql.SQLException;

import model.Escola;
import model.Jurado;
import model.Nota;
import model.Quesito;

public interface INotaDao {


public void insereNota(Nota not,Escola es, Jurado ju,Quesito que) throws SQLException;
}
