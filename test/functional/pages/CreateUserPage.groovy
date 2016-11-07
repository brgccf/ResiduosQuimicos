package pages

import cucumber.api.PendingException

/**
 * Created by brgccf on 11/6/2016.
 */
import geb.Page
import residuosquimicos.UsuarioList
import steps.InternationalizationHelper

class CreateUserPage extends Page {
    static url = "/ResiduosQuimicos/usuario/create/"

    static at =  {
        InternationalizationHelper helper = InternationalizationHelper.instance
        String labelUsuario = "Criar Usuario"
        String CreateUsuarioList = helper.getMessage("default.create.label", labelUsuario)
        title ==~ CreateUsuarioList
    }

    def criarUsuario(nome, UsuarioList tipo) {
        $('input#nome').value(nome)
        $('input#senha').value("senha")
        $('input#associado').value(false)
        $('input#email').value("email@ufpe.br")
        $('input#ramal').value("1234")
        $('select#tipo').value(tipo)
        $("input", name: "create").click()
    }
}

