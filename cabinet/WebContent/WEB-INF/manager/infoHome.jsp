<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="cabinet.domain.model.*"%>
<%@ page import="java.util.List"%>
<%
	List<Info> dataView = (List<Info>) (request.getAttribute("viewModel"));
	Category category=(Category) (request.getAttribute("categoryModel"));
%>
<jsp:include page="/manager/header"></jsp:include>
<body>
	<jsp:include page="/manager/navigation"></jsp:include>
	<div class="container theme-showcase" role="main">
		<div class="page-header"></div>
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<%=category.getCname() %> <a href="info/edit?cid=<%=category.getCategoryId()%>">添加</a>
			</div>
			<!-- Table -->
			<table class="table table-striped  table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>标题</th>
						<th>类型</th>
						<th>排序</th>
						<th>更新时间</th>
						<th>创建时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (Info i : dataView) {
							String itemId = String.valueOf(i.getArticleId());
					%>
					<tr>
						<th scope="row"><%=String.valueOf(i.getArticleId())%></th>
						<td><%=i.getTitle()%></td>
						<td><%=i.getCategoryId()%></td>
						<td><%=i.getSortOrder() %></td>
						<td><%=i.getDataChangeLastTime()%></td>
						<td><%=i.getDataChangeCreateTime()%></td>
						<td><a href="info/edit?id=<%=itemId%>">修改</a> | <a
							href="###" data-key="<%=itemId%>" class="_del">删除</a></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
		<jsp:include page="/manager/pagination" flush="true">
			<jsp:param name="pidx" value="${pIdx}" />
			<jsp:param name="rowCount" value="0" />
			<jsp:param name="pageSize" value="20" />
		</jsp:include>
	</div>
</body>
<script type="text/javascript">
	$("._del").on("click", function() {
		if (confirm("确定删除？")) {
			$.ajax({
				type : "get",
				url : "info/del?id=" + $(this).data("key"),
				cache : false,
				success : function(data) {
					var obj = $.parseJSON(data)
					if (obj.errorCode == 0) {
						document.location.reload();
					} else {
						alert(obj.errorMessage);
					}
				}
			});
		}
	})
</script>
<jsp:include page="/manager/footer"></jsp:include>