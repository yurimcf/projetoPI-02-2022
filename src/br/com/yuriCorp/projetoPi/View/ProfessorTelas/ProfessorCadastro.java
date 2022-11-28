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

import br.com.yuriCorp.projetoPi.Model.BEAN.ValidaRG;
import br.com.yuriCorp.projetoPi.Model.DAO.ProfessorDAO;
import br.com.yuriCorp.projetoPi.Model.entity.Professor;
import br.com.yuriCorp.projetoPi.View.MenuAppTela;

public class ProfessorCadastro extends JFrame implements ActionListener {
	private JButton btnAdd, btnCancelar;
	private JTextField campNome, campRg;
	private JLabel nome, rgf, rg;

	public ProfessorCadastro() {
		// nome
		nome = criarEtiqueta("Nome.: ");
		campNome = new JTextField();
		campNome.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		getContentPane().add(campNome);

		// rg
		rg = criarEtiqueta("RG.:");
		campRg = new JTextField();
		campRg.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		getContentPane().add(campRg);

		// botoes
		btnAdd = criarBotao("Adicionar", 'A');
		btnCancelar = criarBotao("Cancelar", 'C');

		setTitle("Cadastrar novo Professor");
		setSize(550, 300);
		GridLayout gl = new GridLayout(3, 2, 3, 40); // linha, coluna, espessuraH - espessuraV
		getContentPane().setBackground(new Color(200, 200, 200));
		getContentPane().setLayout(gl);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	// metodo para criarLabel
	private JLabel criarEtiqueta(String texto) {
		JLabel etiqueta = new JLabel(texto);
		etiqueta.setFont(new Font("Times New Roman", Font.BOLD, 25));
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

	private void limparCampos() {
		campNome.setText("");
		campRg.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			Professor novo = new Professor(campNome.getText(), campRg.getText());
			String rg = novo.getRg();
			if (ValidaRG.isRG(rg) == true) {
				System.out.printf("%s\n", ValidaRG.imprimeRG(rg));
				if (ProfessorDAO.inserir(novo) == true) {
					limparCampos();
				}
			} else {
				JOptionPane.showMessageDialog(null, "RG inválido");
			}
		}

		if (e.getSource() == btnCancelar) {
			JOptionPane.showMessageDialog(null, "Formulario Cancelado");
			MenuAppTela a = new MenuAppTela();
			setVisible(false);
			a.setVisible(true);
		}
	}
}
