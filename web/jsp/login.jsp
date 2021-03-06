<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>

    <!-- CSS  -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css" rel="stylesheet">
</head>
<body>

<div class="row">
    <form class="col s12" action="/controller" method="post">
        <input type="hidden" name="command" value="login" />
        <div class="input-field col s12">
            <input id="login" type="text" class="validate" name="loginParam">
            <label for="login">Login</label>
        </div>
        <div class="input-field col s12">
            <input id="password" type="password" class="validate" name="passwordParam">
            <label for="password">Password</label>
        </div>
        <br/>
        <input type="submit" value="Login" class="btn btn-success">
        <br/>
    </form>
    <form method="get">
        <a href="jsp/registration.jsp" class="btn btn-success">Registration</a>
    </form>
${errorLoginPassMessage}
</div>

<!--  Scripts-->
<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js"></script>
</body>
</html>
