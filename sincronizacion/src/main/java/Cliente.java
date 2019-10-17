import java.net.*;
import java.io.*;
import java.time.Instant;

public class Cliente{
    public static final int PUERTO = 9090;
    public static final String DIRECCION = "127.0.0.1";
    
    public static void main(String[] args) {
 
        try (Socket socket = new Socket(DIRECCION, PUERTO)) {
            
            //Obtiene el flujo de entrada del socket
            InputStream flujo_entrada = socket.getInputStream();
            
            //Manda la peticion al socket y la recibe en un buffer de lectura
            BufferedReader lector = new BufferedReader(new InputStreamReader(flujo_entrada));
            
            //Transforma el buffer de lectura a una cadena
            String tiempo = lector.readLine();

            //Transforma el tiempo que mando el servidor como cadena a instante
            Instant tiempo_servidor = Instant.parse(tiempo);

            //Obtiene el tiempo actual despues de terminar la peticion de hacer la peticion
            Instant tiempo_despues = Instant.now();
            
            //Obtiene el contador de los milisegundos de la hora del servidor
            long miliseg_serv = tiempo_servidor.toEpochMilli();
            //Obtiene el contador de los milisegundos de la hora obtenida despues de llamar al servidor
            long miliseg_desp = tiempo_despues.toEpochMilli();
            //Resta los contadores para obtener la diferencia de tiempo
            long diferencia_tiempo = miliseg_despues - miliseg_serv;
            
            System.out.println("Tiempo del Servidor UTC:", tiempo_servidor);
            System.out.println("Tiempo actual: ", tiempo_despues);
            System.out.println("Diferencia de milisegundos: ", diferencia_tiempo);
            
            //Nota: Originalmente este metodo no debia de ser main,
            //si no una funcion llamada pedir tiempo que regrese la variable diferencia_tiempo
            //esta variable del tipo long se le puede sumar a cualquier instante con el metodo
            //instante_de_tiempo.plusMillis(diferencia_tiempo);
            //donde instante_de_tiempo es la variable del tipo Instant que le queremos sumar el error
            //al recibir la hora del servidor
 
        } catch (UnknownHostException ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
