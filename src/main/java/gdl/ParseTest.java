package gdl;

import java.io.FileReader;
import java.util.Iterator;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class ParseTest {
	// provo a scrivere un parser per i JSON e mandare le richieste direttamente
	public static void main(String[] args) {
		JSONParser parser = new JSONParser();

		Settings settings = ImmutableSettings.settingsBuilder()
				.put("cluster.name", "giulio").build();
		Client client = new TransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(
						"localhost", 9300));

		try {
			Object obj = parser.parse(new FileReader("utenti"));
			JSONArray jsonObject = (JSONArray) obj;

			Iterator it = jsonObject.iterator();
			int count = 0;
			while (it.hasNext()) {

				Object next = it.next();
				System.out.println(next);
				IndexResponse risposta = client.prepareIndex("geocon", "users")
						.setSource(next.toString()).execute().actionGet();

				System.out.println(count++);
				System.out.println(risposta.getId());

			}// while

			obj = parser.parse(new FileReader("posti"));
			jsonObject = (JSONArray) obj;
			it = jsonObject.iterator();
			while (it.hasNext()) {
				Object next = it.next();
				System.out.println(next);
				IndexResponse risposta = client
						.prepareIndex("geocon", "places")
						.setSource(next.toString()).execute().actionGet();

				System.out.println(count++);
				System.out.println(risposta.getId());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.close();
		}
	}
}
