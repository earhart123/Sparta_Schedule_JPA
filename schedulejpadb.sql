-- schedule 테이블: 일정 정보
CREATE TABLE schedule (
    schedule_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    title VARCHAR(30) NOT NULL,
    content VARCHAR(1000),
    created_at DATETIME NOT NULL,
    modified_at DATETIME NOT NULL,
    user_id BIGINT NOT NULL
);

-- user 테이블: 사용자 정보
CREATE TABLE user (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(10) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(16) NOT NULL,
    created_at DATETIME NOT NULL,
    modified_at DATETIME NOT NULL
);