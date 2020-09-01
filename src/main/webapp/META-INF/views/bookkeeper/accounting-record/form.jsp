
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">

    <acme:form-textbox code="bookkeeper.accounting.form.label.title" path="title" />
    <acme:form-textbox code="bookkeeper.accounting.form.label.status" path="status" />
    <acme:form-moment  code="bookkeeper.accounting.form.label.creationMoment" path="creationMoment" />
    <acme:form-textarea code="bookkeeper.accounting.form.label.body" path="body" />
    
	<acme:form-return code="bookkeeper.accounting.form.button.return" />
</acme:form>