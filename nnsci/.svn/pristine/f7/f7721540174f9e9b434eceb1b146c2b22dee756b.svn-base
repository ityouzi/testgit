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
					
					<form action="finance/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="FINANCE_ID" id="FINANCE_ID" value="${pd.FINANCE_ID}"/>
						<textarea style="display: none;" name="REMAKE" id="REMAKE" ></textarea>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
						
						<c:if test="${msg == 'edit'}">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">创建人:</td>
								<td><input type="text" name="CREATE_NAME" id="CREATE_NAME" value="${pd.CREATE_NAME}" maxlength="255" placeholder="这里输入创建人" title="创建人" style="width:98%;" readonly="readonly"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">创建时间:</td>
								<td><input type="text" name="CREATE_TIME" id="CREATE_TIME" value="${pd.CREATE_TIME}" maxlength="255" placeholder="这里输入创建时间" title="创建时间" style="width:98%;" readonly="readonly"/></td>
									
							</tr>
							
						</c:if>
							
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">发生人:</td>
								<td><input type="text" name="USER_NAME" id="USER_NAME" value="${pd.USER_NAME}" maxlength="255" placeholder="这里输入发生人" title="发生人" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">权限:</td>
								<%-- <td><input type="text" name="YL1" id="YL1" value="${pd.YL1}" maxlength="255" placeholder="这里输入问题标题" title="问题标题" style="width:98%;"/>
								<select class="chosen-select form-control" name="YL3" id="YL3" data-placeholder="请选择" style="vertical-align:top;width: 120px;">
									<option value="">--请选择--</option>
									<option value="所有人">所有人</option>
									<option value="仅自己">仅自己</option>
								</select>
								</td> --%>
								<td style="vertical-align:top;padding-left:2px;">								
									<select name="YL3" id="YL3" >
										<option value="">--请选择--</option>
			                            <c:forEach items="${list1}" var="var" varStatus="vs">
			                                <option value="${var.NAME}" <c:if test="${var.NAME==pd.YL3}">selected</c:if> > ${var.NAME}</option>
			                            </c:forEach>
                        			</select>						
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">金额:</td>
								<td><input type="text" onkeyup="clearNoNum(this)" name="PRICE" id="PRICE" value="${pd.PRICE}" maxlength="32" placeholder="这里输入金额" title="金额" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">收支科目:</td>
								<td>
									<select class="chosen-select form-control" name="YL1" id="YL1" data-placeholder="请选择" style="vertical-align:top;width: 120px;">
										<option value="">--请选择--</option>
										<c:forEach items="${list }" var="list">
											<option value="${list.NAME }"  <c:if test="${pd.YL1==list.NAME}">selected</c:if>>${list.NAME }</option>
										</c:forEach>
								  	</select>
			
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">收支类型:</td>
								<%-- <td><input type="text" name="PRICE_TYPE" id="PRICE_TYPE" value="${pd.PRICE_TYPE}" maxlength="255" placeholder="这里输入收支类型" title="收支类型" style="width:98%;"/></td> --%>
								<td>
								<input type="radio" <c:if test="${pd.PRICE_TYPE eq '支出'}">checked</c:if> name="PRICE_TYPE" id="PRICE_TYPE" title="收支类型" value="支出">支出</input>
								<input type="radio" <c:if test="${pd.PRICE_TYPE eq '收入'}">checked</c:if> name="PRICE_TYPE" id="PRICE_TYPE" title="收支类型" value="收入">收入</input>
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">支付时间:</td>
								<%-- <td><input type="text" name="PRICE_TIME" id="PRICE_TIME" value="${pd.PRICE_TIME}" maxlength="255" placeholder="这里输入支付时间" title="支付时间" style="width:98%;"/></td> --%>
								<td style="padding-left:2px;"><input class="span10 date-picker" name="PRICE_TIME" id="PRICE_TIME"  value="${pd.PRICE_TIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="支付时间" title="支付时间"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">用途:</td>
								<td><input type="text" name="REMAKE" id="REMAKE" value="${pd.REMAKE}" maxlength="255" placeholder="这里输入用途" title="用途" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注:</td>
								<td><script id="editor" type="text/plain" style="width:88%;height:220px;" name="YL2" >${pd.YL2}</script></td>
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
			if($("#USER_NAME").val()==""){
				$("#USER_NAME").tips({
					side:3,
		            msg:'请输入发生人',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#USER_NAME").focus();
			return false;
			}
			if($("#PRICE").val()==""){
				$("#PRICE").tips({
					side:3,
		            msg:'请输入金额',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PRICE").focus();
			return false;
			}
			if($("#PRICE_TYPE").val()==""){
				$("#PRICE_TYPE").tips({
					side:3,
		            msg:'请输入收支类型',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PRICE_TYPE").focus();
			return false;
			}
			if($("#PRICE_TIME").val()==""){
				$("#PRICE_TIME").tips({
					side:3,
		            msg:'请输入支付时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PRICE_TIME").focus();
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
		
		
		<script language="JavaScript" type="text/javascript">    
		function clearNoNum(obj){
			//修复第一个字符是小数点 的情况.
			if(obj.value !=''&& obj.value.substr(0,1) == '.'){
				obj.value="";
			}
			obj.value = obj.value.replace(/^0*(0\.|[1-9])/, '$1');//解决 粘贴不生效
			obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
			obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的     
			obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");    
			obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数     
			if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额
				if(obj.value.substr(0,1) == '0' && obj.value.length == 2){
					obj.value= obj.value.substr(1,obj.value.length);	
				}
			}    
	}    
</script>
</body>
</html>