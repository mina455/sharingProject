<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html:html>
    <html:head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>메인 페이지</title>
    </html:head>
    <html:body>
    
    <a href="add">
        <button class="btn btn-warning" type="button">add</button>
    </a>

    <table>
        <c:forEach items="${list}" var="item" varStatus="status">
            <c:set var="colNum" value="3"/>
            <c:if test="${status.index % colNum == 0}">
                <tr>
            </c:if>
    
                <td>
                    <c:out value="${status.index + 1}"/>
                </td>
                <td>
                    <c:out value="${item.username}"/>
                    <img src="/member/thumbnail?username=${item.username}" width="200px" height="200px"/>
                </td>
    
            <c:if test="${status.last and status.index % colNum == 0}">
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </c:if>
    
            <c:if test="${status.last and status.index % colNum == 1}">
                <td></td>
                <td></td>
            </c:if>
    
            <c:if test="${status.index % colNum == colNum - 1}">
                </tr>
            </c:if>
        </c:forEach>
   </table>

   <script type="text/javascript">
     $(document).ready(function () {

     });
   </script>
   </html:body>
</html:html>
