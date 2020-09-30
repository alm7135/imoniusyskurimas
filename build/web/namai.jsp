<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title> My first JSP   </title>
                <style>
                    TABLE, TD, TH, TR {
                        border-collapse:collapse;
                        border-width: 1px;
                        border-style: solid;
                        border-spacing: 5px;
                        padding: 2px 5px;
                    }
                </style>
                
	</head>	
	<body align="center">		
            <form action="/jlab1" method="POST">			
			Vardas
			<input type="text" name="name"size="20px">
                        Komentaras
			<input type="text" name="message"size="20px">
			<input type="submit" value="Siųsti">					
		</form>	
            <hr>
            <div>
                <c:if test="${not empty msg}">
                    <jsp:getProperty name="msg" property="name"/>:
                    <jsp:getProperty name="msg" property="msg"/>
                </c:if>
            </div>
            <hr>
            <div align="center">
                <table id="messageTable">
                <tr >
                    <th bgcolor=>ID</th>
                    <th bgcolor=>Vardas</th>
                    <th bgcolor=>Komentaras</th>
                    <th bgcolor=>Data</th>
                </tr>
                <c:if test="${not empty msg_list}">
                    <c:forEach var="m" begin="0" items="${msg_list}">
                    <tr>
                        <td>${m.id}&nbsp;&nbsp;</td> 
                        <td>${m.name}&nbsp;&nbsp;</td> 
                        <td>${m.message}&nbsp;&nbsp;</td> 
                        <td>${m.time}&nbsp;&nbsp;</td> 
                    </tr> 

                    </c:forEach>
                </c:if>
                </table>
            </div>
	</body>	
</html>
