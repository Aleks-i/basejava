package com.urise.webapp;

import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ResumeTestData {
    public static void main(String[] args) throws MalformedURLException {
        Resume resume = new Resume("First Resume");
        resume.addContactData(ContactType.PHONENUMBER, "+7(921) 855-0482");
        resume.addContactData(ContactType.SKYPE, "skype:grigory.kislin");
        resume.addContactData(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.addContactData(ContactType.PROFILE, "https://www.linkedin.com/in/gkislin",
                "https://github.com/gkislin", "https://stackoverflow.com/users/548473");
        resume.addContactData(ContactType.URL, "http://gkislin.ru/");

        //text sections
        Resume.TextSection textSectionObjective = resume.new TextSection(
                List.of("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям")
        );
        Resume.TextSection textSectionPersonal = resume.new TextSection(
                List.of("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.")
        );

        //text marker section
        Resume.TextSection textSectionAchievement1 = resume.new TextSection(
                List.of(
                        "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
                        "Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\".",
                        "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников."
                )
        );
        Resume.TextSection textSectionAchievement2 = resume.new TextSection(
                List.of(
                        "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio,",
                        "DuoSecurity, Google Authenticator, Jira, Zendesk."
                )
        );
        Resume.TextSection textSectionAchievement3 = resume.new TextSection(
                List.of(
                        "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM,",
                        "CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO",
                        "аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера."
                )
        );
        Resume.TextSection textSectionAchievement4 = resume.new TextSection(
                List.of(
                        "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT",
                        "(GXT), Commet, HTML5, Highstock для алгоритмического трейдинга."
                )
        );
        Resume.TextSection textSectionAchievement5 = resume.new TextSection(
                List.of(
                        "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура,",
                        "JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios.",
                        "Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django)."
                )
        );
        Resume.TextSection textSectionAchievement6 = resume.new TextSection(
                List.of(
                        "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay,",
                        "Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."
                )
        );

        Resume.TextSection textSectionQualifications1 = resume.new TextSection(
                List.of(
                        "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2"
                )
        );
        Resume.TextSection textSectionQualifications2 = resume.new TextSection(
                List.of(
                        "Version control: Subversion, Git, Mercury, ClearCase, Perforce"
                )
        );
        Resume.TextSection textSectionQualifications3 = resume.new TextSection(
                List.of(
                        "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,"
                )
        );
        Resume.TextSection textSectionQualifications4 = resume.new TextSection(
                List.of(
                        "MySQL, SQLite, MS SQL, HSQLDB"
                )
        );
        Resume.TextSection textSectionQualifications5 = resume.new TextSection(
                List.of(
                        "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,"
                )
        );
        Resume.TextSection textSectionQualifications6 = resume.new TextSection(
                List.of(
                        "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,"
                )
        );
        Resume.TextSection textSectionQualifications7 = resume.new TextSection(
                List.of(
                        "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA",
                        "(Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit,",
                        "Selenium (htmlelements)."
                )
        );
        Resume.TextSection textSectionQualifications8 = resume.new TextSection(
                List.of(
                        "Python: Django."
                )
        );
        Resume.TextSection textSectionQualifications9 = resume.new TextSection(
                List.of(
                        "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js"
                )
        );
        Resume.TextSection textSectionQualifications10 = resume.new TextSection(
                List.of(
                        "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka"
                )
        );
        Resume.TextSection textSectionQualifications11 = resume.new TextSection(
                List.of(
                        "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX,",
                        "JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT."
                )
        );
        Resume.TextSection textSectionQualifications12 = resume.new TextSection(
                List.of(
                        "Инструменты: Maven + plugin development, Gradle, настройка Ngnix,"
                )
        );
        Resume.TextSection textSectionQualifications13 = resume.new TextSection(
                List.of(
                        "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita,",
                        "pgBouncer."
                )
        );
        Resume.TextSection textSectionQualifications14 = resume.new TextSection(
                List.of(
                        "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML,",
                        "функционального программирования"
                )
        );
        Resume.TextSection textSectionQualifications15 = resume.new TextSection(
                List.of(
                        "Родной русский, английский \"upper intermediate\""
                )
        );

        Resume.MarkerTextSection markerTextSectionAchievement = resume.new MarkerTextSection(
                List.of(textSectionAchievement1, textSectionAchievement2, textSectionAchievement3, textSectionAchievement4,
                        textSectionAchievement5, textSectionAchievement6)
        );

        Resume.MarkerTextSection markerTextSectionQualifications = resume.new MarkerTextSection(
                List.of(textSectionQualifications1, textSectionQualifications2, textSectionQualifications3, textSectionQualifications4,
                        textSectionQualifications5, textSectionQualifications6, textSectionQualifications7, textSectionQualifications8,
                        textSectionQualifications9, textSectionQualifications10, textSectionQualifications11, textSectionQualifications12,
                        textSectionQualifications13, textSectionQualifications14, textSectionQualifications15)
        );

        //text url link section
        Resume.UrlLinkSection sectionExperience = resume.new UrlLinkSection();
        Resume.UrlLinkSection.OrganizationSection javaOnlineProjectsOrganization = sectionExperience.new OrganizationSection(
                new URL("http://javaops.ru/"),
                "10/2013 - Сейчас",
                "Автор проекта.",
                resume.new TextSection(List.of("Создание, организация и проведение Java онлайн проектов и стажировок."))
        );
        Resume.UrlLinkSection.OrganizationSection wrikeOrganization = sectionExperience.new OrganizationSection(
                new URL("https://www.wrike.com/"),
                "10/2014 - 01/2016",
                "Старший разработчик (backend)",
                resume.new TextSection(List.of(
                        "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring,",
                        "MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2,",
                        "JWT SSO."
                ))
        );
        Resume.UrlLinkSection.OrganizationSection RITCenterOrganization = sectionExperience.new OrganizationSection(
                new URL("http://ritcenter.ru/"),
                "04/2012 - 10/2014",
                "Java архитектор",
                resume.new TextSection(List.of(
                        "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование,",
                        "ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx),",
                        "AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS,",
                        "BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco",
                        "JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache",
                        "Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell",
                        "remote scripting via ssh tunnels, PL/Python"
                ))
        );
        Resume.UrlLinkSection.OrganizationSection luxoftOrganization = sectionExperience.new OrganizationSection(
                new URL("http://www.luxoft.ru/"),
                "12/2010 - 04/2012",
                "Ведущий программист",
                resume.new TextSection(List.of(
                        "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper,",
                        "Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для",
                        "администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring,",
                        "Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."
                ))
        );
        Resume.UrlLinkSection.OrganizationSection yotaOrganization = sectionExperience.new OrganizationSection(
                new URL("https://www.yota.ru/"),
                "06/2008 - 12/2010",
                "Ведущий специалист",
                resume.new TextSection(List.of(
                        "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J,",
                        "EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и",
                        "мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"
                ))
        );
        Resume.UrlLinkSection.OrganizationSection enkataOrganization = sectionExperience.new OrganizationSection(
                new URL("http://enkata.com/"),
                "03/2007 - 06/2008",
                "Разработчик ПО",
                resume.new TextSection(List.of(
                        "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного",
                        "J2EE приложения (OLAP, Data mining)."
                ))
        );
        Resume.UrlLinkSection.OrganizationSection siemensOrganization = sectionExperience.new OrganizationSection(
                new URL("https://www.siemens.com/ru/ru/home.html"),
                "01/2005 - 02/2007",
                "Разработчик ПО",
                resume.new TextSection(List.of(
                        "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной",
                        "IN платформе Siemens @vantage (Java, Unix)."
                ))
        );
        Resume.UrlLinkSection.OrganizationSection alcatelOrganization = sectionExperience.new OrganizationSection(
                new URL("http://www.alcatel.ru/"),
                "09/1997 - 01/2005",
                "Инженер по аппаратному и программному тестированию",
                resume.new TextSection(List.of(
                        "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."
                ))
        );

        sectionExperience.addOrganizations(List.of(javaOnlineProjectsOrganization, wrikeOrganization, RITCenterOrganization,
                luxoftOrganization, yotaOrganization, enkataOrganization, siemensOrganization, alcatelOrganization));

        Resume.UrlLinkSection sectionEducation = resume.new UrlLinkSection();
        Resume.UrlLinkSection.OrganizationSection courseraOrganization = sectionExperience.new OrganizationSection(
                new URL("https://www.coursera.org/course/progfun"),
                "03/2013 - 05/2013",
                "\"Functional Programming Principles in Scala\" by Martin Odersky"
        );
        Resume.UrlLinkSection.OrganizationSection luxofttrainingOrganization = sectionExperience.new OrganizationSection(
                new URL("http://www.luxoft-training.ru/training/catalog/course.html?ID=22366"),
                "03/2011 - 04/2011",
                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\""
        );
        Resume.UrlLinkSection.OrganizationSection siemensOrganizationEducation = sectionExperience.new OrganizationSection(
                new URL("http://www.siemens.ru/"),
                "01/2005 - 04/2005",
                "3 месяца обучения мобильным IN сетям (Берлин)"
        );
        Resume.UrlLinkSection.OrganizationSection alcatelOrganizationEducation = sectionExperience.new OrganizationSection(
                new URL("http://www.alcatel.ru/"),
                "09/1997 - 03/1998",
                "6 месяцев обучения цифровым телефонным сетям (Москва)"
        );
        Resume.UrlLinkSection.OrganizationSection ifmoOrganization1 = sectionExperience.new OrganizationSection(
                new URL("http://www.ifmo.ru/"),
                "09/1993 - 07/1996",
                "Аспирантура (программист С, С++)"
        );
        Resume.UrlLinkSection.OrganizationSection ifmoOrganization2 = sectionExperience.new OrganizationSection(
                new URL("http://www.ifmo.ru/"),
                "09/1987 - 07/1993",
                "Инженер (программист Fortran, C)"
        );
        Resume.UrlLinkSection.OrganizationSection miptOrganization = sectionExperience.new OrganizationSection(
                new URL("http://www.school.mipt.ru/"),
                "09/1984 - 06/1987",
                "Закончил с отличием"
        );

        sectionEducation.addOrganizations(List.of(courseraOrganization, luxofttrainingOrganization, siemensOrganizationEducation,
                alcatelOrganizationEducation, ifmoOrganization1, ifmoOrganization2, miptOrganization));

        resume.getSectionData().put(SectionType.OBJECTIVE, textSectionObjective);
        resume.getSectionData().put(SectionType.PERSONAL, textSectionPersonal);
        resume.getSectionData().put(SectionType.ACHIEVEMENT, markerTextSectionAchievement);
        resume.getSectionData().put(SectionType.QUALIFICATIONS, markerTextSectionQualifications);
        resume.getSectionData().put(SectionType.EXPERIENCE, sectionExperience);
        resume.getSectionData().put(SectionType.EDUCATION, sectionEducation);
        System.out.println(resume);
    }
}
