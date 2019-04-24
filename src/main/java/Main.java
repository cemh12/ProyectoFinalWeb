
import Services.UsuarioService;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import freemarker.template.Configuration;
import org.h2.util.DateTimeUtils;
import org.jasypt.util.text.StrongTextEncryptor;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;
import Entidades.Visita;
import Entidades.DireccionURL;
import Entidades.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.sql.Array;
import java.util.*;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static spark.Spark.*;


public class Main {
    public static void main(String[] args) {

        DBService.getInstancia().StartDB();
        crearEntidades();
        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            return "klk";
        });
    }
    private static void crearEntidades() {
        EntityManagerFactory emf =  Persistence.createEntityManagerFactory("Persistencia");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Usuario usuario = new Usuario();
        usuario.setpassword("carlos123");
        usuario.setEmail("cemhcemh12@gmail.com");
        usuario.setNombre("Carlos");
        usuario.setRol("Administrador");
        usuario.setUsuario("cemh12");
        UsuarioService.getInstancia().crear(usuario);
        //entityManager.persist(usuario);
        //entityManager.getTransaction().commit();
    }

}
