<%@ page import="com.urise.webapp.model.ContactType" %>
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
    <h1 align="center">Редактирование контактов, позиции, личных качеств, достижений и квалификации</h1>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" required name="fullName" size=50 value="${resume.fullName}"></dd>
        </dl>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <p>
            <c:set var="sections" value="${resume.getSections()}"/>
        <dl>
            <dt><h3>${SectionType.OBJECTIVE.title}</h3></dt>
            <dd><input type="text" name="textSectionObjective" size=100%
                       value="${sections.get(SectionType.OBJECTIVE)}"></dd>
        </dl>
        <dl>
            <dt><h3>${SectionType.PERSONAL.title}</h3></dt>
            <dd><input type="text" name="textSectionPersonal" size=100%
                       value="${sections.get(SectionType.PERSONAL)}"></dd>
        </dl>
        <dl>
            <dt><h3>${SectionType.ACHIEVEMENT.title}</h3></dt>
            <dd><textarea id="listSectionAchievement"
                          name="listSectionAchievement" style="width: 900px; height: 250px; resize: none">${sections
                    .get(SectionType.ACHIEVEMENT).getItems().get(0)}</textarea>
            </dd>
        </dl>
        <dl>
            <dt><h3>${SectionType.QUALIFICATIONS.title}</h3></dt>
            <dd><textarea id="listSectionQualifications"
                          name="listSectionQualifications" style="width: 900px; height: 250px; resize: none">${sections
                    .get(SectionType.QUALIFICATIONS).getItems().get(0)}</textarea>
            </dd>
        </dl>
        <br>
        <center>
            <button type="submit">Сохранить и продолжить</button>
            <button type="reset" onclick="window.history.back()">Отменить</button>
        </center>
        <br>
    </form>
    <hr/>
    <h1 align="center">Редактирование разделов опыта работы и образования</h1>
    <table cellpadding="2">
        <tr>
            <td colspan="2">
                <h2>${SectionType.EXPERIENCE.title}</h2>
            </td>
        </tr>
    </table>
</section>
<jsp:include page="fragments/addexperiencesection.jsp"/>
<section>
    <table>
        <c:forEach items="${sections.get(SectionType.EXPERIENCE).getOrganizations()}"
                   var="itemOrganizationExperience">
            <tr>
                <td colspan="2">
                    <h3>
                        <a href=${itemOrganizationExperience.getHomePage().getUrl()}>${itemOrganizationExperience.getHomePage().getName()}</a>
                    </h3>
                </td>
            </tr>
            <c:forEach items="${itemOrganizationExperience.getPositions()}" var="itemPosition">
                <tr>
                    <td width="15%" style="vertical-align: top">${itemPosition.getStartDatetoHtml()}
                        - ${itemPosition.getEndDatetoHtml()}</td>
                    <td><b>${itemPosition.getTitle()}</b>.<br>${itemPosition.getDescription()}</td>
                </tr>
                <tr>
                    <td colspan="2">
                        <center>
                            <a href="organization?uuid=${resume.uuid}&idOrganization=${itemOrganizationExperience.getId()}&action=editExperienceOrganization">
                                <button>Изменить</button>
                            </a>
                        </center>
                    </td>
                </tr>
            </c:forEach>
        </c:forEach>
    </table>
</section>
<section>
    <table cellpadding="2">
        <tr>
            <td colspan="2">
                <h2>${SectionType.EDUCATION.title}</h2>
            </td>
        </tr>
    </table>
</section>
<jsp:include page="fragments/addeducationsection.jsp"/>
<section>
    <table cellpadding="2">
        <c:forEach items="${sections.get(SectionType.EDUCATION).getOrganizations()}"
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
                    <td><b>${itemPosition.getTitle()}</b></td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="2">
                    <center>
                        <a href="organization?uuid=${resume.uuid}&idOrganization=${itemOrganizationEducation.getId()}&action=editEducationOrganization">
                            <button>Изменить</button>
                        </a>
                    </center>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="2">
                <hr/>
                <br>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <form method="get" action="resume" enctype="application/x-www-form-urlencoded">
                    <input type="hidden" name="uuid" value="${resume.uuid}">
                    <center>
                        <button type="submit"><a href="resume?uuid=${resume.uuid}&action=view">Завершить
                            редактирование</a>
                        </button>
                    </center>
                </form>
            </td>
        </tr>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>