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
					
					<form action="xiangmu/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="XIANGMU_ID" id="XIANGMU_ID" value="${pd.XIANGMU_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">项目名称:</td>
								<td><input type="text" name="PROJECTNAME" id="PROJECTNAME" value="${pd.PROJECTNAME}" maxlength="255" placeholder="这里输入项目名称" title="项目名称" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">项目成员:</td>
								<td><input type="text" name="NUMBER" id="NUMBER" value="${pd.NUMBER}" maxlength="255" placeholder="这里输入项目成员" title="项目成员" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">开始日期:</td>
								<td><input class="span10 date-picker" name="BEGIN_DATE" id="BEGIN_DATE" value="${pd.BEGIN_DATE}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="开始日期" title="开始日期" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">权限:</td>
								<%-- <td><input type="text" name="YL1" id="YL1" value="${pd.YL1}" maxlength="255" placeholder="这里输入问题标题" title="问题标题" style="width:98%;"/>
								<select class="chosen-select form-control" name="YL2" id="YL2" data-placeholder="请选择" style="vertical-align:top;width: 120px;">
									<option value="">--请选择--</option>
									<option value="所有人">所有人</option>
									<option value="仅自己">仅自己</option>
								</select>
								</td> --%>
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
								<td style="width:75px;text-align: right;padding-top: 13px;">状态:</td>
								<td>
									<select name="STATUS" id="STATUS" >
									<option value="">--状态--</option>		
			                            <c:forEach items="${list}" var="var" varStatus="vs">
			                                <option value="${var.NAME}" <c:if test="${var.NAME==pd.STATUS}">selected</c:if> > ${var.NAME}</option>
			                            </c:forEach>
                        			</select>
								</td>								
								
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">创建人:</td>
								<td><input  name="DUTY" id="DUTY" value="${pd.DUTY}" readonly="readonly"/></td>
							</tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">项目信息:</td>
								<td>
								<script id="editor" type="text/plain" style="width:88%;height:220px;" name="INFORMATION" >${pd.INFORMATION}</script>
								</td>
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
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript" charset="utf-8">window.UEDITOR_HOME_URL = "<%=path%>/plugins/ueditor/";</script>
	<script type="text/javascript" charset="utf-8" src="plugins/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="plugins/ueditor/ueditor.all.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		//保存
		function save(){
			if($("#PROJECTNAME").val()==""){
				$("#PROJECTNAME").tips({
					side:3,
		            msg:'请输入项目名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PROJECTNAME").focus();
			return false;
			}
			if($("#NUMBER").val()==""){
				$("#NUMBER").tips({
					side:3,
		            msg:'请输入项目成员',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#NUMBER").focus();
			return false;
			}
			
			if($("#BEGIN_DATE").val()==""){
				$("#BEGIN_DATE").tips({
					side:3,
		            msg:'请输入开始日期',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#BEGIN_DATE").focus();
			return false;
			}
			if($("#STATUS").val()==""){
				$("#STATUS").tips({
					side:3,
		            msg:'请输入状态',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#STATUS").focus();
			return false;
			}
			if($("#DUTY").val()==""){
				$("#DUTY").tips({
					side:3,
		            msg:'请输入创建人',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#DUTY").focus();
			return false;
			}
			if($("#INFORMATION").val()==""){
				$("#INFORMATION").tips({
					side:3,
		            msg:'请输入项目信息',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#INFORMATION").focus();
			return false;
			}
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
		
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