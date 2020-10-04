import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Hello", urlPatterns = "/hello")
public class Hello extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("deu certo");

        ServletContext servletContext = getServletContext();

        List<Usuario> usuarioList = (ArrayList<Usuario>) servletContext.getAttribute("usuarios");

        for (Usuario u : usuarioList) {
            response.getWriter().println(u.toString());
        }

        Connection connection = null;
        try {
            connection = ConectaBanco.getConnection();
        } catch (SQLException ex) {
            response.getWriter().append("Connection Failed! Check output console ");
        }
        if(connection!= null){
            response.getWriter().append("A conexão com o banco foi realizada!");
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else{
            response.getWriter().append("A conexão com o banco falhou!");
        }
        
    }
}
