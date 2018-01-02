<%@include file="../path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>demo</title>
</head>
<body>
	<h2>@ModelAttribute Demonstration</h2>
	<form action="${path}demo/submitBean" method="post">
	 <input type="text" name="key" value="${demo.key}"/>
	 <input type="text" name="url" value="${demo.url }"/>
	 <input type="submit" title="Submit"/>
	</form>
</body>
</html>