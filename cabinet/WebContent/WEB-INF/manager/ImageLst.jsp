<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="cabinet.domain.model.*"%>
<%@ page import="java.util.List"%>
<%
	List<ImageRelation> dataView = (List<ImageRelation>) (request.getAttribute("list"));
%>
<jsp:include page="/manager/header"></jsp:include>
<body style="width: 98%; margin: 0px; padding: 0px;">
<div class="panel panel-warning">
<div class="panel-heading">
	<form class="form-inline"  action="saveRalation?type=0&relationId=12"  onsubmit="return false;" >
		<div class="form-group">
			<input type="text" name="inputImageURL" style="width:500px;" id="inputImageURL"
				class="form-control" placeholder="图片地址" required autofocus>
		</div>
		<div class="form-group">
			<input type="text" placeholder="排序" name="inputSortOrder"
				maxlength="4" value="2" class="form-control" pattern="[0-9]{0,10}" style="width:80px;"
				autofocus>
		</div>
		<button type="button" class="btn btn-sm btn-primary"  id="btnSave" data-loading-text="处理...">添加</button>
		<button type="submit" id="btnSubmit" style="display: none"></button>
	</form>
	</div>
	<table class="table table-condensed table-hover">
				<thead>
					<tr>
						<th>图片</th>
						<th>排序</th>
						<th>创建时间</th>
						<th>更新时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<%for(ImageRelation i:dataView){
				String itemId = String.valueOf(i.getImageRelationId());
				%>
				<tr>
						<th scope="row"><a href="<%=i.getImageUrl() %>" target="_blank">
						<img src="<%=i.getImageUrl()%>" width="80"></a></th>
						<td><%=i.getSortOrder() %></td>
						<td><%=i.getDataChangeCreateTime() %></td>
						<td><%=i.getDataChangeLastTime() %></td>
						<td><a href="#" class="_modifed">修改</a> | <a
							href="###" data-key="<%=itemId%>" class="_del">删除</a></td>
				</tr>
				<%} %>
				<tbody>
					
				</tbody>
			</table>
			</div>
</body>
<script type="text/javascript">
	$("._del").on("click", function() {
		if (confirm("确定删除？")) {
			$.ajax({
				type : "get",
				url : "image/deleteRalation?id=" + $(this).data("key"),
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
	$("#btnSave").on("click",function(){
		$("#btnSubmit").trigger("click");
		var form = $(".form-inline");
		if ($("form")[0].checkValidity()) {
			var btn = $(this).button("loading");
			$.ajax({
				type : "post",
				url : form.attr("action"),
				data : form.serialize(),
				cache : false,
				success : function(data) {
					var obj = $.parseJSON(data)
					if (obj.errorCode == 0) {
						alert("操作成功");
						document.location.reload();
					} else {
						alert(obj.message);
						btn.button("reset");
					}
				}
			});
		}
	});
</script>
<jsp:include page="/manager/footer"></jsp:include>