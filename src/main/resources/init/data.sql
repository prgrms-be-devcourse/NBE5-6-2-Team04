DROP TABLE IF EXISTS `level`;

CREATE TABLE `level` (
                         `level_id` BIGINT NOT NULL PRIMARY KEY,
                         `level_name` VARCHAR(100) NULL,
                         `level_image_url` VARCHAR(255) NULL,
                         `xp` INT NULL
);
