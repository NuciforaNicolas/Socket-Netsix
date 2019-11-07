import java.net.*;
import java.io.*;

public class NetsixClient{
    private static final int PORT = 39999;

    public static void main(String[] args) throws IOException{
        Socket remoteHost = null;
        BufferedReader in = null;
        BufferedReader input = null;
        PrintWriter out = null;
        String msg = null;
        
        try{
            remoteHost = new Socket(InetAddress.getLocalHost(), PORT);
            in = new BufferedReader(new InputStreamReader(remoteHost.getInputStream()));
            input = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(remoteHost.getOutputStream())), true);
            
            msg = input.readLine();

            out.println(msg);
            String Answermsg = in.readLine();

            System.out.format("---Received message---\nAnswer: %s", Answermsg);

            in.close();
            out.close();
            remoteHost.close();
        }
        catch(IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        
    }
}