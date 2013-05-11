<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/header.jsp" %>
<body>
<script type="text/javascript" src="js/desktop.js"></script>
<div id="doc3" class="yui-t2">
<div id="hd">
	<h1 class="ft20">Showcase示例</h1>
	<h2>--开源项目大派对</h2>
</div>
<div id="bd">
    <%@ include file="/common/left.jsp"%>
    <div id="tabs" style="position:relative; left: 180px; width: 80%">
        <ul>
            <li><a href="#tabs-1">One</a></li>
        </ul>
        <div id="tabs-1">
            <p>欢迎</p>
        </div>
    </div>
</div>
<%@ include file="/common/footer.jsp" %>
</div>
</body>
</html>