<%@ page import="com.urise.webapp.model.SectionType" %>
<%@ page import="com.urise.webapp.model.TextSection" %>
<%@ page import="com.urise.webapp.model.ListSection" %>
<%@ page import="com.urise.webapp.model.OrganizationSection" %>
<%@ page import="com.urise.webapp.util.DateUtil" %>
<%@ page import="com.urise.webapp.util.HtmlUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
        <c:if test="${contactEntry.value.toString().length() > 0}">
            ${contactEntry.getKey().toHtml(contactEntry.getValue())}
        <br/>
        </c:if>
        </c:forEach>
    <p>
    <hr>
    <table cellpadding="2">
        <c:forEach items="${resume.sections}" var="sectionEntry">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<com.urise.webapp.model.SectionType, com.urise.webapp.model.AbstractSection>"/>
            <c:set var="type" value="${sectionEntry.key}"/>
            <c:set var="section" value="${sectionEntry.value}"/>
            <jsp:useBean id="section" type="com.urise.webapp.model.AbstractSection"/>
            <tr>
                <td colspan="2"><h3><a name="type.name">${type.title}</a></h3></td>
            </tr>
            <tr>
                <c:if test="${type=='OBJECTIVE'}">
                    <td colspan="2">
                        <h2><%=((TextSection) section).getContent()%>
                        </h2></td>
                </c:if>
            </tr>
            <c:if test="${type!='OBJECTIVE'}">
                <c:choose>
                    <c:when test="${type=='PERSONAL'}">
                        <tr>
                            <td colspan="2">
                                <%=((TextSection) section).getContent()%>
                            </td>
                        </tr>
                    </c:when>
                    <c:when test="${type=='ACHIEVEMENT' || type=='QUALIFICATIONS'}">
                        <tr>
                            <td colspan="2">
                                <ul>
                                    <c:forEach var="item" items="<%=((ListSection) section).getItems()%>">
                                        <li>${item}</li>
                                    </c:forEach>
                                </ul>
                            </td>
                        </tr>
                    </c:when>
                    <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                        <c:forEach items="<%=((OrganizationSection) section).getOrganizations()%>" var="organization">
                            <tr>
                                <td colspan="2">
                                    <c:choose>
                                        <c:when test="${empty organization.homePage.url}">
                                            <h3>${organization.homePage.name}</h3>
                                        </c:when>
                                        <c:otherwise>
                                            <h3><a href="${organization.homePage.url}">${organization.homePage.name}</a>
                                            </h3>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            <c:forEach var="position" items="${organization.positions}">
                                <jsp:useBean id="position"
                                             type="com.urise.webapp.model.organization.Organization.Position"/>
                                <tr>
                                    <td colspan="2">
                                        <%=HtmlUtil.formatDates(position)%>
                                    </td>
                                    <td><b>${position.title}</b><br>${position.description}</td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                    </c:when>
                </c:choose>
            </c:if>
        </c:forEach>
    </table>
    <button onclick="window.history.back()">Ok</button>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
