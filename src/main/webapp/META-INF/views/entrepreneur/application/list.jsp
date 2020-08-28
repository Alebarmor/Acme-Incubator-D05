<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:list>
<acme:list-column code="entrepreneur.application.list.label.ticker" path="ticker" width="50%" />
	<acme:list-column code="entrepreneur.application.list.label.creationMoment" path="creationMoment" width="50%"/>
</acme:list>