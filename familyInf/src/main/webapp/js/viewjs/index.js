function clickbranks(id){
		$("#branks option[value='"+id+"']").attr("selected", true); 
		 $('#branks').trigger('change');
	}
function clickxls(id){
	$("#xls option[value='"+id+"']").attr("selected", true); 
	 $('#xls').trigger('change');
}
function clickproType(id){
	$("#proType option[value='"+id+"']").attr("selected", true); 
	 $('#proType').trigger('change');
}
function clickyears(id){
	$("#years option[value='"+id+"']").attr("selected", true); 
	 $('#years').trigger('change');
}
function clickmodel(id){
	$("#model option[value='"+id+"']").attr("selected", true); 
	 $('#model').trigger('change');
}
function clickmodel2(id){
	$("#model2 option[value='"+id+"']").attr("selected", true); 
	 $('#model2').trigger('change');
}

$(function() {
	$.ajax({
		url:ctx()+'/branks',
	    type:'POST', //GET
	    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
	    success:function(data){
	        /*alert(JSON.stringify(data));*/
	    	var obj=document.getElementById('branks'); 
	    	obj.options.add(new Option('请选择品牌','')); //这个兼容IE与firefox
	    	 $.each(data.extend.result, function(i,val){     
	    		 obj.options.add(new Option(val.brankName,val.id)); //这个兼容IE与firefox
	    		 if(i%4==0){
	    			 $("#right").append("<tr>"); 
	    		 }
	    		 $("#right").append('<td onclick="clickbranks(\''+val.id+'\')"><img id="smallImg" class="center-block" src=/img'+val.brankImgpath.substr(15,val.brankImgpath.length)+'  alt="Responsive image"><h4 class="text-center">'+val.brankName+'&nbsp&nbsp&nbsp</h4></td><td>&nbsp&nbsp</td>');
	    		 if(i%4==0){
	    			 $("#right").append("</tr>"); 
	    		 }
	    	  });
	    }
	})
	
	$("#branks").change(function(){
		var xls=document.getElementById('xls'); 
		xls.options.length=0; 
		var proType=document.getElementById('proType'); 
		proType.options.length=0; 
		var years=document.getElementById('years'); 
		years.options.length=0; 
		var model=document.getElementById('model'); 
		model.options.length=0; 
		var model2=document.getElementById('model2'); 
		model2.options.length=0; 
		$("#right").empty();
		
		var brankId=$('#branks option:selected').val();
		$.ajax({
			url:ctx()+'/xls/'+brankId,
		    type:'POST', //GET
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    success:function(data){
		    	var obj=document.getElementById('xls'); 
		    	obj.options.add(new Option('请选择系列','')); //这个兼容IE与firefox
		    	 $.each(data.extend.result, function(i,val){
		    		 obj.options.add(new Option(val.xlName,val.id)); //这个兼容IE与firefox
		    		 
		    		 if(i%4==0){
		    			 $("#right").append("<tr>"); 
		    		 }
		    		 $("#right").append('<td onclick="clickxls(\''+val.id+'\')"><img id="smallImg" class="center-block" src=/img'+val.xlImgpath.substr(15,val.xlImgpath.length)+'  alt="Responsive image"><h4 class="text-center">'+val.xlName+'</h4></td>');
		    		 if(i%4==0){
		    			 $("#right").append("</tr>"); 
		    		 }
		    	  });
		    }
		})
	})
		
	$("#xls").change(function(){
		var proType=document.getElementById('proType'); 
		proType.options.length=0; 
		var years=document.getElementById('years'); 
		years.options.length=0; 
		var model=document.getElementById('model'); 
		model.options.length=0; 
		var model2=document.getElementById('model2'); 
		model2.options.length=0; 
		$("#right").empty();
		
		var xlsId=$('#xls option:selected').val();
		$.ajax({
			url:ctx()+'/proType/'+xlsId,
		    type:'POST', //GET
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    success:function(data){
		    	var obj=document.getElementById('proType'); 
		    	obj.options.add(new Option('请选择类型','')); //这个兼容IE与firefox
		    	 $.each(data.extend.result, function(i,val){     
		    		 obj.options.add(new Option(val.typeName,val.id)); //这个兼容IE与firefox
		    		 
		    		 if(i%4==0){
		    			 $("#right").append("<tr>"); 
		    		 }
		    		 $("#right").append('<td onclick="clickproType(\''+val.id+'\')"><img id="smallImg" class="center-block" src=/img'+val.typeImgpath.substr(15,val.typeImgpath.length)+'  alt="Responsive image"><h4 class="text-center">'+val.typeName+'</h4></td>');
		    		 if(i%4==0){
		    			 $("#right").append("</tr>"); 
		    		 }
		    	  });
		    }
		})
	})
	
	$("#proType").change(function(){
		var years=document.getElementById('years'); 
		years.options.length=0; 
		var model=document.getElementById('model'); 
		model.options.length=0; 
		var model2=document.getElementById('model2'); 
		model2.options.length=0; 
		$("#right").empty();
		
		var proTypeId=$('#proType option:selected').val();
		$.ajax({
			url:ctx()+'/years/'+proTypeId,
		    type:'POST', //GET
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    success:function(data){
		    	var obj=document.getElementById('years'); 
		    	obj.options.add(new Option('请选择年份','')); //这个兼容IE与firefox
		    	 $.each(data.extend.result, function(i,val){     
		    		 obj.options.add(new Option(val.yearName,val.id)); //这个兼容IE与firefox
		    		 
		    		 if(i%4==0){
		    			 $("#right").append("<tr>"); 
		    		 }
		    		 $("#right").append('<td onclick="clickyears(\''+val.id+'\')"><img id="smallImg" src=/img'+val.yearImgpath.substr(15,val.yearImgpath.length)+'  alt="Responsive image"><h4 class="text-center">'+val.yearName+'</h4></td>');
		    		 if(i%4==0){
		    			 $("#right").append("</tr>"); 
		    		 }
		    	  });
		    }
		})
	})
	
	$("#years").change(function(){
		var model=document.getElementById('model'); 
		model.options.length=0; 
		var model2=document.getElementById('model2'); 
		model2.options.length=0; 
		$("#right").empty();
		
		var yearsId=$('#years option:selected').val();
		$.ajax({
			url:ctx()+'/model1/'+yearsId,
		    type:'POST', //GET
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    success:function(data){
		    	var obj=document.getElementById('model');
		    	obj.options.add(new Option('请选择','')); //这个兼容IE与firefox
		    	 $.each(data.extend.result, function(i,val){     
		    		 obj.options.add(new Option(val.model1Name,val.id)); //这个兼容IE与firefox
		    		 
		    		 if(i%4==0){
		    			 $("#right").append("<tr>"); 
		    		 }
		    		 $("#right").append('<td onclick="clickmodel(\''+val.id+'\')"><img id="smallImg" class="center-block" src=/img'+val.model1Imgpath.substr(15,val.model1Imgpath.length)+'  alt="Responsive image"><h4 class="text-center">'+val.model1Name+'</h4></td>');
		    		 if(i%4==0){
		    			 $("#right").append("</tr>"); 
		    		 }
		    	  });
		    }
		})
	})
	
	$("#model").change(function(){
		var model2=document.getElementById('model2'); 
		model2.options.length=0; 
		$("#right").empty();
		
		var modelId=$('#model option:selected').val();
		$.ajax({
			url:ctx()+'/model2/'+modelId,
		    type:'POST', //GET
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    success:function(data){
		    	var obj=document.getElementById('model2');
		    	obj.options.add(new Option('请选择','')); //这个兼容IE与firefox
		    	 $.each(data.extend.result, function(i,val){     
		    		 obj.options.add(new Option(val.model2Name,val.id)); //这个兼容IE与firefox
		    		 
		    		 if(i%4==0){
		    			 $("#right").append("<tr>"); 
		    		 }
		    		 $("#right").append('<td onclick="clickmodel2(\''+val.id+'\')"><img id="smallImg" src=/img'+val.model2Imgpath.substr(15,val.model2Imgpath.length)+'  alt="Responsive image"><h4 class="text-center">'+val.model2Name+'</h4></td>');
		    		 if(i%4==0){
		    			 $("#right").append("</tr>"); 
		    		 }
		    	  });
		    }
		})
	})
	
	$("#model2").change(function(){
		var model2Id=$('#model2 option:selected').val();
		$("#right").empty();
		
		/*$.ajax({
			url:ctx()+'/ljInfo/'+model2Id,
		    type:'POST', //GET
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    success:function(data){
		    	var obj=document.getElementById('ljInfo'); 
		    	obj.options.add(new Option('请选择','')); //这个兼容IE与firefox
		    	 $.each(data.extend.result, function(i,val){     
		    		 obj.options.add(new Option(val.ljDesc,val.id)); //这个兼容IE与firefox
		    	  });
		    }
		})*/
		$.ajax({
			url:ctx()+'/getBigImg/'+model2Id,
		    type:'POST', //GET
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    success:function(data){
		    	 $("#right").append('<tr><td><img id="bigImg" src=/img'+data.extend.result.model2Bigimgpath.substr(15,data.extend.result.model2Bigimgpath.length)+'  alt="Responsive image"></td></tr>');
		    }
		})
	})
	
})

	
