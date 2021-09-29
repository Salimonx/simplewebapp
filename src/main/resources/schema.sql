CREATE TABLE IF NOT EXISTS EMPLOYEE
(
    employee_id   BIGSERIAL primary key,
    first_name    varchar,
    last_name     varchar,
    department_id bigint,
    job_title     varchar,
    gender        int,
    date_of_birth date
);
