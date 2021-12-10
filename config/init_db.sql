DROP TABLE IF EXISTS resume CASCADE ;
DROP TABLE IF EXISTS contact CASCADE ;
DROP TABLE IF EXISTS section CASCADE ;

create table resume
(
    uuid      char(36) not null constraint resume_pk primary key,
    full_name text     not null
);

create table contact
(
    id              serial   not null constraint contact_pk primary key,
    resume_uuid     char(36) not null constraint contact_resume_uuid_fk references resume on delete cascade,
    type            text     not null,
    value           text     not null
);
create unique index contact_resume_uuid_type_index on contact (resume_uuid, type);

create table section
(
    id              serial   not null constraint text_list_section_pk primary key,
    resume_uuid     char(36) not null constraint text_list_section_resume_uuid_fk references resume on delete cascade,
    type            text     not null,
    value           text     not null
);
create unique index section_resume_uuid_type_index on section (resume_uuid, type);

