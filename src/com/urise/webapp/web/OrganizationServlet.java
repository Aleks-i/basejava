package com.urise.webapp.web;

import com.urise.webapp.model.OrganizationSection;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;
import com.urise.webapp.model.organization.Organization;
import com.urise.webapp.storage.Storage;
import com.urise.webapp.util.Config;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrganizationServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getSqlStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        Resume resume = storage.get(uuid);
        LocalDate dateStart = LocalDateTime.parse(request.getParameter("dateStart")).toLocalDate();
        LocalDate dateEnd = LocalDateTime.parse(request.getParameter("dateEnd")).toLocalDate();
        SectionType sectionType = "experience".equals(request.getParameter("sectionType")) ? SectionType.EXPERIENCE : SectionType.EDUCATION;
        ((OrganizationSection) resume.getSections().get(sectionType)).getOrganizations().add(0,
                new Organization(request.getParameter("nameOrganization"), request.getParameter("homePage"),
                        new Organization.Position(dateStart, dateEnd, request.getParameter("position"), request.getParameter("responsibility"))));
        storage.update(resume);
        response.sendRedirect("resume?uuid=" + uuid + "&action=view");
    }
}