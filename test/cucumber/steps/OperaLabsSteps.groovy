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

static final int FACILITADOR = 4
static final int ADMINISTRADOR = 5
this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

Usuario facilit = new Usuario("Pedro", "pgrr@cin.ufpe.br", "pedroca", FACILITADOR)
Usuario admininst = new Usuario("Fatima", "fhg@ufpe.br", "fatima123", ADMINISTRADOR)


Given(~/^"([^"]*)" não possui associação a nenhum laboratório cadastrado$/) { String fac ->
    Usuario facilitador = Usuario.findByNome(fac)
    assert facilitador.getTipo() == FACILITADOR
    assert !(facilitador.getAssociado())
}

And(~/^os laboratórios "([^"]*)" e "([^"]*)" estão disponíveis para associação$/) { String labA, String labB ->
    Laboratorio A = Laboratorio.findByLaboratorio(labA)
    Laboratorio B = Laboratorio.findByLaboratorio(labB)
    assert A.estaSolicitado()
    assert B.estaSolicitado()
}
When(~/^Eu solicito a associação de "([^"]*)" ao Laboratório "([^"]*)"$/) { String fac, String labA ->
    def controlador = new LaboratorioController()
    Usuario facilitador = Usuario.findByNome(fac)
    assert facilitador.getTipo() == FACILITADOR
    Laboratorio A = Laboratorio.findByLaboratorio(labA)
    controlador.solicitarAssociacao(facilitador, A)
}
Then(~/^o laboratório "([^"]*)" não pode receber mais solicitações$/) { String lab ->
    Laboratorio A = Laboratorio.findByLaboratorio(lab)
    assert !(A.estaSolicitado())
}


Given(~/^"([^"]*)" é um usuário do tipo administrador do sistema$/) { String user ->
    Usuario adm = Usuario.findByNome(user)
    assert adm.getTipo() == ADMINISTRADOR
}
And(~/^Existe uma solicitação de acesso ao laboratório "([^"]*)" feita pelo usuário do tipo Facilitador "([^"]*)"$/)
        { String lab, String fac ->
            def controlador = new LaboratorioController()
            Usuario facilitador = Usuario.findByNome(fac)
            assert facilitador.getTipo() == FACILITADOR
            Laboratorio labSolicitado = Laboratorio.findByLaboratorio(lab)
            assert labSolicitado.getSolicitante().getNome() == (facilitador.getNome())

}
When(~/^"([^"]*)" realiza a operação de concessão de acesso ao laboratório "([^"]*)" para "([^"]*)"$/)
        { String adm, String lab, String fac ->
            def controlador = new LaboratorioController()
            Laboratorio labConcedido = Laboratorio.findByLaboratorio(lab)
            Usuario admin = Usuario.findByNome(adm)
            assert admin.getTipo() == ADMINISTRADOR
            Usuario facilitador = Usuario.findByNome(fac)
            assert facilitador.getTipo() == FACILITADOR
            controlador.setFacilitador(admin, labConcedido, facilitador)

}
Then(~/^"([^"]*)" passa a ficar associado ao laboratório "([^"]*)"$/)
        { String fac, String lab ->
            Laboratorio labAssociado = Laboratorio.findByLaboratorio(lab)
            Usuario facilitador = Usuario.findByNome(fac)
            assert labAssociado.getResponsavel().getNome() == facilitador.getNome()
}

And(~/^"([^"]*)" não pode mais solicitar acesso a laboratórios$/) { String fac ->
    Usuario facilitador = Usuario.findByNome(fac)
    assert facilitador.getAssociado()
    facilitador.setAssociado(true)
}

/*
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
*/