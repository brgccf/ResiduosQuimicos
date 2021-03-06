<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title><g:message code="default.title.page.estatisticas" /></title>
</head>
<body>
<div id="estatistica-content" class="content scaffold-list" role="main">
    <div id="percentual-content">
        <label for="percent-message">Distribuição dos Residuos:</label>
        <textField id ="percent-message"> Até o presente momento ${percentualResiduo}% dos laboratórios cadastrados no sistema tem resíduos disponíveis para coleta</textField>
    </div>

    <div id="maior-gerador-content">
    <label for="maiorGerador-message">Maior Gerador de Residuos da UFPE: </label>
        <g:if test="${maiorGeradorDeResiduos !=null}">
            <textField id ="maiorGerador-message">O laboratório que mais gerou resíduos é o ${maiorGeradorDeResiduos.nomeLaboratorio}, que é do departamento ${maiorGeradorDeResiduos.nomeDepartamento} e do Centro ${maiorGeradorDeResiduos.nomeCentro}</textField>
        </g:if>
        <g:else>
            <textField id ="maiorGerador-message">O sistema ainda não possui Laboratórios cadastrados</textField>
        </g:else>
    </div>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
</div>
</body>
</html>