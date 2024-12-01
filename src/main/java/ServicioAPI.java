import com.google.gson.Gson;
import java.util.Map;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Servicio para realizar conversiones de moneda utilizando la API de ExchangeRate.
 * Esta clase proporciona métodos para obtener tasas de cambio y realizar conversiones
 * entre diferentes monedas.
 *
 * @author [Camila Durand]
 * @version 2.0
 * @since 2024-03-23
 */
public class ServicioAPI {

    private final String urlBase = "https://v6.exchangerate-api.com/v6/2f6194399b4c52a33f9b591d/latest/USD";
    private final String apiKey = System.getenv("EXCHANGE_API_KEY");
    private final HttpClient client;
    private final Gson gson;

    /**
     * Constructor que inicializa el cliente HTTP y el parser JSON.
     * Configura el cliente HTTP con timeout y versión HTTP/2.
     */
    public ServicioAPI() {
        this.client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
        this.gson = new Gson();
    }

    /**
     * Obtiene las tasas de cambio de varias monedas desde la API ExchangeRate.
     *
     * Este método realiza una solicitud HTTP a la API para obtener un JSON con las tasas de cambio.
     * Los datos se procesan usando Gson para convertirlos en un objeto RespuestasAPI,
     * que contiene las tasas dentro del campo conversion_rates. Solo las monedas soportadas
     * por el record Monedas son consideradas.
     *
     * @return Un objeto Monedas que contiene las tasas de cambio de las monedas seleccionadas (USD, BRL, ARS, COL, MXN).
     * @throws IOException            Si ocurre un error al realizar la solicitud HTTP.
     * @throws InterruptedException   Si la solicitud HTTP es interrumpida.
     * @throws RuntimeException       Si la API devuelve un código de error o no responde correctamente.
     */

    public Monedas obtenerTasasDeCambio() throws IOException, InterruptedException {
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(urlBase))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> respuesta = client.send(solicitud, HttpResponse.BodyHandlers.ofString());

        if (respuesta.statusCode() == 200) {
            RespuestasAPI datos = gson.fromJson(respuesta.body(), RespuestasAPI.class);
            return datos.conversion_rates();
        } else {
            throw new RuntimeException("Error al obtener datos de la API: " + respuesta.statusCode());
        }
    }

    /**
     * Obtiene la tasa específica entre dos monedas.
     *
     * @param tasas         Todas las tasas de cambio obtenidas.
     * @return La tasa de cambio entre las dos monedas.
     */
    private double obtenerTasaMoneda(Monedas tasas, String moneda) {
        switch (moneda) {
            case "USD": return tasas.USD();
            case "BRL": return tasas.BRL();
            case "ARS": return tasas.ARS();
            case "COP": return tasas.COP();
            case "CLP": return tasas.CLP();
            case "BOB": return tasas.BOB();
            default:
                throw new IllegalArgumentException("Moneda no soportada: " + moneda);
        }
    }

    /**
     * Realiza la conversión entre dos monedas utilizando la tasa de cambio proporcionada.
     *
     * @param monto         El monto ingresado por el usuario en la moneda origen.
     * @param tasaDeCambio  La tasa de cambio entre la moneda origen y la moneda destino.
     * @return El monto convertido en la moneda destino.
     */
    public double convertirMoneda(double monto, double tasaDeCambio) {
        return monto * tasaDeCambio;
    }


    /**
     * Calcula la tasa de cambio entre dos monedas.
     *
     * @param tasas         Todas las tasas de cambio obtenidas.
     * @param monedaOrigen  Moneda de partida.
     * @param monedaDestino Moneda destino.
     * @return La tasa de cambio entre las dos monedas.
     */

    public double obtenerTasaEspecifica(Monedas tasas, String monedaOrigen, String monedaDestino) {
        double tasaOrigen = obtenerTasaMoneda(tasas, monedaOrigen);
        double tasaDestino = obtenerTasaMoneda(tasas, monedaDestino);
        return tasaDestino / tasaOrigen;
    }


}




