package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import model.Bateria;
import model.Competicao;
import model.Provas;
import persistence.CompeticaoDao;
import persistence.ProvasDao;

public class CompeticaoController {
	private JComboBox<Competicao> comboBoxFase;
	private JComboBox<Bateria> comboBoxBateria;

	public CompeticaoController(JComboBox<Competicao> comboBoxFase,JComboBox<Bateria> comboBoxBateria){
		this.comboBoxFase=comboBoxFase;
		this.comboBoxBateria=comboBoxBateria;
	}

	public void listaFases(){
		try {
			CompeticaoDao cDao = new CompeticaoDao();
			List<Competicao> listaCompeticao =cDao.consultaFases();
			if(comboBoxFase.getItemCount()>0){
				comboBoxFase.removeAllItems();
			}
			if(listaCompeticao!= null){
				for(Competicao c: cDao.consultaFases()){
					comboBoxFase.addItem(c);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"ERRO",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	
	/*public void listaBaterias(){
		try {
			CompeticaoDao cDao = new CompeticaoDao();
			List<Competicao> listaCompeticao =cDao.consultaBateria();
			if(comboBoxBateria.getItemCount()>0){
				comboBoxBateria.removeAllItems();
			}
			if(listaCompeticao!= null){
				for(Competicao c: cDao.consultaBateria()){
					comboBoxBateria.addItem(c);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"ERRO",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			// TODO: handle exception
		}
	}*/
}
