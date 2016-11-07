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
    CentroList nomeCentro
    /**
     * Nome do departamento ao qual pertence
     */
    DepartamentoList nomeDepartamento
    /**
     * Nome do laboratorio ao qual pertence
     */
    LaboratorioList nomeLaboratorio
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

    /**
     * metodo estaSolicitado()
     * @return
     * um boolean informando se o laboratório está solicitado ou não
     */
    def boolean estaSolicitado()
    {
        return this.solicitante
    }

}