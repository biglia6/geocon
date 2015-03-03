package geocon.service;

import static geocon.service.Configuration.PRIMARY_INDEX_NAME;
import static geocon.service.Configuration.RESOURCES_TYPE_NAME;
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

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;

@Path(Paths.RESOURCES)
public class ResourceService {

	    private static final String OK_MESSAGE_RESOURCESERVICE_PUT = Messages.OK_MESSAGE_RESOURCESERVICE_PUT;
		private Client esClient = ElasticSearch.getClient();

	    @GET
	    @Produces(MediaType.APPLICATION_JSON)
	    public String get(@QueryParam(QParams.ID) String id) {
	        // TODO Authentication
	        try {
	            GetResponse response = esClient
	                    .prepareGet(PRIMARY_INDEX_NAME, RESOURCES_TYPE_NAME, id)
	                    .execute().actionGet();
	            if (response != null) {
	                return response.getSourceAsString();
	            }
	        } catch (ElasticsearchException e) {
	            e.printStackTrace();
	            return e.getMessage();
	        }
	        return Messages.RESOURCE_NOT_FOUND;
	    }

	    @PUT
	    @Consumes(MediaType.APPLICATION_JSON)
	    public String update(@QueryParam(QParams.ID) String id,
	            @PathParam("metadata") String metadata) {
	        // TODO Authentication

	    	boolean isMyself = true;
	    	
	    	// precondition, the user exsists. If the check fails, you
	    	// should put the isMyself flag at false.
	    	if (isMyself){
	    			
	    				esClient
	    				.prepareIndex(PRIMARY_INDEX_NAME, RESOURCES_TYPE_NAME, id)
	    				.setSource(metadata).execute().actionGet();
	    	}
	    	
	        return OK_MESSAGE_RESOURCESERVICE_PUT;
	    }

	    @POST
	    public String register(@PathParam("metadata") String metadata) {

	        // TODO Authentication
	    	IndexResponse response = esClient
	    			.prepareIndex(PRIMARY_INDEX_NAME, RESOURCES_TYPE_NAME)
	    			.setSource(metadata).execute().actionGet();
	    	
	        return response.getId();
	    }

	    @DELETE
	    public String delete(@PathParam("id") String id) {
	        // TODO Authentication

	        // TODO only a user can delete himself
	    	// TODO manage if the ID doesn't exist.
	    	boolean isMyself = true;
	    	boolean deleted = false;
	    	
	    	if (isMyself){
	    		DeleteResponse response = esClient
	    				.prepareDelete(PRIMARY_INDEX_NAME, RESOURCES_TYPE_NAME, id)
	    				.execute().actionGet();
	    	
	    		deleted = response.isFound();
	    	}
	    	
	        return Boolean.toString(deleted);
	    }

	

}
