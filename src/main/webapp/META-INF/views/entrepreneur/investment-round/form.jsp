<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<acme:form-textbox code="entrepreneur.investmentRound.form.label.title" path="title" />
	<jstl:if test="${command =='create'}">
    	<acme:form-textbox code="entrepreneur.investmentRound.form.label.ticker" path="ticker" />
    </jstl:if>
    <jstl:if test="${command !='create'}">
    	<acme:form-textbox code="entrepreneur.investmentRound.form.label.ticker" path="ticker" readonly="true"/>
    	<acme:form-moment code="entrepreneur.investmentRound.form.label.creationMoment" path="creationMoment" readonly="true"/>
    </jstl:if>
    <acme:form-textbox code="entrepreneur.investmentRound.form.label.roundKind" path="roundKind" />
    <acme:form-textarea code="entrepreneur.investmentRound.form.label.description" path="description" />
    <acme:form-money code="entrepreneur.investmentRound.form.label.amount" path="amount" />
    <acme:form-url code="entrepreneur.investmentRound.form.label.additionalInformation" path="additionalInformation" />
    
    <jstl:if test="${finalMode}">
		<acme:form-checkbox code="entrepreneur.investmentRound.form.label.finalMode" path="finalMode" readonly="true"/>
	</jstl:if>
	<jstl:if test="${!finalMode}">
		<acme:form-checkbox code="entrepreneur.investmentRound.form.label.finalMode" path="finalMode"/>
	</jstl:if>
	
    <p></p>
    
    <jstl:if test="${command == 'show'}" >
    	<a href=/acme-incubator/entrepreneur/activity/list?id=${id}><acme:message code="entrepreneur.investmentRound.activity.list" /></a>
    	<p></p>
    	<jstl:if test="${!finalMode}">
    		<acme:form-return code="entrepreneur.activity.form.button.create-activity"
				action="/entrepreneur/activity/create?investmentId=${id}" />
		</jstl:if>
	</jstl:if>
   
	<p></p>
	
	<acme:form-submit test="${command == 'show' && !finalMode}" 
		code="entrepreneur.investmentRound.form.button.update"
		action="/entrepreneur/investment/update" />
	<acme:form-submit test="${command == 'show'}" 
		code="entrepreneur.investmentRound.form.button.delete"
		action="/entrepreneur/investment/delete" />
	<acme:form-submit test="${command == 'create'}" 
		code="entrepreneur.investmentRound.form.button.create"
		action="/entrepreneur/investment/create" />
	<acme:form-submit test="${command == 'update' && !finalMode}" 
		code="entrepreneur.investmentRound.form.button.update"
		action="/entrepreneur/investment/update" />
	<acme:form-submit test="${command == 'delete'}" 
		code="entrepreneur.investmentRound.form.button.delete"
		action="/entrepreneur/investment/delete" />
    
	<acme:form-return code="entrepreneur.investmentRound.form.button.return" />
</acme:form> 