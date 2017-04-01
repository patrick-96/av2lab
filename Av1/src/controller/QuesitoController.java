package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import model.Escola;
import persistence.EscolaDao;

public class QuesitoController implements IQuesitoController{
	private JComboBox<Escola> comboBox_e;
	
	public QuesitoController(JComboBox<Escola> comboBox_e){
		this.comboBox_e=comboBox_e;
		
	}
	@Override
	public void listaQuesito() {
		try {
			EscolaDao eDao = new EscolaDao();
			List<Escola> listaEscola =eDao.consultaEscolas();
			if(comboBox_e.getItemCount()>0){
				comboBox_e.removeAllItems();
			}
			if(listaEscola!= null){
				for(Escola e: listaEscola){
					comboBox_e.addItem(e);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"ERRO",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		
		
	}

}
