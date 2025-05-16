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
INSERT INTO achievement (name, description) VALUES
                                                ('튜토리얼을 끝냈어요!', '첫 시작을 축하합니다! 프로필을 완성했어요.'),
                                                ('목표를 정해브렀어~', '진로를 향해 한 걸음 내디뎠어요.'),
                                                ('\'J\'로 가는 길.', '첫 번째 할 일을 완료했어요! 멋져요.'),
                                                ('초보 개발자 두두등장', '첫 성장의 발판! 이제 시작이에요.'),
                                                ('성장 중입니다', '당신은 더 이상 초보가 아니에요.'),
                                                ('전설의 지원자', '궁극의 개발자 여정을 완수했습니다. 🎉'),
                                                ('7일의 기적', '대단해요! 일주일 동안 쉬지 않았군요.'),
                                                ('계속해서 도전 중!', '목표를 향한 열정이 느껴져요!'),
                                                ('정리왕 등장', '완벽주의자도 울고 갈 체크 마스터!'),
                                                ('\'J\'스러운 사람', '다양한 목표를 세워 전진 중이에요.'),
                                                ('이력서도 써봤어요', '준비하는 당신, 프로의 자세네요.'),
                                                ('꾸준함의 상징', '진정한 노력가는 당신!'),
                                                ('몰아치기 장인', '놀라워요! 오늘 정말 열일했네요.'),
                                                ('알림 개시자', '알림 기능을 켰어요! 준비 완료~'),
                                                ('🧘 고요한 하루', '알림 기능을 껐어요. 휴식도 중요하죠.');
# ALTER TABLE user MODIFY deleted_at DATE NULL DEFAULT NULL;
# ALTER TABLE user ADD COLUMN interest_id BIGINT;
#
# ALTER TABLE user
#     ADD CONSTRAINT fk_user_interest
#         FOREIGN KEY (interest_id) REFERENCES interest(interest_id);

# 대시보드 주요알림 테스트용 데이터
INSERT INTO goal_company (company_id, company_name, content, status, end_date, user_id)
VALUES (3, '네이버', '알림테스트용', 'DOCUMENT', DATE_ADD(NOW(), INTERVAL 3 DAY), 3);
INSERT INTO goal_company (company_id, company_name, content, status, end_date, user_id)
VALUES (5, '토스', '알림테스트용', 'INTERVIEW_1', DATE_ADD(NOW(), INTERVAL 2 DAY), 3);