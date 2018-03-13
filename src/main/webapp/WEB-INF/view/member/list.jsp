<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/common/css/common.css">
<title>메인 페이지</title>
</head>
<body>

    <header>
        <img src="/common/img/logo.png" height="50px" />
    </header>
    
    <nav>
        <ul>
            <li><a href="list">Main</a></li> |
            <li><a href="add">add</a></li> |
            <li><a href="">Noitce</a></li>
        </ul>
    </nav>

    <div id="content">
<!--         <aside>
            <ul>
                <li>javascript</li>
                <li>css</li>
                <li>html</li>
            </ul>
        </aside> -->

        <section>
            <c:forEach items="${list}" var="item" varStatus="status">
                <img title="${item.username}" src="/member/thumbnail?seq=${item.seq}" 
                    width="400px" height="300px" style="border-radius: 7px;"/>
            </c:forEach>
        </section>
    </div>

    <footer>
        sharing page는 컨텐츠 공유 페이지로 누구나 이용가능합니다.
        <img src="/common/img/logo2.png" height="50px" />
        <br/> 본 사이트의 저작물은 출처를 밝히고 자유롭게 사용가능합니다.
        <br/> @localhost:8181/member/list 2018
    </footer>
    
    <script type="text/javascript">
        $(document).ready(function() {

        });
    </script>
</body>
</html>
