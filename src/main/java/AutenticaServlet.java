import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AutenticaServlet", urlPatterns = "/autentica")
public class AutenticaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var email = request.getParameter("email");
        var senha = request.getParameter("senha");

        ServletContext servletContext = getServletContext();
        List<Usuario> usuarioList = (ArrayList<Usuario>) servletContext.getAttribute("usuarios");
        List<String> tokensList = (ArrayList<String>) servletContext.getAttribute("tokens");


        var watcher = false;
        for (var u : usuarioList) {
            if(u.getEmail().equals(email) && u.getSenha().equals(senha)){
                watcher = true;
                var token = u.getEmail() + u.getSenha();
                Cookie cookie = new Cookie("authentication", token);
                response.addCookie(cookie);

                tokensList.add(token);
                servletContext.setAttribute("tokens", tokensList);

                var dispatcher = request.getRequestDispatcher("user/hello.html");
                dispatcher.forward(request, response);
            }
        }
        if(watcher == false){
            //redirecionar
        }
    }
}
