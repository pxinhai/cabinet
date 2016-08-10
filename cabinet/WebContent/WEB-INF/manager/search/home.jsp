<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="cabinet.domain.model.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>
<%
	List<Info> viewModel = (List<Info>) (request.getAttribute("dataList"));
%>
<jsp:include page="/manager/header"></jsp:include>
<body>
	<jsp:include page="/manager/navigation"></jsp:include>
	<div class="container theme-showcase" role="main">
		<div class="page-header"></div>
		<div style="text-align: center; margin: 20px;">
			<form method="get">
				<input type="text" name="key"> <input type="submit"
					value="搜索">
			</form>
		</div>
		<div class="panel panel-default">
		<%
			if (viewModel != null) {
				for (Info i : viewModel) {
		%>
		
			<div style="margin: 10px;">
				<div><a href="info/edit?id=<%=i.getArticleId() %>"><%=i.getTitle() %></a></div>
				<div><%=i.getContent()%></div>
			</div>
			<%
				}
				}
			%>


		</div>
	</div>
</body>
<jsp:include page="/manager/footer"></jsp:include>