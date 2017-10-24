
import controller.BrokerProtocol;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BrokerServerThread extends Thread {
    private Socket socket = null;

    public BrokerServerThread(Socket socket) {
        super("BrokerServerThread");
        this.socket = socket;
    }
    
    public void run() {

        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream()));
        ) {
            JSONObject inputLine = null;
            JSONParser parser = new JSONParser();
            JSONObject outputLine;
            
            BrokerProtocol kkp = new BrokerProtocol();
            outputLine = kkp.processInput(null);
            out.println(outputLine);

            while ((inputLine=(JSONObject)parser.parse(in.readLine())) != null) {
                //System.out.println(inputLine.toString());
                outputLine = kkp.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.toString().equals("{\"Servicio\":\"cerrar\"}"))
                    break;
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(BrokerServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}