<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
    <acme:form-textbox code="administrator.tool.form.label.title" path="title" />    
	<acme:form-textbox code="administrator.tool.form.label.inventorName" path="inventorName" />
	<acme:form-textbox code="administrator.tool.form.label.sector" path="sector" />
	<acme:form-textarea code="administrator.tool.form.label.description" path="description" />
	<acme:form-textbox code="administrator.tool.form.label.website" path="website" />
	<acme:form-textbox code="administrator.tool.form.label.email" path="email" />
	<acme:form-checkbox code="administrator.tool.form.label.isOpenSource" path="isOpenSource" />
	<acme:form-textbox code="administrator.tool.form.label.starsNumber" path="starsNumber" />
	
	
	<acme:form-submit test="${command == 'show'}"
	code="administrator.tool.form.button.update"
	action="/administrator/tool/update"/>
	<acme:form-submit test="${command == 'show'}"
	code="administrator.tool.form.button.delete"
	action="/administrator/tool/delete"/>
	<acme:form-submit test="${command == 'create'}"
	code="administrator.tool.form.button.create"
	action="/administrator/tool/create"/>
	<acme:form-submit test="${command == 'update'}"
	code="administrator.tool.form.button.update"
	action="/administrator/tool/update"/>
	<acme:form-submit test="${command == 'delete'}"
	code="administrator.tool.form.button.delete"
	action="/administrator/tool/delete"/>
	<acme:form-return
	code="administrator.tool.form.button.return"/>
</acme:form>