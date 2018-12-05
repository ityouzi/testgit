<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<base href="<%=basePath%>">
	<!-- 下拉框 -->
	<link rel="stylesheet" href="static/ace/css/chosen.css" />
	<!-- jsp文件头和头部 -->
	<%@ include file="/WEB-INF/jsp/system/index/top.jsp"%>
	<!-- 日期框 -->
	<link rel="stylesheet" href="static/ace/css/datepicker.css" />
<title>详情</title>
<style type="text/css">
.page-content{
box-sizing: border-box;
}
</style>
</head>
<body>
	
	<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top: 5px">
	<thead>
	<tr><th>
		<input type="hidden" name="QUESTION_ID" id="QUESTION_ID" value="${pd.QUESTION_ID}"/>
		<div style="text-align:center;"><h3>${pd.YL1}</h3></div>
		<div name="CREATOR" id="CTEATOR" style="text-align:right; margin-right: 20px">${pd.CREATOR}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${pd.CREATE_TIME}</div>
		<div style="margin:5px auto;">${pd.QUESTION}</div>
	</th></tr>
	</thead>
	<tbody>	
	<tr><td><h3>回答:</h3></td></tr>
	<c:forEach items="${commentlist}" var="var" varStatus="vs">
		<tr>
			<td>${var.CONTENT}<div style="text-align:right; margin-right: 20px">${var.CREATOR}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${var.CREATE_TIME}</div></td>
		</tr>
	</c:forEach>
	</tbody>
	</table>
	<form action="comment/reply.do" name="Form" id="Form" method="post">
						<textarea style="display: none;" name="CONTENT" id="CONTENT" ></textarea>
						<div id="zhongxin" style="padding-top: 13px;box-sizing: border-box;">
						<input type="hidden" name="QUESTION_ID" id="QUESTION_ID" value="${pd.QUESTION_ID}"/>
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td>
								  <script id="editor" type="text/plain" style="width:96%;height:420px;"></script>
								</td>
							</tr>
							<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-primary" onclick="save();">提交</a>
									<a class="btn btn-mini btn-danger" onclick="history.back();">取消</a>
								</td>
							</tr>
						</table>
						</div>
						<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
					</form>
	<!-- 页面底部js¨ -->
	<%@ include file="/WEB-INF/jsp/system/index/foot.jsp"%>
	<!-- 百度富文本编辑框-->
	<script type="text/javascript" charset="utf-8">window.UEDITOR_HOME_URL = "<%=path%>/plugins/ueditor/";</script>
	<script type="text/javascript" charset="utf-8" src="plugins/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="plugins/ueditor/ueditor.all.js"></script>
	<!-- 百度富文本编辑框-->
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		//保存
		function save(){
			$("#CONTENT").val(getContent());
			if($("#CONTENT").val()==""){
				$("#CONTENT").tips({
					side:3,
		            msg:'请输入内容',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CONTENT").focus();
			return false;
			}
			$("#Form").submit();
			//$("#zhongxin").hide();
			//$("#zhongxin").show();
		}
		
		//百度富文本
		setTimeout("ueditor()",500);
		function ueditor(){
			UE.getEditor('editor');
		}
		//ueditor有标签文本
		function getContent() {
		    var arr = [];
		    arr.push(UE.getEditor('editor').getContent());
		    return arr.join("");
		}
		</script>
</body>
</html>