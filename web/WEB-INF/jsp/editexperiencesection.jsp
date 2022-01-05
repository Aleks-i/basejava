<%@ page import="com.urise.webapp.model.ContactType" %>
<%@ page import="com.urise.webapp.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="organization" type="com.urise.webapp.model.organization.Organization" scope="request"/>
    <title>Организация ${organization.homePage.name}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <table cellpadding="2">
        <tr>
            <td colspan="2">
                <h3>
                    Редактирование <a href=${organization.homePage.url}>${organization.homePage.name}</a>
                </h3>
            </td>
        </tr>
        <c:forEach items="${organization.positions}"
                   var="position">
            <form method="post" action="organization" enctype="application/x-www-form-urlencoded">
                <input type="hidden" name="uuid" value="${organization.uuid}">
                <input type="hidden" name="idOrganization" value="${organization.id}">
                <input type="hidden" name="idPosition" value="${position.id}">
                <input type="hidden" name="action" value="editExperienceOrganization">
                <tr>
                    <td colspan="2">
                        <dl>
                            <dt><label for="organizationNameEdit">Название организации</label></dt>
                            <dd><input type="text" id="organizationNameEdit"
                                       name="nameOrganization"
                                       value="${organization.homePage.name}"></dd>
                        </dl>
                        <dl>
                            <dt><label for="organizationURLEdit">Домашняя страница</label></dt>
                            <dd><input type="text" id="organizationURLEdit" name="homePage"
                                       value="${organization.homePage.url}"></dd>
                        </dl>
                        <dl>
                            <dt><label for="dateStartEdit">Период работы</label></dt>
                            <dd>
                                <input type="datetime-local" id="dateStartEdit" name="dateStart"
                                       value="${position.startLocalDateToLocalDateTime}"> -
                                <input type="datetime-local" name="dateEnd"
                                       value="${position.endLocalDateToLocalDateTime}">
                            </dd>
                        </dl>
                        <dl>
                            <dt><label for="positionEdit">Позиция</label></dt>
                            <dd>
                                <input type="text" id="positionEdit" name="title" size="90%"
                                       value="${position.title}">.<br>
                            </dd>
                        </dl>
                        <dl>
                            <dt>
                                <label for="descriptionExperience">Ключевые обязанности</label>
                            </dt>
                            <dd>
                                <br>
                                <textarea type="text" name="description" id="descriptionExperienceEdit"
                                          style="width: 900px; height: 100px; resize: none">${position.description}
                                </textarea>
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
    </table>
</section>
<section>
    <h1 align="center">Добавить новую позицию</h1>
    <table cellpadding="2">
        <form method="post" action="organization" enctype="application/x-www-form-urlencoded">
            <input type="hidden" name="uuid" value="${organization.uuid}">
            <input type="hidden" name="idOrganization" value="${organization.id}">
            <input type="hidden" name="action" value="newExperiencePosition">
            <tr>
                <td colspan="2">
                    <dl>
                        <dt><label for="dateStartNew">Период работы</label></dt>
                        <dd>
                            <input type="datetime-local" id="dateStartNew" name="dateStart"
                                   value=""> -
                            <input type="datetime-local" name="dateEnd"
                                   value="">
                        </dd>
                    </dl>
                    <dl>
                        <dt><label for="positionNew">Позиция</label></dt>
                        <dd>
                            <input type="text" id="positionNew" name="title" size="90%"
                                   value="">.<br>
                        </dd>
                    </dl>
                    <dl>
                        <dt>
                            <label for="descriptionExperience">Ключевые обязанности</label>
                        </dt>
                        <dd>
                            <br>
                            <textarea type="text" name="description" id="descriptionExperience"
                                      style="width: 900px; height: 100px; resize: none">
                                </textarea>
                        </dd>
                    </dl>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <center>
                        <button type="submit">Добавить позицию и продолжить</button>
                    </center>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <br>
                </td>
            </tr>
        </form>
    </table>
</section>
<section>
    <hr/>
    <br>
    <form method="get" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${organization.uuid}">
        <center>
            <button type="submit"><a href="resume?uuid=${organization.uuid}&action=edit">Завершить редактирование</a></button>
        </center>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>