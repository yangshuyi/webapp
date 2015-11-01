CREATE TABLE T_UNIT (
        ID varchar(32) not null,
        CREATED_DATE datetime,
        MODIFIED_DATE datetime,
        code varchar(20) not null,
        DISABLED boolean,
        name varchar(20) not null,
        primary key (ID)
    );
CREATE TABLE T_USER (
        ID varchar(32) not null,
        CREATED_DATE datetime,
        MODIFIED_DATE datetime,
        DISABLED boolean,
        employee blob,
        lastlogintime datetime not null,
        name varchar(20) not null,
        password varchar(20) not null,
        unitId varchar(20) not null,
        primary key (ID)
    );
CREATE TABLE T_DEPARTMENT_1001 (
        ID varchar(32) not null,
        CREATED_DATE datetime,
        MODIFIED_DATE datetime,
        CODE varchar(200) not null,
        DESCRIPTION varchar(200),
        NAME varchar(20) not null,
        MANAGER_ID varchar(32),
        PARENT_ID varchar(32),
        primary key (ID)
    );
CREATE TABLE T_EMPLOYEE_1001 (
        ID varchar(32) not null,
        CREATED_DATE datetime,
        MODIFIED_DATE datetime,
        CODE varchar(200) not null,
        DISABLED boolean,
        MAIL_ADDRESS varchar(200),
        MOBILE_NUMBER varchar(200),
        NAME varchar(20) not null,
        DEPARTMENT_ID varchar(32),
        JOB_TITLE_ID varchar(32) not null,
        primary key (ID)
    );
CREATE TABLE T_JOB_TITLE_1001 (
        ID varchar(32) not null,
        CREATED_DATE datetime,
        MODIFIED_DATE datetime,
        CODE varchar(200) not null,
        DESCRIPTION varchar(200),
        NAME varchar(20) not null,
        primary key (ID)
    );
CREATE TABLE T_NAVIGATOR_ITEM_1001 (
        ID varchar(32) not null,
        CREATED_DATE datetime,
        MODIFIED_DATE datetime,
        ACTION_URL varchar(200),
        DESCRIPTION varchar(200),
        IMAGE_URL varchar(200),
        NAME varchar(20) not null,
        POSITION integer,
        NAVIGATOR_MODULE_ID varchar(32),
        primary key (ID)
    );
CREATE TABLE T_NAVIGATOR_MODULE_1001 (
        ID varchar(32) not null,
        CREATED_DATE datetime,
        MODIFIED_DATE datetime,
        DESCRIPTION varchar(200),
        NAME varchar(20) not null,
        primary key (ID)
    );

