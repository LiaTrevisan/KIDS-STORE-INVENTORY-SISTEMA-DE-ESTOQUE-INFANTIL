/**
 * Controller para gerenciar operações de Usuario
 * Princípio SRP: Responsável por controlar operações de usuário
 *
 * @author lia
 */

package Control;

import Model.Usuario;

public class UsuarioController {
    private Usuario usuario;

    public void UsuarioBd(){
        //conexao BD
    }
    
    public UsuarioController(Usuario usuario) {
        this.usuario = usuario;
    }

    public void atualizarNome(String nome) {
        usuario.setNome(nome);
    }

    public void atualizarEmail(String email) {
        usuario.setEmail(email);
    }

    public void atualizarSenha(String senha) {
        usuario.setSenha(senha);
    }
}
