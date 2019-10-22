import java.net.*;
import java.io.*;
import java.time.Instant;

public class Cliente{
    public static final int PUERTO = 9090;
    public static final String DIRECCION = "127.0.0.1";
    
    public static void main(String[] args) {
 
        try (Socket socket = new Socket(DIRECCION, PUERTO)) {
            
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                //Primero se le manda la cantidad de clientes para sacar el promedio
                writer.println("3");
                //Despues se le mandan las horas de los clientes
                writer.println(Instant.now().toString());
                writer.println(Instant.now().toString());
                writer.println(Instant.now().toString());
 
        } catch (UnknownHostException ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
