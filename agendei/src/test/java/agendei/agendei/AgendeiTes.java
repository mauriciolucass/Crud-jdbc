package agendei.agendei;

import java.util.List;

import org.junit.Test;

import dao.UserAgendeiDAO;
import model.Telefone;
import model.userAgendei;

public class AgendeiTes {
	
	@Test
	public void intbanco() {
		UserAgendeiDAO userAgendeiDAO = new UserAgendeiDAO();
		userAgendei userAgendei = new userAgendei();
		
		userAgendei.setNome("Mauricio");
		userAgendei.setEmail("mauricio@gmail.com");
		
		
		userAgendeiDAO.salvar(userAgendei);
		
		
	}
	
	@Test
	public void intbusca() {
		UserAgendeiDAO dao = new UserAgendeiDAO();
		try {
			
			userAgendei userAgendei = dao.busca(2L);
			System.out.println(userAgendei);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
  @Test
  public void intlistar() {
	  UserAgendeiDAO dao = new UserAgendeiDAO();
	  
	  try {
		  
		  List<userAgendei> list = dao.listar();
		  for(userAgendei userAgendei : list) {
			  System.out.println(userAgendei);
			  System.out.println("================================");
		  }
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	  
  }
  @Test
  public void initatualizar() {
	  UserAgendeiDAO dao = new UserAgendeiDAO();
	  try {
		  userAgendei userobjetobanco = dao.busca(1L);
		  userobjetobanco.setNome("Isis Emanuelly");
		  dao.atualizar(userobjetobanco);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
  @Test
  public void intdelete(){
	  try {
		  UserAgendeiDAO dao = new UserAgendeiDAO();
		  dao.deletar(5L);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	  
  }
  @Test
  public void inttelefone() {
	  Telefone telefone = new Telefone();
	  telefone.setNumero("(86)99425-1245");
	  telefone.setTopo("casa");
	  telefone.setUsuario(4L);
	  
	  UserAgendeiDAO dao = new UserAgendeiDAO();
	  dao.salvartelefone(telefone);
	  
	  
	  
  }
  
}
