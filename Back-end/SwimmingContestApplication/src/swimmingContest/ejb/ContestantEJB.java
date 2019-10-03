package swimmingContest.ejb;


import swimmingContest.ejb.interfaces.IContestant;
import swimmingContest.entity.Contest;
import swimmingContest.entity.Contestant;
import swimmingContest.exception.AppException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Stateless
public class ContestantEJB implements IContestant {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Contestant createContestant(Contestant contestant) {
        entityManager.persist(contestant);
        return contestant;
    }

    @Override
    public Contestant updateContestant(Contestant contestant) {
        entityManager.merge(contestant);
        return contestant;
    }

    @Override
    public Contestant getContestantById(int id) throws AppException{
        Contestant contestant;
        try {
            contestant = entityManager.createQuery(
                    "SELECT c FROM Contestant c WHERE c.id = :id", Contestant.class)
                    .setParameter("id", id).getSingleResult();
            return contestant;
        } catch (NoResultException exc) {
            exc.printStackTrace();
            throw new AppException("Wrong id");
        }
    }

    @Override
    public List<Contestant> getAllContestant() {
        try {
            List<Contestant> contestants = entityManager.createQuery(
                    "SELECT c FROM Contestant c", Contestant.class)
                    .getResultList();

            return contestants;
        } catch (NoResultException exc) {
            return Collections.emptyList();
        } catch (Exception exc) {
            exc.printStackTrace();
            return Collections.emptyList();
        }
    }


    @Override
    public void deleteContestant(int id) throws AppException{
        Contestant contestant;
        try {
            contestant = entityManager.createQuery("SELECT c FROM Contestant c WHERE c.id = :id", Contestant.class)
                    .setParameter("id", id)
                    .getSingleResult();
            entityManager.remove(contestant);
        } catch (NoResultException ex) {
            ex.printStackTrace();
            throw new AppException("Wrong id!");
        }
    }
}