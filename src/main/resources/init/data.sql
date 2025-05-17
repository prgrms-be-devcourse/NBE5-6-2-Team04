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


-- 관심분야 (ROLE) 데이터 --
INSERT INTO interest (type, interest_name, roadmap_url) VALUES
                                               ('ROLE', 'Frontend', 'https://roadmap.sh/frontend'),
                                               ('ROLE', 'Backend', 'https://roadmap.sh/backend'),
                                               ('ROLE', 'DevOps', 'https://roadmap.sh/devops'),
                                               ('ROLE', 'Full Stack', 'https://roadmap.sh/full-stack'),
                                               ('ROLE', 'AI Engineer', 'https://roadmap.sh/ai-engineer'),
                                               ('ROLE', 'Data Analyst', 'https://roadmap.sh/data-analyst'),
                                               ('ROLE', 'AI and Data Scientist', 'https://roadmap.sh/ai-data-scientist'),
                                               ('ROLE', 'Android', 'https://roadmap.sh/android'),
                                               ('ROLE', 'iOS', 'https://roadmap.sh/ios'),
                                               ('ROLE', 'PostgreSQL', 'https://roadmap.sh/postgresql-dba'),
                                               ('ROLE', 'Blockchain', 'https://roadmap.sh/blockchain'),
                                               ('ROLE', 'QA', 'https://roadmap.sh/qa'),
                                               ('ROLE', 'Software Architect', 'https://roadmap.sh/software-architect'),
                                               ('ROLE', 'Cyber Security', 'https://roadmap.sh/cyber-security'),
                                               ('ROLE', 'UX Design', 'https://roadmap.sh/ux-design'),
                                               ('ROLE', 'Game Developer', 'https://roadmap.sh/game-developer'),
                                               ('ROLE', 'Technical Writer', 'https://roadmap.sh/technical-writer'),
                                               ('ROLE', 'MLOps', 'https://roadmap.sh/mlops'),
                                               ('ROLE', 'Product Manager', 'https://roadmap.sh/product-manager'),
                                               ('ROLE', 'Engineering Manager', 'https://roadmap.sh/engineering-manager'),
                                               ('ROLE', 'Developer Relations', 'https://roadmap.sh/devrel');

-- 관심분야 (SKILL) 데이터 --
INSERT INTO interest (type, interest_name, roadmap_url) VALUES
                                               ('SKILL', 'SQL', 'https://roadmap.sh/sql'),
                                               ('SKILL', 'Computer Science', 'https://roadmap.sh/computer-science'),
                                               ('SKILL', 'React', 'https://roadmap.sh/react'),
                                               ('SKILL', 'Vue', 'https://roadmap.sh/vue'),
                                               ('SKILL', 'Angular', 'https://roadmap.sh/angular'),
                                               ('SKILL', 'JavaScript', 'https://roadmap.sh/javascript'),
                                               ('SKILL', 'Node.js', 'https://roadmap.sh/nodejs'),
                                               ('SKILL', 'TypeScript', 'https://roadmap.sh/typescript'),
                                               ('SKILL', 'Python', 'https://roadmap.sh/python'),
                                               ('SKILL', 'System Design', 'https://roadmap.sh/system-design'),
                                               ('SKILL', 'API Design', 'https://roadmap.sh/api-design'),
                                               ('SKILL', 'ASP.NET Core', 'https://roadmap.sh/aspnet-core'),
                                               ('SKILL', 'Java', 'https://roadmap.sh/java'),
                                               ('SKILL', 'C++', 'https://roadmap.sh/cpp'),
                                               ('SKILL', 'Flutter', 'https://roadmap.sh/flutter'),
                                               ('SKILL', 'Spring Boot', 'https://roadmap.sh/spring-boot'),
                                               ('SKILL', 'Go Roadmap', 'https://roadmap.sh/golang'),
                                               ('SKILL', 'Rust', 'https://roadmap.sh/rust'),
                                               ('SKILL', 'GraphQL', 'https://roadmap.sh/graphql'),
                                               ('SKILL', 'Design and Architecture', 'https://roadmap.sh/software-design-architecture'),
                                               ('SKILL', 'Design System', 'https://roadmap.sh/design-system'),
                                               ('SKILL', 'React Native', 'https://roadmap.sh/react-native'),
                                               ('SKILL', 'AWS', 'https://roadmap.sh/aws'),
                                               ('SKILL', 'Code Review', 'https://roadmap.sh/code-review'),
                                               ('SKILL', 'Docker', 'https://roadmap.sh/docker'),
                                               ('SKILL', 'Kubernetes', 'https://roadmap.sh/kubernetes'),
                                               ('SKILL', 'Linux', 'https://roadmap.sh/linux'),
                                               ('SKILL', 'MongoDB', 'https://roadmap.sh/mongodb'),
                                               ('SKILL', 'Prompt Engineering', 'https://roadmap.sh/prompt-engineering'),
                                               ('SKILL', 'Terraform', 'https://roadmap.sh/terraform'),
                                               ('SKILL', 'Data Structures & Algorithms', 'https://roadmap.sh/datastructures-and-algorithms'),
                                               ('SKILL', 'Git and GitHub', 'https://roadmap.sh/git-github'),
                                               ('SKILL', 'Redis', 'https://roadmap.sh/redis'),
                                               ('SKILL', 'PHP', 'https://roadmap.sh/php'),
                                               ('SKILL', 'Cloudflare', 'https://roadmap.sh/cloudflare'),
                                               ('SKILL', 'AI Agents', 'https://roadmap.sh/ai-agents'),
                                               ('SKILL', 'AI Red Teaming', 'https://roadmap.sh/ai-red-teaming');


# 대시보드 주요알림 테스트용 데이터
INSERT INTO goal_company (company_id, company_name, content, status, end_date, user_id)
VALUES (1, '네이버', '알림테스트용', 'DOCUMENT', DATE_ADD(NOW(), INTERVAL 3 DAY), 1);
INSERT INTO goal_company (company_id, company_name, content, status, end_date, user_id)
VALUES (3, '토스', '알림테스트용', 'INTERVIEW_1', DATE_ADD(NOW(), INTERVAL 2 DAY), 1);

INSERT INTO goal (company_id, title, start_date, end_date, created_at, is_done)
VALUES
    (1, '기술 면접 준비', '2025-06-01', '2025-06-30', NOW(), false),
    (1, '포트폴리오 작성', '2025-05-15', '2025-06-15', NOW(), true);

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