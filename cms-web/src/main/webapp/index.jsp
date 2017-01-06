<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.util.my.js"></script>
<link href="<%=request.getContextPath() %>/resources/css/tree/zTreeStyle.css">

<script>
    var zTreeObj,
            setting = {
                view: {
                    selectedMulti: false
                }
            },
            zTreeNodes = [
                {"name":"网站导航", open:true, children: [
                    { "name":"google", "url":"http://g.cn", "target":"_blank"},
                    { "name":"baidu", "url":"http://baidu.com", "target":"_blank"},
                    { "name":"sina", "url":"http://www.sina.com.cn", "target":"_blank"}
                ]
                }
            ];

    $(function(){
        zTreeObj = $.fn.zTree.init($("#tree"), setting, zTreeNodes);

    })


</script>
<body>

<ul id="tree" class="ztree" ></ul>

</body>
</html>
