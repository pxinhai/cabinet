<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="cabinet.domain.model.*"%>
<%@page import="cabinet.infrastructure.*"%>
<%@page import="cabinet.domain.model.ValueObject.*"%>
<%@page import="java.util.*"%>
<%
	List<CategoryProperty> categoryProperties = (List<CategoryProperty>) (request
			.getAttribute("categoryProperties"));
	for (CategoryProperty p : categoryProperties) {
%>
<div class="form-group">
	<%
		switch (p.getValueTypeEnum()) {
			case SingleText:
	%>

	<label class="col-sm-1 control-label"><%=p.getPropertyName()%></label>
	<div class="col-sm-5">
	<div class="input-group">
		<input type="text" name="input_<%=p.getCagetroyPropertyId()%>"
			value="<%=p.getPropertyValue() != null ? p.getPropertyValue().getShortStrValue() : ""%>"
			class="form-control">
			<%if(!CharacterUtility.IsNullOrWhiteSpace(p.getUnit())){ %>
			<span class="input-group-addon" id="sizing-addon1"><%=p.getUnit()%></span>
			<%} %>
			</div>
	</div>
	<%
		break;
			case MultisText:
	%>

	<label class="col-sm-1 control-label"><%=p.getPropertyName()%></label>
	<div class="col-sm-5">
		<textarea style="display: none"
			name="input_<%=p.getCagetroyPropertyId()%>"
			placeholder="请输入<%=p.getPropertyName()%>">
			<%=p.getPropertyValue() != null ? p.getPropertyValue().getLongStrValue() : ""%>
		</textarea>
	</div>
	<%
		break;
			case Number:
	%>

	<label class="col-sm-1 control-label"><%=p.getPropertyName()%></label>
	<div class="col-sm-5">
	<div class="input-group">
		<input type="text" class="form-control" autofocus
			pattern="[0-9]{0,10}"
			value="<%=p.getPropertyValue() != null ? p.getPropertyValue().getDecimalValue().toString().replaceAll(".00","") : ""%>"
			name="input_<%=p.getCagetroyPropertyId()%>">
			<%if(!CharacterUtility.IsNullOrWhiteSpace(p.getUnit())){ %>
			<span class="input-group-addon" id="sizing-addon1"><%=p.getUnit()%></span>
			<%} %>
			</div>
	</div>
	
	<%
		break;
			case SingleTag:
			case MulitsTag:
	%>
	<label class="col-sm-1 control-label"><%=p.getTag().getCname()%></label>
	<div class="col-sm-10">
		<%
			for (Tag ch : p.getTag().getChilden()) {
						boolean isCheck;
		%>
		<label><%
 	if (p.getValueTypeEnum() == InputValueType.SingleTag) {
 					isCheck = p.getPropertyValue() != null
 							&& p.getPropertyValue().getDecimalValue().intValue() == ch.getTagId();
 %> <input type="radio" value="<%=ch.getTagId()%>"
			name="input_<%=p.getCagetroyPropertyId()%>"
			<%=isCheck ? "checked=\"checked\"" : ""%> /> <%
 	} else {
 					isCheck = p.getPropertyValue() != null
 							&& p.getPropertyValue().getShortStrValue().contains("," + ch.getTagId() + ",");
 %> <input type="checkbox" value="<%=ch.getTagId()%>"
			name="input_<%=p.getCagetroyPropertyId()%>"
			<%=isCheck ? "checked=\"checked\"" : ""%> /> <%
 	}
 %> <%=ch.getCname()%> </label>&nbsp;
		<%
			}
		%>
	</div>
	<%
		break;
			}
	%>
</div>
<%
	} //for
%>
