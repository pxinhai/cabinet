<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import="cabinet.domain.model.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<%
List<CategoryProperty> model=(List<CategoryProperty>)(request.getAttribute("model"));
%>
<jsp:include page="/manager/header"></jsp:include>
<body>
	<jsp:include page="/manager/navigation"></jsp:include>
	<div class="container theme-showcase" role="main">
<div class="page-header"></div>
<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">
				属性列表
				</div>
				<!-- Table -->
				<table class="table table-striped  table-hover">
					<thead>
						<tr>
							<th>类别</th>
							<th>属性名称</th>
							<th>值类型</th>
							<th>单位</th>
							<th>排序</th>
							<th>添加时间</th>
							<th>更新时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					<%for(CategoryProperty i : model){ %>
						<tr>
							<th scope="row"><%=i.getCategoryId() %></th>
							
							<td><%=i.getPropertyName() %></td>
							<td><%=i.getValueTypeEnum().getText() %></td>
							<td><%=i.getUnit() %></td>
							<td><%=i.getSortOrder() %></td>
							<td><%=i.getDataChangeCreateTime() %></td>
							<td><%=i.getDataChangeLastTime() %></td>					
							<td></td>
						</tr>
					<%} %>
					</tbody>
				</table>
			</div>
			<jsp:include page="/manager/pagination" flush="true" >
			<jsp:param name="pidx" value="${pIdx}"/>
			<jsp:param name="rowCount" value=""/>
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