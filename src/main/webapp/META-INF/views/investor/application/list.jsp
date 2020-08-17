<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:list>
	<acme:list-column code="investor.application.list.label.offer" path="offer" width="60%"/>
	<acme:list-column code="investor.application.list.label.creationMoment" path="creationMoment" width="40%"/>
</acme:list>