package br.com.yuriCorp.projetoPi.View.ProfessorTelas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.yuriCorp.projetoPi.Model.DAO.ProfessorDAO;
import br.com.yuriCorp.projetoPi.Model.entity.Professor;
import br.com.yuriCorp.projetoPi.View.MenuAppTela;

public class ListaProfessor extends JFrame {
	private JPanel painelFundo, painelBotoes;
	private JTable tabela;
	private JScrollPane barraRolagem;
	private JButton btVoltar;
	private DefaultTableModel modelo = new DefaultTableModel();

	public ListaProfessor() {
		tabela = new JTable(modelo);
		modelo.addColumn("RGF");
		modelo.addColumn("Nome");
		modelo.addColumn("RG");
		tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(150);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(90);
		pesquisar(modelo);

		btVoltar = new JButton("Voltar");
		painelBotoes = new JPanel();
		barraRolagem = new JScrollPane(tabela);
		painelFundo = new JPanel();

		painelFundo.setLayout(new BorderLayout());
		painelFundo.add(BorderLayout.CENTER, barraRolagem);
		painelBotoes.add(btVoltar);
		painelFundo.add(BorderLayout.SOUTH, painelBotoes);
		btVoltar.addActionListener(new BtVoltarListener());

		getContentPane().add(painelFundo);
		setTitle("Lista de Professores");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(550, 300);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void pesquisar(DefaultTableModel modelo) {
		modelo.setNumRows(0);
		ProfessorDAO dao = new ProfessorDAO();

		for (Professor p : dao.getProfessor()) {
			modelo.addRow(new Object[] { p.getRgf(), p.getNome(), p.getRg() });
		}
	}

	private class BtVoltarListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btVoltar) {
				MenuAppTela a = new MenuAppTela();
				setVisible(false);
				a.setVisible(true);
			}
		}
	}
}
