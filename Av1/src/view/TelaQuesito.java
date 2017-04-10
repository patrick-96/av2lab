package view;

import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.EscolaController;
import controller.QuesitoConsultaController;
import model.Quesito;

import javax.swing.JScrollPane;

public class TelaQuesito {

	public JFrame frame;
	private JTable table;
	private JTable table_Quesito;
	private JScrollPane scrollPane_1;
	JComboBox<Quesito> comboBox_q;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaQuesito window = new TelaQuesito();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaQuesito() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 772, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 736, 239);
		frame.getContentPane().add(scrollPane_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);

		table_Quesito = new JTable();
		scrollPane.setViewportView(table_Quesito);
		DefaultTableModel modelo=new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null, null, null, null},
				},
				new String[] {
					"Escola", "Quesito", "1\u00BA_Jurado", "2\u00BA_Jurado", "3\u00BA_Jurado", "4\u00BA_Jurado", "5\u00BA_Jurado", "Menor Descartadas", "Menor Descartada", "Total"
				}
			);
		table_Quesito.setModel(modelo);
		table_Quesito.getColumnModel().getColumn(7).setPreferredWidth(111);
		/*DefaultTableModel modelo = new DefaultTableModel(new Object[][] {},
				new String[] { "Escola", "Quesito", "Maior descartada", "Menor Descartada", "Total" });*/

		QuesitoConsultaController qcController=new QuesitoConsultaController(table_Quesito, comboBox_q);
		qcController.tabelaVerQuesito();
	}
}
