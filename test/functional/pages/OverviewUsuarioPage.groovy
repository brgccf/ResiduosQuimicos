package pages

import geb.Page
import steps.InternationalizationHelper
import residuosquimicos.Laboratorio
class OverviewUsuarioPage extends Page{
    static url = "/ResiduosQuimicos/usuario/overview/"
    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance
        String labelOverview = "List"
        String overviewUsuario = helper.getMessage("default.list.label", labelOverview)
        title ==~ overviewUsuario
    }

    def selecionaLab(String lab)
    {
        $("td", class: "referencia").each {
            if(it.find('a').text().contains(lab))
                it.find('a').click()
        }
    }

    boolean existeMensagemDeConfirmacao(String str)
    {
        $("div", class: "message").text().contains(str)
    }
}
