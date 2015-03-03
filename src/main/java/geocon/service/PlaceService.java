package geocon.service;

import geocon.common.Messages;
import geocon.common.Paths;
import geocon.common.QParams;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;

/**
 * Root resource (exposed at "Event" path)
 */
@Path(Paths.EVENTS)
public class PlaceService {

    private static final String OK_MESSAGE_PLACESERVICE_PUT = Messages.OK_MESSAGE_PLACESERVICE_PUT;
    
	private static final String PRIMARY_INDEX_NAME = Configuration.PRIMARY_INDEX_NAME;
    private static final String PLACES_TYPE_NAME = Configuration.PLACES_TYPE_NAME;
    
    private Client esClient = ElasticSearch.getClient();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get(@QueryParam(QParams.ID) String id) {


        GetResponse response = esClient
                .prepareGet(PRIMARY_INDEX_NAME, PLACES_TYPE_NAME, id).execute()
                .actionGet();

        return response.getSourceAsString();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(@QueryParam(QParams.ID) String id,
            @PathParam("metadata") String metadata) {

    			esClient
    			.prepareIndex(PRIMARY_INDEX_NAME, PLACES_TYPE_NAME, id)
    			.setSource(metadata).execute().actionGet();

    	
        return OK_MESSAGE_PLACESERVICE_PUT;
    }

    @POST
    public String register(@PathParam("metadata") String metadata) {

    	IndexResponse response = esClient
    			.prepareIndex(PRIMARY_INDEX_NAME, PLACES_TYPE_NAME)
    			.setSource(metadata).execute().actionGet();

        return response.getId();
    }

    @DELETE
    public String delete(@PathParam("id") String id) {
    
    	DeleteResponse response = esClient
				.prepareDelete(PRIMARY_INDEX_NAME, PLACES_TYPE_NAME, id)
				.execute().actionGet();
	

        return Boolean.toString(response.isFound());
    }

}
