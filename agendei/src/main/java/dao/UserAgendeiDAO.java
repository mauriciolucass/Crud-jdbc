package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.ConecxaoAgendei;
import model.BeanUserFone;
import model.Telefone;
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

		while (resuldado.next()) {
			retorno.setId(resuldado.getLong("id"));
			retorno.setNome(resuldado.getString("nome"));
			retorno.setEmail(resuldado.getString("email"));

		}

		return retorno;

	}

	public List<userAgendei> listar() throws SQLException {
		List<userAgendei> list = new ArrayList<userAgendei>();
		String sql = " select * from useragendei";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
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
		String sql = " delete from useragendei where id = " + id;

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

	public void salvartelefone(Telefone telefone) {
		String sql = "INSERT INTO public.telefoneuser( numero, topo, usuariopessoa)VALUES (?,?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, telefone.getNumero());
			statement.setString(2, telefone.getTopo());
			statement.setLong(3, telefone.getUsuario());

			statement.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public List<BeanUserFone> listaUserFone(long idUser){
		List<BeanUserFone> beanUserFones = new ArrayList<BeanUserFone>();
		
	
	try {
		String sql = "select nome,numero,email from telefoneuser as fone ";
		sql += "inner join useragendei as userag";
		sql += "on fone.usuariopessoa = userag.id";
		sql += "where userag.id = "+ idUser;
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			BeanUserFone userFone = new BeanUserFone();
		userFone.setEmail(resultSet.getString("email"));
		userFone.setNome(resultSet.getString("nome"));
		userFone.setNumero(resultSet.getString("numero"));
		
		beanUserFones.add(userFone);
			
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
		return beanUserFones;
		
		
	}
	public void  deletarFonePorUser(long idUser) {
		try {
			String sqlFone = "delete from telefoneuser where usuariopessoa ="+idUser;
			String sqlUser = "delete from useragendei where id ="+ idUser;
			
			PreparedStatement preparedStatement  = connection.prepareStatement(sqlFone);
			preparedStatement.executeLargeUpdate();
			connection.commit();
			
			preparedStatement = connection.prepareStatement(sqlUser);
			preparedStatement.executeLargeUpdate();
			connection.commit();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
