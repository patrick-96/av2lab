package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Quesito;
//talvez eu n tenha que usar nenhuma delas(muito provavel) 
//já que eu n mexo com essa tabela no meu programa
public interface IQuesitoDao {
public void insereQuesito(Quesito q) throws SQLException;
public void atualizaQuesito(Quesito q) throws SQLException;
public Quesito consultaQuesito(Quesito q)throws SQLException;
public List<Quesito> consultaQuesitos() throws SQLException;

}
