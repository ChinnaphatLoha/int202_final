<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: FIRST-ACER-Desktop
  Date: 12/15/2023
  Time: 3:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/output.css">
</head>
<body>
<div class="hero min-h-screen bg-base-200">
    <div class="hero-content flex-col w-full max-w-lg">
        <div class="text-center lg:text-left">
            <h1 class="text-5xl font-bold">Login</h1>
        </div>
        <div class="card shrink-0 w-full shadow-2xl bg-base-100">
            <form class="card-body" method="post" action="${pageContext.request.contextPath}/057/login">
                <c:if test="${requestScope.loginError != null}">
                    <div role="alert" class="alert alert-error">
                        <svg xmlns="http://www.w3.org/2000/svg" class="stroke-current shrink-0 h-6 w-6" fill="none"
                             viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                  d="M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z"/>
                        </svg>
                        <span>${requestScope.loginError}</span>
                    </div>
                </c:if>
                <div class="form-control">
                    <label class="label" for="email">
                        <span class="label-text" id="email">Email</span>
                    </label>
                    <input type="email" placeholder="email" class="input input-bordered" name="email" required>
                </div>
                <div class="form-control">
                    <label class="label" for="password">
                        <span class="label-text" id="password">Password</span>
                    </label>
                    <input type="password" placeholder="password" class="input input-bordered" name="password"
                           required/>
                </div>
                <div class="form-control mt-6">
                    <button class="btn btn-primary">Login</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
