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
        String labelUser = "Usuario"
        String userCreatePage = helper.getMessage("default.create.label", labelUser)
        title ==~ userCreatePage
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

