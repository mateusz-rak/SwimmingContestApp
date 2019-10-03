package swimmingContest.ejb;


import swimmingContest.ejb.interfaces.IContest;
import swimmingContest.entity.Contest;
import swimmingContest.entity.Contestant;
import swimmingContest.entity.Racing;
import swimmingContest.entity.Result;
import swimmingContest.exception.AppException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Stateless
public class ContestEJB implements IContest {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Contest createContest(Contest contest) {
        contest.setDate(contest.getDate().substring(0,10));
        entityManager.persist(contest);
        return contest;
    }

    @Override
    public Contest getContestById(int id) throws AppException{
        Contest contest;
        try {
            contest = entityManager.createQuery(
                    "SELECT c FROM Contest c WHERE c.id = :id", Contest.class)
                    .setParameter("id", id).getSingleResult();
            contest.getRacings().size();
            return contest;
        } catch (NoResultException exc) {
            exc.printStackTrace();
            throw new AppException("Wrong id");
        }
    }

    @Override
    public Racing createRacing(Racing racing, int id) throws AppException {
        Contest contest;
        try {
            contest = entityManager.createQuery(
                    "SELECT c FROM Contest c WHERE c.id = :id", Contest.class)
                    .setParameter("id", id).getSingleResult();
            contest.getRacings().size();
            contest.getRacings().add(racing);
            entityManager.persist(racing);
            entityManager.merge(contest);
            return racing;
        } catch (NoResultException exc) {
            exc.printStackTrace();
            throw new AppException("Wrong id!");
        }
    }

    @Override
    public Result createResult(Result result, int idR, int idC) throws AppException {
        Racing racing;
        Contestant contestant;
        try {
            racing = entityManager.createQuery(
                    "SELECT c FROM Racing c WHERE c.id = :id", Racing.class)
                    .setParameter("id", idR).getSingleResult();
            contestant = entityManager.createQuery(
                    "SELECT c FROM Contestant c WHERE c.id = :id", Contestant.class)
                    .setParameter("id", idC).getSingleResult();
            racing.getResults().size();
            racing.getResults().add(result);
            result.setContestant(contestant);
            entityManager.persist(result);
            entityManager.merge(racing);
            return result;
        } catch (NoResultException exc) {
            exc.printStackTrace();
            throw new AppException("Wrong id!");
        }
    }

    @Override
    public void deleteContest(int id) throws AppException {
        Contest contest;
        try {
            contest = entityManager.createQuery("SELECT c FROM Contest c WHERE c.id = :id", Contest.class)
                    .setParameter("id", id)
                    .getSingleResult();
            entityManager.remove(contest);
        } catch (NoResultException ex) {
            ex.printStackTrace();
            throw new AppException("Wrong id!");
        }
    }

    @Override
    public List<Racing> getAllRacing(int id) {
        Contest contest;
        try {
            contest = entityManager.createQuery(
                    "SELECT c FROM Contest c WHERE c.id = :id", Contest.class)
                    .setParameter("id", id).getSingleResult();
            contest.getRacings().size();
            List<Racing>racings = contest.getRacings();
            for(Racing racing : racings){
                racing.getResults().size();
            }
            return racings;
        } catch (NoResultException exc) {
            return Collections.emptyList();
        } catch (Exception exc) {
            exc.printStackTrace();
            return Collections.emptyList();
        }

    }

    @Override
    public List<Result> getAllResult(int id) {
        Racing racing;
        try {
            racing = entityManager.createQuery(
                    "SELECT c FROM Racing c WHERE c.id = :id", Racing.class)
                    .setParameter("id", id).getSingleResult();
            racing.getResults().size();
            List<Result>results = racing.getResults();
            return results;
        } catch (NoResultException exc) {
            return Collections.emptyList();
        } catch (Exception exc) {
            exc.printStackTrace();
            return Collections.emptyList();
        }

    }

    @Override
    public List<Contest> getAllContest() {
        try {
            List<Contest> contests = entityManager.createQuery(
                    "SELECT c FROM Contest c", Contest.class)
                    .getResultList();
            for(Contest contest : contests){
                contest.getRacings().size();
                for(Racing racing: contest.getRacings()){
                    racing.getResults().size();
                }
            }
            return contests;
        } catch (NoResultException exc) {
            return Collections.emptyList();
        } catch (Exception exc) {
            exc.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void deleteRacingById(int idRacing, int idContest) throws AppException {
        Racing racing;
        Contest contest;
        try {
            contest = entityManager.createQuery(
                    "SELECT c FROM Contest c WHERE c.id = :id", Contest.class)
                    .setParameter("id", idContest).getSingleResult();
            contest.getRacings().size();
            racing = entityManager.createQuery("SELECT c FROM Racing c WHERE c.id = :id", Racing.class)
                    .setParameter("id", idRacing)
                    .getSingleResult();
            contest.getRacings().remove(racing);
            entityManager.merge(contest);
            entityManager.remove(racing);
        } catch (NoResultException ex) {
            ex.printStackTrace();
            throw new AppException("There is no racing with id ");
        }
    }

    @Override
    public void deleteResultById(int idRacing, int idResult) throws AppException {
        Result result;
        Racing racing;
        try {
            racing = entityManager.createQuery(
                    "SELECT c FROM Racing c WHERE c.id = :id", Racing.class)
                    .setParameter("id", idRacing).getSingleResult();
            racing.getResults().size();
            result = entityManager.createQuery("SELECT c FROM Result c WHERE c.id = :id", Result.class)
                    .setParameter("id", idResult)
                    .getSingleResult();
            racing.getResults().remove(result);
            entityManager.merge(racing);
            entityManager.remove(result);
        } catch (NoResultException ex) {
            ex.printStackTrace();
            throw new AppException("There is no racing with id ");
        }
    }

}
