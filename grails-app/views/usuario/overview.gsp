//#if SolicitacaoDeAcesso
<%@ page import="residuosquimicos.Usuario" %>
<%@ page import="residuosquimicos.Laboratorio" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'list.label', default: 'List')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
	<a href="#list-laboratorio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-laboratorio" class="content scaffold-list" role="main">
			<h1>Overview</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
				<tr>
					<g:sortableColumn property="nomeCentro" title="${message(code: 'laboratorio.nomeCentro.label', default: 'Nome Centro')}" />

					<g:sortableColumn property="nomeDepartamento" title="${message(code: 'laboratorio.nomeDepartamento.label', default: 'Nome Departamento')}" />

					<g:sortableColumn property="nomeLaboratorio" title="${message(code: 'laboratorio.nomeLaboratorio.label', default: 'Nome Laboratorio')}" />

				</tr>
				</thead>
				<tbody>
				<g:each in="${Laboratorio.list()}" status="i" var="laboratorioInstance">

					<td>${fieldValue(bean: laboratorioInstance, field: "nomeCentro")}</td>

					<td>${fieldValue(bean: laboratorioInstance, field: "nomeDepartamento")}</td>

					<td class="referencia"><g:link action="solicitarLab" id="${laboratorioInstance.id}">${fieldValue(bean: laboratorioInstance, field: "nomeLaboratorio")}</g:link></td>


				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${laboratorioInstanceCount ?: 0}" />
			</div>

		</div>
	</body>
</html>
//#end