<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main</title>

    <!-- CSS  -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
<nav>
    <div class="nav-wrapper">
        <a href="#!" class="brand-logo"><i class="material-icons">store</i></a>
        <ul class="right hide-on-med-and-down">
            <li><a href="/jsp/basket.jsp">Basket</a></li>
            <li><a class="btn btn-success" href="/controller?command=logout">Logout</a></li>
        </ul>
    </div>
</nav>

<h1> Hello, ${user} </h1>
<hr>
<table class="item-table">
    <caption><h4>All Products</h4></caption>
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Add product</th>
    </tr>
    <c:forEach items="${allProducts}" var="product">
        <tr>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td><a class="btn btn-success" href="/controller?command=add_product&id=${product.id}">+</a></td>
        </tr>
    </c:forEach>
</table>


<!--  Scripts-->
<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js"></script>
</body>
</html>
