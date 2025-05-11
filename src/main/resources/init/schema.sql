-- 0. 테이블 삭제 (순서 주의)
DROP TABLE IF EXISTS users_achieve;
DROP TABLE IF EXISTS ai_feedback;
DROP TABLE IF EXISTS project_recommendation;
DROP TABLE IF EXISTS todo;
DROP TABLE IF EXISTS goal;
DROP TABLE IF EXISTS goal_company;
DROP TABLE IF EXISTS interest;
DROP TABLE IF EXISTS user_image;
DROP TABLE IF EXISTS achievement;
DROP TABLE IF EXISTS level;
DROP TABLE IF EXISTS user;

-- 1. 레벨
CREATE TABLE level (
                       level_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       level_name VARCHAR(100),
                       level_image_url VARCHAR(255),
                       xp INT
);

-- 2. 사용자
CREATE TABLE user (
                      user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      level_id BIGINT,
                      email VARCHAR(255) UNIQUE NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      nickname VARCHAR(100) UNIQUE,
                      role VARCHAR(50) DEFAULT 'USER',
                      exp INT DEFAULT 0,
                      profile_image_url VARCHAR(255),
                      comment TEXT,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      FOREIGN KEY (level_id) REFERENCES level(level_id)
);

-- 3. 유저 이미지 (여러 장 업로드 가능)
CREATE TABLE user_image (
                            user_image_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            user_id BIGINT NOT NULL,
                            origin_file_name VARCHAR(255),
                            rename_file_name VARCHAR(255),
                            save_path VARCHAR(255),
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            FOREIGN KEY (user_id) REFERENCES user(user_id)
);

-- 4. 관심 분야
CREATE TABLE interest (
                          interest_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          user_id BIGINT NOT NULL,
                          job_type VARCHAR(100),
                          dev_lang VARCHAR(255), -- JSON or comma-separated
                          FOREIGN KEY (user_id) REFERENCES user(user_id)
);

-- 5. 업적
CREATE TABLE achievement (
                             achieve_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             name VARCHAR(100),
                             description TEXT
);

-- 6. 유저 업적 획득 기록
CREATE TABLE users_achieve (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               user_id BIGINT NOT NULL,
                               achieve_id BIGINT NOT NULL,
                               achieved_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (user_id) REFERENCES user(user_id),
                               FOREIGN KEY (achieve_id) REFERENCES achievement(achieve_id)
);

-- 7. 목표 기업
CREATE TABLE goal_company (
                              company_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              user_id BIGINT NOT NULL,
                              company_name VARCHAR(255) NOT NULL,
                              d_day DATE, -- 마감일
                              content TEXT,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              FOREIGN KEY (user_id) REFERENCES user(user_id)
);

-- 8. 목표 (회사 하위 단위 목표)
CREATE TABLE goal (
                      goal_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      company_id BIGINT NOT NULL,
                      user_id BIGINT NOT NULL,
                      title VARCHAR(255) NOT NULL,
                      start_date DATE,
                      end_date DATE,
                      status ENUM('진행중', '완료', '미달성') DEFAULT '진행중',
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      FOREIGN KEY (company_id) REFERENCES goal_company(company_id),
                      FOREIGN KEY (user_id) REFERENCES user(user_id)
);

-- 9. 할 일 (각 목표의 세부 단위)
CREATE TABLE todo (
                      todo_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      goal_id BIGINT NOT NULL,
                      user_id BIGINT NOT NULL,
                      content TEXT NOT NULL,
                      start_date DATE,
                      end_date DATE,
                      is_done BOOLEAN DEFAULT FALSE,
                      FOREIGN KEY (goal_id) REFERENCES goal(goal_id),
                      FOREIGN KEY (user_id) REFERENCES user(user_id)
);

-- 10. AI 피드백
CREATE TABLE ai_feedback (
                             feedback_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             user_id BIGINT NOT NULL,
                             feedback TEXT NOT NULL,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             FOREIGN KEY (user_id) REFERENCES user(user_id)
);

-- 11. 추천 프로젝트
CREATE TABLE project_recommendation (
                                        recom_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        user_id BIGINT NOT NULL,
                                        content TEXT,
                                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                        FOREIGN KEY (user_id) REFERENCES user(user_id)
);
