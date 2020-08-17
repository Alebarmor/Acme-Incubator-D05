<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:list>
	<acme:list-column code="administrator.inquires.list.label.title" path="title" width="25%"/>
	<acme:list-column code="administrator.inquires.list.label.deadline" path="deadline" width="25%"/>
	<acme:list-column code="administrator.inquires.list.label.minMoney" path="minMoney" width="25%"/>
	<acme:list-column code="administrator.inquires.list.label.maxMoney" path="maxMoney" width="25%"/>

</acme:list>