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
							<c:if test="${msg == 'edit'}">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">创建人:</td>
								<td><input type="text" name="NAME" id="NAME" value="${pd.NAME}" maxlength="255" placeholder="这里输入创建人" title="创建人" style="width:98%;" readonly="readonly"/></td>
							</tr>
							</c:if>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">标题:</td>
								<td><input type="text" name="TITLE" id="TITLE" value="${pd.TITLE}" maxlength="255" placeholder="这里输入标题" title="标题" style="width:98%;"/></td>
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
								<td><%-- <input type="text" name="STATE" id="STATE" value="${pd.STATE}" maxlength="255" placeholder="这里输入状态" title="状态" style="width:98%;"/> --%>
									<select  name="STATE" id="STATE">
										<option value="">--状态--</option>
									  <option value="已发布" <c:if test="${pd.STATE=='已发布'}">selected</c:if>>已发布</option>
									 <option value="未发布" <c:if test="${pd.STATE=='未发布'}">selected</c:if>>未发布</option> 
									
										
									</select>
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">通告类型:</td>
								<td><%-- <input type="text" name="TYPE" id="TYPE" value="${pd.TYPE}" maxlength="255" placeholder="这里输入通告类型" title="通告类型" style="width:98%;"/> --%>
									<select  name="TYPE" id="TYPE">	
											<option value="">--通告类型--</option>								 
											<c:forEach items="${list}" var="tg">
												<option value="${tg.NAME }" <c:if test="${tg.NAME == pd.TYPE }">selected</c:if>>${tg.NAME }</option>
											</c:forEach>
									</select>
								</td> 
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
		//保存
		function save(){
			if($("#NAME").val()==""){
				$("#NAME").tips({
					side:3,
		            msg:'请输入创建人',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#NAME").focus();
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
			/* if($("#CONTENT").val()==""){
				$("#CONTENT").tips({
					side:3,
		            msg:'请输入通告内容',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CONTENT").focus();
			return false; 
			}*/
			if($("#STATE").val()==""){
				$("#STATE").tips({
					side:3,
		            msg:'请输入状态',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#STATE").focus();
			return false;
			}
			if($("#YL1").val()==""){
				$("#YL1").tips({
					side:3,
		            msg:'请输入预留字段',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL1").focus();
			return false;
			}
			if($("#YL2").val()==""){
				$("#YL2").tips({
					side:3,
		            msg:'请输入预留字段',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL2").focus();
			return false;
			}
			if($("#YL3").val()==""){
				$("#YL3").tips({
					side:3,
		            msg:'请输入预留字段',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL3").focus();
			return false;
			}
			if($("#YL4").val()==""){
				$("#YL4").tips({
					side:3,
		            msg:'请输入预留字段',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL4").focus();
			return false;
			}
			if($("#YL5").val()==""){
				$("#YL5").tips({
					side:3,
		            msg:'请输入预留字段',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL5").focus();
			return false;
			}
			if($("#YL6").val()==""){
				$("#YL6").tips({
					side:3,
		            msg:'请输入预留字段',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL6").focus();
			return false;
			}
			if($("#YL7").val()==""){
				$("#YL7").tips({
					side:3,
		            msg:'请输入预留字段',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL7").focus();
			return false;
			}
			if($("#YL8").val()==""){
				$("#YL8").tips({
					side:3,
		            msg:'请输入预留字段',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL8").focus();
			return false;
			}
			if($("#YL9").val()==""){
				$("#YL9").tips({
					side:3,
		            msg:'请输入预留字段',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL9").focus();
			return false;
			}
			if($("#YL10").val()==""){
				$("#YL10").tips({
					side:3,
		            msg:'请输入预留字段',
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