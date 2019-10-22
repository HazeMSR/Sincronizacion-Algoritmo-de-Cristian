import java.io.*;
import java.net.*;
import java.time.Instant;
import java.util.ArrayList;

public class ServidorUtc {
    public static final int PUERTO = 9090;
    
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
 
            System.out.println("El servidor esta corriendo en el puerto " + PUERTO);
            int num_clientes = 0;
            int i =0;
            ArrayList<Long> tiempos_clientes = new ArrayList<Long>();
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado!");
                               
                //Obtiene el flujo de entrada del socket
                InputStream flujo_entrada = socket.getInputStream();
                
                //Manda la peticion al socket y la recibe en un buffer de lectura
                BufferedReader lector = new BufferedReader(new InputStreamReader(flujo_entrada));
                
                //Transforma el buffer de lectura a una cadena
                String linea = lector.readLine();
                
                if(linea.matches("[0-9]+")){
                    num_clientes = Integer.valueOf(linea);
                    tiempos_clientes = new ArrayList<Long>();
                }else{
                    //Transforma el tiempo que mando el servidor como cadena a instante
                    Instant tiempo_servidor = Instant.parse(linea);

                    //Obtiene el tiempo actual despues de terminar la peticion de hacer la peticion
                    Instant tiempo_despues = Instant.now();
                    
                    //Obtiene el contador de los milisegundos de la hora del servidor
                    long miliseg_serv = tiempo_servidor.toEpochMilli();
                    //Obtiene el contador de los milisegundos de la hora obtenida despues de llamar al servidor
                    long miliseg_desp = tiempo_despues.toEpochMilli();
                    //Resta los contadores para obtener la diferencia de tiempo
                    long diferencia_tiempo = miliseg_desp - miliseg_serv;
                    
                    System.out.println("Tiempo del Servidor UTC:"+ tiempo_servidor);
                    System.out.println("Tiempo actual: "+ tiempo_despues);
                    System.out.println("Diferencia de milisegundos: "+ diferencia_tiempo);
                    tiempos_clientes.add(diferencia_tiempo);
                    i++;
                    if(i == num_clientes){
                        long promedio = 0;
                        for(int j=0; j < tiempos_clientes.size();j++){
                            promedio += tiempos_clientes.get(j);
                        }
                        promedio /= tiempos_clientes.size();
                    }
                }
            }
 
        } catch (IOException ex) {
            System.out.println("Excepcion del servidor: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
