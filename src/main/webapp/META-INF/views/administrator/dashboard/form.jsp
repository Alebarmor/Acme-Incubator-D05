<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<style>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
div.containerL{
display: inline-block;
width: 40%
}
div.containerR{
display: inline-block;
width: 40%}
</style>
<h1>
	<acme:message code="administrator.dashboard.form.title" />
</h1>
<table>
	<tr>
		<td>
			<acme:message code="administrator.dashboard.form.table.cell.number-notices"/>
		</td>
		<td>
			<acme:print value="${ numberOfNotices}"/>
		</td>
	</tr>
	<tr>
		<td>
			<acme:message code="administrator.dashboard.form.table.cell.number-technology-records"/>
		</td>
		<td>
			<acme:print value="${ numberOfTechnologyRecords}"/>
		</td>
	</tr>
	<tr>
		<td>
			<acme:message code="administrator.dashboard.form.table.cell.number-tools"/>
		</td>
		<td>
			<acme:print value="${ numberOfTools}"/>
		</td>
	</tr>
</table>
<br />
<br />
<table>
	<tr>
		<th colspan="2">
			<acme:message code="administrator.dashboard.form.table.title.money-active-inquires"/>
		</th>
	</tr>
	<tr>
		<td>
			<acme:message code="administrator.dashboard.form.table.cell.stats.min"/>
		</td>
		<td>
			<acme:print value="${ minimumMoneyActiveInquires}"/>
		</td>
	</tr>
	<tr>
		<td>
			<acme:message code="administrator.dashboard.form.table.cell.stats.max"/>
		</td>
		<td>
			<acme:print value="${ maximumMoneyActiveInquires}"/>
		</td>
	</tr>
	<tr>
		<td>
			<acme:message code="administrator.dashboard.form.table.cell.stats.avg"/>	
		</td>
		<td>
			<acme:print value="${ averageMoneyActiveInquires}"/>
		</td>
	</tr>
	<tr>
		<td>
			<acme:message code="administrator.dashboard.form.table.cell.stats.stddev"/>
		</td>
		<td>
			<acme:print value="${ stddevMoneyActiveInquires}"/>
		</td>
	</tr>
</table>
<br />
<br />
<table>
	<tr>
		<th colspan="2">
			<acme:message code="administrator.dashboard.form.table.title.money-active-overtures"/>
		</th>
	</tr>
	<tr>
		<td>
			<acme:message code="administrator.dashboard.form.table.cell.stats.min"/>
		</td>
		<td>
			<acme:print value="${ minimumMoneyActiveOvertures}"/>
		</td>
	</tr>
	<tr>
		<td>
			<acme:message code="administrator.dashboard.form.table.cell.stats.max"/>
		</td>
		<td>
			<acme:print value="${ maximumMoneyActiveOvertures}"/>
		</td>
	</tr>
	<tr>
		<td>
			<acme:message code="administrator.dashboard.form.table.cell.stats.avg"/>	
		</td>
		<td>
			<acme:print value="${ averageMoneyActiveOvertures}"/>
		</td>
	</tr>
	<tr>
		<td>
			<acme:message code="administrator.dashboard.form.table.cell.stats.stddev"/>
		</td>
		<td>
			<acme:print value="${ stddevMoneyActiveOvertures}"/>
		</td>
	</tr>
</table>
<br />
<h2></h2>
<div class="containerL">
	<b><acme:message code="administrator.dashboard.form.chart.title.technology-by-sector"/></b>
	<canvas id="chart-area"></canvas>
</div>

<div class="containerL">
	<b><acme:message code="administrator.dashboard.form.chart.title.technology-by-source"/></b>
	<canvas id="chart-area2" ></canvas>
</div>

<div class="containerR">
	<b><acme:message code="administrator.dashboard.form.chart.title.tools-by-sector"/></b>
	<canvas id="chart-area3" ></canvas>
</div>

<div class="containerR">
	<b><acme:message code="administrator.dashboard.form.chart.title.tools-by-source"/></b>
	<canvas id="chart-area4" ></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
				<jstl:forEach var="sector" items="${trSectors}" varStatus="loop">
					"<jstl:out value="${sector}"/>"
					<jstl:if test="${!loop.last}"> ,</jstl:if>
				</jstl:forEach>		
			],
			datasets: [
				{
					data: [
						<jstl:forEach var="companiesNum" items="${technologiesBySector}" varStatus="loop">
							<jstl:out value="${companiesNum}"/>
							<jstl:if test="${!loop.last}"> ,</jstl:if>
							
						</jstl:forEach>
					],
					backgroundColor : [
						<jstl:forEach var="companiesNum" items="${technologiesBySector}" varStatus="loop">
							<jstl:choose>
								<jstl:when test="${loop.count % 3 == 1}">
									'rgba(50,0,200,1)'
								</jstl:when>
								
								<jstl:when test="${loop.count % 3 == 2}">
									'rgba(200, 50, 0, 1)'
								</jstl:when>
								
								<jstl:otherwise>
									'rgba(0, 200, 50, 1)'
								</jstl:otherwise>
							</jstl:choose>
							<jstl:if test="${!loop.last}"> ,</jstl:if>
						</jstl:forEach>
					]
				}
			]
		};
		var options = {
				responsive : true
			};
		var canvas, context;
		
		canvas = document.getElementById("chart-area");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "pie",
			data : data,
			options : options
		});
	});
</script>

<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
				<jstl:forEach var="trSource" items="${trSource}" varStatus="loop">
					"<jstl:out value="${trSource}"/>"
					<jstl:if test="${!loop.last}"> ,</jstl:if>
				</jstl:forEach>		
			],
			datasets: [
				{
					data: [
						<jstl:forEach var="sourcesNum" items="${technologiesBySource}" varStatus="loop">
							<jstl:out value="${sourcesNum}"/>
							<jstl:if test="${!loop.last}"> ,</jstl:if>
							
						</jstl:forEach>
					],
					backgroundColor : [
						<jstl:forEach var="sourcesNum" items="${technologiesBySource}" varStatus="loop">
							<jstl:choose>
								<jstl:when test="${loop.count % 3 == 1}">
									'rgba(50,0,200,1)'
								</jstl:when>
								
								<jstl:when test="${loop.count % 3 == 2}">
									'rgba(200, 50, 0, 1)'
								</jstl:when>
								
								<jstl:otherwise>
									'rgba(0, 200, 50, 1)'
								</jstl:otherwise>
							</jstl:choose>
							<jstl:if test="${!loop.last}"> ,</jstl:if>
						</jstl:forEach>
					]
				}
			]
		};
		var options = {
				responsive : true
			};
		var canvas, context;
		
		canvas = document.getElementById("chart-area2");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "pie",
			data : data,
			options : options
		});
	});
</script>

<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
				<jstl:forEach var="sector" items="${tSectors}" varStatus="loop">
					"<jstl:out value="${sector}"/>"
					<jstl:if test="${!loop.last}"> ,</jstl:if>
				</jstl:forEach>		
			],
			datasets: [
				{
					data: [
						<jstl:forEach var="investorsNum" items="${toolsBySector}" varStatus="loop">
							<jstl:out value="${investorsNum}"/>
							<jstl:if test="${!loop.last}"> ,</jstl:if>
							
						</jstl:forEach>
					],
					backgroundColor : [
						<jstl:forEach var="investorsNum" items="${toolsBySector}" varStatus="loop">
							<jstl:choose>
								<jstl:when test="${loop.count % 3 == 1}">
									'rgba(50,0,200,1)'
								</jstl:when>
								
								<jstl:when test="${loop.count % 3 == 2}">
									'rgba(200, 50, 0, 1)'
								</jstl:when>
								
								<jstl:otherwise>
									'rgba(0, 200, 50, 1)'
								</jstl:otherwise>
							</jstl:choose>
							<jstl:if test="${!loop.last}"> ,</jstl:if>
						</jstl:forEach>
					]
				}
			]
		};
		var options = {
				responsive : true
			};
		var canvas, context;
		
		canvas = document.getElementById("chart-area3");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "pie",
			data : data,
			options : options
		});
	});
</script>

<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
				<jstl:forEach var="tSource" items="${tSource}" varStatus="loop">
					"<jstl:out value="${tSource}"/>"
					<jstl:if test="${!loop.last}"> ,</jstl:if>
				</jstl:forEach>		
			],
			datasets: [
				{
					data: [
						<jstl:forEach var="toolS" items="${toolsBySource}" varStatus="loop">
							<jstl:out value="${toolS}"/>
							<jstl:if test="${!loop.last}"> ,</jstl:if>
							
						</jstl:forEach>
					],
					backgroundColor : [
						<jstl:forEach var="toolS" items="${toolsBySource}" varStatus="loop">
							<jstl:choose>
								<jstl:when test="${loop.count % 3 == 1}">
									'rgba(50,0,200,1)'
								</jstl:when>
								
								<jstl:when test="${loop.count % 3 == 2}">
									'rgba(200, 50, 0, 1)'
								</jstl:when>
								
								<jstl:otherwise>
									'rgba(0, 200, 50, 1)'
								</jstl:otherwise>
							</jstl:choose>
							<jstl:if test="${!loop.last}"> ,</jstl:if>
						</jstl:forEach>
					]
				}
			]
		};
		var options = {
				responsive : true
			};
		var canvas, context;
		
		canvas = document.getElementById("chart-area4");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "pie",
			data : data,
			options : options
		});
	});
</script>