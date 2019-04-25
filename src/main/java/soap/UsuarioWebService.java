package soap;

import Entidades.DireccionURL;
import Entidades.Usuario;
import Services.UsuarioService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebService
public class UsuarioWebService {

    @WebMethod
    public List<UrlDao> getUrl(String usuario) {
        List<UrlDao> urlDaos = new ArrayList<>();
        Usuario user = UsuarioService.getInstancia().find(usuario);
        Set<DireccionURL> urls = user.getDireccionesURL();
        // ArrayList<UrlS> urlsList = new ArrayList<>();
        for (DireccionURL url: urls) {
            UrlDao urlDao = new UrlDao();
            urlDao.setHashMaked(url.getHashMaked());
            urlDao.setUrl(url.getUrl());
            urlDaos.add(urlDao);
        }
        return urlDaos;
    }
}
