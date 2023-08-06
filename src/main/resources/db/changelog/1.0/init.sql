CREATE EXTENSION IF NOT EXISTS pg_trgm;

CREATE TABLE IF NOT EXISTS user_info
(
    id               BIGINT PRIMARY KEY,
    username         VARCHAR(255) UNIQUE                      NOT NULL,
    current_position VARCHAR(100)             DEFAULT 'start' NOT NULL,
    age              INTEGER                                  NULL,
    role             INTEGER                  DEFAULT 0       NOT NULL,
    experience_years INTEGER                                  NOT NULL,
    biography        VARCHAR(500)                             NULL,
    icon             BYTEA                                    NULL,
    skills           VARCHAR[]                                NULL,
    first_name       VARCHAR(255)                             NULL,
    last_name        VARCHAR(255)                             NULL,
    created_date     TIMESTAMP WITH TIME ZONE DEFAULT now()   NOT NULL
);

CREATE TABLE IF NOT EXISTS user_project
(
    id           SERIAL PRIMARY KEY,
    user_id      INT REFERENCES user_info ON DELETE CASCADE NOT NULL,
    name         VARCHAR(255)                               NOT NULL,
    description  VARCHAR(500)                               NOT NULL,
    achievements VARCHAR[]                                  NULL
);

CREATE TABLE IF NOT EXISTS proposed_project
(
    id           SERIAL PRIMARY KEY,
    author_id    INT REFERENCES user_info               NOT NULL,
    title        VARCHAR(255)                           NOT NULL,
    description  VARCHAR(500)                           NOT NULL,
    status       SMALLINT                               NOT NULL,
    p_type       SMALLINT                               NOT NULL,
    created_date TIMESTAMP WITH TIME ZONE DEFAULT now() NOT NULL
);

CREATE TABLE IF NOT EXISTS proposed_project_member
(
    id              SERIAL PRIMARY KEY,
    user_id         INT REFERENCES user_info                               NULL,
    project_id      INT REFERENCES proposed_project (id) ON DELETE CASCADE NOT NULL,
    position_type   SMALLINT                                               NOT NULL,
    title           VARCHAR(255)                                           NOT NULL,
    description     VARCHAR(500)                                           NULL,
    skills          VARCHAR[]                                              NULL,
    work_week_hours SMALLINT                                               NULL,
    joined_date     TIMESTAMP WITH TIME ZONE DEFAULT now()                 NOT NULL
);

CREATE INDEX user_info_data_gin_idx ON user_info USING gin (skills);
CREATE INDEX proposed_project_title_gin_idx ON proposed_project USING gin (title gin_trgm_ops, description gin_trgm_ops);
CREATE INDEX proposed_project_member_skills_gin_idx ON proposed_project_member USING gin (skills, title gin_trgm_ops, description gin_trgm_ops);