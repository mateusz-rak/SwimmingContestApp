package swimmingContest.ejb.interfaces;

import swimmingContest.entity.Contest;
import swimmingContest.entity.Contestant;
import swimmingContest.entity.Result;
import swimmingContest.exception.AppException;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IContestant {

    Contestant createContestant(Contestant contestant);

    Contestant updateContestant(Contestant contestant);

    Contestant getContestantById(int id) throws AppException;

    List<Contestant> getAllContestant();

    void deleteContestant(int id) throws AppException;

}
