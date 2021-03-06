package com.api.resources;

import com.input.PaperInput;
import com.model.PaperJson;
import com.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.List;

@Path("/")
public class PaperController {

    private PaperService paperService;

    @Autowired
    public PaperController(PaperService paperService) {
        this.paperService = paperService;
    }

    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    @Path("paper")
    public Serializable submitPaper(PaperInput paper) {
        return paperService.submitPaper(paper);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("paper/{paperId}")
    public PaperJson findById(@PathParam("paperId") int id) {
        return paperService.findById(id);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("papers")
    public List<PaperJson> getAll() {

        return paperService.getAll();
    }

    @PUT
    @Path("paper/{paperId}")
    @Consumes("application/json")
    public void updatePaper(@PathParam("paperId") int paperId, PaperInput paperInput) {
        paperService.updatePaper(paperId, paperInput);
    }


}
