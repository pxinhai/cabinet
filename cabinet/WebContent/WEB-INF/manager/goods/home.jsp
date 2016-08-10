<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="cabinet.domain.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="cabinet.web.util.*" %>
<%
	List<Goods> dataView = (List<Goods>) (request.getAttribute("viewModel"));
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
				<%=category.getCname() %> <a href="goods/edit?cid=<%=category.getCategoryId()%>">添加</a>
			</div>
			<!-- Table -->
			<table class="table table-striped  table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>名称</th>
						<th>排序</th>
						<th>更新时间</th>
						<th>创建时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (Goods i : dataView) {
							String itemId = String.valueOf(i.getGoodsId());
					%>
					<tr>
						<th scope="row"><%=itemId%></th>
						<td><%=i.getProName() %></td>
						<td><%=i.getSortOrder() %></td>
						<td><%=i.getDataChangeLastTime()%></td>
						<td><%=i.getDataChangeCreateTime()%></td>
						<td><a href="goods/edit?id=<%=itemId%>">修改</a> | <a
							href="###" data-key="<%=itemId%>" class="_del">删除</a>
							<a href="###" data-key="<%=itemId%>" data-toggle="modal" data-target=".bs-example-modal-lg">图片</a>
							</td>
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
	<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
		aria-labelledby="gridSystemModalLabel">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">产品图片</h4>
				</div>
				<div class="modal-body">
				
					<iframe  style="border:none;overflow-x:hidden;height:500px; width:100%;" 
					src="<%=httpUtil.getContextPath(request)%>/manager/image/relationlist?type=0&relationId=12" >
					</iframe>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$("._del").on("click", function() {
		if (confirm("确定删除？")) {
			$.ajax({
				type : "get",
				url : "goods/del?id=" + $(this).data("key"),
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