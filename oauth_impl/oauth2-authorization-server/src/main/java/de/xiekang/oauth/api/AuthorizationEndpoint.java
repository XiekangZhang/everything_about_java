package de.xiekang.oauth.api;

import de.xiekang.oauth.model.AppDataRepository;
import de.xiekang.oauth.model.Client;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.*;

import java.io.IOException;

@RolesAllowed("USER")
@RequestScoped
@Path("authorize")
public class AuthorizationEndpoint {

    @Inject
    private AppDataRepository appDataRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response doGet(@Context HttpServletRequest request,
                          @Context HttpServletResponse response,
                          @Context UriInfo uriInfo) throws ServletException, IOException {
        MultivaluedMap<String, String> params = uriInfo.getQueryParameters();

        // error about redirect_uri && client_id ==> forward user, thus to error.jsp
        // otherwise ==> sendRedirect redirect_uri?error=error&error_description=error_description
        // 1. client_id
        String clientId = params.getFirst("client_id");
        if (clientId == null || clientId.isEmpty()) {
            request.setAttribute("error", "{\"error\": \"Invalid client_id\"}");
            return null;
        }
        Client client = appDataRepository.getClient(clientId);
        if (client == null) {
            request.setAttribute("error", "{\"error\": \"Invalid client_id\"}");
            return null;
        }
        return null;
    }

}
