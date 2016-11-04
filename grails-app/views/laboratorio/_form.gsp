<%@ page import="residuosquimicos.Laboratorio" %>



<div class="fieldcontain ${hasErrors(bean: laboratorioInstance, field: 'nomeDepartamento', 'error')} required">
	<label for="nomeDepartamento">
		<g:message code="laboratorio.nomeDepartamento.label" default="Nome Departamento" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="nomeDepartamento" from="${residuosquimicos.DepartamentoList?.values()}" keys="${residuosquimicos.DepartamentoList.values()*.name()}" required="" value="${laboratorioInstance?.nomeDepartamento?.name()}" />

</div>

<div class="fieldcontain ${hasErrors(bean: laboratorioInstance, field: 'nomeLaboratorio', 'error')} required">
	<label for="nomeLaboratorio">
		<g:message code="laboratorio.nomeLaboratorio.label" default="Nome Laboratorio" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nomeLaboratorio" required="" value="${laboratorioInstance?.nomeLaboratorio}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: laboratorioInstance, field: 'residuos', 'error')} ">
	<label for="residuos">
		<g:message code="laboratorio.residuos.label" default="Residuos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${laboratorioInstance?.residuos?}" var="r">
    <li><g:link controller="residuo" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="residuo" action="create" params="['laboratorio.id': laboratorioInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'residuo.label', default: 'Residuo')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: laboratorioInstance, field: 'responsavel', 'error')} required">
	<label for="responsavel">
		<g:message code="laboratorio.responsavel.label" default="Responsavel" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="responsavel" name="responsavel.id" from="${residuosquimicos.Usuario.list()}" optionKey="id" required="" value="${laboratorioInstance?.responsavel?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: laboratorioInstance, field: 'solicitante', 'error')} required">
	<label for="solicitante">
		<g:message code="laboratorio.solicitante.label" default="Solicitante" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="solicitante" name="solicitante.id" from="${residuosquimicos.Usuario.list()}" optionKey="id" required="" value="${laboratorioInstance?.solicitante?.id}" class="many-to-one"/>

</div>

