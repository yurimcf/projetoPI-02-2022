package br.com.yuriCorp.projetoPi.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import br.com.yuriCorp.projetoPi.View.AlunoTela.AlunoCadastro;
import br.com.yuriCorp.projetoPi.View.AlunoTela.AlunoDelete;
import br.com.yuriCorp.projetoPi.View.AlunoTela.AlunoUpdate;
import br.com.yuriCorp.projetoPi.View.AlunoTela.ListarAlunos;
import br.com.yuriCorp.projetoPi.View.ProfessorTelas.ListaProfessor;
import br.com.yuriCorp.projetoPi.View.ProfessorTelas.ProfessorCadastro;
import br.com.yuriCorp.projetoPi.View.ProfessorTelas.ProfessorDelete;
import br.com.yuriCorp.projetoPi.View.ProfessorTelas.ProfessorUpdate;

public class MenuAppTela extends JFrame implements ActionListener {
	JLabel home;
	JMenuBar barraMenu;
	JMenu cadastro, atualizar, exclusao, leitura, encerrar;
	JMenuItem professor, aluno, attAluno, attProfessor, delAluno, delProfessor, ListProf, ListAluno, sair;

	public MenuAppTela() {

		// componentes da home
		home = criarHome("Bem Vindo ao Sistema");

		// barra de menu
		barraMenu = new JMenuBar();
		setJMenuBar(barraMenu);

		// elementedo da barra de menu
		cadastro = new JMenu("Cadastro");
		cadastro.setMnemonic('C');
		atualizar = new JMenu("Alteracao");
		atualizar.setMnemonic('T');
		exclusao = new JMenu("Exclusao");
		exclusao.setMnemonic('X');
		leitura = new JMenu("Leitura");
		leitura.setMnemonic('L');
		encerrar = new JMenu("Encerrar");
		encerrar.setMnemonic('N');

		barraMenu.add(cadastro);
		barraMenu.add(atualizar);
		barraMenu.add(exclusao);
		barraMenu.add(leitura);
		barraMenu.add(encerrar);

		// criar itens dos elementos do menu
		professor = criarItem("Professor", 'P', cadastro);
		aluno = criarItem("Aluno", 'A', cadastro);

		attProfessor = criarItem("Atualizar Professor", 'P', atualizar);
		attAluno = criarItem("Atualizar Aluno", 'A', atualizar);

		delProfessor = criarItem("Deletar Professor", 'P', exclusao);
		delAluno = criarItem("Deletar Aluno", 'A', exclusao);

		ListProf = criarItem("Leitura Professor", 'P', leitura);
		ListAluno = criarItem("Leitura Aluno", 'A', leitura);

		sair = criarItem("Sair", 'S', encerrar);

		setTitle("Projeto CRUD PI");
		setSize(550, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	// cria os itens do menu
	private JMenuItem criarItem(String texto, char c, JMenu menu) {
		JMenuItem item = new JMenuItem(texto);
		item.setMnemonic(c);
		item.addActionListener(this);
		menu.add(item);
		return item;
	}

	// metedo pra implementrar a home
	private JLabel criarHome(String texto) {
		JLabel textoH = new JLabel(texto);
		textoH.setVerticalAlignment(SwingConstants.CENTER);
		textoH.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		textoH.setForeground(Color.BLACK);
		textoH.setHorizontalAlignment(SwingConstants.CENTER);
		add(textoH);
		return textoH;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// Cadastro
		if (e.getSource() == professor) { // feito
			ProfessorCadastro a = new ProfessorCadastro();
			a.setVisible(true);
			setVisible(false);
		}

		if (e.getSource() == aluno) { // feito
			AlunoCadastro a = new AlunoCadastro();
			a.setVisible(true);
			setVisible(false);
		}

		// atualizacao
		if (e.getSource() == attAluno) { // feito
			AlunoUpdate a = new AlunoUpdate();
			a.setVisible(true);
			setVisible(false);
		}
		if (e.getSource() == attProfessor) { // feito
			ProfessorUpdate a = new ProfessorUpdate();
			a.setVisible(true);
			setVisible(false);
		}

		// exclusao
		if (e.getSource() == delAluno) { // feito
			AlunoDelete a = new AlunoDelete();
			a.setVisible(true);
			setVisible(false);
		}
		if (e.getSource() == delProfessor) { // feito
			ProfessorDelete a = new ProfessorDelete();
			a.setVisible(true);
			setVisible(false);
		}

		// listas
		if (e.getSource() == ListProf) { // feito
			ListaProfessor a = new ListaProfessor();
			a.setVisible(true);
			setVisible(false);
		}

		if (e.getSource() == ListAluno) { // feito
			ListarAlunos a = new ListarAlunos();
			a.setVisible(true);
			setVisible(false);
		}

		// encerrar
		if (e.getSource() == sair) {
			int sair = JOptionPane.showConfirmDialog(null, "Certeza que deseja Sair");
			if (sair == 0) {
				System.exit(0);
			}
		}
	}
}
