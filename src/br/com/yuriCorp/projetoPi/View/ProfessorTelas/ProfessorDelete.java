package br.com.yuriCorp.projetoPi.View.ProfessorTelas;

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

import br.com.yuriCorp.projetoPi.Model.DAO.ProfessorDAO;
import br.com.yuriCorp.projetoPi.Model.entity.Professor;
import br.com.yuriCorp.projetoPi.View.MenuAppTela;

public class ProfessorDelete extends JFrame implements ActionListener {
	private JButton btnCancelar, btnDelete, btnPesq;
	private JTextField campRgf;
	private JLabel campNome, campRg;
	private JLabel nome, rgf, rg, empty;

	public ProfessorDelete() {
		// RGF ID
		rgf = criarEtiqueta("RGF para Deletar.:");
		campRgf = new JTextField();
		getContentPane().add(campRgf);
		btnPesq = criarBotao("Pesq. RGF ", 'P');

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
		setTitle("Deletar Professor");
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
		campRgf.setText("");
		campRg.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ProfessorDAO dao = new ProfessorDAO();

		if (e.getSource() == btnPesq) {
			int rgfPesq = Integer.parseInt(campRgf.getText());
			int rgfId = 0;
			for (Professor a : dao.getProfessor()) {
				rgfId = Integer.parseInt(a.getRgf());
				if (rgfId == rgfPesq) {
					campNome.setText(a.getNome());
					campRg.setText(a.getRg());
					break;
				}
				rgfId = 0;
			}
			if (rgfId == 0) {
				JOptionPane.showMessageDialog(null, "RGF foi deletado");
				campRgf.setText("");
			}
		}

		if (e.getSource() == btnDelete) {
			int rgfDel = Integer.parseInt(campRgf.getText());
			for (Professor a : dao.getProfessor()) {
				int rgfId = Integer.parseInt(a.getRgf());
				if (rgfId == rgfDel) {
					dao.deleteID(rgfDel);
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
