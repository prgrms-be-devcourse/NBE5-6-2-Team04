DROP TABLE IF EXISTS `users_achieve`;
DROP TABLE IF EXISTS `User`;
DROP TABLE IF EXISTS `level`;
DROP TABLE IF EXISTS `User_image`;
DROP TABLE IF EXISTS `Achievement`;
DROP TABLE IF EXISTS `project_recommendation`;
DROP TABLE IF EXISTS `AI feedback`;
DROP TABLE IF EXISTS `todos_table`;
DROP TABLE IF EXISTS `Interest`;
DROP TABLE IF EXISTS `goal_company`;
DROP TABLE IF EXISTS `Goal`;

-- 1. level 테이블
CREATE TABLE level (
                       level_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       level_name VARCHAR(255),
                       level_image_url VARCHAR(255),
                       xp INT
);
-- 2. user 테이블
CREATE TABLE user (
                      user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      level_id BIGINT,
                      user_image VARCHAR(255),
                      email VARCHAR(255),
                      password VARCHAR(255),
                      nickname VARCHAR(255),
                      role VARCHAR(255),
                      exp INT,
                      profile_image_url VARCHAR(255),
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      comment VARCHAR(255),
                      FOREIGN KEY (level_id) REFERENCES level(level_id)
);
-- 3. user_image 테이블
CREATE TABLE user_image (
                            user_image_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            user_id BIGINT,
                            origin_file_name VARCHAR(255),
                            rename_file_name VARCHAR(255),
                            save_path VARCHAR(255),
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            FOREIGN KEY (user_id) REFERENCES user(user_id)
);
-- 4. interest 테이블
CREATE TABLE interest (
                          interest_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          user_id BIGINT,
                          job_type VARCHAR(255),
                          dev_lang VARCHAR(255),
                          FOREIGN KEY (user_id) REFERENCES user(user_id)
);
-- 5. achievement 테이블
CREATE TABLE achievement (
                             achieve_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             user_id BIGINT,
                             name VARCHAR(255),
                             description VARCHAR(255),
                             FOREIGN KEY (user_id) REFERENCES user(user_id)
);
-- 6. usersAchieve 테이블
CREATE TABLE users_achieve (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               user_id BIGINT,
                               achieve_id BIGINT,
                               achieved_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (user_id) REFERENCES user(user_id),
                               FOREIGN KEY (achieve_id) REFERENCES achievement(achieve_id)
);
-- 7. goal_company 테이블
CREATE TABLE goal_company (
                              company_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              user_id BIGINT,
                              company_name VARCHAR(255),
                              d_day INT,
                              schedule VARCHAR(255),
                              content VARCHAR(255),
                              progress_rate INT,
                              FOREIGN KEY (user_id) REFERENCES user(user_id)
);
-- 8. goal 테이블
CREATE TABLE goal (
                      goal_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      company_id BIGINT,
                      user_id BIGINT,
                      title VARCHAR(255),
                      start_date DATETIME,
                      end_date DATETIME,
                      progress INT,
                      status VARCHAR(255),
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      FOREIGN KEY (company_id) REFERENCES goal_company(company_id),
                      FOREIGN KEY (user_id) REFERENCES user(user_id)
);
-- 9. todo 테이블
CREATE TABLE todo (
                      todo_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      goal_id BIGINT,
                      user_id BIGINT,
                      content VARCHAR(255),
                      start_date TIMESTAMP,
                      end_date TIMESTAMP,
                      is_done BOOLEAN,
                      FOREIGN KEY (goal_id) REFERENCES goal(goal_id),
                      FOREIGN KEY (user_id) REFERENCES user(user_id)
);
-- 10. project_recommendation 테이블
CREATE TABLE project_recommendation (
                                        recom_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        user_id BIGINT,
                                        content VARCHAR(255),
                                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                        FOREIGN KEY (user_id) REFERENCES user(user_id)
);
-- 11. ai_feedback 테이블
CREATE TABLE ai_feedback (
                             feedback_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             user_id BIGINT,
                             feedback VARCHAR(255),
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             FOREIGN KEY (user_id) REFERENCES user(user_id)
);