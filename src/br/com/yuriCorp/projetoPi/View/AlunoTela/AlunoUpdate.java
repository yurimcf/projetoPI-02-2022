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

import br.com.yuriCorp.projetoPi.Model.BEAN.ValidaRG;
import br.com.yuriCorp.projetoPi.Model.DAO.AlunoDAO;
import br.com.yuriCorp.projetoPi.Model.entity.Aluno;
import br.com.yuriCorp.projetoPi.View.MenuAppTela;

public class AlunoUpdate extends JFrame implements ActionListener {
	private JButton btnAtt, btnCancelar, btnPesq, btnAttNome, btnAttRg;
	private JTextField campNome, campRa, campRg;
	private JLabel nome, ra, rg;

	public AlunoUpdate() {
		// RA ID
		ra = criarEtiqueta("RA para atualizar.:");
		campRa = new JTextField(null);
		campRa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		getContentPane().add(campRa);
		btnPesq = criarBotao("Pesq. RA ", 'P');

		// nome
		nome = criarEtiqueta("Nome.: ");
		campNome = new JTextField();
		campNome.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		getContentPane().add(campNome);
		btnAttNome = criarBotao("Att. Nome", 'N');

		// rg
		rg = criarEtiqueta("RG.: ");
		campRg = new JTextField();
		campRg.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		getContentPane().add(campRg);
		btnAttRg = criarBotao("Att. RG", 'R');

		// botoes
		btnAtt = criarBotao("Atualizar", 'A');
		btnCancelar = criarBotao("Cancelar", 'C');

		// parâmetros da tela
		setTitle("Atualizar Aluno");
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
		etiqueta.setFont(new Font("Times New Roman", Font.BOLD, 18));
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
	public void limparCampos() {
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
			}
		}

		if (e.getSource() == btnAtt) {
			Aluno novo = new Aluno(campNome.getText(), campRg.getText());
			if (ValidaRG.isRG(novo.getRg()) == true) {
				Aluno attAluno = new Aluno();
				attAluno.setRa(campRa.getText());
				attAluno.setNome(campNome.getText());
				attAluno.setRg(campRg.getText());
				dao.updateRegistro(attAluno);
			} else {
				JOptionPane.showMessageDialog(null, "RG inválido");
			}
			limparCampos();
		}

		if (e.getSource() == btnAttNome) {
			Aluno attAluno = new Aluno();
			attAluno.setNome(campNome.getText());
			attAluno.setRa(campRa.getText());
			dao.updateNome(attAluno);
			limparCampos();
		}

		if (e.getSource() == btnAttRg) {
			Aluno novo = new Aluno(campNome.getText(), campRg.getText());
			if (ValidaRG.isRG(novo.getRg()) == true) {
				Aluno attAluno = new Aluno();
				attAluno.setRg(campRg.getText());
				attAluno.setRa(campRa.getText());
				dao.updateRg(attAluno);
			} else {
				JOptionPane.showMessageDialog(null, "Att. de RG inválida");
			}
		}

		if (e.getSource() == btnCancelar) {
			MenuAppTela a = new MenuAppTela();
			setVisible(false);
			a.setVisible(true);
		}
	}
}
