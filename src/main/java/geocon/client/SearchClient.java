package geocon.client;

import geocon.common.Paths;
import geocon.common.QParams;
import geocon.service.Main;
import geocon.service.SearchService;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * @author Marco Lackovic <mlackovic@dimes.unical.it>
 * @version 1.0, 19/feb/2015
 */
public class SearchClient {
    private static WebTarget target = ClientBuilder.newClient().target(
            Main.BASE_URI);

    private SearchClient() {
    }

    /**
     * Invoca il metodo remoto {@link SearchService#search(String)} passandogli
     * la query specificata e restituisce il risultato dell'invocazione.
     */
    public static String search(String query) {
        return target.path(Paths.SEARCH).queryParam(QParams.QUERY, "???")
                .request().get(String.class);
    }
}
