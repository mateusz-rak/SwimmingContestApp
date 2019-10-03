package swimmingContest.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import swimmingContest.ejb.interfaces.IContest;
import swimmingContest.entity.Contest;
import swimmingContest.entity.Racing;
import swimmingContest.entity.Result;
import swimmingContest.exception.AppException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/contest")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContestREST  {

    @EJB
    private IContest contestBean;

    private ObjectMapper objectMapper = new ObjectMapper();

    @GET
    @Path("/getAllContest")
    public Response getAllContest(){
        try {
            List<Contest> contest = contestBean.getAllContest();
            return Response.ok(objectMapper.writeValueAsString(contest)).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{contestId}")
    public Response getContestById(@PathParam("contestId") int contestId){
        try {
            Contest contest = contestBean.getContestById(contestId);
            return Response.ok(objectMapper.writeValueAsString(contest)).build();
        } catch (JsonProcessingException | AppException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @POST
    @Path("/createContest")
    public Response createNewContest(Contest contest) {
        try {
            contestBean.createContest(contest);
            return Response.ok(objectMapper.writeValueAsString(contest)).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/{contestId}/racing/createRacing")
    public Response createNewRacing(Racing racing, @PathParam("contestId") int contestId) {
        try {
            contestBean.createRacing(racing, contestId);
            return Response.ok(objectMapper.writeValueAsString(racing)).build();
        } catch (JsonProcessingException | AppException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
        @Path("/{contestId}/racing/{racingId}/result/createResult/{contestantId}")
    public Response createNewResult(Result result, @PathParam("contestId") int contestId, @PathParam("racingId") int racingId,
    @PathParam("contestantId") int contestantId) {
        try {
            contestBean.createResult(result, racingId,contestantId);
            return Response.ok(objectMapper.writeValueAsString(result)).build();
        } catch (JsonProcessingException | AppException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/deleteContest/{contestId}")
    public Response deleteContest(@PathParam("contestId") int contestId){
        try {
            contestBean.deleteContest(contestId);
        } catch (AppException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

    @GET
    @Path("/{contestId}/racing/{racingId}/getAllResult")
    public Response getAllResult(@PathParam("contestId") int contestId, @PathParam("racingId") int racingId){
        try {
            List<Result> results = contestBean.getAllResult(racingId);
            return Response.ok(objectMapper.writeValueAsString(results)).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{contestId}/getAllRacing")
    public Response getAllRacing(@PathParam("contestId") int contestId){
        try {
            List<Racing> racings = contestBean.getAllRacing(contestId);
            return Response.ok(objectMapper.writeValueAsString(racings)).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{contestId}/deleteRacing/{racingId}")
    public Response deleteRacingById(@PathParam("contestId") int contestId, @PathParam("racingId") int racingId){
        try {
            contestBean.deleteRacingById(racingId,contestId);
        } catch (AppException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/{contestId}/racing/{racingId}/deleteResult/{resultId}")
    public Response deleteRacingById(@PathParam("contestId") int contestId, @PathParam("racingId") int racingId,
                                     @PathParam("racingId") int resultId){
        try {
            contestBean.deleteResultById(racingId,resultId);
        } catch (AppException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

}
