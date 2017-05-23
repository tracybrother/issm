<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>注册页面</title>
<!-- 引入bootstrap css -->
<link rel="stylesheet" href="/issm/assets/css/bootstrap.min.css">
<style>
body {
	background-size: cover;
	background-repeat: no repeat;
}

.col-center-block {
	float: none;
	display: block;
	margin-left: auto;
	margin-right: auto;
}

.error {
	color: red;
}
</style>
<script type="text/javascript">
	if (window != "shuaxin") {
		location.reload;
		window.name = "shuaxin";
	} else {
		window.name = "";
	}
</script>
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header">
					<h1>
						注册 <small></small>
					</h1>
				</div>
			</div>
		</div>
		<br> <br>
		<div class="row clearfix">
			<div class="col-md-4 column"></div>
			<div class="col-md-4 column">
				<form class="cmxform" id="commentForm" modelAttribute="user" method="post"
					action="${pageContext.request.contextPath}/user/insertuser">
					<fieldset>
						<div class="form-group">
							<label for="userName">账号名</label><input type="text" id="username" name="username"
								placeholder="请在这里输入账户名" class="form-control" minlength="5" required onblur="checkIsExist();"
								onfocus="clearCss();" />
							<site:required />
							<span id="showResult"></span>
						</div>
						<div class="form-group">
							<label for="password">密码</label><input type="password" class="form-control" id="password"
								placeholder="请在这里输入密码" name="password" minlength="8" required />
						</div>
						<div class="form-group">
							<label for="confirm_password">确认密码</label><input type="password" class="form-control"
								id="confirm_password" placeholder="请确认密码" name="confirm_password" minlength="8" required
								equalTo="#password" />
						</div>
						<div class="form-group">
							<label for="userNickname">昵称</label><input type="text" name="usercnname" class="form-control"
								id="" usercnname"" placeholder="请输入昵称" required minlength="1" />
						</div>
						<div class="form-group">
							<label for="age">电话</label><input type="text" class="form-control" name="userphone"
								id="userphone" required minlength="1" placeholder="请输入电话号码" />
						</div>
						<div class="form-group">
							<label for="age">邮箱</label><input type="text" class="form-control" name="useremail"
								id="useremail" required minlength="1" placeholder="请输入邮箱" />
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">所在大区</label> <select class="form-control m-b"
								name="userarea">
								<option value="0">西南区</option>
								<option value="1">西北区</option>
							</select>
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">所在部门</label> <select class="form-control m-b"
								name="userdept">
								<option value="0">技术部</option>
								<option value="1">业务部</option>
							</select>
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">职位类型</label> <select class="form-control m-b"
								name="usertype">
								<option value="0">领导</option>
								<option value="1">员工</option>
							</select>
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">性别</label> <select class="form-control m-b" name="usersex">
								<option value="0">男</option>
								<option value="1">女</option>
							</select>
						</div>
						<br>
						<button class="btn btn-lg btn-primary btn-block" type="submit">注册</button>
					</fieldset>
				</form>
			</div>
			<div class="col-md-4 column"></div>
		</div>
		<br> <br> <br> <br> <br> <br>
		<div class="row clearfix">
			<div class="col-md-4 column">
				<dl>
					<dt>Description lists</dt>
					<dd>A description list is perfect for defining terms.</dd>
					<dt>Euismod</dt>
					<dd>Vestibulum id ligula porta felis euismod semper eget lacinia odio sem nec elit.</dd>
					<dd>Donec id elit non mi porta gravida at eget metus.</dd>
				</dl>
			</div>
			<div class="col-md-4 column">
				<address>
					<strong>tracybrother, Inc.</strong><br /> 795 Folsom Ave, Suite 600<br /> San Francisco, CA 94107<br />
					<abbr title="Phone">P:</abbr> (123) 456-7890
				</address>
			</div>
			<div class="col-md-4 column"></div>
		</div>
	</div>
</body>
</html>