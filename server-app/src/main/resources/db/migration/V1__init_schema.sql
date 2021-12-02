CREATE TABLE project
(
    id   BIGINT       NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_project PRIMARY KEY (id)
);
CREATE TABLE task
(
    id         BIGINT NOT NULL,
    name       VARCHAR(255),
    project_id BIGINT,
    CONSTRAINT pk_task PRIMARY KEY (id)
);

ALTER TABLE task
    ADD CONSTRAINT FK_TASK_ON_PROJECT FOREIGN KEY (project_id) REFERENCES project (id);

INSERT INTO project (id, name)
VALUES (1, 'JUG.EKB Preparation');
INSERT INTO project (id, name)
VALUES (2, 'JUG.EKB Participation');


INSERT INTO task (id, name, project_id)
VALUES (1, 'Prepare sample code', 1);
INSERT INTO task (id, name, project_id)
VALUES (2, 'Prepare presentation', 1);
INSERT INTO task (id, name, project_id)
VALUES (3, 'Prepare speech', 1);

INSERT INTO task (id, name, project_id)
VALUES (4, 'Speech rehearsal', 2);
INSERT INTO task (id, name, project_id)
VALUES (5, 'Speech', 2);