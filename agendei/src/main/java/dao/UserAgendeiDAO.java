package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.ConecxaoAgendei;
import model.userAgendei;

public class UserAgendeiDAO {
	
	private Connection connection;
	
	public UserAgendeiDAO() {
		connection = ConecxaoAgendei.getconection();
	}
	
	public void salvar(userAgendei userAgendei) {
		String sql = "insert into useragendei (nome,email)values(?,?)";
		
		
		try {
			PreparedStatement insert = connection.prepareStatement(sql);
			
			insert.setString(1, userAgendei.getNome());
			insert.setString(2, userAgendei.getEmail());
			insert.execute();
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public userAgendei busca(long id) throws SQLException {
		userAgendei retorno = new userAgendei();
		String sql = "select * from useragendei where id = " + id;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resuldado = statement.executeQuery();
		
		while(resuldado.next()) {
			retorno.setId(resuldado.getLong("id"));
			retorno.setNome(resuldado.getString("nome"));
			retorno.setEmail(resuldado.getString("email"));
			
			
		}
		
		return retorno;
		
		
	}
	public List<userAgendei> listar() throws SQLException{
		List<userAgendei> list = new ArrayList<userAgendei>();
		String sql = " select * from useragendei";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {
			userAgendei userAgendei = new userAgendei();
			
			userAgendei.setId(resultado.getLong("id"));
			userAgendei.setNome(resultado.getString("nome"));
			userAgendei.setEmail(resultado.getString("email"));
			
			list.add(userAgendei);
		}
		
		return list;
		
	}
	public void atualizar(userAgendei userAgendei) {
		
		try {
			String sql = "update useragendei set nome = ? where " + " id = " + userAgendei.getId();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userAgendei.getNome());
			statement.execute();
			connection.commit();
	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			connection.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void deletar(long id) {
		String sql = " delete from useragendei where id = "+ id;
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			connection.commit();
			try {
				connection.rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
