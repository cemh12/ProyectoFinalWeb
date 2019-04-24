import org.h2.tools.Server;

import java.sql.SQLException;

public class DBService {
    private static DBService instancia;

    public static DBService getInstancia() {
        if (instancia == null) {
            instancia = new DBService();
        }
        return instancia;
    }

    public void StartDB() {
        try {
            Server.createTcpServer("-tcpPort","9092","-tcpAllowOthers","-tcpDaemon").start();

        } catch (SQLException ex) {
            System.out.println("Problema "+ex.getMessage());
        }
    }
    public void init(){
        StartDB();
    }

}
