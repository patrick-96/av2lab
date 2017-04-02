package teste;

import java.sql.SQLException;
import java.util.List;

import model.Quesito;
import persistence.QuesitoDao;

public class TesteLista {
	public static void main(String[]args) throws ClassNotFoundException, SQLException{
		QuesitoDao q=new QuesitoDao();
		List<Quesito> que =q.consultaQuesitos();
		for(Quesito quesito : que){
			System.out.println("id : "+quesito.getId_q());
			System.out.println("nome : "+quesito.getNome_q());
			
		}
	

}
}