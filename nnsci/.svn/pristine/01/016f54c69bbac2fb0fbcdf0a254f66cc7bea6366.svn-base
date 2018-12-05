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
					
					<form action="report/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="REPORT_ID" id="REPORT_ID" value="${pd.REPORT_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">汇报人:</td>
								<td><input name=MEMBER id="MEMBER" value="${pd.MEMBER}" readonly="readonly"/></td>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">提交日期:</td>
								<td><input class="span10 date-picker" name="DATE" id="DATE" value="${pd.DATE}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="提交日期" title="提交日期" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">权限:</td>
								<td style="vertical-align:top;padding-left:2px;">								
									<select name="YL2" id="YL2" >
										<option value="">--请选择--</option>
			                            <c:forEach items="${list1}" var="var" varStatus="vs">
			                                <option value="${var.NAME}" <c:if test="${var.NAME==pd.YL2}">selected</c:if> > ${var.NAME}</option>
			                            </c:forEach>
                        			</select>						
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">类型:</td>
								<%-- <td><input type="text" name="TITLE" id="TITLE" value="${pd.TITLE}" maxlength="255" placeholder="这里输入标题" title="标题" style="width:98%;"/></td> --%>
								<td style="vertical-align:top;padding-left:2px;">								
									<select name="TITLE" id="TITLE" >
			                            <c:forEach items="${list2}" var="var" varStatus="vs">
			                                <option value="${var.NAME}" <c:if test="${var.NAME==pd.TITLE}">selected</c:if> > ${var.NAME}</option>
			                            </c:forEach>
                        			</select>						
								</td>
							
							</tr>
							<%-- <tr>
								<td style="width:75px;text-align: right;padding-top: 13px;display: none">是否查阅:</td>   
								<td style="vertical-align:top;padding-left:2px;display: none">								
									<select name="STATE" id="STATE" >
			                            <c:forEach items="${list1}" var="var" varStatus="vs">
			                                <option value="${var.NAME}" <c:if test="${var.NAME==pd.STATE}">selected</c:if> > ${var.NAME}</option>
			                            </c:forEach>
                        			</select>						
								</td>								
							</tr> --%>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">汇报内容:</td>
								<%-- <td><input type="text" name="CONTENT" id="CONTENT" value="${pd.CONTENT}" maxlength="255" placeholder="这里输入汇报内容" title="汇报内容" style="width:98%;"/></td> --%>
								<!-- <td><textarea maxlength="255" placeholder="这里输入汇报内容" title="汇报内容" style="width:98%;" name="CONTENT" id="CONTENT"></textarea></td> -->
								<td><script id="editor" type="text/plain" style="width:88%;height:220px;" name="CONTENT" id="CONTENT">${pd.CONTENT }</script></td>
							</tr>			
							
							<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
									<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
								</td>
							</tr>
						</table>
						</div>
						<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
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
	<!-- 百度富文本 -->
	<script type="text/javascript" charset="utf-8">window.UEDITOR_HOME_URL = "<%=path%>/plugins/ueditor/";</script>
	<script type="text/javascript" charset="utf-8" src="plugins/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="plugins/ueditor/ueditor.all.js"></script>
	
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		//保存
		function save(){
			if($("#MEMBER").val()==""){
				$("#MEMBER").tips({
					side:3,
		            msg:'请输入汇报人',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#MEMBER").focus();
			return false;
			}
			if($("#DATE").val()==""){
				$("#DATE").tips({
					side:3,
		            msg:'请输入提交日期',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#DATE").focus();
			return false;
			}
			if($("#CONTENT").val()==""){
				$("#CONTENT").tips({
					side:3,
		            msg:'请输入汇报内容',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CONTENT").focus();
			return false;
			}
			if($("#STATE").val()==""){
				$("#STATE").tips({
					side:3,
		            msg:'请输入是否查阅',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#STATE").focus();
			return false;
			}
			if($("#TITLE").val()==""){
				$("#TITLE").tips({
					side:3,
		            msg:'请输入标题',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TITLE").focus();
			return false;
			}
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
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
		
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		</script>
</body>
</html>