package residuosquimicos
/**
 * Classe Laboratorio criada por Bruno Filho em 13/10
 * Funcao: mapear laboratorios de acordo com o centro escolhido, facilitadores cadastrados
 * e referenciar residuos cadastrados nos labs
 */
class Laboratorio {
    /**
     * Nome do centro ao qual pertence
     */
    NomesDeCentros nomeCentro
    /**
     * Nome do departamento ao qual pertence
     */
    NomesDeDepartamentos nomeDepartamento
    /**
     * Nome do laboratorio ao qual pertence
     */
    String nomeLaboratorio
    /**
     * Usuario que solicitou acesso ao sistema
     */
    Usuario solicitante
    /**
     * Usuario que e responsavel pelo laboratorio
     */
    Usuario responsavel

    static hasMany = [residuos: Residuo]

    static constraints = {
        nomeCentro blank: false, nullable: false
        nomeDepartamento blank: false, nullable: false
        nomeLaboratorio blank: false, nullable: false
        solicitante blank: true, nullable: true
        responsavel blank: true, nullable: true
    }

    Laboratorio() {
        residuos = []
    }

    /**
     * metodo estaSolicitado()
     * @return
     * um boolean informando se o laboratório está solicitado ou não
     */
    def boolean estaSolicitado()
    {
        return this.solicitante
    }
    /**
     * metodo tipoUsuarioSolicitante
     * informa o tipo do usuario solicitando o lab
     * @return tipo usuario solicitante
     */
    def tipoUsuarioSolicitante()
    {
        return this.solicitante.tipo
    }
    /**
     * metodo tipoUsuarioResponsavel
     * informa o tipo do usuario responsavel pelo lab
     * @return tipo usuario responsavel
     */
    def tipoUsuarioResponsavel()
    {
        return this.responsavel.tipo
    }
    /**
     * metodo equalsSolicitante
     * verifica se o usuario solicitante é o que foi recebido como parametro
     */
    def equalsSolicitante(Usuario fac)
    {
        return fac == this.solicitante
    }
    /**
     * metodo equalsResponsavel
     * verifica se o usuario responsavel é aquele passado como parametro
     */
    def equalsResponsavel(Usuario fac)
    {
        return fac == this.responsavel
    }
    /**
     * metodo getNomeResponsavel
     * @return nome do responsavel pelo lab
     */
    def getNomeResponsavel()
    {
        return this.responsavel.nome
    }

}