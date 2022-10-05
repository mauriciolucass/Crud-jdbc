package conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConecxaoAgendei {
	
	private static String url = "jdbc:postgresql://localhost:5432/agendei";
	private static String password ="admin";
	private static String user = "postgres";
	private static Connection connection = null;
	
	static {
		conectar();
	}
	
	public ConecxaoAgendei() {
		 conectar();
	}
	
	
	public static void conectar () {
		try {
			
			if(connection == null) {
				
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				System.out.println("estou conectado ao banco");
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public static Connection getconection() {
		return connection;
	}

}
