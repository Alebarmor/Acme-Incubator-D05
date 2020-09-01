
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">

    <acme:form-textbox code="bookkeeper.investmentRound.form.label.title" path="title" />
    <acme:form-textbox code="bookkeeper.investmentRound.form.label.ticker" path="ticker" />
    <acme:form-moment  code="bookkeeper.investmentRound.form.label.creationMoment" path="creationMoment" />
    <acme:form-textbox code="bookkeeper.investmentRound.form.label.roundKind" path="roundKind" />
    <acme:form-textarea code="bookkeeper.investmentRound.form.label.description" path="description" />
    <acme:form-money code="bookkeeper.investmentRound.form.label.amount" path="amount" />
    <acme:form-textbox code="bookkeeper.investmentRound.form.label.additionalInformation" path="additionalInformation" />
    
    <a href=/acme-incubator/bookkeeper/activity/list?id=${id}>
    	<acme:message code="bookkeeper.investmentRound.activity.list"/>
    </a>
    
    <br>
    <a href=/acme-incubator/bookkeeper/accounting-record/list?id=${id}>
    	<acme:message code="bookkeeper.investmentRound.accounting.list"/>
    </a>

	 <br>
	<acme:form-return code="bookkeeper.investmentRound.form.button.return" />
</acme:form> 