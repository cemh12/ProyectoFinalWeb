package Services;

import Entidades.Visita;

public class VisitaService extends GeneralService<Visita> {
    private static VisitaService visitaServiceInstance;

    public VisitaService() {
        super(Visita.class);
    }

    public static VisitaService getInstancia() {
        if (visitaServiceInstance == null) {
            visitaServiceInstance = new VisitaService();
        }
        return visitaServiceInstance;
    }

}
