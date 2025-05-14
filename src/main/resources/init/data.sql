INSERT INTO level (level_name, level_image_url, xp) VALUES
            ('씨앗 개발자', NULL, 100),
            ('새싹 개발자', NULL, 200),
            ('잎새 개발자', NULL, 400),
            ('나무 개발자', NULL, 700),
            ('숲 개발자', NULL, 1000);

CREATE TABLE todos (
                       todo_id BIGINT NOT NULL AUTO_INCREMENT,
                       goal_id BIGINT NOT NULL,
                       content VARCHAR(255),
                       start_date TIMESTAMP NULL,
                       end_date TIMESTAMP NULL,
                       is_done BOOLEAN,
                       PRIMARY KEY (todo_id),
                       FOREIGN KEY (goal_id) REFERENCES goal(goal_id)
);