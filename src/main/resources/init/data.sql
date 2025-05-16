INSERT INTO level (level_name, xp) VALUES
                                       ('초보 개발자', 0),
                                       ('수련 개발자', 10),
                                       ('견습 개발자', 20),
                                       ('전사 개발자', 30),
                                       ('정예 개발자', 40),
                                       ('기사 개발자', 50),
                                       ('용사 개발자', 60),
                                       ('마스터 개발자', 70),
                                       ('전설 개발자', 80),
                                       ('신화 개발자', 90);

# 대시보드 주요알림 테스트용 데이터
INSERT INTO goal_company (company_id, company_name, content, status, end_date, user_id)
VALUES (3, '네이버', '알림테스트용', 'DOCUMENT', DATE_ADD(NOW(), INTERVAL 3 DAY), 3);
INSERT INTO goal_company (company_id, company_name, content, status, end_date, user_id)
VALUES (5, '토스', '알림테스트용', 'INTERVIEW_1', DATE_ADD(NOW(), INTERVAL 2 DAY), 3);



INSERT INTO goal (company_id, title, start_date, end_date, created_at, is_done)
VALUES
    (6, '기술 면접 준비', '2025-06-01', '2025-06-30', NOW(), false),
    (6, '포트폴리오 작성', '2025-05-15', '2025-06-15', NOW(), true);


-- goal_id = 1번 (기술 면접 준비)
INSERT INTO todos (goal_id, content, start_date, end_date, is_done)
VALUES
    (1, '자료구조 복습', '2025-06-01 09:00:00', '2025-06-05 18:00:00', false),
    (1, '자바 심화 정리', '2025-06-06 09:00:00', '2025-06-10 18:00:00', true);

-- goal_id = 2번 (포트폴리오 작성)
INSERT INTO todos (goal_id, content, start_date, end_date, is_done)
VALUES
    (2, '프로젝트 README 작성', '2025-05-15 10:00:00', '2025-05-18 17:00:00', true),
    (2, '배포 테스트', '2025-06-10 09:00:00', '2025-06-12 17:00:00', false);
