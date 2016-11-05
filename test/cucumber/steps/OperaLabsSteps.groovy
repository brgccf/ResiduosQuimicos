
import cucumber.api.PendingException
import residuosquimicos.Laboratorio
import residuosquimicos.LaboratorioController
import residuosquimicos.Usuario
import residuosquimicos.UsuarioController
import pages.CreateLaboratorioPage
import pages.ShowLaboratorioPage
import residuosquimicos.UsuarioList

import static cucumber.api.groovy.EN.*

/**
 * Created by brgccf on 10/13/2016.
 */

this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

//Usuario facilit = new Usuario("Pedro", "pgrr@cin.ufpe.br", "pedroca", 4)
//Usuario admininst = new Usuario("Fatima", "fhg@ufpe.br", "fatima123", 5)


Given(~/^"([^"]*)" não possui associação a nenhum laboratório cadastrado$/) { String fac ->
    UsuarioController controlador = new UsuarioController()
    criarUsuarioFacilitador(fac, controlador)
    Usuario facilitador = Usuario.findByNome(fac)
    assert facilitador.getTipo() == UsuarioList.FAC
    assert !(facilitador.getAssociado())
}

And(~/^os laboratórios "([^"]*)" e "([^"]*)" do centro "([^"]*)" e dept "([^"]*)" estão disponíveis para associação$/) {
    String labA, String labB, String centro, String depta ->
    createLaboratorioAndCheck(labA, centro, depta)
    createLaboratorioAndCheck(labB, centro, depta)
    Laboratorio A = Laboratorio.findByNomeLaboratorio(labA)
    Laboratorio B = Laboratorio.findByNomeLaboratorio(labB)
    assert A.estaSolicitado()
    assert B.estaSolicitado()
}

When(~/^Eu solicito a associação de "([^"]*)" ao Laboratório "([^"]*)"$/) { String fac, String labA ->
    def controlador = new LaboratorioController()
    Usuario facilitador = Usuario.findByNome(fac)
    assert facilitador.getTipo() == UsuarioList.FAC
    Laboratorio A = Laboratorio.findByNomeLaboratorio(labA)
    controlador.solicitarAssociacao(facilitador, A)
}
Then(~/^o laboratório "([^"]*)" não pode receber mais solicitações$/) { String lab ->
    Laboratorio A = Laboratorio.findByNomeLaboratorio(lab)
    assert !(A.estaSolicitado())
}

//testar parametros
def createLaboratorioAndCheck(String nomeLab, String nomeDep, String nomeCentro){
    to CreateLaboratorioPage
    at CreateLaboratorioPage
    page.createLab(nomeLab, nomeDep, nomeCentro)
    at ShowLaboratorioPage
}
static def criarUsuarioFacilitador(String nome, UsuarioController controlador)
{
    Usuario userFac = new Usuario(nome)
    userFac.setTipo(UsuarioList.FAC)
    controlador.save(userFac)
    controlador.response.reset()
}

static def criarUsuarioAdministrador(String nome, UsuarioController controlador)
{
    Usuario userAdm = new Usuario(nome)
    userAdm.setTipo(UsuarioList.ADMIN)
    controlador.save(userAdm)
    controlador.response.reset()
}


Given(~/^"([^"]*)" é um usuário do tipo administrador do sistema$/) { String user ->
    UsuarioController controlador = new UsuarioController()
    criarUsuarioAdministrador(user, controlador)
    Usuario adm = Usuario.findByNome(user)
    assert adm.getTipo() == UsuarioList.ADMIN

}

And(~/^Existe uma solicitação de acesso ao laboratório "([^"]*)" do centro "([^"]*)" e dept "([^"]*)" feita pelo usuário Facilitador "([^"]*)"$/)
        { String labA, String centro, String depta, String fac ->
            UsuarioController controlador = new UsuarioController()
            criarUsuarioFacilitador(fac, controlador)
    Usuario facilitador = Usuario.findByNome(fac)
    assert facilitador.getTipo() == UsuarioList.FAC
            createLaboratorioAndCheck(labA, centro, depta)
    Laboratorio labSolicitado = Laboratorio.findByNomeLaboratorio(lab)
    assert !(labSolicitado.estaSolicitado())
    assert labSolicitado.getSolicitante().getNome() == (facilitador.getNome())
}
When(~/^"([^"]*)" realiza a operação de concessão de acesso ao laboratório "([^"]*)" para "([^"]*)"$/)
        { String adm, String lab, String fac ->
            def controlador = new LaboratorioController()
            Laboratorio labConcedido = Laboratorio.findByNomeLaboratorio(lab)
            Usuario admin = Usuario.findByNome(adm)
            assert admin.getTipo() == UsuarioList.ADMIN
            Usuario facilitador = Usuario.findByNome(fac)
            assert facilitador.getTipo() == UsuarioList.FAC
            controlador.setFacilitador(admin, labConcedido, facilitador)

}
Then(~/^"([^"]*)" passa a ficar associado ao laboratório "([^"]*)"$/)
        { String fac, String lab ->
            Laboratorio labAssociado = Laboratorio.findByNomeLaboratorio(lab)
            Usuario facilitador = Usuario.findByNome(fac)
            assert labAssociado.getResponsavel().getNome() == facilitador.getNome()
}

And(~/^"([^"]*)" não pode mais solicitar acesso a laboratórios$/) { String fac ->
    Usuario facilitador = Usuario.findByNome(fac)
    assert facilitador.getAssociado()
    facilitador.setAssociado(true)
}


Given(~/^"([^"]*)" é um usuário faciitador associado ao laboratório "([^"]*)"$/) { String fac, String labA ->

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

