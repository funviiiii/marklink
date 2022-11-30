DROP TABLE IF EXISTS `t_account`;

DROP TABLE IF EXISTS `t_user`;

DROP TABLE IF EXISTS `t_resume`;

DROP TABLE IF EXISTS `t_department`;

DROP TABLE IF EXISTS `t_salary`;

DROP TABLE IF EXISTS `t_role`;

DROP TABLE IF EXISTS `t_manager`;

DROP TABLE IF EXISTS `t_log`;

DROP TABLE IF EXISTS `t_level`;

CREATE TABLE `t_account`
(
    `id`          BIGINT AUTO_INCREMENT COMMENT '主键',
    `uid`         VARCHAR(30)      NOT NULL UNIQUE COMMENT '用户编号',
    `username`    VARCHAR(25)      NOT NULL UNIQUE COMMENT '用户名',
    `password`    VARCHAR(32)      NOT NULL COMMENT '密码',
    `email`       VARCHAR(40) UNIQUE COMMENT '邮箱',
    `phone`       VARCHAR(20) UNIQUE COMMENT '电话号码',
    `create_time` DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_time` DATETIME         NOT NULL DEFAULT NOW() COMMENT '上次更新时间',
    `is_deleted`  TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除标识',
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_user`
(
    `id`          BIGINT AUTO_INCREMENT COMMENT '主键',
    `uid`         VARCHAR(30)      NOT NULL UNIQUE COMMENT '用户编号',
    `name`        VARCHAR(16)      NOT NULL COMMENT '用户名',
    `gender`      CHAR(1)          NOT NULL DEFAULT 's' COMMENT '性别: 保密(s), 男(m), 女(f)',
    `birthday`    DATE COMMENT '生日',
    `induction`   DATE COMMENT '入职时间',
    `department`  VARCHAR(30) COMMENT '部门（使用部门编号）',
    `role`        VARCHAR(30)               DEFAULT 'rid_member' COMMENT '职位（使用职位编号）',
    `is_married`  TINYINT          NOT NULL COMMENT '是否已婚',
    `resume`      VARCHAR(30) UNIQUE COMMENT '简历',
    `create_time` DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_time` DATETIME         NOT NULL DEFAULT NOW() COMMENT '上次更新时间',
    `is_deleted`  TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除标识',
    PRIMARY KEY (`id`)
);

# 简历单独分表，避免大容量字段影响表中其他字段的数据的索引效率
CREATE TABLE `t_resume`
(
    `id`          BIGINT AUTO_INCREMENT COMMENT '主键',
    `rid`         VARCHAR(30)      NOT NULL UNIQUE COMMENT '简历编号',
    `content`     TEXT             NOT NULL COMMENT '正文',
    `create_time` DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_time` DATETIME         NOT NULL DEFAULT NOW() COMMENT '上次更新时间',
    `is_deleted`  TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除标识：已删除(1), 未删除(0)',
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_salary`
(
    `id`           BIGINT AUTO_INCREMENT COMMENT '主键',
    `sid`          VARCHAR(30)      NOT NULL COMMENT '工资编号',
    `uid`          VARCHAR(30)      NOT NULL COMMENT '用户编号',
    `basic_salary` DECIMAL(15, 2)   NOT NULL DEFAULT 0.00 COMMENT '基本工资',
    `allowance`    DECIMAL(15, 2)   NOT NULL DEFAULT 0.00 COMMENT '岗位津贴',
    `reward`       DECIMAL(15, 2)   NOT NULL DEFAULT 0.00 COMMENT '奖励',
    `should_pay`   DECIMAL(15, 2)   NOT NULL DEFAULT 0.00 COMMENT '应付工资',
    `cost`         DECIMAL(15, 2)   NOT NULL DEFAULT 0.00 COMMENT '其他花销',
    `insurance`    DECIMAL(15, 2)   NOT NULL DEFAULT 0.00 COMMENT '保险',
    `real_salary`  DECIMAL(15, 2)   NOT NULL DEFAULT 0.00 COMMENT '实发工资',
    `create_time`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_time`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '上次更新时间',
    `is_deleted`   TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除标识: 已删除(1), 未删除(0)',
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_department`
(
    `id`              BIGINT AUTO_INCREMENT COMMENT '主键',
    `did`             VARCHAR(30)      NOT NULL UNIQUE COMMENT '部门编号',
    `department_name` VARCHAR(25)      NOT NULL COMMENT '部门名',
    `create_time`     DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_time`     DATETIME         NOT NULL DEFAULT NOW() COMMENT '上次更新时间',
    `is_deleted`      TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除标识: 已删除(1), 未删除(0)',
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_role`
(
    `id`              BIGINT AUTO_INCREMENT COMMENT '主键',
    `rid`             VARCHAR(30)      NOT NULL UNIQUE COMMENT '职位编号',
    `role_name`       VARCHAR(25)      NOT NULL COMMENT '职称',
    `personnel_right` TINYINT          NOT NULL DEFAULT 0 COMMENT '是否有管理人事的权力',
    `salary_right`    TINYINT          NOT NULL DEFAULT 0 COMMENT '是否有管理工资的权力',
    `info_right`      TINYINT          NOT NULL DEFAULT 0 COMMENT '是否有管理基本信息的权力（部门，职位等）',
    `advance_right`   TINYINT          NOT NULL DEFAULT 0 COMMENT '是否有管理系统的权力',
    `create_time`     DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_time`     DATETIME         NOT NULL DEFAULT NOW() COMMENT '上次更新时间',
    `is_deleted`      TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除标识: 已删除(1), 未删除(0)',
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_manager`
(
    `id`          BIGINT AUTO_INCREMENT COMMENT '主键',
    `uid`         VARCHAR(30)      NOT NULL UNIQUE COMMENT '用户编号',
    `auth`        VARCHAR(64)      NOT NULL COMMENT '口令',
    `department`  VARCHAR(30)      NOT NULL COMMENT '所属部门',
    `create_time` DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_time` DATETIME         NOT NULL DEFAULT NOW() COMMENT '上次更新时间',
    `is_deleted`  TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除标识: 已删除(1), 未删除(0)',
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_log`
(
    `id`          BIGINT AUTO_INCREMENT COMMENT '主键',
    `lid`         VARCHAR(30)       NOT NULL UNIQUE COMMENT '日志编号',
    `user`        VARCHAR(30)       NOT NULL COMMENT '操作用户',
    `msg`         VARCHAR(255)      NOT NULL COMMENT '操作详细记录',
    `level`       SMALLINT UNSIGNED NOT NULL COMMENT '操作行为级别',
    `create_time` DATETIME          NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_time` DATETIME          NOT NULL DEFAULT NOW() COMMENT '上次更新时间',
    `is_deleted`  TINYINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '逻辑删除标识: 已删除(1), 未删除(0)',
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_level`
(
    `id`          BIGINT AUTO_INCREMENT COMMENT '主键',
    `code`        SMALLINT UNSIGNED NOT NULL UNIQUE COMMENT '操作行为级别号',
    `level_name`  CHAR(10)          NOT NULL COMMENT '操作行为级别名',
    `create_time` DATETIME          NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_time` DATETIME          NOT NULL DEFAULT NOW() COMMENT '上次更新时间',
    `is_deleted`  TINYINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '逻辑删除标识: 已删除(1), 未删除(0)',
    PRIMARY KEY (`id`)
);

# 对某些表的初始化
# level
INSERT INTO `t_level`(`code`, `level_name`)
VALUES (1, 'info'),
       (2, 'warning'),
       (3, 'error'),
       (4, 'critical');

# role
INSERT INTO `t_role`(`rid`, `role_name`, `personnel_right`, `salary_right`, `info_right`, `advance_right`)
VALUES ('rid_member', 'member', 0, 0, 0, 0),
       ('rid_manager', 'manager', 1, 1, 1, 1);