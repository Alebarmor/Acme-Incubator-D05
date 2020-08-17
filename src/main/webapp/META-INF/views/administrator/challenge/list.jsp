
<%--
- list.jsp

--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.challenge.list.label.title" path="title" width="33%"/>
	<acme:list-column code="administrator.challenge.list.label.deadline" path="deadline" width="33%"/>
	<acme:list-column code="administrator.challenge.list.label.expertReward" path="expertReward" width="11%"/>
	<acme:list-column code="administrator.challenge.list.label.averageReward" path="averageReward" width="11%"/>
	<acme:list-column code="administrator.challenge.list.label.rookieReward" path="rookieReward" width="11%"/>
</acme:list>


