package gdl;
import static geocon.service.Configuration.PRIMARY_INDEX_NAME;
import static geocon.service.Configuration.USERS_TYPE_NAME;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import geocon.service.*;
public class Deletetest {
	public static void main(String[] args) {
		Settings settings = ImmutableSettings.settingsBuilder()
				.put("cluster.name", "giulio").build();
		Client esClient = new TransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(
						"localhost", 9300));
		
		String id = "AUu7Tezj9dmBncEvtcqu";
		
		DeleteResponse response = esClient
				.prepareDelete(PRIMARY_INDEX_NAME, USERS_TYPE_NAME, id)
				.execute().actionGet();
	System.out.println(response.isFound());
	
	id = "nonesiste";
	response = esClient
			.prepareDelete(PRIMARY_INDEX_NAME, USERS_TYPE_NAME, id)
			.execute().actionGet();

	System.out.println(response.isFound());
	}
}
