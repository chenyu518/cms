<%--
  Created by IntelliJ IDEA.
  User: cy
  Date: 17/1/11
  Time: 上午9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/js/ext/resources/css/ext-all.css"/>
  <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext/bootstrap.js"></script>
    <title></title>
</head>
<body>
<script>
  Ext.onReady(function(){

    var myFormPanel = Ext.create('Ext.form.Panel',{
      renderTo:Ext.getBody(),
      title: 'Client and routing info',
      width:300,
      fieldDefaults:{
        labelSeparator:':',
        labelWidth:80,
        width:200
      },
      items: [{
        fieldLabel: '产品名称',
        xtype:'textfield',
        name:'productName',
        value: 'u盘'
      }, {
        fieldLabel: '金额',
        xtype:'numberfield',
        name: 'price',
        value:100
      }, {
        fieldLabel: '生产日期',
        xtype:'datefield',
        format:'Y年m月d日',
        name: 'date',
        value:new Date()
      },{
        xtype:'hidden',
        name:'productId',
        value:'001'
      },{
        fieldLabel:'产品简介',
        name:'introduction',
        xtype:'textarea'
      }],
      buttons:[{
        text:'load',
        handler:loadIntroduction
      }]
    });

    function loadIntroduction(){
      var params = myFormPanel.getForm().getValues();
      myFormPanel.load({
        url:'loadInfo',
        params:params,
        method:'get',
        success:function(form,action){
          console.log(form);
          console.log(action);
        },
        failure: function(form, action) {
//          Ext.Msg.alert("Load failed", action.result.errorMessage);
        }
      })
    }
  });
</script>
</body>
</html>
