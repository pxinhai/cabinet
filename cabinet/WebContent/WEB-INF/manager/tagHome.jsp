<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import="cabinet.domain.model.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<%
List<Tag> viewModel=(List<Tag>)(request.getAttribute("tagLst"));
Tag pModel=(Tag)(request.getAttribute("pModel"));
%>
<jsp:include page="/manager/header"></jsp:include>
<body>
	<jsp:include page="/manager/navigation"></jsp:include>
	<div class="container theme-showcase" role="main">
<div class="page-header"></div>
<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">
				<%if(pModel.getTagId()>0){%>
				<%=pModel.getCname()%> <a href="tag/edit?pid=<%=pModel.getTagId() %>">添加</a>
				<%}else{%>
				<a href="tag/edit">添加</a>
				<%} %>
				</div>
				<!-- Table -->
				<table class="table table-striped  table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>名称</th>
							<th>eName</th>
							<th>排序</th>
							<th>添加时间</th>
							<th>更新时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					<%for(Tag i : viewModel){
						String itemId=String.valueOf(i.getTagId());
						%>
						<tr>
							<th scope="row"><%=itemId %></th>
							<td><%=i.getCname()%></td>
							<td><%=i.getEname()%></td>
							<td><%=String.valueOf(i.getSortOrder())%></td>
							<td><%=i.getDataChangeCreateTime() %> </td>
							<td><%=i.getDataChangeLastTime() %> </td>
							<td>
							<a href="?pid=<%=itemId%>">子集</a>
							 | <a href="tag/edit?id=<%=itemId %>">修改</a>
							 | <a href="###" data-key="<%=itemId %>" class="_del">删除</a></td>
						</tr>
						<%} %>
					</tbody>
				</table>
			</div>
			<jsp:include page="/manager/pagination" flush="true" >
			<jsp:param name="pidx" value="${pIdx}"/>
			<jsp:param name="rowCount" value="<%=viewModel.size() %>"/>
			<jsp:param name="pageSize" value="20"/>
			</jsp:include>
		
	</div>
</body>
<script type="text/javascript">
	$("._del").on("click", function() {
		if(confirm("确定删除？"))
		{
			$.ajax({
				type:"get",
				url:"tag/del?id="+$(this).data("key"),
				cache:false,
				success:function(data){
					var obj=$.parseJSON(data)
					if(obj.errorCode==0){
						document.location.reload();
					}else{
						alert(obj.errorMessage);
					}
				}
			});
		}
	})
</script>
<jsp:include page="/manager/footer" ></jsp:include>