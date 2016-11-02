package residuosquimicos
/**
 * Classe Laboratorio criada por Bruno Filho em 13/10
 * Funcao: mapear laboratorios de acordo com o centro escolhido, facilitadores cadastrados
 * e referenciar residuos cadastrados nos labs
 */
class Laboratorio {
    boolean solicitado
    /**
     * booleano para informar se o laboratorio ja foi solicitado por algum facilitador. em caso positivo
     * nao pode haver mais solicitaçoes, pois somente um facilitador pode estar associado a um laboratorio
     */
    String nomeDepartamento
    /**
     * nome do departamento associado ao laboratorio
     */
    String nomeLaboratorio
    Usuario solicitante //facilitador que solicitou acesso ao lab
    Usuario responsavel //facilitador associado ao laboratorio
    static hasMany = [residuos: Residuo] //lista de residuos cadastrados no laboratorio

    static constraints = {
        nomeDepartamento blank: false, nullable: false
        nomeLaboratorio blank: false, nullable: false
    }
    /**
     * @param nomeLaboratorio
     * nome do lab
     * @param responsavel
     * facilitador responsavel pelo lab
     */

    Laboratorio(String nome) {
        this.nomeLaboratorio = nome
    }

}