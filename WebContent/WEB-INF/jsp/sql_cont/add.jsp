<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">

	$(function() {
		$("#submitBtn").click(function() {
			$.post("sqlcont/save", {
				sqlName : $("#sqlName").val(),
				sqlCont : $("#sqlCont").val()
			}, function(data) {
				alert(data);
			});
			return false;
		});
	});
</script>
<form>
    <fieldset>
        <legend>添加SQL</legend>
        SQL名称<input id="sqlName" type="text" /> <br/>
        SQL内容<textarea id="sqlCont" rows="20" cols="100"></textarea><br/>
        <input type="button" value="提交" id="submitBtn"/>
    </fieldset>
</form>

