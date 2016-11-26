package pages

import geb.Page
import steps.InternationalizationHelper
import residuosquimicos.Laboratorio
class OverviewUsuarioPage extends Page{
    static url = "/ResiduosQuimicos/usuario/overview/"
    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance
        String overviewUsuario = helper.getMessage("default.title.page.overviewUsuario")
        title ==~ overviewUsuario
    }

    def selecionaLab(String lab){
        $("a").each {
            if (it.text().equalsIgnoreCase(lab))
            {
                it.click()
            }

        }
    /*
        $("tr", class: "even").each {
             if (it.find('a').text().contains(lab))
             {
                    it.find('a').click()
                    return
            }

        }
        $("tr", class: "odd").each {
            $("td", class: "referencia").each {
                if (it.find('a').text().contains(lab))
                    it.find('a').click()
            }
        }
        */
    }

    boolean existeMensagemDeConfirmacao(String str)
    {
        $("div", class: "message").text().contains(str)
    }
}
