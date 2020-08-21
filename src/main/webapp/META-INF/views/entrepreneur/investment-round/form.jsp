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
    	<acme:form-textbox code="entrepreneur.investmentRound.form.label.ticker" path="ticker" />
    	<acme:form-moment  code="entrepreneur.investmentRound.form.label.creationMoment" path="creationMoment" />
    	<acme:form-textbox code="entrepreneur.investmentRound.form.label.roundKind" path="roundKind" />
    	<acme:form-textarea code="entrepreneur.investmentRound.form.label.description" path="description" />
    	<acme:form-money code="entrepreneur.investmentRound.form.label.amount" path="amount" />
    	<acme:form-textbox code="entrepreneur.investmentRound.form.label.additionalInformation" path="additionalInformation" />
		<acme:form-checkbox code="entrepreneur.investmentRound.form.label.finalMode" path="finalMode"/>
	</jstl:if>
	
	<jstl:if test="${finalMode == true }">
		<acme:form-textbox code="entrepreneur.investmentRound.form.label.title" path="title" readonly="true" />
    	<acme:form-textbox code="entrepreneur.investmentRound.form.label.ticker" path="ticker" readonly="true" />
    	<acme:form-moment  code="entrepreneur.investmentRound.form.label.creationMoment" path="creationMoment" readonly="true" />
    	<acme:form-textbox code="entrepreneur.investmentRound.form.label.roundKind" path="roundKind" readonly="true" />
    	<acme:form-textarea code="entrepreneur.investmentRound.form.label.description" path="description" readonly="true" />
    	<acme:form-money code="entrepreneur.investmentRound.form.label.amount" path="amount" readonly="true" />
    	<acme:form-textbox code="entrepreneur.investmentRound.form.label.additionalInformation" path="additionalInformation" readonly="true" />
		<acme:form-checkbox code="entrepreneur.investmentRound.form.label.finalMode" path="finalMode" readonly="true"/>
	</jstl:if>
	
	<acme:form-hidden path="id"/>
	
	<acme:form-submit test="${command == 'show' && finalMode == false}" code="entrepreneur.investmentRound.form.button.update" action="/entrepreneur/investment/update"/>
	<acme:form-submit test="${command == 'show' && listAppEmpty}" code="entrepreneur.investmentRound.formbutton.delete" action="/entrepreneur/investment/delete"/>
	
	<acme:form-submit test="${command == 'create' }" code="entrepreneur.investmentRound.form.button.create" action="/entrepreneur/investment/create"/>
	<acme:form-submit test="${command == 'update' }" code="entrepreneur.investmentRound.form.button.update" action="/entrepreneur/investment/update"/>
	<acme:form-submit test="${command == 'delete' }" code="entrepreneur.investmentRound.form.button.delete" action="/entrepreneur/investment/delete"/>

    
    <a href=/acme-incubator/entrepreneur/activity/list?id=${id}>
    	<acme:message code="entrepreneur.investmentRound.activity.list"/>
    </a>

	 <br>
	<acme:form-return code="entrepreneur.investmentRound.form.button.return" />
</acme:form> 