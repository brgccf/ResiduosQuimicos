package residuosquimicos

class Residuo {
    String nome
    String composicao
    double peso
    String pessoaGerador
    Date dataCadastro
    static belongsTo = [laboratorio:Laboratorio]

    static constraints = {
        nome blank:false, nullable: false
        composicao blank:false, nullable: false
        peso nullable: false
        pessoaGerador blank:false, nullable: false
        dataCadastro nullable:false
        laboratorio nullable:false
    }
    /**
     *
     * @param nome
     * nome do residuo
     * @param composicao
     * composicao do residuo
     * @param peso
     * peso do residuo
     * @param pessoaGerador
     * pessoa Gerador eh a pessoa que gerou o residuo (deve ser informada pelo facilitador)
     * e nao necessariamente eh o facilitador que cadastrar o residuo
     * @param dataCadastro
     * data em que o residuo foi cadastrado
     */

    Residuo(String nome, String composicao, double peso, String pessoaGerador, Date dataCadastro)
    {
        this.nome = nome
        this.composicao = composicao
        this.peso = peso
        this.pessoaGerador = pessoaGerador
        this.dataCadastro = dataCadastro
    }


}