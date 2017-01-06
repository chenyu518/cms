<%--
  Created by IntelliJ IDEA.
  User: cy
  Date: 16/12/27
  Time: 下午3:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/admin/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/zTree/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/tree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.core.js"></script>
<script>
  $(function(){
    var t = $('#tree').mytree({
      url:$('#treePath').val(),
      mine:{listChild:0},
      callback:{
        onAsyncSuccess:function(){
//          t.expandAll(true);
        }
      }
    });
  })

</script>
<html>
<head>
</head>
<body>
<div id="content">
  <h3 class="admin_link_bar">
    <jsp:include page="inc.jsp"></jsp:include>
  </h3>
  <div>
    <input type="hidden" id="treePath" value="<%=request.getContextPath() %>/admin/group/groupTree/${group.id}"/>
    <div style="padding-left: 10px;font-size: 12px">当前组名称:${group.name}</div>
    <div id="tree" class="ztree"></div>
  </div>
</div>
</body>
</html>
