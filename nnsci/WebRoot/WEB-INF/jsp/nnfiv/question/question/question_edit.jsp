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
					<form action="question/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="QUESTION_ID" id="QUESTION_ID" value="${pd.QUESTION_ID}"/>
						<textarea style="display: none;" name="QUESTION" id="QUESTION" ></textarea>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<%-- <tr>
								<td style="width:150px;text-align: right;padding-top: 13px;">创建人:</td>
								<td><input type="text" name="CREATOR" id="CREATOR" value="${pd.CREATOR}" maxlength="255" placeholder="这里输入创建人" title="创建人" style="width:98%;"/></td>
							</tr> --%>
							<%-- <tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">创建时间:</td>
								<td><input class="span10 date-picker" name="CREATE_TIME" id="CREATE_TIME" value="${pd.CREATE_TIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="创建时间" title="创建时间" style="width:98%;"/></td>
							</tr> --%>
							<tr style="display:none">
								<td style="width:75px;text-align: right;padding-top: 13px;">状态:</td>
								<td><input type="text" name="STATUS" id="STATUS" value="${pd.STATUS}" maxlength="255" placeholder="这里输入状态" title="状态" style="width:98%;"/>
								<select class="chosen-select form-control" name="STATUS" id="STATUS" data-placeholder="请选择" style="vertical-align:top;width: 120px;">
									<option value="待解决"></option>
									<option value="待解决">待解决</option>
									<option value="已解决">已解决</option>
								 </select>
								</td> 
							</tr>
							
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">标题:</td>
								<td><input type="text" name="YL1" id="YL1" value="${pd.YL1}" maxlength="255" placeholder="这里输入问题标题" title="问题标题" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">问题权限:</td>
								<td><%-- <input type="text" name="YL1" id="YL1" value="${pd.YL1}" maxlength="255" placeholder="这里输入问题标题" title="问题标题" style="width:98%;"/> --%>
								<select class="chosen-select form-control" name="YL2" id="YL2" data-placeholder="请选择" style="vertical-align:top;width: 120px;">
									<option>${pd.YL2}</option>
									<option value="">--请选择--</option>
									<option value="所有人">所有人</option>
									<option value="仅自己">仅自己</option>
								</select>
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">类型:</td>
								<td>
									<select class="chosen-select form-control" name="TYPE" id="TYPE" data-placeholder="请选择" style="vertical-align:top;width: 120px;">
									<c:forEach items="${list}" var="list">
										<option value="${list.NAME}" <c:if test="${list.NAME == pd.TYPE}">selected</c:if>>${list.NAME}</option>
									</c:forEach>
								  	</select>
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">问题:</td>
								<td><%-- <input type="text" name="QUESTION" id="QUESTION" value="${pd.QUESTION}" maxlength="255" placeholder="这里输入问题" title="问题" style="width:98%;"/> --%>
								<script id="editor" type="text/plain" style="width:88%;height:220px;" name="QUESTION" >${pd.QUESTION}</script>
								</td>
							</tr>
							
							
							<%-- <tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留2:</td>
								<td><input type="text" name="YL2" id="YL2" value="${pd.YL2}" maxlength="255" placeholder="这里输入预留2" title="预留2" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留3:</td>
								<td><input type="text" name="YL3" id="YL3" value="${pd.YL3}" maxlength="255" placeholder="这里输入预留3" title="预留3" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留4:</td>
								<td><input type="text" name="YL4" id="YL4" value="${pd.YL4}" maxlength="255" placeholder="这里输入预留4" title="预留4" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留5:</td>
								<td><input type="text" name="YL5" id="YL5" value="${pd.YL5}" maxlength="255" placeholder="这里输入预留5" title="预留5" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留6:</td>
								<td><input type="text" name="YL6" id="YL6" value="${pd.YL6}" maxlength="255" placeholder="这里输入预留6" title="预留6" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留7:</td>
								<td><input type="text" name="YL7" id="YL7" value="${pd.YL7}" maxlength="255" placeholder="这里输入预留7" title="预留7" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留8:</td>
								<td><input type="text" name="YL8" id="YL8" value="${pd.YL8}" maxlength="255" placeholder="这里输入预留8" title="预留8" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留9:</td>
								<td><input type="text" name="YL9" id="YL9" value="${pd.YL9}" maxlength="255" placeholder="这里输入预留9" title="预留9" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留10:</td>
								<td><input type="text" name="YL10" id="YL10" value="${pd.YL10}" maxlength="255" placeholder="这里输入预留10" title="预留10" style="width:98%;"/></td>
							</tr> --%>
							<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-primary" onclick="save();">发布</a>
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
	<!-- 百度富文本编辑框-->
	<%@ include file="/WEB-INF/jsp/system/index/foot.jsp"%>
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
			if($("#CREATOR").val()==""){
				$("#CREATOR").tips({
					side:3,
		            msg:'请输入创建人',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CREATOR").focus();
			return false;
			}
			/* if($("#STATUS").val()==""){
				$("#STATUS").tips({
					side:3,
		            msg:'请输入状态',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#STATUS").focus();
			return false;
			} */
			/* if($("#editor").val()==""){
				$("#editor").tips({
					side:3,
		            msg:'请输入问题',
		            bg:'#AE81FF',
		            time:2
		        });	
				$("#editor").focus();
			return false;
			} */
			if($("#TYPE").val()==""){
				$("#TYPE").tips({
					side:3,
		            msg:'请输入类型',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TYPE").focus();
			return false;
			}
			if($("#YL1").val()==""){
				$("#YL1").tips({
					side:3,
		            msg:'请输入预留1',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL1").focus();
			return false;
			}
			if($("#YL2").val()==""){
				$("#YL2").tips({
					side:3,
		            msg:'请输入预留2',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL2").focus();
			return false;
			}
			if($("#YL3").val()==""){
				$("#YL3").tips({
					side:3,
		            msg:'请输入预留3',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL3").focus();
			return false;
			}
			if($("#YL4").val()==""){
				$("#YL4").tips({
					side:3,
		            msg:'请输入预留4',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL4").focus();
			return false;
			}
			if($("#YL5").val()==""){
				$("#YL5").tips({
					side:3,
		            msg:'请输入预留5',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL5").focus();
			return false;
			}
			if($("#YL6").val()==""){
				$("#YL6").tips({
					side:3,
		            msg:'请输入预留6',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL6").focus();
			return false;
			}
			if($("#YL7").val()==""){
				$("#YL7").tips({
					side:3,
		            msg:'请输入预留7',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL7").focus();
			return false;
			}
			if($("#YL8").val()==""){
				$("#YL8").tips({
					side:3,
		            msg:'请输入预留8',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL8").focus();
			return false;
			}
			if($("#YL9").val()==""){
				$("#YL9").tips({
					side:3,
		            msg:'请输入预留9',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL9").focus();
			return false;
			}
			if($("#YL10").val()==""){
				$("#YL10").tips({
					side:3,
		            msg:'请输入预留10',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL10").focus();
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