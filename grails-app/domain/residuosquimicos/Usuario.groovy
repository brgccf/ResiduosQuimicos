package residuosquimicos
/**
 * Classe Usuario
 * Created By Bruno Filho (brgccf) em 19/10/16
 */
class Usuario {
    String nome
    String senha
    static final int FACILITADOR = 4
    static final int ADMINISTRADOR = 5
    int tipo
    boolean associado
    String ramal //ramal para contato com o usuario
    String email //email de acesso ao sistema

    static constraints = {
        nome nullable: false, blank: false
        senha nullable: false, blank: false
    }
    /**
     * @param nome
     * nome do usuario
     * @param senha
     * senha de acesso ao sistema
     * @param tipo
     * tipo do usuario
     */
    Usuario(String nome, String email, String senha, int tipo)
    {
        this.nome = nome
        this.email = email
        this.senha = senha
        this.associado = false
        this.tipo = tipo
    }

    void setAdmin()
    {
        this.tipo = ADMINISTRADOR
    }

    void setFacilitador()
    {
        this.tipo = FACILITADOR
    }
}
