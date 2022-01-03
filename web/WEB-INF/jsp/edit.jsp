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
        <c:if test="${!sections.get(SectionType.EXPERIENCE).getOrganizations().isEmpty()}">
            <tr>
                <td colspan="2">
                    <h2>${SectionType.EXPERIENCE.title}</h2>
                </td>
            </tr>
            <c:forEach items="${sections.get(SectionType.EXPERIENCE).getOrganizations()}"
                       var="itemOrganizationExperience">
                <form method="post" action="organization" enctype="application/x-www-form-urlencoded">
                    <input type="hidden" name="uuid" value="${resume.uuid}">
                    <input type="hidden" name="sectionType" value="editExperience">
                    <input type="hidden" name="idOrganization" value=${itemOrganizationExperience.getId()}>
                    <input type="hidden" name="idPosition" value=${itemOrganizationExperience.getPositions().get(0).getId()}>
                    <tr>
                        <td colspan="2">
                            <dl>
                                <dt><label for="organizationSectionExperienceName">Название организации</label></dt>
                                <dd><input type="text" id="organizationSectionExperienceName"
                                           name="nameOrganization"
                                           value="${itemOrganizationExperience.getHomePage().getName()}"></dd>
                            </dl>
                            <dl>
                                <dt><label for="organizationSectionExperienceURL">Домашняя страница</label></dt>
                                <dd><input type="text" id="organizationSectionExperienceURL" name="homePage"
                                           value="${itemOrganizationExperience.getHomePage().getUrl()}"></dd>
                            </dl>
                            <c:forEach items="${itemOrganizationExperience.getPositions()}" var="itemPosition">
                            <dl>
                                <dt><label for="date-timeExperience">Период работы</label></dt>
                                <dd>
                                    <input type="datetime-local" id="date-timeExperience" name="dateStart"
                                           value="${itemPosition.getStartLocalDateToLocalDateTime()}"> -
                                    <input type="datetime-local" name="dateEnd"
                                           value="${itemPosition.getEndLocalDateToLocalDateTime()}">
                                </dd>
                            </dl>
                            <dl>
                                <dt><label for="positionExperience">Позиция</label></dt>
                                <dd>
                                    <input type="text" id="positionExperience" name="position" size="90%"
                                           value="${itemPosition.getTitle()}">.<br>
                                </dd>
                            </dl>
                            <dl>
                                <dt>
                                    <label for="descriptionExperience">Ключевые обязанности</label>
                                </dt>
                                <dd>
                                    <br>
                                    <textarea type="text" name="description" id="descriptionExperience"
                                              style="width: 900px; height: 100px; resize: none">"${itemPosition.getDescription()}"
                                            </textarea>
                                </dd>
                            </dl>
                        </td>
                    </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="2">
                            <center>
                                <button type="submit">Сохранить изменения по организации и продолжить</button>
                            </center>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <br>
                        </td>
                    </tr>
                </form>
            </c:forEach>
        </c:if>
        <c:if test="${!sections.get(SectionType.EDUCATION).getOrganizations().isEmpty()}">
            <tr>
                <td colspan="2">
                    <h2>${SectionType.EDUCATION.title}</h2>
                </td>
            </tr>
            <c:forEach items="${sections.get(SectionType.EDUCATION).getOrganizations()}"
                       var="itemOrganizationEducation">
                <c:forEach items="${itemOrganizationEducation.getPositions()}" var="itemPosition">
                    <form method="post" action="organization" enctype="application/x-www-form-urlencoded">
                        <input type="hidden" name="sectionType" value="editEducation">
                        <input type="hidden" name="uuid" value=${resume.uuid}>
                        <input type="hidden" name="idOrganization" value=${itemOrganizationEducation.getId()}>
                        <input type="hidden" name="idPosition" value=${itemPosition.getId()}>
                        <tr>
                            <td colspan="2">
                                <dl>
                                    <dt><label for="organizationSectionEducation">Название организации</label></dt>
                                    <dd><input type="text" id="organizationSectionEducation"
                                               name="nameOrganization"
                                               value="${itemOrganizationEducation.getHomePage().getName()}"></dd>
                                </dl>
                                <dl>
                                    <dt><label for="organizationSectionEducationURL">Домашняя страница</label></dt>
                                    <dd><input type="text" id="organizationSectionEducationURL" name="homePage"
                                               value="${itemOrganizationEducation.getHomePage().getUrl()}"></dd>
                                </dl>
                                <dl>
                                    <dt><label for="date-time">Период работы</label></dt>
                                    <dd>
                                        <input type="datetime-local" id="date-time" name="dateStart"
                                               value="${itemPosition.getStartLocalDateToLocalDateTime()}"> -
                                        <input type="datetime-local" name="dateEnd"
                                               value="${itemPosition.getEndLocalDateToLocalDateTime()}">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt><label for="position">Позиция</label></dt>
                                    <dd>
                                        <input type="text" id="position" name="position" size="90%"
                                               value="${itemPosition.getTitle()}">.<br>
                                    </dd>
                                </dl>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <center>
                                    <button type="submit">Сохранить изменения по организации и продолжить</button>
                                </center>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <br>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </c:forEach>
        </c:if>
    </table>
    <table cellpadding="2">
        <form method="post" action="organization" enctype="application/x-www-form-urlencoded">
            <input type="hidden" name="sectionType" value="newExperience">
            <input type="hidden" name="uuid" value="${resume.uuid}">
            <tr>
                <td colspan="2">
                    <hr/>
                    <h1 align="center">Добавить новое место работы</h1>
                </td>
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
                        <dt><label for="positionExperienceNew">Должность</label></dt>
                        <dd><input type="text" id="positionExperienceNew" name="position"></dd>
                    </dl>
                    <dl>
                        <dt><label for="responsibilityExperience">Основные обязанности</label></dt>
                        <dd><textarea id="responsibilityExperience" name="responsibility"
                                      style="width: 900px; height: 50px; resize: none"></textarea></dd>
                    </dl>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <center>
                        <button type="submit">Добавить организацию</button>
                        <button type="reset" onclick="window.history.back()">Отменить</button>
                    </center>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <hr/>
                </td>
            </tr>
        </form>
        <form method="post" action="organization" enctype="application/x-www-form-urlencoded">
            <input type="hidden" name="sectionType" value="newEducation">
            <input type="hidden" name="uuid" value="${resume.uuid}">

            <tr>
                <td colspan="2">
                    <h1 align="center">Добавить образовательное уереждение</h1></td>
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
                    <center>
                        <button type="submit">Добавить образовательное учереждение</button>
                    </center>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <hr/>
                </td>
            </tr>
        </form>
    </table>
    <form method="get" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <center>
            <button type="submit"><a href="resume?uuid=${resume.uuid}&action=view">Завершить редактирование</a></button>
        </center>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
