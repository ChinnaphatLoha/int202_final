<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 12/16/2023
  Time: 3:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/output.css">
    <link href="https://cdn.jsdelivr.net/npm/daisyui@4.4.20/dist/full.min.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<section class="p-5">
    <div class="text-sm breadcrumbs">
        <ul>
            <li><a onclick="loadContent('057/office')">Office List</a></li>
            <li>Employee Office #${requestScope.officeId}</li>
        </ul>
    </div>
    <div class="overflow-x-auto">
        <table class="table table-md">
            <!-- head -->
            <thead>
            <tr class="bg-base-200">
                <th></th>
                <th>id</th>
                <th>Name</th>
                <th>email</th>
                <th>delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.officeEmployee}" var="employee" varStatus="vs">
                <tr>
                    <th>${vs.index}</th>
                    <td>${employee.id}</td>
                    <td>${employee.firstName} ${employee.lastName}</td>
                    <td>${employee.email}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/057/employee" method="post">
                            <input type="hidden" name="employeeId" value="${employee.id}">
                            <input type="hidden" name="officeId" value="${requestScope.officeId}">
                            <button class="btn btn-outline btn-error btn-sm">delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>
</body>
</html>
