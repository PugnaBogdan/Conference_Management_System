package com.config;


import com.api.resources.PaperController;
import com.api.resources.RecommendationController;
import com.api.resources.SectionController;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import io.swagger.v3.jaxrs2.integration.resources.AcceptHeaderOpenApiResource;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * JAX-RS application configuration class.
 */
@ApplicationPath("/api")
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        register(PaperController.class);
        register(SectionController.class);
        register(ComiteeMemberController.class);
        register(AuthorController.class);
        register(ConferenceController.class);
        register(ParticipantController.class);
        register(RecommendationController.class);
        register(JacksonJaxbJsonProvider.class);

        register(OpenApiResource.class);
        register(AcceptHeaderOpenApiResource.class);
    }

}
