package swimmingContest.ejb.interfaces;

import swimmingContest.entity.Contest;
import swimmingContest.entity.Racing;
import swimmingContest.entity.Result;
import swimmingContest.exception.AppException;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IContest {

    Contest createContest(Contest contest);

    Contest getContestById(int id) throws AppException;

    Racing createRacing(Racing racing, int id) throws AppException;

    List<Racing> getAllRacing(int id);

    List<Contest> getAllContest();

    void deleteRacingById(int idRacing, int idContest) throws AppException;

    List<Result> getAllResult(int id);

    Result createResult(Result result, int idR, int idC) throws AppException;

    void deleteContest(int id) throws AppException;

    void deleteResultById(int idRacing, int idResult) throws AppException;

}
