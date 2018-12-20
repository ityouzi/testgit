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
					
					<form action="b2bdata/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="B2BDATA_ID" id="B2BDATA_ID" value="${pd.B2BDATA_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">航班号:</td>
								<td><input type="text" name="HBH" id="HBH" value="${pd.HBH}" maxlength="255" placeholder="这里输入航班号" title="航班号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">出发时间:</td>
								<td><input type="text" name="CFTIME" id="CFTIME" value="${pd.CFTIME}" maxlength="255" placeholder="这里输入出发时间" title="出发时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">到达时间:</td>
								<td><input type="text" name="DDTIME" id="DDTIME" value="${pd.DDTIME}" maxlength="255" placeholder="这里输入到达时间" title="到达时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">舱位:</td>
								<td><input type="text" name="CABIN" id="CABIN" value="${pd.CABIN}" maxlength="255" placeholder="这里输入舱位" title="舱位" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">数量:</td>
								<td><input type="text" name="SEATS" id="SEATS" value="${pd.SEATS}" maxlength="255" placeholder="这里输入数量" title="数量" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">价格:</td>
								<td><input type="text" name="PRICE" id="PRICE" value="${pd.PRICE}" maxlength="255" placeholder="这里输入价格" title="价格" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">返佣:</td>
								<td><input type="text" name="RET" id="RET" value="${pd.RET}" maxlength="255" placeholder="这里输入返佣" title="返佣" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">执行时间:</td>
								<td><input type="text" name="EXCTIME" id="EXCTIME" value="${pd.EXCTIME}" maxlength="255" placeholder="这里输入执行时间" title="执行时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留:</td>
								<td><input type="text" name="YL1" id="YL1" value="${pd.YL1}" maxlength="255" placeholder="这里输入预留" title="预留" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留:</td>
								<td><input type="text" name="YL2" id="YL2" value="${pd.YL2}" maxlength="255" placeholder="这里输入预留" title="预留" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留:</td>
								<td><input type="text" name="YL3" id="YL3" value="${pd.YL3}" maxlength="255" placeholder="这里输入预留" title="预留" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留:</td>
								<td><input type="text" name="YL4" id="YL4" value="${pd.YL4}" maxlength="255" placeholder="这里输入预留" title="预留" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留:</td>
								<td><input type="text" name="YL5" id="YL5" value="${pd.YL5}" maxlength="255" placeholder="这里输入预留" title="预留" style="width:98%;"/></td>
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
		<script type="text/javascript">
		$(top.hangge());
		//保存
		function save(){
			if($("#HBH").val()==""){
				$("#HBH").tips({
					side:3,
		            msg:'请输入航班号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#HBH").focus();
			return false;
			}
			if($("#CFTIME").val()==""){
				$("#CFTIME").tips({
					side:3,
		            msg:'请输入出发时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CFTIME").focus();
			return false;
			}
			if($("#DDTIME").val()==""){
				$("#DDTIME").tips({
					side:3,
		            msg:'请输入到达时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#DDTIME").focus();
			return false;
			}
			if($("#CABIN").val()==""){
				$("#CABIN").tips({
					side:3,
		            msg:'请输入舱位',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CABIN").focus();
			return false;
			}
			if($("#SEATS").val()==""){
				$("#SEATS").tips({
					side:3,
		            msg:'请输入数量',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#SEATS").focus();
			return false;
			}
			if($("#PRICE").val()==""){
				$("#PRICE").tips({
					side:3,
		            msg:'请输入价格',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PRICE").focus();
			return false;
			}
			if($("#RET").val()==""){
				$("#RET").tips({
					side:3,
		            msg:'请输入返佣',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#RET").focus();
			return false;
			}
			if($("#EXCTIME").val()==""){
				$("#EXCTIME").tips({
					side:3,
		            msg:'请输入执行时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#EXCTIME").focus();
			return false;
			}
			if($("#YL1").val()==""){
				$("#YL1").tips({
					side:3,
		            msg:'请输入预留',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL1").focus();
			return false;
			}
			if($("#YL2").val()==""){
				$("#YL2").tips({
					side:3,
		            msg:'请输入预留',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL2").focus();
			return false;
			}
			if($("#YL3").val()==""){
				$("#YL3").tips({
					side:3,
		            msg:'请输入预留',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL3").focus();
			return false;
			}
			if($("#YL4").val()==""){
				$("#YL4").tips({
					side:3,
		            msg:'请输入预留',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL4").focus();
			return false;
			}
			if($("#YL5").val()==""){
				$("#YL5").tips({
					side:3,
		            msg:'请输入预留',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL5").focus();
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
		</script>
</body>
</html>