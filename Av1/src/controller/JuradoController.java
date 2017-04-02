package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import model.Escola;
import model.Jurado;
import persistence.JuradoDao;

public class JuradoController<comboBox_j> implements IJuradoController{
	private JComboBox<Jurado> comboBox_j;
	
	public JuradoController(JComboBox<Jurado> comboBox_j){
		this.comboBox_j=comboBox_j;
	}

	@Override
	public void listaJurado() {
		try {
			JuradoDao jDao = new JuradoDao();
			List<Jurado> listaJurado =jDao.consultaJurados();
			if(comboBox_j.getItemCount()>0){
				comboBox_j.removeAllItems();
			}
			if(listaJurado!= null){
				for(Jurado j: listaJurado){
					comboBox_j.addItem(j);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"ERRO",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}

}
