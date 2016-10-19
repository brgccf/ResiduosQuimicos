import cucumber.api.PendingException
import residuosquimicos.Laboratorio
import residuosquimicos.LaboratorioController

import static cucumber.api.groovy.EN.And
import static cucumber.api.groovy.EN.Given
import static cucumber.api.groovy.EN.When
import static cucumber.api.groovy.EN.Then
/**
 * Created by brgccf on 10/13/2016.
 */

this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

Given(~/^"([^"]*)" não possui associação a nenhum laboratório cadastrado$/) { String fac ->

    //assert !(fac.estaAssociado())
}

And(~/^os laboratórios "([^"]*)" e "([^"]*)" estão disponíveis para associação$/) { Laboratorio labA, Laboratorio labB ->
    assert labA.solicitado
    assert labB.solicitado
}
When(~/^Eu solicito a associação de "([^"]*)" ao Laboratório "([^"]*)"$/) { String fac, String labA ->
    def controlador = new LaboratorioController()
    Laboratorio A = Laboratorio.findByNomeLaboratorio(labA)
    controlador.solicitar(fac, A)
}
Then(~/^o laboratório "([^"]*)" não pode receber mais solicitações$/) { String lab ->
    Laboratorio A = Laboratorio.findByNomeLaboratorio(lab)
    A.setSolicitado(true)
}


Given(~/^"([^"]*)" é um usuário do tipo administrador do sistema$/) { String user ->
    user = "adm"
}
And(~/^Existe uma solicitação de acesso ao laboratório "([^"]*)" feita pelo usuário do tipo Facilitador "([^"]*)"$/)
        { String lab, String fac ->
            def controlador = new LaboratorioController()
            Laboratorio labSolicitado = Laboratorio.findByNomeLaboratorio(lab)
            controlador.solicitar(fac, labSolicitado)
}
When(~/^"([^"]*)" realiza a operação de concessão de acesso ao laboratório "([^"]*)" para "([^"]*)"$/)
        { String adm, String lab, String fac ->
            def controlador = new LaboratorioController()
            Laboratorio labConcedido = Laboratorio.findByNomeLaboratorio(lab)
            controlador.setFacilitador(adm, labConcedido, fac)

}
Then(~/^"([^"]*)" passa a ficar associado ao laboratório "([^"]*)"$/)
        { String fac, String lab ->
            Laboratorio labAssociado = Laboratorio.findByNomeLaboratorio(lab)
            labAssociado.setResponsavel(fac)
}

And(~/^"([^"]*)" não pode mais solicitar acesso a laboratórios$/) { String fac ->
    //fac.setAssociado(true)
}
