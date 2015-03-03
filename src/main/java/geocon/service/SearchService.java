package geocon.service;

import geocon.common.Paths;
import geocon.common.QParams;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path(Paths.SEARCH)
public class SearchService {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String search(@QueryParam(QParams.QUERY) String query) {
        // TODO Return the result of the query

        return query;
    }

}
