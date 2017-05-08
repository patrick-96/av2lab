package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import model.Bateria;
import persistence.BateriaDao;

public class BateriaController {
	private JComboBox<Bateria> comboBoxBateria;
	
	public BateriaController(JComboBox<Bateria> comboBoxBateria){
		this.comboBoxBateria=comboBoxBateria;
	}
	
	public void listaBaterias(){
		try {
			BateriaDao bDao = new BateriaDao();
			List<Bateria> listaBateria =bDao.consultaBateria();
			if(comboBoxBateria.getItemCount()>0){
				comboBoxBateria.removeAllItems();
			}
			if(listaBateria!= null){
				for(Bateria b: bDao.consultaBateria()){
					comboBoxBateria.addItem(b);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"ERRO",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
