<%@ page import="com.example.int202javassrpreexam.constants.Constants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/output.css">
    <link href="https://cdn.jsdelivr.net/npm/daisyui@4.4.20/dist/full.min.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<c:set var="defaultPath" value="<%= Constants.DEFAULT_PATH %>" />
<c:set var="servletPath" value="<%= Constants.SERVLET_PATH %>" />
<section class="p-5">
    <div class="text-sm breadcrumbs">
        <ul>
            <li><a onclick="loadContent('${defaultPath}office')">Office List</a></li>
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
                        <c:set var="deletePath" value="${defaultPath}employee?deleting=y&employeeId=${employee.id}&officeId=${requestScope.officeId}"/>
                        <button class="btn btn-outline btn-error btn-sm" onclick="loadContent('${deletePath}')">delete</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>
</body>
</html>
