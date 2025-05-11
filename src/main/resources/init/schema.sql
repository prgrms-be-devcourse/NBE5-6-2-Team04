-- DROP TABLES (자식 → 부모 순서)
DROP TABLE IF EXISTS goal;
DROP TABLE IF EXISTS todos_table;
DROP TABLE IF EXISTS goal_company;
DROP TABLE IF EXISTS achievement;
DROP TABLE IF EXISTS usersAchieve;
DROP TABLE IF EXISTS interest;
DROP TABLE IF EXISTS project_recommendation;
DROP TABLE IF EXISTS ai_feedback;
DROP TABLE IF EXISTS user_image;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS level;

-- CREATE TABLES

CREATE TABLE level (
                       level_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       level_name VARCHAR(255),
                       level_image_url VARCHAR(255),
                       xp INT
);

CREATE TABLE User (
                      user_id BIGINT NOT NULL AUTO_INCREMENT,
                      level_id BIGINT NOT NULL,
                      user_image VARCHAR(255),
                      email VARCHAR(255) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      nickname VARCHAR(255) NOT NULL,
                      role VARCHAR(255) NOT NULL,
                      exp INT NOT NULL,
                      profile_image_url VARCHAR(255),
                      created_at TIMESTAMP NULL,
                      comment VARCHAR(255),
                      PRIMARY KEY (user_id, level_id),
                      FOREIGN KEY (level_id) REFERENCES level(level_id)
);

CREATE TABLE goal_company (
                              company_id BIGINT NOT NULL AUTO_INCREMENT,
                              user_id BIGINT NOT NULL,
                              content TEXT,
                              company_name VARCHAR(255),
                              created_at TIMESTAMP NULL,
                              PRIMARY KEY (company_id),
                              FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE goal (
                      goal_id BIGINT NOT NULL AUTO_INCREMENT,
                      company_id BIGINT NOT NULL,
                      title VARCHAR(255) NOT NULL,
                      start_date DATETIME,
                      end_date DATETIME,
                      status VARCHAR(255),
                      created_at TIMESTAMP,
                      is_done BOOLEAN,
                      PRIMARY KEY (goal_id, company_id),
                      FOREIGN KEY (company_id) REFERENCES goal_company(company_id)
);

CREATE TABLE todos_table (
                             todo_id BIGINT NOT NULL AUTO_INCREMENT,
                             company_id BIGINT NOT NULL,
                             content VARCHAR(255),
                             start_date TIMESTAMP NULL,
                             end_date TIMESTAMP NULL,
                             is_done BOOLEAN,
                             PRIMARY KEY (todo_id, company_id),
                             FOREIGN KEY (company_id) REFERENCES goal_company(company_id)
);

CREATE TABLE achievement (
                             achieve_id BIGINT NOT NULL AUTO_INCREMENT,
                             user_id BIGINT NOT NULL,
                             name VARCHAR(255),
                             description VARCHAR(255),
                             PRIMARY KEY (achieve_id, user_id),
                             FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE usersAchieve (
                              id BIGINT NOT NULL AUTO_INCREMENT,
                              user_id BIGINT NOT NULL,
                              achieved_at TIMESTAMP NULL,
                              PRIMARY KEY (id, user_id),
                              FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE interest (
                          interest_id BIGINT NOT NULL AUTO_INCREMENT,
                          user_id BIGINT NOT NULL,
                          job_type VARCHAR(255) NOT NULL,
                          dev_lang VARCHAR(255) NOT NULL,
                          PRIMARY KEY (interest_id, user_id),
                          FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE project_recommendation (
                                        recom_id BIGINT NOT NULL AUTO_INCREMENT,
                                        user_id BIGINT NOT NULL,
                                        content VARCHAR(255),
                                        created_at TIMESTAMP NULL,
                                        PRIMARY KEY (recom_id, user_id),
                                        FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE ai_feedback (
                             feedback_id BIGINT NOT NULL AUTO_INCREMENT,
                             user_id BIGINT NOT NULL,
                             feedback VARCHAR(255),
                             created_at TIMESTAMP NULL,
                             PRIMARY KEY (feedback_id, user_id),
                             FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE user_image (
                            user_image_id BIGINT NOT NULL AUTO_INCREMENT,
                            user_id BIGINT NOT NULL,
                            origin_file_name VARCHAR(255),
                            rename_file_name VARCHAR(255),
                            save_path VARCHAR(255),
                            created_at TIMESTAMP NULL,
                            PRIMARY KEY (user_image_id, user_id),
                            FOREIGN KEY (user_id) REFERENCES User(user_id)
);
