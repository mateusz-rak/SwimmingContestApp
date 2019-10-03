package swimmingContest.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import swimmingContest.ejb.interfaces.IContestant;
import swimmingContest.entity.Contest;
import swimmingContest.entity.Contestant;
import swimmingContest.exception.AppException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/contestant")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContestantREST {

    @EJB
    private IContestant contestantBean;

    private ObjectMapper objectMapper = new ObjectMapper();

    @GET
    @Path("/getAllContestant")
    public Response getAllContestant(){
        try{
            List<Contestant> contestants = contestantBean.getAllContestant();
            return Response.ok(objectMapper.writeValueAsString(contestants)).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{contestantId}")
    public Response getContestById(@PathParam("contestantId") int contestantId){
        try {
            Contestant contestant = contestantBean.getContestantById(contestantId);
            return Response.ok(objectMapper.writeValueAsString(contestant)).build();
        } catch (JsonProcessingException | AppException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @POST
    @Path("/createContestant")
    public Response createNewContestant(Contestant contestant) {
        try {
            contestantBean.createContestant(contestant);
            return Response.ok(objectMapper.writeValueAsString(contestant)).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/updateContestant/{contestantId}")
    public Response updateNewContestant(@PathParam("contestantId") int contestantId, Contestant contestant) {
        try {
            contestantBean.updateContestant(contestant);
            return Response.ok(objectMapper.writeValueAsString(contestant)).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/deleteContestant/{contestantId}")
    public Response deleteContestant(@PathParam("contestantId") int contestantId){
        try {
            contestantBean.deleteContestant(contestantId);
        } catch (AppException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

}

