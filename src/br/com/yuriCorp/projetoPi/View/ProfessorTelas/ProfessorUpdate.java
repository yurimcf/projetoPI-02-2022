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

public class ProfessorUpdate extends JFrame implements ActionListener {
	private JButton btnAtt, btnCancelar, btnPesq, btnAttNome, btnAttRg;
	private JTextField campNome, campRgf, campRg;
	private JLabel nome, rgf, rg;

	public ProfessorUpdate() {
		// RGF ID
		rgf = criarEtiqueta("RGF para atualizar.:");
		campRgf = new JTextField(null);
		campRgf.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		getContentPane().add(campRgf);
		btnPesq = criarBotao("Pesq. RGF ", 'P');

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
		btnAtt = criarBotao("Atualizar Tudo", 'A');
		btnCancelar = criarBotao("Cancelar", 'C');

		// parâmetros da tela
		setTitle("Atualizar Professor");
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
			}
		}

		if (e.getSource() == btnAtt) {
			Professor novo = new Professor(campNome.getText(), campRg.getText());
			if (ValidaRG.isRG(novo.getRg()) == true) {
				Professor attProf = new Professor();
				attProf.setRgf(campRgf.getText());
				attProf.setNome(campNome.getText());
				attProf.setRg(campRg.getText());
				dao.updateRegistro(attProf);
			} else {
				JOptionPane.showMessageDialog(null, "RG inválido");
			}
			limparCampos();
		}

		if (e.getSource() == btnAttNome) {
			Professor attProf = new Professor();
			attProf.setNome(campNome.getText());
			attProf.setRgf(campRgf.getText());
			dao.updateNome(attProf);
			limparCampos();
		}

		if (e.getSource() == btnAttRg) {
			Professor novo = new Professor(campNome.getText(), campRg.getText());
			if (ValidaRG.isRG(novo.getRg()) == true) {
				Professor attProf = new Professor();
				attProf.setRg(campRg.getText());
				attProf.setRgf(campRgf.getText());
				dao.updateRg(attProf);
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
