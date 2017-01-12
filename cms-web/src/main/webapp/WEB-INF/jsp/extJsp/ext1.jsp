<%--
  Created by IntelliJ IDEA.
  User: cy
  Date: 17/1/6
  Time: 下午4:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/js/ext/resources/css/ext-all.css"/>
  <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext/bootstrap.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext/locale/ext-lang-zh_CN.js"></script>
  <style type="text/css">
    table{
      margin: 20px 0 0 20px;
      font-size: 20px;
      line-height: 40px;
      border: 1px solid #000;
      padding: 3px;
    }
    th{text-align: center;}
    #Calculator{border: 1px solid #000;}
    #result{width: 156px;}
    .cal{
      width: 40px;
      height: 40px;
      text-align: center;
    }
    .number{width: 40px;height: 40px;text-align: center}
    .op{width: 40px;height: 40px;text-align: center}
    .sign{width: 40px;height: 40px;text-align: center}
    .cmd{width: 40px;height: 40px;text-align: center}
  </style>
  <script type="text/javascript">
    Ext.onReady(function(){
//      if(Ext.BLANK_IMAGE_URL.substr(0,4)!='data'){
//        Ext.BLANK_IMAGE_URL='./images/s.gif'
//      }
      var first = '';
      var second='';
      var op='';
      var result=Ext.getDom("result");
      var cal = function(){
        switch (op){
          case '-':
            first = parseFloat(first) - parseFloat(second);
            break;
          case '*':
            first = parseFloat(first) * parseFloat(second);
            break;
          case "/":
            second = parseFloat(second);
            if(second!=0){
              first = parseFloat(first) / second
            }
            break;
          default :
            first = parseFloat(first) + parseFloat(second);
            break;
        }
//        console.log(arguments)
//        if(arguments.length>0){
//          op = arguments[0];
//        };
        second = '';
        result.value = first;
      };

      Ext.addBehaviors({
        'input.number@click':function(e,t){
          if(Ext.isEmpty(op)){

            if(!(t.value==0 && first==0) || first.indexOf('.')){
              first += t.value;
              result.value=first;
            }
          }else{
            if(!(second==0 && t.value==0)){
              second += t.value;
              result.value=second;
            }else{
              second += t.value;
              result.value=0;
            }
          }
        },
        'input.cmd@click':function(e,t){
          if(t.value=='C'){
            if(Ext.isEmpty(op)){
              first='';
            }else{
              second='';
            }
            result.value=0;
          }else{
            cal();
          }
        },
        'input.sign@click':function(e,t){
          if(t.value=='.'){
            if(Ext.isEmpty(op) && first.toString().indexOf('.')==-1) {
              if(first==0){
                first = result.value + '.'
              }else{

                first += '.';
              }
              result.value = first;
            }else{
              if(second.toString().indexOf('.')==-1){
                second += '.';
                result.value = second;
              }
            }
          }else{
            if(Ext.isEmpty(op)){
              first *= -1;
              result.value = first;
            }else{
              second *= -1
              result.value = second
            }
          }
        },
        'input.op@click':function(e,t){
          if(Ext.isEmpty(op) || Ext.isEmpty(second)){
            op = t.value;
          }else{
            cal()
          }
        }

      })
    })


  </script>
</head>
<body>

<table cellpadding="1" border="0">
  <tr style="border: 1px solid #000;background: #2159c2;color: #fff;">
    <th colspan="4">计算机</th>
  </tr>
  <tr>
    <td colspan="4" align="center">
      <input id="result" readonly="true" style="text-align: right" type="text" value="0"/>
    </td>
  </tr>
  <tr>
    <td colspan="2"><input class="cmd" type="button" value="="/></td>
    <td colspan="2"><input class="cmd" type="button" value="C"/></td>
  </tr>
  <tr>
    <td><input class="number" type="button" value="7"/></td>
    <td><input class="number" type="button" value="8"/></td>
    <td><input class="number" type="button" value="9"/></td>
    <td><input class="op" type="button" value="+"/></td>
  </tr>
  <tr>
    <td><input class="number" type="button" value="4"/></td>
    <td><input class="number" type="button" value="5"/></td>
    <td><input class="number" type="button" value="6"/></td>
    <td><input class="op" type="button" value="-"/></td>
  </tr>
  <tr>
    <td><input class="number" type="button" value="1"/></td>
    <td><input class="number" type="button" value="2"/></td>
    <td><input class="number" type="button" value="3"/></td>
    <td><input class="op" type="button" value="*"/></td>
  </tr>
  <tr>
    <td><input class="number" type="button" value="0"/></td>
    <td><input class="sign" type="button" value="-/+"/></td>
    <td><input class="sign" type="button" value="."/></td>
    <td><input class="op" type="button" value="/"/></td>
  </tr>
</table>


</body>
</html>
