<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="dist/output.css" rel="stylesheet">
    <script>
        function loadContent(path) {
            fetch(path)
                .then(response => {
                    if (!response.ok) throw new Error('Network response was not ok');
                    return response.text();
                })
                .then(html => document.getElementById('content').innerHTML = html)
                .catch(error => alert('There has been a problem with your fetch operation: ' + error.message));
        }
    </script>
</head>
<body>
<div class="navbar bg-base-100">
    <div class="flex-1">
        <a class="btn btn-ghost text-xl">Company</a>
    </div>
    <div class="flex-none">
        <ul class="menu menu-horizontal px-1">
            <c:choose>
                <c:when test="${empty sessionScope.user}">
                    <li><a href="057/login">Login</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="057/login">Logout</a></li>
                </c:otherwise>
            </c:choose>

            <li>
                <details>
                    <summary>
                        Organization
                    </summary>
                    <ul class="p-2 bg-base-100 rounded-t-none">
                        <li>
                            <a onclick="loadContent('057/office')">Office</a>
                        </li>
                        <li>
                            <a onclick="loadContent('057/employee')">Employee</a>
                        </li>
                    </ul>
                </details>
            </li>
        </ul>
    </div>
</div>

<section id="content">
    <div class="hero min-h-screen" style="background-image: url(https://www.ikea.com/images/small-office-space-with-idasen-beige-sit-stand-desks-alefjae-9b5623c200848100a14b982fb9185ffd.jpg?f=l);">
        <div class="hero-overlay bg-opacity-80"></div>
        <div class="hero-content text-center">
            <div class="max-w-md">
                <h1 class="text-5xl font-bold">Company Organization</h1>
                <p class="py-6">Provident cupiditate voluptatem et in. Quaerat fugiat ut assumenda excepturi
                    exercitationem quasi. In deleniti eaque aut repudiandae et a id nisi.</p>
                <button class="btn btn-primary">Get Started</button>
            </div>
        </div>
    </div>
</section>
</body>
</html>