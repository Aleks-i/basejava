<%@page contentType="text/html" pageEncoding="UTF-8" %>
<section>
    <table cellpadding="2">
        <form method="post" action="organization" enctype="application/x-www-form-urlencoded">
            <input type="hidden" name="action" value="newEducationOrganization">
            <input type="hidden" name="uuid" value="${resume.uuid}">
            <tr>
                <td colspan="2">
                    <h1 align="center">Добавить образовательное учреждение</h1>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <dl>
                        <dt><label for="nameOrganization">Название образовательного учереждения</label></dt>
                        <dd><input type="text" id="nameOrganization" name="nameOrganization"></dd>
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
                        <dd><input type="text" id="titleEducation" name="title"></dd>
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
        </form>
    </table>
</section>