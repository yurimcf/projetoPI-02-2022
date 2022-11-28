package br.com.yuriCorp.projetoPi.Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.yuriCorp.projetoPi.Model.entity.Aluno;

public class AlunoDAO {
	private final static String INSERT = "INSERT INTO aluno (nome, rg) VALUES (?,?)";
	private final String DELETE = "DELETE FROM aluno WHERE ra=?";
	private final String UPDATE = "UPDATE `aluno` SET `nome` = ?, `rg` = ? WHERE (`ra` = ?);";
	private final String UPDATENOME = "UPDATE `aluno` SET `nome` = ? WHERE (`ra` = ?);";
	private final String UPDATERG = "UPDATE `aluno` SET `rg` = ? WHERE (`ra` = ?);";
	private final String SELECTALL = "SELECT * FROM aluno";
	private final static String SELECTID = "SELECT * FROM ALUNO WHERE ra=?";

	public static boolean inserir(Aluno aluno) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConnectarMySQL.getConexao();
			pstm = conn.prepareStatement(INSERT);

			pstm.setString(1, aluno.getNome());
			pstm.setString(2, aluno.getRg());

			pstm.executeUpdate();
			ConnectarMySQL.closeConexao(conn, pstm);
			JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso.");
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Falha ao tentar REGISTRAR novo aluno " + "ERRO: " + e.getMessage());
		}
		return false;
	}

	public void deleteID(int ra) {
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectarMySQL.getConexao();
			pstm = conn.prepareStatement(DELETE);
			pstm.setInt(1, ra);

			pstm.executeUpdate();
			ConnectarMySQL.closeConexao(conn, pstm);
			JOptionPane.showMessageDialog(null, "Aluno deletado com exito");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Falha ao tentar DELETAR Aluno " + "ERRO: " + e.getMessage());
		}
	}

	public void updateRegistro(Aluno aluno) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConnectarMySQL.getConexao();
			pstm = conn.prepareStatement(UPDATE);
			pstm.setString(1, aluno.getNome());
			pstm.setString(2, aluno.getRg());
			pstm.setString(3, aluno.getRa());

			pstm.executeUpdate();
			ConnectarMySQL.closeConexao(conn, pstm);
			JOptionPane.showMessageDialog(null, "Aluno Atualizado com Sucesso");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Falha ao tentar ATUALIZAR o professor " + "ERRO: " + e.getMessage());
		}
	}

	public void updateNome(Aluno aluno) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConnectarMySQL.getConexao();
			pstm = conn.prepareStatement(UPDATENOME);
			pstm.setString(1, aluno.getNome());
			pstm.setString(2, aluno.getRa());

			pstm.executeUpdate();
			ConnectarMySQL.closeConexao(conn, pstm);
			JOptionPane.showMessageDialog(null, "Nome do Aluno Atualizado com Sucesso");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Falha ao tentar ATUALIZAR o nome do Aluno " + "ERRO: " + e.getMessage());
		}
	}
	
	public void updateRg(Aluno aluno) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConnectarMySQL.getConexao();
			pstm = conn.prepareStatement(UPDATERG);
			pstm.setString(1, aluno.getRg());
			pstm.setString(2, aluno.getRa());

			pstm.executeUpdate();
			ConnectarMySQL.closeConexao(conn, pstm);
			JOptionPane.showMessageDialog(null, "RG do Aluno Atualizado com Sucesso");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Falha ao tentar atualizar o Rg do Aluno " + "ERRO: " + e.getMessage());
		}
	}

	public List<Aluno> getAluno() {
		Connection conn;
		PreparedStatement pstm;
		ResultSet rs;
		ArrayList<Aluno> alunoLista = new ArrayList<Aluno>();
		try {
			conn = ConnectarMySQL.getConexao();
			pstm = conn.prepareStatement(SELECTALL);
			rs = pstm.executeQuery();

			while (rs.next()) {
				Aluno aluno = new Aluno();

				aluno.setNome(rs.getString("nome"));
				aluno.setRa(rs.getString("ra"));
				aluno.setRg(rs.getString("rg"));
				alunoLista.add(aluno);
			}
			ConnectarMySQL.closeConexao(conn, pstm, rs);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar Alunos" + e.getMessage());
		}
		return alunoLista;
	}

	public static Aluno getAlunoId(int ra) {
		Connection conn;
		PreparedStatement pstm;
		ResultSet rs;
		Aluno aluno = new Aluno();
		try {
			conn = ConnectarMySQL.getConexao();
			pstm = conn.prepareStatement(SELECTID);
			rs = pstm.executeQuery();

			pstm.setInt(1, ra);
			while (rs.next()) {
				aluno.setNome(rs.getString("nome"));
				aluno.setRa(rs.getString("ra"));
				aluno.setRg(rs.getString("rg"));
			}

			ConnectarMySQL.closeConexao(conn, pstm);
			JOptionPane.showMessageDialog(null, "Aluno atualizado com sucesso.");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar Alunos" + e.getMessage());
		}

		return aluno;
	}
}
