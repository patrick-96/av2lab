package br.com.teste.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GECservlet
 */
@WebServlet("/GECservlet")
public class GECservlet extends HttpServlet {
	
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 2240661816454385141L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public GECservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
		// TODO Auto-generated method stub
		
	}
	
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out= response.getWriter();
		out.println("<html><head><title>GEEC</title></head><body><h1>GEC</h1></body></head>");
		out.close();
		
	}

}
