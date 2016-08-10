<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="cabinet.web.util.*" %>
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Project name</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
			    <li class="active"><a href="<%=httpUtil.getContextPath(request) %>/manager/goods">产品</a></li>
				<li><a href="<%=httpUtil.getContextPath(request) %>/manager/info">信息</a></li>
				<li><a href="<%=httpUtil.getContextPath(request) %>/manager/image">图片</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">配制<span class="caret"></span></a>
					<ul class="dropdown-menu">
					<li><a href="<%=httpUtil.getContextPath(request) %>/manager/tag">标签</a></li>
					<li><a href="<%=httpUtil.getContextPath(request) %>/manager/property">类别属性</a></li>
						<li role="separator" class="divider"></li>
						<li class="dropdown-header">Nav header</li>
						<li><a href="#">Separated link</a></li>
						<li><a href="#">One more separated link</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">安全退出</a></li>

			</ul>
		</div>
	</div>
</nav>