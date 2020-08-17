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

<acme:form >
	<acme:form-url code="administrator.notices.form.label.headerPicture" path="headerPicture"/>
	<acme:form-textbox code="administrator.notices.form.label.title" path="title"/>
	<acme:form-moment code="administrator.notices.form.label.deadline" path="deadline"/>
	<acme:form-url code="administrator.notices.form.label.relatedNotices" path="relatedNotices"/>
	<acme:form-textarea code="administrator.notices.form.label.body" path="body"/>
	
	<jstl:if test="${command=='create' }">
	<acme:form-checkbox  code="administrator.notices.form.label.accept" path="accept"/>
	</jstl:if>
	
	<acme:form-submit test="${command == 'create'}" code="administrator.notice.form.button.create" 
						action="/administrator/notice/create"/>
			
	<acme:form-return code="administrator.notices.form.button.return"/>
</acme:form>
