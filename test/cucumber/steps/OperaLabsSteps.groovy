import cucumber.api.PendingException

import static cucumber.api.groovy.EN.And
import static cucumber.api.groovy.EN.Given
import static cucumber.api.groovy.EN.When
import static cucumber.api.groovy.EN.Then
/**
 * Created by brgccf on 10/13/2016.
 */

this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

Given(~/^"([^"]*)" não possui associação a nenhum laboratório cadastrado$/) { String arg1 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
And(~/^a lista de solicitações de associações ao laboratório  por usuários do tipo Facilitador está vazia$/) { ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
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


Given(~/^"([^"]*)" é um usuário do tipo administrador do sistema$/) { String arg1 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
And(~/^Existe uma solicitação de acesso ao laboratório "([^"]*)" feita pelo usuário do tipo Facilitador "([^"]*)"$/) { String arg1, String arg2 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
When(~/^"([^"]*)" realiza a operação de concessão de acesso ao laboratório "([^"]*)" para "([^"]*)"$/) { String arg1, String arg2, String arg3 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
Then(~/^"([^"]*)" passa a ficar associado ao laboratório "([^"]*)"$/) { String arg1, String arg2 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
And(~/^"([^"]*)" recebe um email de confirmação de acesso ao laboratório "([^"]*)"$/) { String arg1, String arg2 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
And(~/^"([^"]*)" não pode mais solicitar acesso a laboratórios$/) { String arg1 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
