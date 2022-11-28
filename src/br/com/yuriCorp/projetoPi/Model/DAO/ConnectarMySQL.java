package br.com.yuriCorp.projetoPi.Model.DAO;

import java.sql.*;

public class ConnectarMySQL {
	private final static String url = "jdbc:mysql://localhost:3306/projetopi2";
	private final static String username = "root";
	private final static String password = "@yuri5140";

	private static Connection conn;

	public static Connection getConexao() {
		try {
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("\nConexao estabelecida com sucesso!\n");
		} catch (SQLException e) {
			System.out.println("\nNao foi possivel estabelecer conexao " + e + "\n");
		}
		return conn;
	}

	public static void closeConexao() {
		try {
			conn.close();
			System.out.println("\nExito ao encerrar conexao");
		} catch (SQLException e) {
			System.out.println("\nNao foi possivel fechar conexao " + e + "\n");
			System.exit(1);
		}
	}
	
	public static void closeConexao(Connection conn, PreparedStatement ptmt) {
		try {
			conn.close();
			ptmt.close();
			System.out.println("\nExito ao encerrar conexao");
		} catch (SQLException e) {
			System.out.println("\nNao foi possivel fechar conexao " + e + "\n");
			System.exit(1);
		}
	}
	public static void closeConexao(Connection conn, PreparedStatement ptmt, ResultSet rs) {
		try {
			conn.close();
			ptmt.close();
			System.out.println("\nExito ao encerrar conexao");
		} catch (SQLException e) {
			System.out.println("\nNao foi possivel fechar conexao " + e + "\n");
			System.exit(1);
		}
	}
}
