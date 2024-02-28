package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * The Class DAO.
 */
public class DAO {
	// Módulo de conexão
	
	/** The driver. */
	// Parâmetros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "";
	
	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	// Método de conexão
	private Connection conectar() {
		Connection connection = null;
		
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);
			return connection;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 * Inserir contato.
	 *
	 * @param contato the contato
	 */
	public void inserirContato(JavaBeans contato) {
		String sql = "INSERT INTO contatos (nome, fone, email) VALUES (?, ?, ?)";
		
		try {
			// abrir conexão
			Connection connection = conectar();
			
			// Preparar a query para execução no banco de dados
			PreparedStatement pst = connection.prepareStatement(sql);
			
			// Substituir os parametros (?)
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			
			// Executar query
			pst.executeUpdate();
			
			// Encerrar conexão
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Listar contatos.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> listarContatos() {
		ArrayList<JavaBeans> contatos = new ArrayList<JavaBeans>();
		String sql = "SELECT * FROM contatos ORDER BY nome";
		
		try {
			Connection connection = conectar();
			
			PreparedStatement pst = connection.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String id = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				
				contatos.add(new JavaBeans(id, nome, fone, email));
			}
			
			connection.close();
			
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 * Selecionar contato.
	 *
	 * @param contato the contato
	 */
	public void selecionarContato(JavaBeans contato) {
		String sql = "SELECT * FROM contatos WHERE id = ? LIMIT 1";
		
		try {
			Connection connection = conectar();
			
			PreparedStatement pst = connection.prepareStatement(sql);
			
			pst.setString(1, contato.getId());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				contato.setId(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
			
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Atualizar contato.
	 *
	 * @param contato the contato
	 */
	public void atualizarContato(JavaBeans contato) {
		String sql = "UPDATE contatos SET nome = ?, fone = ?, email = ? WHERE id = ? LIMIT 1";
		
		try {
			// abrir conexão
			Connection connection = conectar();
			
			// Preparar a query para execução no banco de dados
			PreparedStatement pst = connection.prepareStatement(sql);
			
			// Substituir os parametros (?)
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getId());
			
			// Executar query
			pst.executeUpdate();
			
			// Encerrar conexão
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Deletar contato.
	 *
	 * @param contato the contato
	 */
	public void deletarContato(JavaBeans contato) {
		String sql = "DELETE FROM contatos WHERE id = ? LIMIT 1";
		
		try {
			// abrir conexão
			Connection connection = conectar();
			
			// Preparar a query para execução no banco de dados
			PreparedStatement pst = connection.prepareStatement(sql);
			
			// Substituir os parametros (?)
			pst.setString(1, contato.getId());
			
			// Executar query
			pst.executeUpdate();
			
			// Encerrar conexão
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
