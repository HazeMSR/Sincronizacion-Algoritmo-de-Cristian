import java.io.*;
import java.net.*;
import java.time.Instant;

public class ServidorUtc {
    public static final int PUERTO = 9090;
    
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
 
            System.out.println("El servidor esta corriendo en el puerto " + PUERTO);
 
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado!");
 
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
 
                writer.println(Instant.now().toString());
            }
 
        } catch (IOException ex) {
            System.out.println("Excepcion del servidor: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
