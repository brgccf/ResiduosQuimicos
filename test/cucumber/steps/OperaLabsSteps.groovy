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
And(~/^a lista de solicitações de associações ao laboratório "([^"])" por usuários do tipo Facilitador está vazia$/) { ->

}
And(~/^os laboratórios "([^"]*)" e "([^"]*)" estão disponíveis para associação$/) { String arg1, String arg2 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
When(~/^Eu solicito a associação de "([^"]*)" ao Laboratório "([^"]*)"$/) { String arg1, String arg2 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
Then(~/^a lista de solicitações de associações é atualizada com uma solicitação de "([^"]*)" para acessar "([^"]*)"$/) { String arg1, String arg2 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}


Given(~/^"([^"]*)" é um usuário do tipo administrador do sistema$/) { String user ->
    user = "adm"
}
And(~/^Existe uma solicitação de acesso ao laboratório "([^"]*)" feita pelo usuário do tipo Facilitador "([^"]*)"$/)
        { String lab, String fac ->
            def controlador = new LaboratorioController()
            Laboratorio labSolicitado = Laboratorio.find(lab)
            controlador.solicitar(fac, labSolicitado)
}
When(~/^"([^"]*)" realiza a operação de concessão de acesso ao laboratório "([^"]*)" para "([^"]*)"$/)
        { String adm, String lab, String fac ->
            def controlador = new LaboratorioController()
            Laboratorio labConcedido = Laboratorio.find(lab)
            controlador.setFacilitador(adm, labConcedido, fac)

}
Then(~/^"([^"]*)" passa a ficar associado ao laboratório "([^"]*)"$/)
        { String fac, String lab ->
            Laboratorio labAssociado = Laboratorio.find(lab)
            labAssociado.setResponsavel(fac)
}

And(~/^"([^"]*)" não pode mais solicitar acesso a laboratórios$/) { String fac ->
    //fac.setAssociado(true)
}
