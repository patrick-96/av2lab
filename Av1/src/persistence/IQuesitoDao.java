package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Quesito;
//talvez eu n tenha que usar nenhuma delas(muito provavel) 
//já que eu n mexo com essa tabela no meu programa
public interface IQuesitoDao {
public List<Quesito> consultaQuesitos() throws SQLException;

}
