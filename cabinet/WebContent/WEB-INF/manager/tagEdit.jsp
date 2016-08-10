<%@page import="javax.management.Query"%>
<%@page import="cabinet.domain.model.Tag"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
Tag viewModel=(Tag)(request.getAttribute("Model")); 
Tag pModel=(Tag)(request.getAttribute("pModel")); 
%>
<jsp:include page="/manager/header"></jsp:include>
<body>
<jsp:include page="/manager/navigation"></jsp:include>
<div class="container theme-showcase" role="main">
	<div class="page-header"></div>
	<div class="panel panel-info">
			<div class="panel-heading"><%=pModel.getTagId()>0?pModel.getCname():(viewModel.getTagId()>0?"修改":"添加") %></div>
			<div class="panel-body">
			<form class="form-horizontal" onsubmit="return false;" 
			action="edit?id=<%=viewModel.getTagId()%>&pid=<%=pModel.getTagId() %>">
				<div class="form-group">
					<label class="col-sm-1 control-label" >名称</label>
					<div class="col-sm-5">
						<input type="text" name="inputCName" class="form-control"
							placeholder="请输入名称" required autofocus value="<%=viewModel.getCname() %>" >
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-1 control-label" >eName</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" name="inputEName" value="<%=viewModel.getEname() %>">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-1 control-label" >排序</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" required autofocus  pattern="[0-9]{0,10}" name="inputSortOrder" value="<%=viewModel.getSortOrder() %>">
					</div>
				</div>
				<div class="form-group">
				<div class="col-sm-offset-1 col-sm-10">
					<button type="button" id="btnEdit" data-loading-text="处理..."
						class="btn btn-sm btn-primary"><%=viewModel!=null&&viewModel.getTagId()>0?"修改":"添加" %></button>
						
					<button type="reset" class="btn btn-sm">重置</button>
					<button type="submit"  id="btnSubmit" style="display:none"></button>
					</div>
				</div>
			</form>
		</div>
			
				
		</div>
			</div>

</body>
<script type="text/javascript">
	$("#btnEdit").on("click", function() {
		$("#btnSubmit").trigger("click");
		var form=$(".form-horizontal");
		if($("form")[0].checkValidity())
		{
			var btn = $(this).button("loading");
			$.ajax({
				type:"post",
				url:form.attr("action"),
				data:form.serialize(),
				cache:false,
				success:function(data){
					var obj=$.parseJSON(data)
					if(obj.errorCode==0){
						alert("操作成功");
						<%if(viewModel.getTagId()>0){%>
						window.history.back();
						<%}else{%>
						document.location.reload();
						<%}%>
					}else{
						alert(obj.message);
						btn.button("reset");
					}
				}
			});
		}
	})
</script>
<jsp:include page="/manager/footer"></jsp:include>