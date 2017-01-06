<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.util.my.js"></script>
<style type="text/css">
	*{
		font-size: 12px;
	}
	input{
		margin: 2px;
	}
	body{
		padding: 40px;
	}
</style>
<script type="text/javascript">

	$(function(){
		$('#copyAddress').click(function(e){
			console.log(e)
			var checked = $('#copyAddress').is(':checked');
			if(checked){
				$('#sendAddress').val($('#homeAddress').val());
			}
			$('input').setReadonly(checked);

		})
	})

</script>
</head>
<body>

<fieldset>
	<legend>输入地址</legend>
	<div id="home">
		<h3>输入家庭地址</h3>
		地址:<input type="text" id="homeAddress" name="homeAddress" size="40"/><br/>
		电话:<input type="text" id="homePhone" name="homePhone" size="40"/>
	</div>
	<div id="send">
		<h3>输入邮寄地址</h3><input type="checkbox" id="copyAddress">是否和家庭地址一样<br/>
		地址:<input type="text" id="sendAddress" name="sendAddress" size="40"/><br/>
		电话:<input type="text" id="sendPhone" name="sendPhone" size="40"/>
	</div>
</fieldset>

</body>
</html>