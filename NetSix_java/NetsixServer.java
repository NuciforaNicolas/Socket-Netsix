import java.net.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import java.io.*;

public class NetsixServer{
    private static final int PORT = 39999;
    public static void main(String[] args) throws IOException{
        ServerSocket server = new ServerSocket(PORT);
        Socket client = null;
        BufferedReader in = null;
        PrintWriter out = null;
        String msg = null;

        System.out.println("---Server Ready---");

        try{
            while(true){
                client = server.accept();
                System.out.format("Connection established: [%s,%d]\n", client.getLocalAddress(), client.getLocalPort());

                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);

                msg = in.readLine();
                StringTokenizer s = new StringTokenizer(msg, ",");
                String title = s.nextToken();
                int episode =  Integer.parseInt(s.nextToken());
                System.out.format("Request -> Title = %s\tEpisode = %d\n", title, episode);

                switch(OffertaAbbonati.getDisponibilita(title, episode)){
                    case 1 : out.println("Disponibile"); break;
                    case 0 : out.println("Coming Soon"); break;
                    case -1 : out.println("Serie non in catalogo"); break;
                    default : out.println("ERROR: please, try again");
                }

                System.out.println("---Message sent---");

                out.close();
                in.close();
                client.close();
            }
        }
        catch(IOException e){
            e.printStackTrace();
            System.exit(1);
        }

        server.close();
    }
}