package residuosquimicos

class Usuario {
    String nome
    String senha

    UsuarioList tipo
    boolean associado //proximo passo: alterar para BelongsTo (associado)
    String ramal //ramal para contato com o usuario
    String email //email de acesso ao sistema

    static constraints = {
        nome nullable: false, blank: false
        senha nullable: false, blank: false
    }

}
