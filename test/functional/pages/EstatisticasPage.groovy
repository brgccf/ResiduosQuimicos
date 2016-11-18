package pages

import geb.Page
import residuosquimicos.Laboratorio
import steps.InternationalizationHelper
import src.groovy.LaboratorioList

class EstatisticasPage extends Page{
    static url = "/ResiduosQuimicos/status/estatisticas/"
    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance
        String estatisticasTitleList = helper.getMessage("default.title.page.estatisticas")
        title ==~ estatisticasTitleList
    }

    boolean eOmaiorGerador(String nomeLab){
        def lab = Laboratorio.findByNomeLaboratorio(nomeLab)
        InternationalizationHelper helper = InternationalizationHelper.instance
        String messageMaiorGerador = helper.getMessage("message.title.estatisticas.maiorGerador", lab.nomeLaboratorio, lab.nomeDepartamento, lab.nomeCentro)
        $('textField#maiorGerador-message').text() ==~ messageMaiorGerador
    }


}
