INSERT INTO member (`CREATED_DATE`, `ID`, `MODIFIED_DATE`, `EMAIL`, `LOGIN_ID`, `NAME`, `NICKNAME`, `PASSWORD`, `PICTURE`)
VALUES (CURRENT_TIMESTAMP, 1, NULL, NULL, 'dbwp031','YUJE-LEE', NULL, NULL, 'https://avatars.githubusercontent.com/u/65337423?v=4');

INSERT INTO member (`CREATED_DATE`, `ID`, `MODIFIED_DATE`, `EMAIL`, `LOGIN_ID`, `NAME`, `NICKNAME`, `PASSWORD`, `PICTURE`)
VALUES (CURRENT_TIMESTAMP, 2, NULL, NULL, 'dhkim12','DH-KIM', NULL, NULL, 'https://avatars.githubusercontent.com/u/65337423?v=4');

INSERT INTO member (`CREATED_DATE`, `ID`, `MODIFIED_DATE`, `EMAIL`, `LOGIN_ID`, `NAME`, `NICKNAME`, `PASSWORD`, `PICTURE`)
VALUES (CURRENT_TIMESTAMP, 3, NULL, NULL, 'shjung34','SH-JUNG', NULL, NULL, 'https://avatars.githubusercontent.com/u/65337423?v=4');

INSERT INTO member (`CREATED_DATE`, `ID`, `MODIFIED_DATE`, `EMAIL`, `LOGIN_ID`, `NAME`, `NICKNAME`, `PASSWORD`, `PICTURE`)
VALUES (CURRENT_TIMESTAMP, 4, NULL, NULL, 'hbyang56','HB-Yang', NULL, NULL, 'https://avatars.githubusercontent.com/u/65337423?v=4');


INSERT INTO project (`CREATED_DATE`, `ID`, `MEMBER_ID`, `MODIFIED_DATE`, `DESCRIPTION`, `TITLE`)
VALUES (CURRENT_TIMESTAMP, 1, 1, NULL, 'this is project 1 description', 'Project 1');

INSERT INTO project (`CREATED_DATE`, `ID`, `MEMBER_ID`, `MODIFIED_DATE`, `DESCRIPTION`, `TITLE`)
VALUES (CURRENT_TIMESTAMP, 2, 1, NULL, 'this is project 2 description', 'Project 2');

INSERT INTO project (`CREATED_DATE`, `ID`, `MEMBER_ID`, `MODIFIED_DATE`, `DESCRIPTION`, `TITLE`)
VALUES (CURRENT_TIMESTAMP, 3, 2, NULL, 'this is project 1 description', 'Project 1');

INSERT INTO project (`CREATED_DATE`, `ID`, `MEMBER_ID`, `MODIFIED_DATE`, `DESCRIPTION`, `TITLE`)
VALUES (CURRENT_TIMESTAMP, 4, 2, NULL, 'this is project 2 description', 'Project 2');


INSERT INTO todo (`DONE`, `CREATED_DATE`, `MEMBER_ID`, `MODIFIED_DATE`, `PROJECT_ID`, `TODO_ID`, `CONTENT`)
VALUES (FALSE, CURRENT_TIMESTAMP, 1, NULL, NULL, 1, 'TODO SAMPLE 1');

INSERT INTO todo (`DONE`, `CREATED_DATE`, `MEMBER_ID`, `MODIFIED_DATE`, `PROJECT_ID`, `TODO_ID`, `CONTENT`)
VALUES (TRUE, CURRENT_TIMESTAMP, 1, NULL, NULL, 2, 'TODO SAMPLE 2');

INSERT INTO todo (`DONE`, `CREATED_DATE`, `MEMBER_ID`, `MODIFIED_DATE`, `PROJECT_ID`, `TODO_ID`, `CONTENT`)
VALUES (TRUE, CURRENT_TIMESTAMP, 2, NULL, 3, 3, 'TODO SAMPLE 3');

INSERT INTO friendship (`CREATED_DATE`, `MODIFIED_DATE`, `MEMBER1_MEMBER_ID`, `MEMBER2_MEMBER_ID`)
VALUES (CURRENT_TIMESTAMP,NULL,1,2);

INSERT INTO friend_request (`CREATED_DATE`,`MODIFIED_DATE`, `REQUESTER_MEMBER_ID`,`REQUESTED_MEMBER_ID`, `REQUEST_STATUS`)
VALUES (CURRENT_TIMESTAMP,NULL,1,3, 'PENDING');

INSERT INTO friend_request (`CREATED_DATE`,`MODIFIED_DATE`, `REQUESTER_MEMBER_ID`,`REQUESTED_MEMBER_ID`, `REQUEST_STATUS`)
VALUES (CURRENT_TIMESTAMP,NULL,4,1, 'PENDING');