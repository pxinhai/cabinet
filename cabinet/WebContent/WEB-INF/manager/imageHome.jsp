<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="cabinet.domain.model.*"%>
<%@ page import="cabinet.web.util.*"%>
<%@ page import="java.util.List"%>
<%
	List<Image> dataView = (List<Image>) (request.getAttribute("viewModel"));
	Category category=(Category) (request.getAttribute("categoryModel"));
	String errorMsg=(String)(request.getAttribute("errorMsg"));
%>
<jsp:include page="/manager/header"></jsp:include>
<body>
	<jsp:include page="/manager/navigation"></jsp:include>
	<div class="container theme-showcase" role="main">
		<div class="page-header"></div>
		
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<form action="?" method="post" enctype="multipart/form-data">
			<div class="panel-heading">
			
				<div class="input-group col-lg-4">
				<input type="file" name="inputFile" class="form-control" value="浏览" />
      <span class="input-group-btn">
        <button class="btn btn-default" id="btnSubmit" type="submit">上传</button>
      </span>
    </div>
			</div>
				</form>
			<!-- Table -->
			<table class="table table-striped  table-hover">
				<thead>
					<tr>
						<th>名称</th>
						<th>图片</th>
						<th>类别</th>
						<th>更新时间</th>
						<th>创建时间</th>
					</tr>
				</thead>
				<tbody>
					<%
					if(dataView!=null)
					{
						for (Image i : dataView) {
							String itemId = String.valueOf(i.getImageId());
					%>
					<tr>
						<th scope="row"><%=i.getFileName() %></th>
						<td>
						<a href="<%=httpUtil.getContextPath(request)+"/"+i.getAddress() %>" target="_blank">
						<img src="<%=httpUtil.getContextPath(request)+"/"+i.getAddress() %>" width="40" >
						</a>
						</td>
						<td><%=i.getCategoryId() %></td>
						<td><%=i.getDataChangeLastTime()%></td>
						<td><%=i.getDataChangeCreateTime()%></td>
					</tr>
					<%
						}
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
<%if(errorMsg!=null){
%>alert("<%=errorMsg%>");
<%}%>
</script>
<jsp:include page="/manager/footer"></jsp:include>