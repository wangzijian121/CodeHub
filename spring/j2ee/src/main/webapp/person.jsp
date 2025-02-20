<%@ page import="com.example.j2ee.bean.Person" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>

<h1>Person</h1>
<%
    // 从 Session 中获取 Person 对象
    Person person = (Person) session.getAttribute("person");
    if (person != null) {
%>
<p>Name: <%= person.getName() %>
</p>
<p>Age: <%= person.getAge() %>
</p>
<%
} else {
%>
<p>No person found in session.</p>
<%
    }
%>
</body>
</html>