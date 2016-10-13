package residuosquimicos
/**
 * Classe Laboratorio criada por Bruno Filho em 13/10
 * Funcao: mapear laboratorios de acordo com o centro escolhido, facilitadores cadastrados
 * e referenciar residuos cadastrados nos labs
 */
class Laboratorio {

    String nomeDepartamento
    String nomeLaboratorio
    String responsavel
    static belongsTo = [centro:Centro]
    static hasMany = [residuos:Residuo]

    static constraints = {
        nomeDepartamento blank: false, nullable: false
        nomeLaboratorio blank: false, nullable: false
        responsavel blank: false, nullable: false
    }
    /**
     *
     * @param nome
     * nome do lab
     * @param responsavel
     * facilitador responsavel pelo lab
     */

    Laboratorio(String nome, String responsavel)
    {
        this.nomeLaboratorio = nome
        this.responsavel = responsavel
    }

}