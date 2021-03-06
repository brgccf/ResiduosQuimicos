package pages

import geb.Page
import residuosquimicos.Laboratorio
import residuosquimicos.InternationalizationHelper

class CreateResiduoPage extends Page{
    static url = "/ResiduosQuimicos/residuo/create/"
    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance
        String residuoLab = "Residuo" //code.label
        String createResiduoTitleList = helper.getMessage("default.create.label", residuoLab)
        title ==~ createResiduoTitleList
    }

    def createResiduo(String nomeResiduo, String pesoResiduo, String nomeLaboratorio){
        $('input#nome').value(nomeResiduo)
        //composição não é relevante para o teste
        $('input#descricao').value("Padrão")
        $('input#peso').value(pesoResiduo)
        $('select#laboratorio').value(Laboratorio.findByNomeLaboratorio(nomeLaboratorio).id)
        $('#create-residuo input#create').click()
    }
}


