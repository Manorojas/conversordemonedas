import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner teclado = new Scanner(System.in);
        String monedaPorCambiar = "USD";
        String monedaPorRecibir = "ARS";
        int monedaValor = 0;
        int opcion = 0;

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();

        String mensaje = """
                *****************************************
                Sea bienvenido/a al conversor de monedas
                
                1) Dolar =>> Peso argentino
                2) Peso argentino =>> Dolar
                3) Dolar =>> Real brasileño
                4) Real Brasileño =>> Dolar
                5) Dolar =>> Peso colombiano
                6) Peso colombiano =>> Dolar
                7) Salir 
                Elija una opción válida
                ******************************************
                """;
        while (opcion !=7) {
            System.out.println(mensaje);
            //System.out.println("Ingrese una opción válida");
            opcion = teclado.nextInt();
            if(opcion !=7) {
                System.out.println("Ingrese el valor que desea convertir");
                monedaValor = teclado.nextInt();


                switch (opcion) {

                    case 1:
                        monedaPorCambiar = "USD";
                        monedaPorRecibir = "ARS";
                        break;
                    case 2:
                        monedaPorCambiar = "ARS";
                        monedaPorRecibir = "USD";
                        break;
                    case 3:
                        monedaPorCambiar = "USD";
                        monedaPorRecibir = "BRL";
                        break;
                    case 4:
                        monedaPorCambiar = "BRL";
                        monedaPorRecibir = "USD";
                        break;
                    case 5:
                        monedaPorCambiar = "USD";
                        monedaPorRecibir = "COP";
                        break;
                    case 6:
                        monedaPorCambiar = "COP";
                        monedaPorRecibir = "USD";
                        break;
                }

                /*String direccion = "https://v6.exchangerate-api."
                        + "com/v6/0c120a41b1a0f336c197565c/pair" +
                        monedaPorCambiar +"/" + monedaPorRecibir +"/" + monedaValor;*/
                String direccion = "https://v6.exchangerate-api."
                        + "com/v6/0c120a41b1a0f336c197565c/pair/" + monedaPorCambiar + "/" + monedaPorRecibir + "/" + monedaValor;




                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccion))
                        .build();
                HttpResponse<String> response = null;
                response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();
                System.out.println(json);
                ConversorResultado miConversorResultado = gson.fromJson(json, ConversorResultado.class);
                Conversor miConversor = new Conversor(miConversorResultado);
                System.out.println("El valor de [" + monedaPorCambiar + " " +
                        monedaValor + "] corresponde al valor final de =>>> " + monedaPorRecibir
                + " " + miConversor.getConversionResult());

            }


        }

    }
}
