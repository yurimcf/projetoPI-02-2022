package br.com.yuriCorp.projetoPi.View.AlunoTela;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.yuriCorp.projetoPi.Model.DAO.AlunoDAO;
import br.com.yuriCorp.projetoPi.Model.entity.Aluno;
import br.com.yuriCorp.projetoPi.View.MenuAppTela;

public class AlunoDelete extends JFrame implements ActionListener {
	private JButton btnCancelar, btnDelete, btnPesq;
	private JTextField campRa;
	private JLabel campNome, campRg;
	private JLabel nome, ra, rg, empty;

	public AlunoDelete() {
		// RA ID
		ra = criarEtiqueta("RA para Deletar.:");
		campRa = new JTextField();
		getContentPane().add(campRa);
		btnPesq = criarBotao("Pesq. RA ", 'P');

		// nome
		nome = criarEtiqueta("Nome.: ");
		campNome = EtiquetaInfo("");
		getContentPane().add(campNome);
		empty = new JLabel();
		getContentPane().add(empty);

		// rg
		rg = criarEtiqueta("RG.: ");
		campRg = EtiquetaInfo("");
		getContentPane().add(campRg);
		empty = new JLabel();
		getContentPane().add(empty);

		// botoes
		btnDelete = criarBotao("Deletar", 'D');
		btnCancelar = criarBotao("Cancelar", 'C');

		// parâmetros da tela
		setTitle("Deletar Aluno");
		setSize(550, 300);
		GridLayout gl = new GridLayout(4, 3, 3, 30); // linha, coluna, espessuraH - espessuraV
		getContentPane().setBackground(new Color(200, 200, 200));
		getContentPane().setLayout(gl);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(false);
	}

	// metodo para criarLabel
	private JLabel criarEtiqueta(String texto) {
		JLabel etiqueta = new JLabel(texto);
		etiqueta.setFont(new Font("Times New Roman", Font.BOLD, 20));
		etiqueta.setForeground(Color.BLACK);
		etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
		add(etiqueta);
		return etiqueta;
	}

	// metodo para criar Label de Info
	private JLabel EtiquetaInfo(String texto) {
		JLabel etiqueta = new JLabel(texto);
		etiqueta.setFont(new Font("Times New Roman", Font.BOLD, 24));
		etiqueta.setForeground(Color.BLACK);
		etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
		add(etiqueta);
		return etiqueta;
	}

	// metodo para criarbotao
	private JButton criarBotao(String texto, char c) {
		JButton botao = new JButton(texto);
		botao.setMnemonic(c);
		botao.addActionListener(this);
		add(botao);
		return botao;
	}

	// limpa os Campos
	private void limparCampos() {
		campNome.setText("");
		campRa.setText("");
		campRg.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AlunoDAO dao = new AlunoDAO();

		if (e.getSource() == btnPesq) {
			int raPesq = Integer.parseInt(campRa.getText());
			int raId = 0;
			for (Aluno a : dao.getAluno()) {
				raId = Integer.parseInt(a.getRa());
				if (raId == raPesq) {
					campNome.setText(a.getNome());
					campRg.setText(a.getRg());
					break;
				}
				raId = 0;
			}
			if (raId == 0) {
				JOptionPane.showMessageDialog(null, "RA foi deletado");
				campRa.setText("");
			}
		}

		if (e.getSource() == btnDelete) {
			int raDel = Integer.parseInt(campRa.getText());
			for (Aluno a : dao.getAluno()) {
				int raId = Integer.parseInt(a.getRa());
				if (raId == raDel) {
					dao.deleteID(raDel);
					break;
				}
			}
			limparCampos();
		}

		if (e.getSource() == btnCancelar) {
			MenuAppTela a = new MenuAppTela();
			setVisible(false);
			a.setVisible(true);
		}
	}
}
