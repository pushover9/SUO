<%@ page import="java.sql.*"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../header/header.jsp"%>


<section class="page-section">
	<div class="container">
		<div class="col-xs-4 text-center" style="margin: 0 auto;">

			<div class="col-xs-4 text-center" style="margin: 0 auto;">
				<img alt="아이콘" src="${ profileImgURL}">
			</div>
			<div class="col-xs-1 text-center"></div>
			<div class="col-xs-4 text-center" id="idLevel">
				<h3>${summoner.name}</h3>
				<p>Lv. ${summoner.getSummonerLevel()}</p>
			</div>
		</div>
		<div class="row">
			<c:forEach var="leagueInfo" items="${leagueInfo}" varStatus="s">
				<div class="col-xs-4" style="margin: 0 auto;">
					<c:choose>
						<c:when test="${leagueInfo.getQueueType() == 'RANKED_FLEX_SR'}">
							<h2>자유 랭크</h2>
						</c:when>
						<c:when test="${leagueInfo.getQueueType()== 'RANKED_SOLO_5x5'}">
							<h2>솔로 랭크</h2>
						</c:when>
					</c:choose>
					<div class="card">
						<div class="card-body text-center">
							<h5 class="card-title">
								<img alt="랭크 엠블램"
									src="../../resources/image/emblems/Emblem_${leagueInfo.getTier()}.png"
									width="250px" height="250px"> <br />
								${leagueName[s.index]}
							</h5>
							<p class="card-text">
							<h6>승리/패배</h6>
							<p style="color: #007bff; font-size: 20px; display: inline">${leagueInfo.getWins()}</p>
							<p style="font-size: 20px; display: inline">/</p>
							<p style="color: #dc3545; font-size: 20px; display: inline">${leagueInfo.getLosses()}</p>
							<fmt:formatNumber var="percent"
								value="${leagueInfo.getWins()/(leagueInfo.getWins()+leagueInfo.getLosses())}"
								pattern="0.00%" />
							<span style="color: #6c757d; font-size: 18px;">
								(${percent}) </span>
							<p>${leagueInfo.getTier()}${leagueInfo.getRank()}</p>

						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>




</section>


<jsp:include page="../footer/footer.jsp" />