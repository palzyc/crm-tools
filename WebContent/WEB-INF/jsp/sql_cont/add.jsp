<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {
		$("#add_cont").click(function() {
			$.post("sqlcont/save", {
				sqlName : $("#cont_name").val(),
				sqlCont : $("#cont_cont").val()
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
        SQL名称<input id="cont_name" type="text" /> <br/>
        SQL内容<textarea id="cont_cont" rows="20" cols="100"></textarea><br/>
        <input type="button" value="提交" id="add_cont"/>
    </fieldset>
</form>

