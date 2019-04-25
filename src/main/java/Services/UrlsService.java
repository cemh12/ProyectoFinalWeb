package Services;

import Entidades.DireccionURL;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class UrlsService extends GeneralService<DireccionURL> {
    private static UrlsService urlsServiceInstance;

    public UrlsService() {
        super(DireccionURL.class);
    }

    public static UrlsService getInstancia() {
        if (urlsServiceInstance == null) {
            urlsServiceInstance = new UrlsService();
        }
        return urlsServiceInstance;
    }

    public DireccionURL findByHash(String hash){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("from DireccionURL where hashMaked = :hash");
        query.setParameter("hash", hash);
        List<DireccionURL> lista = query.getResultList();
        // System.out.println(lista.get(0).getUrl());
        return lista.get(0);
    }
}
