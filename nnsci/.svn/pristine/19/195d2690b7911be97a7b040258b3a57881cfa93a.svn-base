<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>">
	<!-- 下拉框 -->
	<link rel="stylesheet" href="static/ace/css/chosen.css" />
	<!-- jsp文件头和头部 -->
	<%@ include file="/WEB-INF/jsp/system/index/top.jsp"%>
	<!-- 日期框 -->
	<link rel="stylesheet" href="static/ace/css/datepicker.css" />
</head>
<body class="no-skin">
<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
	<!-- /section:basics/sidebar -->
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					
					<form action="tonggao/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="TONGGAO_ID" id="TONGGAO_ID" value="${pd.TONGGAO_ID}"/>
						<textarea style="display: none;" name="CONTENT" id="CONTENT" ></textarea>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">创建人:</td>
								<td>${pd.NAME}</td>
							</tr>
						
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">标题:</td>
								<td>${pd.TITLE}</td>
							</tr>
							
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">状态:</td>
								<td>${pd.STATE}</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">通告类型:</td>
								<td>${pd.TYPE}</td> 
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">通告内容:</td>
								<td>
								<script id="editor" type="text/plain" style="width:88%;height:220px;" name="CONTENT" >${pd.CONTENT}</script>
								</td>
							</tr>
							<%-- <tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留字段:</td>
								<td><input type="text" name="YL2" id="YL2" value="${pd.YL2}" maxlength="255" placeholder="这里输入预留字段" title="预留字段" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留字段:</td>
								<td><input type="text" name="YL3" id="YL3" value="${pd.YL3}" maxlength="255" placeholder="这里输入预留字段" title="预留字段" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留字段:</td>
								<td><input type="text" name="YL4" id="YL4" value="${pd.YL4}" maxlength="255" placeholder="这里输入预留字段" title="预留字段" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留字段:</td>
								<td><input type="text" name="YL5" id="YL5" value="${pd.YL5}" maxlength="255" placeholder="这里输入预留字段" title="预留字段" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留字段:</td>
								<td><input type="text" name="YL6" id="YL6" value="${pd.YL6}" maxlength="255" placeholder="这里输入预留字段" title="预留字段" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留字段:</td>
								<td><input type="text" name="YL7" id="YL7" value="${pd.YL7}" maxlength="255" placeholder="这里输入预留字段" title="预留字段" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留字段:</td>
								<td><input type="text" name="YL8" id="YL8" value="${pd.YL8}" maxlength="255" placeholder="这里输入预留字段" title="预留字段" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留字段:</td>
								<td><input type="text" name="YL9" id="YL9" value="${pd.YL9}" maxlength="255" placeholder="这里输入预留字段" title="预留字段" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留字段:</td>
								<td><input type="text" name="YL10" id="YL10" value="${pd.YL10}" maxlength="255" placeholder="这里输入预留字段" title="预留字段" style="width:98%;"/></td>
							</tr>
							<tr> --%>
								<td style="text-align: center;" colspan="10">
								
									<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
								</td>
							</tr>
						</table>
						</div>
					</form>
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<!-- /.main-content -->
</div>
<!-- /.main-container -->


	<!-- 页面底部js¨ -->
	<%@ include file="/WEB-INF/jsp/system/index/foot.jsp"%>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 百度富文本编辑框-->
	
	<script type="text/javascript" charset="utf-8">window.UEDITOR_HOME_URL = "<%=path%>/plugins/ueditor/";</script>
	<script type="text/javascript" charset="utf-8" src="plugins/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="plugins/ueditor/ueditor.all.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
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