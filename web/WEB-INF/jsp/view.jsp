<%@ page import="com.urise.webapp.model.SectionType" %>
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
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<com.urise.webapp.model.ContactType, java.lang.String>"/>
            ${contactEntry.getKey().toHtml(contactEntry.getValue())}
        <br/>
        </c:forEach>
    <p>
    <table cellpadding="2">
        <c:set var="sections" value="${resume.getSections()}"/>
        <tr>
            <td colspan="2">
                <h2>${SectionType.OBJECTIVE.title}</h2>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <h4>${sections.get(SectionType.OBJECTIVE)}</h4>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <h2>${SectionType.PERSONAL.title}</h2>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                ${sections.get(SectionType.PERSONAL)}
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <h2>${SectionType.ACHIEVEMENT.title}</h2>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <ul>
                    <c:forEach items="${sections.get(SectionType.ACHIEVEMENT).getItems()}"
                               var="itemsAchievement">
                        <li>${itemsAchievement}</li>
                    </c:forEach>
                </ul>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <h2>${SectionType.QUALIFICATIONS.title}</h2>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <ul>
                    <c:forEach items="${sections.get(SectionType.QUALIFICATIONS).getItems()}"
                               var="itemsQualifications">
                        <li>${itemsQualifications}</li>
                    </c:forEach>
                </ul>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <h2>${SectionType.EXPERIENCE.title}</h2>
            </td>
        </tr>
        <c:forEach items="${sections.get(SectionType.EXPERIENCE).getOrganizations()}"
                   var="itemOrganizationEducation">
            <tr>
                <td colspan="2">
                    <h3>
                        <a href=${itemOrganizationEducation.getHomePage().getUrl()}>${itemOrganizationEducation.getHomePage().getName()}</a>
                    </h3>
                </td>
            </tr>
            <c:forEach items="${itemOrganizationEducation.getPositions()}" var="itemPosition">
                <tr>
                    <td width="15%" style="vertical-align: top">${itemPosition.getStartDatetoHtml()}
                        - ${itemPosition.getEndDatetoHtml()}</td>
                    <td><b>${itemPosition.getTitle()}</b>.<br>${itemPosition.getDescription()}</td>
                </tr>
            </c:forEach>
        </c:forEach>
        <tr>
            <td colspan="2">
                <h2>${SectionType.EDUCATION.title}</h2>
            </td>
        </tr>
        <c:forEach items="${sections.get(SectionType.EDUCATION).getOrganizations()}" var="itemOrganizationEducation">
            <tr>
                <td colspan="2">
                    <h3>
                        <a href=${itemOrganizationEducation.getHomePage().getUrl()}>${itemOrganizationEducation.getHomePage().getName()}</a>
                    </h3>
                </td>
            </tr>
            <c:forEach items="${itemOrganizationEducation.getPositions()}" var="itemPosition">
                <tr>
                    <td width="15%" style="vertical-align: top">${itemPosition.getStartDatetoHtml()}
                        - ${itemPosition.getEndDatetoHtml()}</td>
                    <td><b>${itemPosition.getTitle()}</b></td>
                </tr>
            </c:forEach>
        </c:forEach>
    </table>
    <p>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
