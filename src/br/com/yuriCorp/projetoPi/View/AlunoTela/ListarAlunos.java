package br.com.yuriCorp.projetoPi.View.AlunoTela;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.yuriCorp.projetoPi.Model.DAO.AlunoDAO;
import br.com.yuriCorp.projetoPi.Model.entity.Aluno;
import br.com.yuriCorp.projetoPi.View.MenuAppTela;

public class ListarAlunos extends JFrame {
	private JPanel painelFundo, painelBotoes;
	private JTable tabela;
	private JScrollPane barraRolagem;
	private JButton btVoltar;
	private DefaultTableModel modelo = new DefaultTableModel();

	public ListarAlunos() {
		tabela = new JTable(modelo);
		modelo.addColumn("RA");
		modelo.addColumn("Nome");
		modelo.addColumn("RG");
		tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
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
		setTitle("Lista de Alunos");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(550, 300);
		setLocationRelativeTo(null);
		setVisible(false);
	}

	private void pesquisar(DefaultTableModel modelo) {
		modelo.setNumRows(0);
		AlunoDAO dao = new AlunoDAO();

		for (Aluno a : dao.getAluno()) {
			modelo.addRow(new Object[] { a.getRa(), a.getNome(), a.getRg() });
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
