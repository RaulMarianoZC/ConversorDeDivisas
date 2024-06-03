import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class ConversorDeMonedas {

    private static final String API_KEY = "939e54556d78621a844d0021"; // Mi propia API key
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public static double getExchangeRate(String fromCurrency, String toCurrency) {
        try {
            String urlStr = API_URL + fromCurrency;
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            reader.close();

            JSONObject jsonObj = new JSONObject(json.toString());
            JSONObject rates = jsonObj.getJSONObject("conversion_rates");

            return rates.getDouble(toCurrency);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static double convertCurrency(double amount, double exchangeRate) {
        return amount * exchangeRate;
    }
}