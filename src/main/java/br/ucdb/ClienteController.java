package br.ucdb;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ClienteController")
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String acao = req.getParameter("acao");
		System.out.println(acao);
		GerenciadorCliente gc = new GerenciadorCliente();
		
		if(acao.equals("lis")){
		List<Cliente> clientes = gc.getClientes();
		
		req.setAttribute("cli", clientes);
		
		RequestDispatcher view = req.getRequestDispatcher("clientes.jsp");
		view.forward(req, resp);
		
		}else if(acao.equals("exc")){
			String pos= req.getParameter("pos");
			gc.excluir(Integer.parseInt(pos));
			resp.setContentType("text/html");
			resp.getWriter().print("<script> window.alert('excluido com sucesso!'); local.href='ClienteController?acao=lis'>;  </script> ");

			
		}
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nome= request.getParameter("nome");
		
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		
		GerenciadorCliente gc = new GerenciadorCliente();
		gc.salvar(cliente);
		
		response.setContentType("text/html");
		response.getWriter().print("<script> window.alert('salvo com sucesso!'); local.href='ClienteController?acao=lis'>;  </script> ");

		//response.getWriter().print("salvo com sucesso!!!  <a href='ClienteController'> lista </a> ");
		//doGet(request, response);
		
	}

}
