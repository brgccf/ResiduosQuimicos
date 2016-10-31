package residuosquimicos
/**
 * criada em 13/10 por Bruno Filho
 * Funcao: mapear centros e filtrar departamentos de acordo com o centro escolhido
 */
class Centro {
    def departamentos = []
    /**
     * Lista de departamentos vai guardar strings com nomes de departamentos do Centro
     */
    static hasMany = [laboratorios:Laboratorio]
    /**
     * Lista de Laboratorios existentes dentro daquele Centro
     * vai guardar os diversos laboratorios do centro e permitir filtragem a partir da recuperação de
     * nomeDepartamento (atributo de Laboratorio) e comparar isto com a lista de departamentos cadastrados.
     */
    String nome

    static constraints = {
        nome nullable: false, blank: false
    }
    /**
     * método addDepartamento:
     * adiciona departamento à lista de um dado centro
     * @param dept
     * nome do departamento a ser adicionado à lista
     */
    void addDepartamento(String dept)
    {
        this.departamentos.add(dept)
    }


}
