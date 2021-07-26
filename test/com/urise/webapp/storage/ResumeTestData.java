package com.urise.webapp.storage;

import com.urise.webapp.model.*;
import com.urise.webapp.model.organization.Organization;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class ResumeTestData {
    public static void main(String[] args) {
        System.out.println(getResume("254363", "First Resume"));
    }

    public static final String UUID_1 = "uuid1";
    public static final String UUID_2 = "uuid2";
    public static final String UUID_3 = "uuid3";
    public static final String UUID_4 = "uuid4";
    public static final String UUID_SAVED = "uuidsaved";
    public static final String UUID_NOT_EXIST = "dummy";
    public static final String FULL_NAME_1 = "Petrov";
    public static final String FULL_NAME_2 = "Sidorov";
    public static final String FULL_NAME_3 = "Ivanov";
    public static final String FULL_NAME_4 = "Petrov";
    public static final String FULL_NAME_SAVED = "Name Saved";
    public static final String FULL_NAME_UPDATED = "Name Updated";
    public static final String FULL_NAME_NOT_EXIST = "";
    public static final int EMPTY_STORAGE_SIZE = 0;
    public static final int STORAGE_SIZE_EXPECTED = 4;
    public static final int STORAGE_SIZE_AFTER_DELETE = 3;
    public static final int STORAGE_SIZE_AFTER_SAVE = 5;
    public static final Resume RESUME_1 = getResume(UUID_1, FULL_NAME_1);
    public static final Resume RESUME_2 = getResume(UUID_2, FULL_NAME_2);
    public static final Resume RESUME_3 = getResume(UUID_3, FULL_NAME_3);
    public static final Resume RESUME_4 = getResume(UUID_4, FULL_NAME_4);
    public static final Resume RESUME_SAVED = getResume(UUID_SAVED, FULL_NAME_SAVED);
    public static final Resume RESUME_UPDATED = getResume(UUID_1, FULL_NAME_UPDATED);
    public static final Resume RESUME_NOT_EXIST = getResume(UUID_NOT_EXIST, FULL_NAME_NOT_EXIST);
    public static final List<Resume> EXPECTED_RESUMES = List.of(RESUME_3, RESUME_1, RESUME_4, RESUME_2);

    public static Resume getResume(String uuid, String fullName) {
        Resume TEMPLATE_TEST_RESUME = new Resume(uuid, fullName);

        TEMPLATE_TEST_RESUME.addContactData(ContactType.PHONENUMBER, "+7(921) 855-0482");
        TEMPLATE_TEST_RESUME.addContactData(ContactType.SKYPE, "skype:grigory.kislin");
        TEMPLATE_TEST_RESUME.addContactData(ContactType.EMAIL, "gkislin@yandex.ru");
        TEMPLATE_TEST_RESUME.addContactData(ContactType.PROFILELINKEDIN, "https://www.linkedin.com/in/gkislin");
        TEMPLATE_TEST_RESUME.addContactData(ContactType.PROFILEGITHUB,"https://github.com/gkislin");
        TEMPLATE_TEST_RESUME.addContactData(ContactType.PROFILESTACKOVERFLOW,"https://stackoverflow.com/users/548473");
        TEMPLATE_TEST_RESUME.addContactData(ContactType.HOMEPAGE, "http://gkislin.ru/");

        //text sections
        TEMPLATE_TEST_RESUME.addSection(SectionType.OBJECTIVE, new TextSection(
                "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям")
        );
        TEMPLATE_TEST_RESUME.addSection(SectionType.PERSONAL, new TextSection(
                "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.")
        );

        //list section
        TEMPLATE_TEST_RESUME.addSection(SectionType.ACHIEVEMENT, new ListSection(List.of(
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
                "Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\".",
                "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio,",
                "DuoSecurity, Google Authenticator, Jira, Zendesk.",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM,",
                "CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO",
                "аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.",
                "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT",
                "(GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.",
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура,",
                "JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios.",
                "Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).",
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay,",
                "Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."
        )
        ));

        TEMPLATE_TEST_RESUME.addSection(SectionType.QUALIFICATIONS, new ListSection(List.of(
                "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,",
                "MySQL, SQLite, MS SQL, HSQLDB",
                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,",
                "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts",
                "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA",
                "(Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit,",
                "Selenium (htmlelements).",
                "Python: Django.",
                "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js",
                "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka",
                "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX,",
                "JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.",
                "Инструменты: Maven + plugin development, Gradle, настройка Ngnix,",
                "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita,",
                "pgBouncer.",
                "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML,",
                "функционального программирования",
                "Родной русский, английский \"upper intermediate\""
        )
        ));

        TEMPLATE_TEST_RESUME.addSection(SectionType.EXPERIENCE, new OrganizationSection(List.of(
                new Organization("javaops", "http://javaops.ru/",
                        new Organization.Position(2013, Month.OCTOBER, "Автор проекта.",
                                "Создание, организация и проведение Java онлайн проектов и стажировок.")),
                new Organization("wrike", "https://www.wrike.com/",
                        new Organization.Position(2014, Month.OCTOBER, 2016, Month.JANUARY, "Старший разработчик (backend)",
                                "Проектирование и разработка онлайн" +
                                        " платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, " +
                                        "PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.")),
                new Organization("ritcenter", "http://ritcenter.ru/",
                        new Organization.Position(2012, Month.APRIL, 2014, Month.OCTOBER, "Java архитектор",
                                "Организация процесса разработки" +
                                        " системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), " +
                                        "миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. " +
                                        "Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C " +
                                        "(WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция " +
                                        "Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin " +
                                        "development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, " +
                                        "Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python")),
                new Organization("luxoft", "http://www.luxoft.ru/",
                        new Organization.Position(2010, Month.DECEMBER, 2012, Month.APRIL, "Ведущий программист",
                                "Участие в проекте Deutsche " +
                                        "Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация " +
                                        "клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга " +
                                        "и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, " +
                                        "ExtGWT (GXT), Highstock, Commet, HTML5.")),
                new Organization("yota", "https://www.yota.ru/",
                        new Organization.Position(2008, Month.JUNE, 2010, Month.DECEMBER,
                                "Ведущий специалист", "Дизайн и имплементация Java EE " +
                                "фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, " +
                                "Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга " +
                                "фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)")),
                new Organization("enkata", "http://enkata.com/",
                        new Organization.Position(2007, Month.MARCH, 2008, Month.JUNE,
                                "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и " +
                                "серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).")),
                new Organization("siemens", "https://www.siemens.com/ru/ru/home.html",
                        new Organization.Position(2005, Month.JANUARY, 2007, Month.FEBRUARY,
                                "Разработчик ПО", "Разработка информационной " +
                                "модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens " +
                                "@vantage (Java, Unix).")),
                new Organization("alcatel", "http://www.alcatel.ru/",
                        new Organization.Position(1997, Month.SEPTEMBER, 2005, Month.JANUARY,
                                "Инженер по аппаратному и программному тестированию", "Тестирование, отладка, " +
                                "внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."))
        )));

        TEMPLATE_TEST_RESUME.addSection(SectionType.EDUCATION, new OrganizationSection(List.of(
                new Organization("coursera", "https://www.coursera.org/course/progfun",
                        new Organization.Position(2013, Month.MARCH, 2013, Month.APRIL,
                                "\"Functional Programming Principles in Scala\" by Martin Odersky", null)),
                new Organization("luxoft-training", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                        null, new Organization.Position(2011, Month.MARCH, 2011, Month.APRIL,
                        "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"", null)),
                new Organization("siemens", "http://www.siemens.ru/",
                        new Organization.Position(2005, Month.JANUARY, 2005, Month.APRIL,
                        "3 месяца обучения мобильным IN сетям (Берлин)", null)),
                new Organization("alcatel", "http://www.alcatel.ru/",
                        new Organization.Position(1997, Month.SEPTEMBER, 1998, Month.MARCH,
                        "6 месяцев обучения цифровым телефонным сетям (Москва)", null)),
                new Organization("ifmo", "http://www.ifmo.ru/",
                        new Organization.Position(1993, Month.SEPTEMBER, 1996, Month.JULY,
                                "Аспирантура (программист С, С++)", null),
                        new Organization.Position(1987, Month.SEPTEMBER, 1993, Month.JULY,
                                "Инженер (программист Fortran, C)", null)),
                new Organization("mipt", "http://www.school.mipt.ru/",
                        new Organization.Position(1984, Month.SEPTEMBER, 1987, Month.JUNE,
                        "Закончил с отличием", null))
        )));
        return TEMPLATE_TEST_RESUME;
    }
}