package residuosquimicos
/**
 * criada em 13/10 por Bruno Filho
 * Funcao: mapear centros e filtrar departamentos de acordo com o centro escolhido
 */
class Centro {
    def departamentos = []
    String nome

    static constraints = {
        nome nullable: false, blank: false
    }
    /**
     * @param nome
     * nome do centro
     */
    Centro(String nome)
    {
        this.nome = nome
    }

}
