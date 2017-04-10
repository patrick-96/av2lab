package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import view.TelaQuesito;

public class VerQuesitoController implements ActionListener{
	JFrame telaApuracao;
	JButton btnVerQuesito;
	
	public VerQuesitoController(JFrame queFrame,JButton btnVerQuesito) {
		this.telaApuracao=queFrame;
		this.btnVerQuesito=btnVerQuesito;
		
	}
	
	private void montaQuesito(){
		TelaQuesito tq=new TelaQuesito();
		tq.frame.setVisible(true);
		//TotalDao tdao=new TotalDao();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnVerQuesito){
			montaQuesito();
		//	Escola es=new Escola;
	//consultaTotal(es);
		}
		
	}

	
	
	
}
