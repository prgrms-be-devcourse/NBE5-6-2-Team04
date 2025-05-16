-- Level 데이터
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
-- 관심분야 (ROLE) 데이터 --
INSERT INTO interest (type, interest_name) VALUES
                                               ('ROLE', 'Frontend'),
                                               ('ROLE', 'Backend'),
                                               ('ROLE', 'DevOps'),
                                               ('ROLE', 'Full Stack'),
                                               ('ROLE', 'AI Engineer'),
                                               ('ROLE', 'Data Analyst'),
                                               ('ROLE', 'AI and Data Scientist'),
                                               ('ROLE', 'Android'),
                                               ('ROLE', 'iOS'),
                                               ('ROLE', 'PostgreSQL'),
                                               ('ROLE', 'Blockchain'),
                                               ('ROLE', 'QA'),
                                               ('ROLE', 'Software Architect'),
                                               ('ROLE', 'Cyber Security'),
                                               ('ROLE', 'UX Design'),
                                               ('ROLE', 'Game Developer'),
                                               ('ROLE', 'Technical Writer'),
                                               ('ROLE', 'MLOps'),
                                               ('ROLE', 'Product Manager'),
                                               ('ROLE', 'Engineering Manager'),
                                               ('ROLE', 'Developer Relations');

-- 관심분야 (SKILL) 데이터 --
INSERT INTO interest (type, interest_name) VALUES
                                               ('SKILL', 'SQL'),
                                               ('SKILL', 'Computer Science'),
                                               ('SKILL', 'React'),
                                               ('SKILL', 'Vue'),
                                               ('SKILL', 'Angular'),
                                               ('SKILL', 'JavaScript'),
                                               ('SKILL', 'Node.js'),
                                               ('SKILL', 'TypeScript'),
                                               ('SKILL', 'Python'),
                                               ('SKILL', 'System Design'),
                                               ('SKILL', 'API Design'),
                                               ('SKILL', 'ASP.NET Core'),
                                               ('SKILL', 'Java'),
                                               ('SKILL', 'C++'),
                                               ('SKILL', 'Flutter'),
                                               ('SKILL', 'Spring Boot'),
                                               ('SKILL', 'Go Roadmap'),
                                               ('SKILL', 'Rust'),
                                               ('SKILL', 'GraphQL'),
                                               ('SKILL', 'Design and Architecture'),
                                               ('SKILL', 'Design System'),
                                               ('SKILL', 'React Native'),
                                               ('SKILL', 'AWS'),
                                               ('SKILL', 'Code Review'),
                                               ('SKILL', 'Docker'),
                                               ('SKILL', 'Kubernetes'),
                                               ('SKILL', 'Linux'),
                                               ('SKILL', 'MongoDB'),
                                               ('SKILL', 'Prompt Engineering'),
                                               ('SKILL', 'Terraform'),
                                               ('SKILL', 'Data Structures & Algorithms'),
                                               ('SKILL', 'Git and GitHub'),
                                               ('SKILL', 'Redis'),
                                               ('SKILL', 'PHP'),
                                               ('SKILL', 'Cloudflare'),
                                               ('SKILL', 'AI Agents'),
                                               ('SKILL', 'AI Red Teaming');
