package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

import model.Escola;
import view.TelaTotal;

public class VerTotalController  implements ActionListener{
JFrame telaApuracao;
JButton btnVerTotal;

public VerTotalController(JFrame totFrame,
JButton btnVerTotal){
	this.telaApuracao=totFrame;
	this.btnVerTotal=btnVerTotal;
}

private void montaTotal(){
	TelaTotal tt=new TelaTotal();
	tt.frame.setVisible(true);
	//TotalDao tdao=new TotalDao();
}

@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource()==btnVerTotal){
		montaTotal();
	//	Escola es=new Escola;
//consultaTotal(es);
	}
	
}

/*public void consultaTotal(Escola es){
	List<Escola> listaEscola=eDao.
}*/

}
