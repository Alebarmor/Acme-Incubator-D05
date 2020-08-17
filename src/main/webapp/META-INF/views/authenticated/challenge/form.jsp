<%--
- form.jsp
-
- Copyright (c) 2019 Aureliano Piqueras, based on Rafael Corchuelo's DP Starter project.
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

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.challenge.form.label.title" path="title" />
	<acme:form-moment code="authenticated.challenge.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.challenge.form.label.description" path="description" />
	
	<acme:form-panel code="authenticated.challenge.form.label.expert">
	<acme:form-money code="authenticated.challenge.form.label.reward" path="expertReward" />
	<acme:form-textbox code="authenticated.challenge.form.label.goal" path="expertGoal" />
	</acme:form-panel>
	
	<acme:form-panel code="authenticated.challenge.form.label.average">
	<acme:form-money code="authenticated.challenge.form.label.reward" path="averageReward" />
	<acme:form-textbox code="authenticated.challenge.form.label.goal" path="averageGoal" />
	</acme:form-panel>
	
	<acme:form-panel code="authenticated.challenge.form.label.rookie">
	<acme:form-money code="authenticated.challenge.form.label.reward" path="rookieReward" />
	<acme:form-textbox code="authenticated.challenge.form.label.goal" path="rookieGoal" />
	
	</acme:form-panel>
	
  	<acme:form-return code="authenticated.challenge.form.button.return"/>
</acme:form>
