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
        <table cellpadding="2">
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
            </c:forEach>
        </table>
        <button type="submit">Сохранить</button>
        <button type="reset" onclick="window.history.back()">Отменить</button>
    </form>
    <form method="post" action="organization" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="sectionType" value="experience">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <tr>
            <td colspan="2">
                <hr/>
                <h3>Добавить новое место работы</h3></td>
        </tr>
        <tr>
            <td colspan="2">
                <dl>
                    <dt><label for="nameOrganization">Название организации</label></dt>
                    <dd><input type="text" id="nameOrganization" name="nameOrganization"></dd>
                </dl>
                <dl>
                    <dt><label for="homePageExperience">Сайт</label></dt>
                    <dd><input type="text" id="homePageExperience" name="homePage"></dd>
                </dl>
                <dl>
                    <dt><label for="dateStartExperience">Начало работы</label></dt>
                    <dd><input type="datetime-local" id="dateStartExperience" name="dateStart"></dd>
                </dl>
                <dl>
                    <dt><label for="dateStartExperience">Окончание работы</label></dt>
                    <dd><input type="datetime-local" id="dateEndExperience" name="dateEnd"></dd>
                </dl>
                <dl>
                    <dt><label for="dateStartExperience">Должность</label></dt>
                    <dd><input type="text" id="positionExperience" name="position"></dd>
                </dl>
                <dl>
                    <dt><label for="dateStartExperience">Основные обязанности</label></dt>
                    <dd><textarea id="responsibilityExperience" name="responsibility"
                                  style="width: 900px; height: 50px; resize: none"></textarea></dd>
                </dl>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="submit">Добавить организацию</button>
                <button type="reset" onclick="window.history.back()">Отменить</button>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <hr/>
            </td>
        </tr>
    </form>
    <form method="post" action="organization" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="sectionType" value="education">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <tr>
            <td colspan="2">
                <h3>Добавить образовательное уереждение</h3></td>
        </tr>
        <tr>
            <td colspan="2">
                <dl>
                    <dt><label for="nameOrganization">Название образовательного учереждения</label></dt>
                    <dd><input type="text" id="nameOrganizationEducation" name="nameOrganization"></dd>
                </dl>
                <dl>
                    <dt><label for="homePageEducation">Сайт</label></dt>
                    <dd><input type="text" id="homePageEducation" name="homePage"></dd>
                </dl>
                <dl>
                    <dt><label for="dateStartEducation">Начало обучения</label></dt>
                    <dd><input type="datetime-local" id="dateStartEducation" name="dateStart"></dd>
                </dl>
                <dl>
                    <dt><label for="dateStartEducation">Окончание обучения</label></dt>
                    <dd><input type="datetime-local" id="dateEndEducation" name="dateEnd"></dd>
                </dl>
                <dl>
                    <dt><label for="dateStartEducation">Специальность</label></dt>
                    <dd><input type="text" id="positionEducation" name="position"></dd>
                </dl>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="submit">Добавить образовательное учереждение</button>
                <button type="reset" onclick="window.history.back()">Отменить</button>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <hr/>
            </td>
        </tr>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
