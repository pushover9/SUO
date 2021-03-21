<%@ page import="java.sql.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../header/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<section class="page-section">
<div class="container">
    
    <div class="mt-3">
        <table id="rank_table" class="table table-dark">
            <thead>
                <tr>
                    <th class="text-center">등수</th>
                    <th class="text-center">소환사명</th>
                    <th class="text-center">티어</th>
                    <th class="text-center">lp</th>
                    <th class="text-center">승률</th>
                </tr>
            </thead>
            <tbody>

         <c:forEach var="challenger" items="${rank }" varStatus="status">
            <tr>
                <td align="center">${status.count }등</td>
                <td align="center"><a href="/Main?title=${challenger.summonerName}">${challenger.summonerName }</a></td>
                <td align="center">Challenger</td>
                <td align="center">${challenger.leaguePoints }</td>
                <td align="center"><fmt:formatNumber value="${challenger.wins/(challenger.wins+challenger.losses)*100}" pattern=".0"/>%</td>
            </tr>
         </c:forEach>


            </tbody>
        </table>

    </div>

    <div class="item item3"></div>

</div>
</section>



<jsp:include page="../footer/footer.jsp"/>