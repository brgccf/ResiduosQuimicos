package pages

import geb.Page
import residuosquimicos.InternationalizationHelper

class IndexLaboratorioPage extends Page {
    static url = "/ResiduosQuimicos/laboratorio/index/"
    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance
        String labelLab = "Laboratorio" //code.label
        String showLabTitleList = helper.getMessage("default.list.label", labelLab)
        title ==~ showLabTitleList
    }

    boolean naoPossuiLaboratorio(){
        ($('#list-laboratorio tbody').children().size() == 0)
    }
}