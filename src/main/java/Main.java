
import Entidades.*;
import Services.UrlsService;
import Services.UsuarioService;
import Services.VisitaDiaService;
import Services.VisitaService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import freemarker.template.Configuration;
import org.jasypt.util.text.StrongTextEncryptor;
import org.jsoup.Jsoup;
import soap.Arranque;
import spark.ModelAndView;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;
import utils.JsonUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;


public class Main {

    public final static String ACCEPT_TYPE_JSON = "application/json";
    public final static String ACCEPT_TYPE_XML = "application/xml";
    public final static int BAD_REQUEST = 400;
    public final static int ERROR_INTERNO = 500;
    private static String password = "ProyectoFinal";

    public static void main(String[] args) throws Exception {

        DBService.getInstancia().StartDB();
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setClassForTemplateLoading(Main.class, "/templates");
        staticFiles.location("/public");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);
        crearEntidades();
        Arranque.init();

        path("/Api", () -> {
            path("/usuarios", () -> {
                get("/", (request, response) -> {
                    ArrayList<Usuario> listaUsuarios = new ArrayList<>();
                    List<Usuario> usuarios = UsuarioService.getInstancia().findAll();
                    for (Usuario u: UsuarioService.getInstancia().findAll()) {
                        u.setDireccionesURL(null);
                        listaUsuarios.add(u);
                    }
                    return listaUsuarios;
                }, JsonUtils.json());

            });
            path("/urls", () -> {
                get("/", (request, response) -> {
                    ArrayList<DireccionURL> listaUrl = new ArrayList<>();
                    List<DireccionURL> urls = UrlsService.getInstancia().findAll();
                    for (DireccionURL u: UrlsService.getInstancia().findAll()) {
                        System.out.print(u.getUrl());
                        u.setVisitas(null);
                        u.setUsuario(null);
                        listaUrl.add(u);
                    }
                    return listaUrl;
                }, JsonUtils.json());

            });
            path("/urls", () -> {
                get("/user/:usuario/", (request, response) -> {
                    String usuario = request.params("usuario");

                    ArrayList<DireccionURL> listaUrl = new ArrayList<>();
                    for (DireccionURL u: UrlsService.getInstancia().findAll()) {

                        u.setVisitas(null);
                        Usuario user = u.getUsuario();
                        u.setUsuario(null);
                        if(user.getUsuario().equals(usuario)) {

                                listaUrl.add(u);

                        }
                    }
                    return listaUrl;
                }, JsonUtils.json());

            });
            path("/urls", () -> {
                get("/:hashmaked/", (request, response) -> {
                    String hashmaked = request.params("hashmaked");
                    DireccionURL direccionURL = UrlsService.getInstancia().findByHash(hashmaked);

                    URL url = new URL("https://api.linkpreview.net?key=5cc75295d995e28a1faa9dd2f69eeef4279da40dcdfe0&q="+direccionURL.getUrl());
                    URLConnection request1 = url.openConnection();
                    request1.connect();
                    JsonParser jp = new JsonParser(); //from gson
                    JsonElement root = jp.parse(new InputStreamReader((InputStream) request1.getContent())); //Convert the input stream to a json element
                    //May be an array, may be an object.
                    return  root.getAsJsonObject();
                }, JsonUtils.json());

            });

         });

        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
            textEncryptor.setPassword(password);
            Usuario user = UsuarioService.getInstancia().find(String.valueOf(textEncryptor.decrypt(request.cookie("usuario"))));
            if(user != null)
            {
                attributes.put("nombre", user.getNombre());
                attributes.put("rol", user.getRol());
            }else{
                attributes.put("nombre", "No registrado");
                attributes.put("rol", "No registrado");
            }
            return new ModelAndView(attributes, "Home.ftl");
        }, freeMarkerEngine);

        post("/", (request, response) -> {
            StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
            textEncryptor.setPassword(password);
            Map<String, Object> attributes = new HashMap<>();
            DireccionURL direccionURL = new DireccionURL();
            direccionURL.setUrl(request.queryParams("URL"));
            Usuario user = UsuarioService.getInstancia().find(String.valueOf(textEncryptor.decrypt(request.cookie("usuario"))));
            if(user != null)
            {
                direccionURL.setUsuario(user);
                System.out.println(user.getNombre());
                attributes.put("nombre", user.getNombre());
                attributes.put("rol", user.getRol());
            }else{
                attributes.put("nombre", "No registrado");
                attributes.put("rol", "No registrado");
            }
            System.out.print(request.queryParams("URL"));
            Shortener u = new Shortener(5, "www.tinyurl.com/");
            direccionURL.setHashMaked(u.getKey(request.queryParams("URL")));
            UrlsService.getInstancia().crear(direccionURL);
            attributes.put("url", direccionURL.getHashMaked());
            return new ModelAndView(attributes, "Home2.ftl");
        }, freeMarkerEngine);

        get("/Usuarios/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
            textEncryptor.setPassword(password);
            Usuario user = UsuarioService.getInstancia().find(String.valueOf(textEncryptor.decrypt(request.cookie("usuario"))));
            if(user != null)
            {
                attributes.put("nombre", user.getNombre());
                attributes.put("rol", user.getRol());
            }else{
                attributes.put("nombre", "No registrado");
                attributes.put("rol", "No registrado");
            }
            List<Usuario> usuarios = UsuarioService.getInstancia().findAll();
            attributes.put("usuarios", usuarios);
            return new ModelAndView(attributes, "ListarUsuarios.ftl");
        }, freeMarkerEngine);

        get("/Usuarios/:usuario/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
            textEncryptor.setPassword(password);
            Usuario user = UsuarioService.getInstancia().find(String.valueOf(textEncryptor.decrypt(request.cookie("usuario"))));
            if(user != null)
            {
                attributes.put("nombre", user.getNombre());
                attributes.put("rol", user.getRol());
            }else{
                attributes.put("nombre", "No registrado");
                attributes.put("rol", "No registrado");
            }
            String usuario = request.params("usuario");
            Usuario user1 = UsuarioService.getInstancia().find(usuario);
            attributes.put("urls", user1.getDireccionesURL());
            return new ModelAndView(attributes, "ListarURLS.ftl");
        }, freeMarkerEngine);

        get("/:hash/estadisticas/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
            textEncryptor.setPassword(password);
            Usuario user = UsuarioService.getInstancia().find(String.valueOf(textEncryptor.decrypt(request.cookie("usuario"))));
            if(user != null)
            {
                attributes.put("nombre", user.getNombre());
                attributes.put("rol", user.getRol());
            }else{
                attributes.put("nombre", "No registrado");
                attributes.put("rol", "No registrado");
            }
            String hash = request.params("hash");
            DireccionURL direccionURL = UrlsService.getInstancia().findByHash(hash);
            List<VisitaDia> visitaDia1 = VisitaDiaService.getInstancia().findAll();
            List<VisitaDia> visitaDia = new ArrayList<VisitaDia>();
            for (VisitaDia visita :visitaDia1) {

                if(visita.getUrls().getHashMaked().equals(hash))
                {
                    visitaDia.add(visita);
                }
            }
            attributes.put("visitas", direccionURL.getVisitas());
            attributes.put("visitaDia", visitaDia);

            return new ModelAndView(attributes, "ListarVisitas.ftl");
        }, freeMarkerEngine);

        get("/EditarUsuario/:usuario", (request, response) -> {
            String usuario = request.params("usuario");
            Map<String, Object> attributes = new HashMap<>();
            StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
            textEncryptor.setPassword(password);
            Usuario user = UsuarioService.getInstancia().find(String.valueOf(textEncryptor.decrypt(request.cookie("usuario"))));
            if(user != null)
            {
                attributes.put("nombre", user.getNombre());
                attributes.put("rol", user.getRol());
            }else{
                attributes.put("nombre", "No registrado");
                attributes.put("rol", "No registrado");
            }
            Usuario user1 = UsuarioService.getInstancia().find(usuario);
            attributes.put("usuario", user1);
            return new ModelAndView(attributes, "EditarUsuario.ftl");
        }, freeMarkerEngine);

        get("/:hash", (request, response) -> {
            String hash = request.params("hash");
            Map<String, Object> attributes = new HashMap<>();
            DireccionURL direccionURL = UrlsService.getInstancia().findByHash(hash);
            response.redirect(direccionURL.getUrl());
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            Visita visita = new Visita();
            VisitaDia visitadia1 = VisitaDiaService.getInstancia().findBy(direccionURL.getHashMaked(),LocalDate.now().getMonthValue(),
                    LocalDate.now().getDayOfMonth(), LocalDate.now().getYear());
            if(visitadia1 == null) {
                VisitaDia visitaDia = new VisitaDia();
                visitaDia.setDia(LocalDate.now().getDayOfMonth());
                visitaDia.setMes(LocalDate.now().getMonthValue());
                visitaDia.setAnio(LocalDate.now().getYear());
                visitaDia.setUrls(direccionURL);
                visitaDia.setContador(1);
                VisitaDiaService.getInstancia().crear(visitaDia);
            }
            else
            {
                visitadia1.setContador(visitadia1.getContador()+1);
                VisitaDiaService.getInstancia().editar(visitadia1);
            }
            visita.setFecha(sqlDate);
            visita.setIp(request.ip());
            visita.setNavegador(request.userAgent());
            visita.setSistema_operativo(request.userAgent());
            visita.setUrlS(direccionURL);
            VisitaService.getInstancia().crear(visita);
            response.redirect(direccionURL.getUrl());
            return null;
        }, freeMarkerEngine);

        get("/BorrarUsuario/:usuario", (request, response) -> {
            String usuario = request.params("usuario");
            Map<String, Object> attributes = new HashMap<>();
            StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
            textEncryptor.setPassword(password);
            Usuario user = UsuarioService.getInstancia().find(String.valueOf(textEncryptor.decrypt(request.cookie("usuario"))));
            if(user != null)
            {
                attributes.put("nombre", user.getNombre());
                attributes.put("rol", user.getRol());
                if(user.getRol().equals("Administrador"))
                {
                    UsuarioService.getInstancia().eliminar(usuario);
                }
            }else{
                attributes.put("nombre", "No registrado");
                attributes.put("rol", "No registrado");
            }

            List<Usuario> usuarios = UsuarioService.getInstancia().findAll();
            attributes.put("usuarios", usuarios);
            return new ModelAndView(attributes, "ListarUsuarios.ftl");
        }, freeMarkerEngine);

        post("/EditarUsuario/:usuario", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            String usuario = request.params("usuario");
            String rol = request.queryParams("Rol");
            StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
            textEncryptor.setPassword(password);
            Usuario user = UsuarioService.getInstancia().find(String.valueOf(textEncryptor.decrypt(request.cookie("usuario"))));
            if(user != null)
            {
                attributes.put("nombre", user.getNombre());
                attributes.put("rol", user.getRol());
                if(user.getRol().equals("Administrador"))
                {
                    Usuario user1 = UsuarioService.getInstancia().find(usuario);
                    user1.setRol(rol);
                    UsuarioService.getInstancia().editar(user1);
                }
            }else{
                attributes.put("nombre", "No registrado");
                attributes.put("rol", "No registrado");
            }

            List<Usuario> usuarios = UsuarioService.getInstancia().findAll();
            attributes.put("usuarios", usuarios);
            return new ModelAndView(attributes, "ListarUsuarios.ftl");
        }, freeMarkerEngine);

        get("/Registrar/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "Registrar.ftl");
        }, freeMarkerEngine);

        post("/Registrar/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            Usuario usuario = new Usuario();
            usuario.setNombre(request.queryParams("Nombre"));
            usuario.setpassword(request.queryParams("Password"));
            usuario.setEmail(request.queryParams("Email"));
            usuario.setUsuario(request.queryParams("Usuario"));
            usuario.setRol("Ninguno");
            UsuarioService.getInstancia().crear(usuario);
            StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
            textEncryptor.setPassword(password);
            Usuario user = UsuarioService.getInstancia().find(String.valueOf(textEncryptor.decrypt(request.cookie("usuario"))));
            if(user != null)
            {
                attributes.put("nombre", user.getNombre());
                attributes.put("rol", user.getRol());
            }else{
                attributes.put("nombre", "No registrado");
                attributes.put("rol", "No registrado");
            }
            return new ModelAndView(attributes, "Home.ftl");
        }, freeMarkerEngine);


        get("/login/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "Login.ftl");
        }, freeMarkerEngine);

        Spark.post("/login/", (request, response) -> {
            StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
            textEncryptor.setPassword(password);
            //UsuarioService.getInstancia().find();
            Usuario user = UsuarioService.getInstancia().find(request.queryParams("Usuario"));
            System.out.print(user.getpassword());
            System.out.print(request.queryParams("Password"));
            if(user.getpassword().equals(request.queryParams("Password")))
            {
                String userEncripted = textEncryptor.encrypt(user.getUsuario());
                String userRole = textEncryptor.encrypt(user.getRol());
                Map<String, String> cookies=request.cookies();
                String usuario = request.cookie("Usuario");
                boolean esAdministrador = Boolean.valueOf(request.cookie("esAdministrador"));
                System.out.println(request.queryParams("colocarCookieDeMantenerSesion"));
                int esMantenerSesion = (request.queryParams("colocarCookieDeMantenerSesion") != null?86400:1000);
                if (user != null && usuario == null) {
                    response.cookie("/", "usuario", userEncripted, esMantenerSesion, false);
                    System.out.println(user.getNombre());
                    ///Map<String, Object> attributes = new HashMap<>();
                    // attributes.put("titulo", attributes);
                    response.redirect("/");
                } else if(usuario != null) {
                    Map<String, Object> attributes = new HashMap<>();
                    attributes.put("titulo", attributes);
                    response.redirect("/");
                }
                response.redirect("/");
            }
            else response.redirect("/login/");


        return null;
    }, freeMarkerEngine);


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
