package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.EscolaController;

import javax.swing.JScrollPane;

public class TelaTotal {

	public JFrame frame;
	private JTable tblTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaTotal window = new TelaTotal();
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
	public TelaTotal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 239);
		frame.getContentPane().add(scrollPane);
		
		tblTotal = new JTable();
		scrollPane.setViewportView(tblTotal);
		tblTotal.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Escola", "Total"
			}
		));
		tblTotal.getColumnModel().getColumn(0).setPreferredWidth(292);
		tblTotal.getColumnModel().getColumn(1).setPreferredWidth(170);
	EscolaController eController=new EscolaController(tblTotal);
	eController.tabelaEscolaTotal();
	}

}
