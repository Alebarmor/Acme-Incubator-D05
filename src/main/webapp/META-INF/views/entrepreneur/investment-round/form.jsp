
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<acme:form>
	<jstl:if test="${finalMode == false && command == 'create'}">
		
    <acme:form-textbox code="entrepreneur.investmentRound.form.label.title" path="title" />
    <acme:form-textbox code="entrepreneur.investmentRound.form.label.ticker" path="ticker" />
    <acme:form-moment  code="entrepreneur.investmentRound.form.label.creationMoment" path="creationMoment" />
    <acme:form-textbox code="entrepreneur.investmentRound.form.label.roundKind" path="roundKind" />
    <acme:form-textarea code="entrepreneur.investmentRound.form.label.description" path="description" />
    <acme:form-money code="entrepreneur.investmentRound.form.label.amount" path="amount" />
    <acme:form-textbox code="entrepreneur.investmentRound.form.label.additionalInformation" path="additionalInformation" />
    <acme:form-hidden path="finalMode"/>
	</jstl:if>
	
	<jstl:if test="${finalMode == false && command != 'create'}">
		
    <acme:form-textbox code="entrepreneur.investmentRound.form.label.title" path="title" />
    <acme:form-textbox code="entrepreneur.investmentRound.form.label.ticker" path="ticker" placeholder="SSS-YY-NNNNNN"/>
    <acme:form-moment  code="entrepreneur.investmentRound.form.label.creationMoment" path="creationMoment" />
    <acme:form-textbox code="entrepreneur.investmentRound.form.label.roundKind" path="roundKind" />
    <acme:form-textarea code="entrepreneur.investmentRound.form.label.description" path="description" />
    <acme:form-money code="entrepreneur.investmentRound.form.label.amount" path="amount" />
    <acme:form-textbox code="entrepreneur.investmentRound.form.label.additionalInformation" path="additionalInformation" />
    <acme:form-checkbox code="entrepreneur.investmentRound.form.label.finalMode" path="finalMode"/>
	</jstl:if>

	<jstl:if test="${finalMode == true }">
		<acme:form-textbox code="entrepreneur.investmentRound.form.label.ticker" path="ticker" placeholder="SSS-YY-NNNNNN" readonly="true"/>
		<acme:form-textbox code="entrepreneur.investmentRound.form.label.title" path="title" /> readonly="true"/>
		 <acme:form-moment  code="entrepreneur.investmentRound.form.label.creationMoment" path="creationMoment" readonly="true"/>
		 <acme:form-textbox code="entrepreneur.investmentRound.form.label.roundKind" path="roundKind" readonly="true"/>
		<acme:form-textarea code="entrepreneur.investmentRound.form.label.description" path="description" readonly="true"/>
		<acme:form-money code="entrepreneur.investmentRound.form.label.amount" path="amount" readonly="true"/>
		<acme:form-checkbox code="entrepreneur.investmentRound.form.label.finalMode" path="finalMode" readonly="true"/>
	</jstl:if>
	

//--------------------------------  FALTA  ---------------------------------------------------------------------------------



	<acme:form-hidden path="id"/>

	<%-- //<acme:form-submit test="${command != 'create' && !listActivities}" code="entrepreneur.investmentRound.activity.list"" action="/entrepreneur/activity/list?id=${id}" method="get"/>
	//<acme:form-submit test="${command != 'create' }" code="employer.job.form.label.duties.create" action="/employer/duty/create?id=${id}" method="get"/>
	//<acme:form-submit test="${command != 'create' && !listAuditEmpty}" code="employer.job.form.label.audit" action="/authenticated/audit/list?id=${id}" method="get"/>
	 --%>
	
	
	<acme:form-submit test="${command == 'create' }" code="entrepreneur.investmentRound.form.button.create" action="/entrepreneur/investment/create"/>
	
	
	<acme:form-return code="entrepreneur.investmentRound.form.button.return"/>
</acme:form>
