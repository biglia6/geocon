package geocon.service;



import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * @author Marco Lackovic <marco.lackovic@gmail.com>
 * @version 1.0, Feb 20, 2015
 */
public class ElasticSearch {

    private static Client instance;

	private static Settings settings = ImmutableSettings.settingsBuilder()
			.put("cluster.name", Configuration.CLUSTER_NAME ).build();
  
	private ElasticSearch() {
  
    }

    @SuppressWarnings("resource")
	public static synchronized Client getInstance() {
        if (instance == null) {
            instance = new TransportClient(settings)
			.addTransportAddress(
					new InetSocketTransportAddress(
					Configuration.CLUSTER_ADDRESS,
					Integer.parseInt(Configuration.CLUSTER_PORT)));
        }
        return instance;
    }

    public static synchronized void start() {
        getInstance();
    }

    public static synchronized void stop() {
        instance.close();
    }

    public static Client getClient() {
        return getInstance();
    }
}
