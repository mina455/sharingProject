<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/demo/common/css/import.css" />
        <title>등록 페이지</title>
    </head>

    <body>
    <form:form commandName="memberVo" action="${add}" method="post" enctype="multipart/form-data">
        <div class="btn_type_03">
           <span class="btn btnC_03 btnP_04 mr10" onclick="${list}">
              <input type="button" value="취소" />
           </span> <span class="btn btnC_05 btnP_04">
           <input type="submit"  value="등록하기" onclick="return check()" /></span>
        </div>
        <div class="tbl_type_01">
            <table style="border: solid 1px;">
               <colgroup>
                  <col style="width: 120px;" />
                  <col />
               </colgroup>
               <tbody>
                  <tr>
                     <th scope="row">작성자명</th>
                     <td>
                        <form:input cssClass="txt w300" path="username" theme="simple" />
                     </td>
                  </tr>
        
                  <tr>
                     <th scope="row">이미지 등록</th>
                     <td><input Class="txt" type="file" name="img"  accept="image/*"/></td>
                  </tr>

               </tbody>
            </table>
        </div>
    </form:form>

    </body>
</html>
