<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="cabinet.domain.model.Tag"%>
<%@page import="cabinet.web.util.*" %>
<jsp:include page="/manager/header"></jsp:include>
<body>
	<jsp:include page="/manager/navigation"></jsp:include>
	<div class="container theme-showcase" role="main">
		<div class="page-header"></div>
		<div class="panel panel-info">
			<div class="panel-heading">添加</div>
			<div class="panel-body">
				<form class="form-horizontal" onsubmit="return false;" action="edit?id=${goods.goodsId }">
					<div class="form-group">
						<label class="col-sm-1 control-label">产品名称</label>
						<div class="col-sm-5">
							<input type="text" name="inputProName" value="${goods.proName }"
								class="form-control" placeholder="请输入产品名称" required autofocus>
						</div>
					</div>
					<jsp:include page="/manager/goods/property" flush="true">
						<jsp:param value="${cId} }" name="cId" />
					</jsp:include>
					<div class="form-group">
						<label class="col-sm-1 control-label">描述</label>
						<div class="col-sm-10">
							<script id="container" name="inputDiscription" type="text/plain">${goods.description }</script>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">排序</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" required autofocus
								pattern="[0-9]{0,10}" value="${goods.sortOrder }" name="inputSortOrder">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-1 col-sm-10">
							<button type="button" id="btnEdit" data-loading-text="处理..."
								class="btn btn-sm btn-primary">提交</button>

							<button type="button" onclick="window.history.back()"
								class="btn btn-sm">返回</button>
							<button type="submit" id="btnSubmit" style="display: none"></button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript"
	src="../../resource/utf8-jsp/ueditor.config.js"></script>
<script type="text/javascript"
	src="../../resource/utf8-jsp/ueditor.all.js"></script>
<script type="text/javascript">
	UE.getEditor('container');
	$("#btnEdit").on("click", function() {
		$("#btnSubmit").trigger("click");
		var form = $(".form-horizontal");
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
	})
</script>
<jsp:include page="/manager/footer"></jsp:include>