import cucumber.api.PendingException
import pages.CreateUserPage
import residuosquimicos.Laboratorio
import residuosquimicos.LaboratorioController
import residuosquimicos.Usuario
import residuosquimicos.UsuarioController
import residuosquimicos.UsuarioList
import pages.CreateLaboratorioPage
import pages.IndexLaboratorioPage
import pages.ShowLaboratorioPage
import pages.ResumoSistemaPage
/**
 * Created by brgccf on 11/5/2016.
 */
import cucumber.api.groovy.EN.*
this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

//CONTROLLER SCENARIOS
Given(~/^"([^"]*)" não possui associação a nenhum laboratório cadastrado$/) { String fac ->
    UsuarioController controlador = new UsuarioController()
    criarUsuarioFacilitador(fac, controlador)
    Usuario facilitador = Usuario.findByNome(fac)
    assert facilitador.tipo == UsuarioList.FAC
    assert !(facilitador.getAssociado())
}

And(~/^os laboratórios "([^"]*)" e "([^"]*)" do centro "([^"]*)" e dept "([^"]*)" estão disponíveis para associação$/) {
    String labA, String labB, String centro, String depta ->
        criarLaboratorio(labA, depta, centro)
        criarLaboratorio(labB, depta, centro)
        Laboratorio A = Laboratorio.findByNomeLaboratorio(labA)
        Laboratorio B = Laboratorio.findByNomeLaboratorio(labB)
        assert !(A.estaSolicitado())
        assert !(B.estaSolicitado())
}

When(~/^Eu solicito a associação de "([^"]*)" ao Laboratório "([^"]*)"$/) { String fac, String labA ->
    def controlador = new LaboratorioController()
    Usuario facilitador = Usuario.findByNome(fac)
    assert facilitador.tipo == UsuarioList.FAC
    Laboratorio A = Laboratorio.findByNomeLaboratorio(labA)
    controlador.solicitarAssociacao(facilitador, A)
}
Then(~/^o laboratório "([^"]*)" não pode receber mais solicitações$/) { String lab ->
    Laboratorio A = Laboratorio.findByNomeLaboratorio(lab)
    assert (A.estaSolicitado())
}

//testar parametros

static def criarUsuarioFacilitador(String nome, UsuarioController controlador)
{
    Usuario userFac = new Usuario([nome:nome, senha:"senhafac", tipo: UsuarioList.FAC, associado: false, ramal: "1234",
    email: "email@ufpe.br"])
    controlador.save(userFac)
    controlador.response.reset()
}

static def criarUsuarioAdministrador(String nome, UsuarioController controlador)
{
    Usuario userAdm = new Usuario([nome:nome, senha: "senhaadm", tipo: UsuarioList.ADMIN, associado: false, ramal: "4321",
    email: "emailadmin@ufpe.br"])
    controlador.save(userAdm)
    controlador.response.reset()
}

def criarLaboratorio(String nomeLab, String nomeDep, String nomeCentro){
    def lab = new Laboratorio([nomeCentro:nomeCentro, nomeDepartamento:nomeDep, nomeLaboratorio:nomeLab, solicitante: null,
                               responsavel:null])
    def controlador = new LaboratorioController()
    controlador.save(lab)
    controlador.response.reset()
}

Given(~/^"([^"]*)" é um usuário do tipo administrador do sistema$/) { String user ->
    UsuarioController controlador = new UsuarioController()
    criarUsuarioAdministrador(user, controlador)
    Usuario adm = Usuario.findByNome(user)
    if(!adm) throw new PendingException("administrador esta null!")
    assert adm.tipo == UsuarioList.ADMIN

}
And(~/^Existe uma solicitação de acesso ao laboratório "([^"]*)" do centro "([^"]*)" e dept "([^"]*)" feita pelo usuário Facilitador "([^"]*)"$/)
        { String lea, String centro, String depta, String fac ->
            UsuarioController controlador = new UsuarioController()
            criarUsuarioFacilitador(fac, controlador)
            Usuario facilitador = Usuario.findByNome(fac)
            if(!facilitador) throw new PendingException("facilitador esta null!")
            assert facilitador.tipo == UsuarioList.FAC
            Laboratorio labSolicitado = Laboratorio.findByNomeLaboratorio(lea)
            assert labSolicitado.estaSolicitado()
            assert labSolicitado.solicitante.nome == facilitador.nome
}

When(~/^"([^"]*)" realiza a operação de concessão de acesso ao laboratório "([^"]*)" para "([^"]*)"$/)
        { String adm, String lab, String fac ->
            def controlador = new LaboratorioController()
            Laboratorio labConcedido = Laboratorio.findByNomeLaboratorio(lab)
            Usuario admin = Usuario.findByNome(adm)
            if(!adm) throw new PendingException("administrador esta null (PARTE 2)!")
            assert admin.tipo == UsuarioList.ADMIN
            Usuario facilitador = Usuario.findByNome(fac)
            if(!facilitador) throw new PendingException("facilitador esta null (PARTE 2)!")
            assert facilitador.tipo == UsuarioList.FAC
            controlador.setFacilitador(admin, labConcedido, facilitador)

        }
Then(~/^"([^"]*)" passa a ficar associado ao laboratório "([^"]*)"$/)
        { String fac, String lab ->
            Laboratorio labAssociado = Laboratorio.findByNomeLaboratorio(lab)
            Usuario facilitador = Usuario.findByNome(fac)
            assert labAssociado.responsavel.nome == facilitador.nome
        }

And(~/^"([^"]*)" não pode mais solicitar acesso a laboratórios$/) { String fac ->
    Usuario facilitador = Usuario.findByNome(fac)
    assert !(facilitador.associado)
    facilitador.setAssociado(true)
}

//GUI SCENARIOS
//testar parametros
def createLaboratorioAndCheck(String nomeLab, String nomeDep, String nomeCentro){
    to CreateLaboratorioPage
    at CreateLaboratorioPage
    page.createLab(nomeLab, nomeDep, nomeCentro)
    at ShowLaboratorioPage
}

Given(~/^eu criei o usuário Facilitador "([^"]*)"$/) { String nome ->
    to CreateUserPage
    at CreateUserPage
    page.criarUsuario(nome, UsuarioList.FAC)
}

And(~/^eu criei os laboratório "([^"]*)" e "([^"]*)" do centro "([^"]*)" e dept "([^"]*)"$/) {
    String lablae, String lablamai, String centro, String depta ->
    to CreateLaboratorioPage
        at CreateLaboratorioPage
        page.createLab(lablae, centro, depta)
        page.createLab(lablamai, centro, depta)
        at ShowLaboratorioPage
}

And(~/^eu associei o laboratório "([^"]*)" a "([^"]*)"$/) { String lab, String fac ->

}

And(~/^o laboratório "([^"]*)" está disponível para associação$/) { String arg1 ->

}
When(~/^eu tento solicitar associação de "([^"]*)" ao laboratório "([^"]*)"$/) { String arg1, String arg2 ->

}
Then(~/^eu posso ver uma mensagem de erro indicando que "([^"]*)" já está associado laboratorio "([^"]*)"$/) { String arg1, String arg2 ->

}


Given(~/^"([^"]*)" é um usuário do tipo facilitador sem associações a laboratórios$/) { String arg1 ->

}
When(~/^eu tento solicito associação de "([^"]*)" ao laboratório "([^"]*)"$/) { String arg1, String arg2 ->

}
Then(~/^eu posso ver uma mensagem de confirmação indicando a solicitação de "([^"]*)" para acessar o laboratório "([^"]*)"$/)
        { String arg1, String arg2 ->

        }
