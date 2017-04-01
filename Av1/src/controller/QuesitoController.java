package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import model.Quesito;
import persistence.QuesitoDao;

public class QuesitoController implements IQuesitoController{
	private JComboBox<Quesito> comboBox_q;
	
	public QuesitoController(JComboBox<Quesito> comboBox_q){
		this.comboBox_q=comboBox_q;
		
	}
	@Override
	public void listaQuesito() {
		try {
			QuesitoDao qDao = new QuesitoDao();
			List<Quesito> listaQuesito =qDao.consultaQuesitos();
			if(comboBox_q.getItemCount()>0){
				comboBox_q.removeAllItems();
			}
			if(listaQuesito!= null){
				for(Quesito q: listaQuesito){
					comboBox_q.addItem(q);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"ERRO",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		
		
	}

}
