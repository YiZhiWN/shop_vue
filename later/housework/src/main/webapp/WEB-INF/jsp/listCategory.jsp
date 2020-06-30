<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   
<div align="center">
 
</div>
 
<div style="width:500px;margin:20px auto;text-align: center">
    <table align='center' border='1' cellspacing='0'>
        <tr>
            <td>name</td>
            <td>电话</td>
            <td>订购的服务</td>
            <td>价格</td>
            <td>地址</td>
            <td>住址</td>
        </tr>
        <c:forEach items="${page.list}" var="c" varStatus="st">
            <tr>
                <td>${c.name}</td>
                <td>${c.mobile}</td>
                <td>${c.sid.name}</td>
                <td>${c.sid.price}</td>
                <td>${c.address}</td>
                <td>${c.area}</td>
            </tr>
        </c:forEach>
         
    </table>
    <br>
    <div>
                <a href="?start=1">[首  页]</a>
            <a href="?start=${page.pageNum-1}">[上一页]</a>
            <a href="?start=${page.pageNum+1}">[下一页]</a>
            <a href="?start=${page.pages}">[末  页]</a>
    </div>
    <br>
</div>