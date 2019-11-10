import javax.servlet.ServletException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class NetSixClient extends HttpServlet{
    private static String[] serietv = {"CSI", "Game of thrones", "Friends", "Breaking bad", "Jack Ryan", "Vikings"};
    private static int[] episodi = {220, 50, 1500, 65, 100, 45};

    private boolean getDisponibilita(String titolo, int episodio){
        for(int i = 0; i < 6; i++){
            if(serietv[i].equals(titolo)){
                if(episodio <= episodi[i])
                    return true;
                else
                    return false;
            }
        }
        return false;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String titolo = request.getParameter("Titolo");
        int episodio = Integer.parseInt(request.getParameter("Episodio"));

        boolean answer = getDisponibilita(titolo, episodio);

        StringBuffer buff = new StringBuffer();

        buff.append("<html><h1>NetSix</h1>\n");
        buff.append("<p>Hai selezionato: " + titolo + " episodio: " + episodio + "</p\n");
        buff.append("<br><h2>Disponibilita': " + answer + "</h2>\n");
        buff.append("</html>");

        out.println(buff.toString());
        out.close();
    }
}