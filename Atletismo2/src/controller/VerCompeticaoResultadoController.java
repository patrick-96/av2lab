package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import model.Bateria;
import model.Competicao;
import model.Provas;
import view.TelaResultado;

public class VerCompeticaoResultadoController implements ActionListener{
	JFrame telaCombos;
	JButton btnExibir;
	JComboBox<Bateria> comboBoxBateria;
	JComboBox<Competicao> comboBoxFase;
	JComboBox<Provas> comboBoxProva;
	
	public VerCompeticaoResultadoController(JFrame queFrame,JButton btnExibir,JComboBox<Provas> comboBoxProva,JComboBox<Competicao> comboBoxFase, JComboBox<Bateria> comboBoxBateria) {
		this.telaCombos=queFrame;
		this.btnExibir=btnExibir;
		this.comboBoxProva=comboBoxProva;
		this.comboBoxFase=comboBoxFase;
		this.comboBoxBateria=comboBoxBateria;
		
	}
	
	private void montaResultado(){
		TelaResultado tr=new TelaResultado(comboBoxProva,comboBoxFase, comboBoxBateria);
		tr.setVisible(true);;
		//TotalDao tdao=new TotalDao();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnExibir){
			montaResultado();
		//	Escola es=new Escola;
	//consultaTotal(es);
		}
		
	}

}
