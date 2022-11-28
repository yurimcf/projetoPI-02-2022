package br.com.yuriCorp.projetoPi.Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.yuriCorp.projetoPi.Model.entity.Professor;

public class ProfessorDAO {
	private final static String INSERT = "INSERT INTO professor (nome, rg) VALUES (?,?)";
	private final String DELETE = "DELETE FROM professor WHERE rgf=?;";
	private final String UPDATE = "UPDATE `professor` SET `nome` = ?, `rg` = ? WHERE (`rgf` = ?);";
	private final String UPDATENOME = "UPDATE `professor` SET `nome` = ? WHERE (`rgf` = ?);";
	private final String UPDATERG = "UPDATE `professor` SET `rg` = ? WHERE (`rgf` = ?);";
	private final String SELECTALL = "SELECT * FROM professor;";
	private final static String SELECTID = "SELECT * FROM professor WHERE rgf=?;";

	public static boolean inserir(Professor professor) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConnectarMySQL.getConexao();
			pstm = conn.prepareStatement(INSERT);

			pstm.setString(1, professor.getNome());
			pstm.setString(2, professor.getRg());

			pstm.executeUpdate();
			ConnectarMySQL.closeConexao(conn, pstm);
			JOptionPane.showMessageDialog(null, "Professor cadastrado com sucesso.");
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Falha ao tentar REGISTRAR novo professor " + "ERRO: " + e.getMessage());
		}
		return false;
	}

	public void deleteID(int rgf) {
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectarMySQL.getConexao();
			pstm = conn.prepareStatement(DELETE);
			pstm.setInt(1, rgf);

			pstm.executeUpdate();
			ConnectarMySQL.closeConexao(conn, pstm);
			JOptionPane.showMessageDialog(null, "Professor deletado com exito");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Falha ao tentar DELETAR professor " + "ERRO: " + e.getMessage());
		}
	}

	public void updateRegistro(Professor professor) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConnectarMySQL.getConexao();
			pstm = conn.prepareStatement(UPDATE);
			pstm.setString(1, professor.getNome());
			pstm.setString(2, professor.getRg());
			pstm.setString(3, professor.getRgf());

			pstm.executeUpdate();
			ConnectarMySQL.closeConexao(conn, pstm);
			JOptionPane.showMessageDialog(null, "Professor Atualizado com Sucesso");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Falha ao tentar ATUALIZAR o Aluno " + "ERRO: " + e.getMessage());
		}
	}

	public void updateNome(Professor professor) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConnectarMySQL.getConexao();
			pstm = conn.prepareStatement(UPDATENOME);
			pstm.setString(1, professor.getNome());
			pstm.setString(2, professor.getRgf());

			pstm.executeUpdate();
			ConnectarMySQL.closeConexao(conn, pstm);
			JOptionPane.showMessageDialog(null, "Nome do Professor Atualizado com Sucesso");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Falha ao tentar ATUALIZAR o nome do Professor " + "ERRO: " + e.getMessage());
		}
	}

	public void updateRg(Professor professor) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConnectarMySQL.getConexao();
			pstm = conn.prepareStatement(UPDATERG);
			pstm.setString(1, professor.getRg());
			pstm.setString(2, professor.getRgf());

			pstm.executeUpdate();
			ConnectarMySQL.closeConexao(conn, pstm);
			JOptionPane.showMessageDialog(null, "RG do Professor Atualizado com Sucesso");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Falha ao tentar atualizar o Rg do Professor " + "ERRO: " + e.getMessage());
		}
	}

	public List<Professor> getProfessor() {
		Connection conn;
		PreparedStatement pstm;
		ResultSet rs;
		ArrayList<Professor> professorLista = new ArrayList<Professor>();
		try {
			conn = ConnectarMySQL.getConexao();
			pstm = conn.prepareStatement(SELECTALL);
			rs = pstm.executeQuery();

			while (rs.next()) {
				Professor professor = new Professor();

				professor.setNome(rs.getString("nome"));
				professor.setRgf(rs.getString("rgf"));
				professor.setRg(rs.getString("rg"));
				professorLista.add(professor);
			}
			ConnectarMySQL.closeConexao(conn, pstm, rs);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar Professor" + e.getMessage());
		}
		return professorLista;
	}

	public static Professor getProfessorId(int rgf) {
		Connection conn;
		PreparedStatement pstm;
		ResultSet rs;
		Professor professor = new Professor();
		try {
			conn = ConnectarMySQL.getConexao();
			pstm = conn.prepareStatement(SELECTID);
			rs = pstm.executeQuery();

			pstm.setInt(1, rgf);
			while (rs.next()) {
				professor.setNome(rs.getString("nome"));
				professor.setRgf(rs.getString("rgf"));
				professor.setRg(rs.getString("rg"));
			}

			ConnectarMySQL.closeConexao(conn, pstm);
			JOptionPane.showMessageDialog(null, "Professor selecionado com sucesso.");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao selecionar Professor" + e.getMessage());
		}
		return professor;
	}
}
