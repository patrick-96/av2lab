package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import model.Provas;
import persistence.ProvasDao;

public class ProvasController {
private JComboBox<Provas> comboBoxProva;

public ProvasController(JComboBox<Provas> comboBoxProva){
	this.comboBoxProva=comboBoxProva;
}

public void listaProvas(){
	try {
		ProvasDao pDao = new ProvasDao();
		List<Provas> listaProvas =pDao.consultaProvas();
		if(comboBoxProva.getItemCount()>0){
			comboBoxProva.removeAllItems();
		}
		if(listaProvas!= null){
			for(Provas p: pDao.consultaProvas()){
				comboBoxProva.addItem(p);
			}
		}
	} catch (ClassNotFoundException | SQLException e) {
		JOptionPane.showMessageDialog(null,e.getMessage(),"ERRO",JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
		// TODO: handle exception
	}
}
}
