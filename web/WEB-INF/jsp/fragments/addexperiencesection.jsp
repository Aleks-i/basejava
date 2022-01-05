<%@page contentType="text/html" pageEncoding="UTF-8" %>
<section>
    <table cellpadding="2">
        <form method="post" action="organization" enctype="application/x-www-form-urlencoded">
            <input type="hidden" name="action" value="newExperienceOrganization">
            <input type="hidden" name="uuid" value="${resume.uuid}">
            <tr>
                <td colspan="2">
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
                        <dt><label for="titleExperienceNew">Должность</label></dt>
                        <dd><input type="text" id="titleExperienceNew" name="title"></dd>
                    </dl>
                    <dl>
                        <dt><label for="descriptionExperience">Основные обязанности</label></dt>
                        <dd><textarea id="descriptionExperience" name="description"
                                      style="width: 900px; height: 50px; resize: none"></textarea></dd>
                    </dl>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <center>
                        <button type="submit">Добавить организацию</button>
                    </center>
                </td>
            </tr>
        </form>
    </table>
</section>