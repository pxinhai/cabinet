<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="cabinet.web.util.*" %>
<jsp:include page="/manager/header"></jsp:include>
<link href="<%=httpUtil.getContextPath(request)%>/resource/manager/css/signin.css" rel="stylesheet">
<body>
	<div class="container">
		<form class="form-signin" action="<%=httpUtil.getContextPath(request)%>/manager/login" onsubmit="return false;">
			<h2 class="form-signin-heading">后台管理</h2>
			<label for="inputUsername" class="sr-only">用户名</label> <input
				type="text" name="inputUsername" class="form-control"
				placeholder="用户名" required autofocus> <label
				for="inputPassword" class="sr-only">密码</label> <input
				type="password" name="inputPassword" class="form-control"
				placeholder="密码" required>
			<div class="checkbox">
				<label> <input type="checkbox" value="1" name="ckbRememberMe" />
					记住我
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" id="btnLogin" type="button">登陆</button>
			<button type="submit" id="btnSubmit" style="display: none"></button>
		</form>

	</div>
</body>
<jsp:include page="/manager/footer"></jsp:include>
<script type="text/javascript">
	$("#btnLogin").on("click", function() {
		$("#btnSubmit").trigger("click");
		if ($("form")[0].checkValidity())
		{
			var form = $(".form-signin");
			var btn = $(this).button("loading");
			$.ajax({
				type : "post",
				url : form.attr("action"),
				data : form.serialize(),
				cache : false,
				success : function(data) {
					var obj = $.parseJSON(data)
					if (obj.errorCode == 0) {
						document.location.href="goods";
					} else {
						alert(obj.message);
						btn.button("reset");
					}
				}
			});
		}
	});
	</script>