package residuosquimicos
/**
 * Classe Usuario
 * Created By Bruno Filho (brgccf) em 19/10/16
 */
class Usuario {
    String nome
    String senha
    char tipo

    String ramal //ramal para contato com o usuario
    String email //email de acesso ao sistema

    static constraints = {
        nome nullable: false, blank: false
        senha nullable: false, blank: false
        tipo nullable: false, blank: false
    }
    /**
     * @param nome
     * nome do usuario
     * @param senha
     * senha de acesso ao sistema
     * @param tipo
     * tipo do usuario
     * tipo 'A' = Administrador
     * tipo 'F' = Facilitador
     */
    Usuario(String nome, String senha, char tipo)
    {
        this.nome = nome
        this.senha = senha
        this.tipo = tipo
    }

}
