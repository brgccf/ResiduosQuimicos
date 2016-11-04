import cucumber.api.PendingException
import cucumber.api.groovy.FA
import residuosquimicos.Laboratorio
import residuosquimicos.LaboratorioController
import residuosquimicos.Usuario

import static cucumber.api.groovy.EN.And
import static cucumber.api.groovy.EN.Given
import static cucumber.api.groovy.EN.When
import static cucumber.api.groovy.EN.Then
/**
 * Created by brgccf on 10/13/2016.
 */

//static final int FACILITADOR = 4
//static final int ADMINISTRADOR = 5
this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

//Usuario facilit = new Usuario("Pedro", "pgrr@cin.ufpe.br", "pedroca", 4)
//Usuario admininst = new Usuario("Fatima", "fhg@ufpe.br", "fatima123", 5)


Given(~/^"([^"]*)" não possui associação a nenhum laboratório cadastrado$/) { String fac ->
    LaboratorioController controlador = new LaboratorioController()
    criarUsuarioFacilitador(fac, "pgrr@cin.ufpe.br", "pedroca", controlador)
    Usuario facilitador = Usuario.findByNome(fac)
    assert facilitador.getTipo() == 4
    assert !(facilitador.getAssociado())
}

And(~/^os laboratórios "([^"]*)" e "([^"]*)" estão disponíveis para associação$/) { String labA, String labB ->
    Laboratorio A = Laboratorio.findByNomeLaboratorio(labA)
    Laboratorio B = Laboratorio.findByNomeLaboratorio(labB)
    assert A.estaSolicitado()
    assert B.estaSolicitado()
}
When(~/^Eu solicito a associação de "([^"]*)" ao Laboratório "([^"]*)"$/) { String fac, String labA ->
    def controlador = new LaboratorioController()
    Usuario facilitador = Usuario.findByNome(fac)
    assert facilitador.getTipo() == 4
    Laboratorio A = Laboratorio.findByNomeLaboratorio(labA)
    controlador.solicitarAssociacao(facilitador, A)
}
Then(~/^o laboratório "([^"]*)" não pode receber mais solicitações$/) { String lab ->
    Laboratorio A = Laboratorio.findByNomeLaboratorio(lab)
    assert !(A.estaSolicitado())
}

//testar parametros

def criarUsuarioFacilitador(String nome, String email, String senha, LaboratorioController controlador)
{

}


Given(~/^"([^"]*)" é um usuário do tipo administrador do sistema$/) { String user ->
    Usuario adm = Usuario.findByNome(user)
    assert adm.getTipo() == 5
}
And(~/^Existe uma solicitação de acesso ao laboratório "([^"]*)" feita pelo usuário do tipo Facilitador "([^"]*)"$/)
        { String lab, String fac ->
            Usuario facilitador = Usuario.findByNome(fac)
            assert facilitador.getTipo() == 4
            Laboratorio labSolicitado = Laboratorio.findByNomeLaboratorio(lab)
            assert !(labSolicitado.estaSolicitado())
            assert labSolicitado.getSolicitante().getNome() == (facilitador.getNome())

}
When(~/^"([^"]*)" realiza a operação de concessão de acesso ao laboratório "([^"]*)" para "([^"]*)"$/)
        { String adm, String lab, String fac ->
            def controlador = new LaboratorioController()
            Laboratorio labConcedido = Laboratorio.findByNomeLaboratorio(lab)
            Usuario admin = Usuario.findByNome(adm)
            assert admin.getTipo() == 5
            Usuario facilitador = Usuario.findByNome(fac)
            assert facilitador.getTipo() == 4
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


Given(~/^"([^"]*)" é um usuário faciitador associado ao laboratório "([^"]*)"$/) { String arg1, String arg2 ->

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
