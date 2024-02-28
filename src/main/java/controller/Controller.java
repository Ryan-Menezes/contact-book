package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = {"/Controller", "/main", "/insert", "/edit", "/update", "/delete", "/report"})
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	private DAO dao = new DAO();
	
	/** The contato. */
	private JavaBeans contato = new JavaBeans();
       
    /**
     * Instantiates a new controller.
     */
    public Controller() {
        super();
    }

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		
		if (action.equals("/main")) {
			this.contatos(request, response);
			return;
		}
		
		if (action.equals("/insert")) {
			this.novoContato(request, response);
			return;
		}
		
		if (action.equals("/edit")) {
			this.edit(request, response);
			return;
		}
		
		if (action.equals("/update")) {
			this.update(request, response);
			return;
		}
		
		if (action.equals("/delete")) {
			this.remove(request, response);
			return;
		}
		
		if (action.equals("/report")) {
			this.report(request, response);
			return;
		}
		
		response.sendRedirect("index.html");
	}
	
	/**
	 * Contatos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Listar contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<JavaBeans> contatos = dao.listarContatos();
		
		request.setAttribute("contatos", contatos);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * Novo contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Novo contato
	protected void novoContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.contato.setNome(request.getParameter("nome"));
		this.contato.setFone(request.getParameter("fone"));
		this.contato.setEmail(request.getParameter("email"));
		
		this.dao.inserirContato(contato);
		
		// response.sendRedirect("agenda.jsp");
		response.sendRedirect("main");
	}
	
	/**
	 * Edits the.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Página de editar
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		contato.setId(id);
		
		this.dao.selecionarContato(contato);
		
		request.setAttribute("contato", contato);
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * Update.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Atualizar
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.contato.setId(request.getParameter("id"));
		this.contato.setNome(request.getParameter("nome"));
		this.contato.setFone(request.getParameter("fone"));
		this.contato.setEmail(request.getParameter("email"));
		
		this.dao.atualizarContato(contato);
		
		response.sendRedirect("main");
	}
	
	/**
	 * Removes the.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Deletar
	protected void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.contato.setId(request.getParameter("id"));
		
		this.dao.deletarContato(contato);
		
		response.sendRedirect("main");
	}
	
	/**
	 * Report.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Relatório
	protected void report(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Document documento = new Document();
		
		try {
			ArrayList<JavaBeans> contatos = dao.listarContatos();
			
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "inline; filename=contatos.pdf");
			
			PdfWriter.getInstance(documento, response.getOutputStream());
			
			documento.open();
			
			documento.add(new Paragraph("Lista de contatos:"));
			documento.add(new Paragraph(" "));
			
			PdfPTable table = new PdfPTable(3);
			table.addCell(new PdfPCell(new Paragraph("Nome")));
			table.addCell(new PdfPCell(new Paragraph("Fone")));
			table.addCell(new PdfPCell(new Paragraph("E-mail")));
			
			for (JavaBeans contato : contatos) {
				table.addCell(new Paragraph(contato.getNome()));
				table.addCell(new Paragraph(contato.getFone()));
				table.addCell(new Paragraph(contato.getEmail()));
			}
			
			documento.add(table);
			documento.close();
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}
}
