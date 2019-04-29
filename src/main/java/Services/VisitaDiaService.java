package Services;

import Entidades.DireccionURL;
import Entidades.VisitaDia;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class VisitaDiaService extends GeneralService<VisitaDia> {
    private static VisitaDiaService visitaDiaServiceInstance;

    public VisitaDiaService() {
        super(VisitaDia.class);
    }

    public static VisitaDiaService getInstancia() {
        if (visitaDiaServiceInstance == null) {
            visitaDiaServiceInstance = new VisitaDiaService();
        }
        return visitaDiaServiceInstance;
    }
    public VisitaDia findBy(String hash, int month, int day, int year){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("from VisitaDia where mes = :month " +
                "and dia =:day and anio =:year ");
        query.setParameter("month", month);
        query.setParameter("day", day);
        query.setParameter("year", year);
        List<VisitaDia> lista = query.getResultList();
        for (VisitaDia visita :lista) {

            if(visita.getUrls().getHashMaked().equals(hash))
            {
                return visita;
            }
        }

        return null;
    }
}
