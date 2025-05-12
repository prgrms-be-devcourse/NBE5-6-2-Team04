INSERT INTO level (level_id, level_name, level_image_url, xp)
VALUES (1, 'Lv.1', '/images/level/lv1.png', 0);

INSERT INTO user (
    level_id,
    user_image,
    email,
    password,
    nickname,
    role,
    exp,
    profile_image_url,
    created_at,
    comment
) VALUES (
             1, -- level_id: level 테이블에 미리 level_id=1 있어야 함
             'default.png',
             'dummy@example.com',
             '1234', -- 평문 비밀번호 (임시)
             '임시유저',
             'USER',
             0,
             '/images/profile/default.png',
             NOW(),
             '테스트용 유저입니다'
         );