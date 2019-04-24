package Services;

import Entidades.Usuario;

public class UsuarioService extends GeneralService<Usuario> {
    private static UsuarioService usuarioserviceInstance;

    public UsuarioService() {
        super(Usuario.class);
    }

    public static UsuarioService getInstancia() {
        if (usuarioserviceInstance == null) {
            usuarioserviceInstance = new UsuarioService();
        }
        return usuarioserviceInstance;
    }
}

