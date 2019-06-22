package sp.osgi.webinterface;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sp.osgi.contador.servicio.Contador;

public class ContadorServlet extends HttpServlet {

	private static final long serialVersionUID = -3510119696684783187L;

	protected Contador contador = null;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAction(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAction(req, resp);
	}

	protected void doAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (contador != null) {
			String param = req.getParameter("value");
			if (param != null) {
				int i;
				try {
					i = Integer.parseInt(param);
					contador.incr(i);
				} catch (NumberFormatException e) {
					System.err.println("Parametro incorrecto");
				}

			}

			int value = contador.getValue();
			resp.setContentType("text/html");
			resp.getWriter().write("<html><body>Nuevo valor = " + value + "\n");
			resp.getWriter().write("<BR><A HREF=\"contador\">Refrescar</A>\n");
			resp.getWriter().write("</body></html>");
		} else {
			resp.setContentType("text/html");
			resp.getWriter().write("<html><body>Servicio no disponible. Pruebe en unos minutos\n");
			resp.getWriter().write("<BR><A HREF=\"contador\">Refrescar</A>\n");
			resp.getWriter().write("</body></html>");
		}
		resp.getWriter().flush();
		resp.getWriter().close();

	}

	public void setContador(Contador contador) {
		this.contador = contador;
	}

}
