
<%--
- list.jsp

--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
		<acme:list-column code="administrator.tool.list.label.title" path="title" width="40%" />
	<acme:list-column code="administrator.tool.list.label.sector" path="sector" width="40%" />
	<acme:list-column code="administrator.tool.list.label.starsNumber" path="starsNumber" width="20%" />
</acme:list>