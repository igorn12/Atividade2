import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.rmi.server.ServerCloneException;
import java.util.ArrayList;
import java.util.List;

@WebListener()
public class InicializaListaUsuarios implements ServletContextListener{

    public InicializaListaUsuarios() {
    }

    public void contextInitialized(ServletContextEvent sce) {
        Usuario u1 = new Usuario(1, "Igor", "igor@gmail.com", "12345");
        Usuario u2 = new Usuario(2, "Ana", "ana@gmail.com", "11111");
        Usuario u3 = new Usuario(3, "Gustavo", "gustavo@gmail.com", "22222");
        Usuario u4 = new Usuario(4, "Cecilia", "cecilia@gmail.com", "33333");

        List<Usuario> usuariosList = new ArrayList<>();
        usuariosList.add(u1);
        usuariosList.add(u2);
        usuariosList.add(u3);
        usuariosList.add(u4);

        List<String> tokensList = new ArrayList<>();

        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("usuarios", usuariosList);
        servletContext.setAttribute("tokens", tokensList);
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }


}
