package pages

import cucumber.api.PendingException
import geb.Page
import residuosquimicos.UsuarioList
import steps.InternationalizationHelper
import residuosquimicos.Usuario
class CreateLaboratorioPage extends Page {
    static url = "/ResiduosQuimicos/laboratorio/create"
    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance
        String labelLab = "Laboratorio" //code.label
        String createLabTitleList = helper.getMessage("default.create.label", labelLab)
        title ==~ createLabTitleList
    }

    def createLab( String nomeLab, String nomeDep, String nomeCentro){
        $('select#nomeCentro').value(nomeCentro)
        $('select#nomeDepartamento').value(nomeDep)
        $('select#nomeLaboratorio').value(nomeLab)
        $('#create-laboratorio input#create').click()
    }

    def createLabAssociado(String nomeLab, String nomeDep, String nomeCentro, String nomeUsuario)
    {
        $('select#nomeCentro').value(nomeCentro)
        $('select#nomeDepartamento').value(nomeDep)
        $('select#nomeLaboratorio').value(nomeLab)
        $('select#responsavel').value(Usuario.findByNome(nomeUsuario).id)
        $('#create-laboratorio input#create').click()
    }

    boolean existeMensagemDeErroUsuarioAdministrador(String admin)
    {
        $("div", class: "message", role: "status").has("ERRO: não é possível associar laboratório a usuários administradores.\""
                + "Tentativa com usuário administrador: \"" + admin)
    }

}