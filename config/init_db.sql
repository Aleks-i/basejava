DROP TABLE IF EXISTS resume CASCADE ;
DROP TABLE IF EXISTS contact CASCADE ;
DROP TABLE IF EXISTS text_list_section CASCADE ;

create table resume
(
    uuid      char(36) not null constraint resume_pk primary key,
    full_name text     not null
);

create table contact
(
    id              serial   not null constraint contact_pk primary key,
    resume_uuid     char(36) not null constraint contact_resume_uuid_fk references resume on delete cascade,
    type_contact    text     not null,
    value_contact   text     not null
);
create unique index contact_uuid_type_contact_index on contact (resume_uuid, type_contact);

create table text_list_section
(
    id              serial   not null constraint text_list_section_pk primary key,
    resume_uuid     char(36) not null constraint text_list_section_resume_uuid_fk references resume on delete cascade,
    type_text       text     not null,
    value_text      text     not null
);
create unique index text_list_section_uuid_type_text_index on text_list_section (resume_uuid, type_text);

