INSERT INTO member (`CREATED_DATE`, `ID`, `MODIFIED_DATE`, `EMAIL`, `LOGIN_ID`, `NAME`, `NICKNAME`, `PASSWORD`, `PICTURE`)
VALUES (CURRENT_TIMESTAMP, 1, NULL, NULL, 'dbwp031','YUJE-LEE', NULL, NULL, 'https://avatars.githubusercontent.com/u/65337423?v=4');

INSERT INTO project (`CREATED_DATE`, `ID`, `MEMBER_ID`, `MODIFIED_DATE`, `DESCRIPTION`, `TITLE`)
VALUES (CURRENT_TIMESTAMP, 1, 1, NULL, 'this is project 1 description', 'Project 1');

INSERT INTO project (`CREATED_DATE`, `ID`, `MEMBER_ID`, `MODIFIED_DATE`, `DESCRIPTION`, `TITLE`)
VALUES (CURRENT_TIMESTAMP, 2, 1, NULL, 'this is project 2 description', 'Project 2');

INSERT INTO todo (`DONE`, `CREATED_DATE`, `MEMBER_ID`, `MODIFIED_DATE`, `PROJECT_ID`, `TODO_ID`, `CONTENT`)
VALUES (FALSE, CURRENT_TIMESTAMP, 1, NULL, NULL, 1, 'TODO SAMPLE 1');

INSERT INTO todo (`DONE`, `CREATED_DATE`, `MEMBER_ID`, `MODIFIED_DATE`, `PROJECT_ID`, `TODO_ID`, `CONTENT`)
VALUES (TRUE, CURRENT_TIMESTAMP, 1, NULL, NULL, 2, 'TODO SAMPLE 2');
