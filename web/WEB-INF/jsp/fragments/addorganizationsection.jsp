<%@page contentType="text/html" pageEncoding="UTF-8" %>
<section>
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