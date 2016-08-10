<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="cabinet.web.util.*" %>
<%@ page import="cabinet.infrastructure.*;" %>
<%
	int rowCount = CharacterUtility.ConverterToInt(request.getParameter("rowCount"));
	int pageIndex = CharacterUtility.ConverterToInt(request.getParameter("pidx"));
	int pageSize = CharacterUtility.ConverterToInt(request.getParameter("pageSize"));

	if(pageIndex==0)
	{
		pageIndex=1;
	}
	int pageCount = (int) (Math.ceil((float) rowCount / (float) pageSize));
	String queryString=request.getQueryString();
	if (pageCount > pageSize) {
%>
<nav>
	<ul class="pagination">
		<li <%=pageIndex == 1 ? "class=\"disabled\"" : ""%>><a href="?<%=httpUtil.CombinedParam(queryString,"pidx","1") %>"
			aria-label="Previous"><span aria-hidden="true">«</span></a></li>
		<%
			for (int i = 1; i <= pageCount; i++) {
		%>
		<li <%=pageIndex == i ? "class=\"active\"" : ""%>><a href="?<%=httpUtil.CombinedParam(queryString,"pidx",String.valueOf(i)) %>"><%=i%></a></li>
		<%
			}
		%>
		<li <%=pageIndex == pageCount ? "class=\"disabled\"" : ""%>><a
			href="?<%=httpUtil.CombinedParam(queryString,"pidx",String.valueOf(pageCount)) %>" aria-label="Next"><span aria-hidden="true">»</span></a></li>
	</ul>
</nav>
<%
	}
%>