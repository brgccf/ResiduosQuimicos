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
    CentroList centro
    /**
     * Nome do departamento ao qual pertence
     */
    DepartamentoList departamento
    /**
     * Nome do laboratorio ao qual pertence
     */
    LaboratorioList laboratorio
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
        centro blank: false, nullable: false
        departamento blank: false, nullable: false
        laboratorio blank: false, nullable: false
        solicitante nullable: true
        responsavel nullable: true
    }

    boolean estaSolicitado()
    {
        if(this.solicitante == null) return false
        else return true
    }

}