<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="divhead">
	<table cellspacing="0" class="headtable">
		<tr>
			<td>
				<a href="${pageContext.request.contextPath }/jsp/index.jsp">
					<img src="${pageContext.request.contextPath}/jsp/client/images/logo.png" width="200" height="60" border="0" /> 
				</a>
			</td>
			<td style="text-align:right">
				<img src="${pageContext.request.contextPath}/jsp/client/images/cart.gif" width="26" height="23" style="margin-bottom:-4px" />&nbsp;<a href="${pageContext.request.contextPath}/client/cart.jsp">购物车</a> 
				| <a href="#">帮助中心</a> 
				| <a href="${pageContext.request.contextPath}/myAccount">我的帐户</a>
				| <a href="${pageContext.request.contextPath}/jsp/client/register.jsp">新用户注册</a>
			</td>
		</tr>
	</table>
</div>