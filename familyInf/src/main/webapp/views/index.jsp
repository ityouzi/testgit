<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript" >
function ctx(){
	return '${ctx}';
}
</script>


<link href="${ctx }/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript" charset="utf-8"
	src="${ctx }/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${ctx }/js/viewjs/index.js"></script>

<script type="text/javascript"
	src="${ctx }/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<style type="text/css">
		.form-control{
			width: 80%
		}
		#bigImg{
			width: 700px;
		    height: 400px;
		}
		#smallImg{
		width: 180px;
		    height: 100px;
		}
	</style>
</head>
<body style="text-align:center;margin-left: auto;margin-right: auto;">
<table style="width: 80%;margin: 50px 0 0 50px;">
	<tr>
		<td style="width: 30%" valign="top">
			<select name="branks" id="branks" class="form-control" ></select> </br>
			<select name="xls" id="xls" class="form-control"></select></br>
			<select name="proType" id="proType" class="form-control"></select></br>
			<select name="years" id="years" class="form-control"></select></br>
			<select name="model" id="model" class="form-control"></select> </br>
			<select name="model2" id="model2" class="form-control"></select></br>
		</td>
		<td>
			<td id="right" style="width: 70%">
			
			</td>
		</td>
	</tr>
</table>
</body>
</html>