DROP TABLE IF EXISTS `myDB`.`user`;
DROP TABLE IF EXISTS `myDB`.`matches`;
DROP TABLE IF EXISTS `myDB`.`team`;
DROP TABLE IF EXISTS `myDB`.`player`;
DROP TABLE IF EXISTS `myDB`.`record`;
DROP TABLE IF EXISTS `myDB`.`game`;
DROP TABLE IF EXISTS `myDB`.`inning`;
DROP TABLE IF EXISTS `myDB`.`half`;
DROP TABLE IF EXISTS `myDB`.`plate`;
DROP TABLE IF EXISTS `myDB`.`round`;

-- -----------------------------------------------------
-- Table `baseball`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myDB`.`user`
(
    id      BIGINT PRIMARY KEY AUTO_INCREMENT,
    team_id BIGINT REFERENCES team (id),
    user_id VARCHAR(32),
    email   VARCHAR(45) UNIQUE
);

-- -----------------------------------------------------
-- Table `baseball`.`MATCHES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myDB`.`matches`
(
    id        BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_a   BIGINT REFERENCES user (id),
    user_b   BIGINT REFERENCES user (id),
    home_team VARCHAR(45) NULL,
    away_team VARCHAR(45) NULL
);

-- -----------------------------------------------------
-- Table `baseball`.`team`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myDB`.`team`
(
    id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45)
);

-- -----------------------------------------------------
-- Table `baseball`.`player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myDB`.`player`
(
    id              BIGINT PRIMARY KEY AUTO_INCREMENT,
    team_id         BIGINT REFERENCES team (id),
    name            VARCHAR(45),
    batting_average DOUBLE,
    is_pitcher      BOOLEAN
);

-- -----------------------------------------------------
-- Table `baseball`.`record`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myDB`.`record`
(
    id              BIGINT PRIMARY KEY AUTO_INCREMENT,
    player_number   BIGINT REFERENCES player (id),
    mount           INT,
    hit             INT,
    strike          INT,
    ball            INT,
    outs            INT,
    batting_average DOUBLE
);

-- -----------------------------------------------------
-- Table `baseball`.`game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myDB`.`game`
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    matches_id BIGINT REFERENCES matches (id)
);

-- -----------------------------------------------------
-- Table `baseball`.`inning`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myDB`.`inning`
(
    id             BIGINT PRIMARY KEY AUTO_INCREMENT,
    game_id        BIGINT REFERENCES game (id),
    first_half_id  BIGINT REFERENCES half (id),
    second_half_id BIGINT REFERENCES half (id),
    half           ENUM ('초', '말') DEFAULT '초'
);

-- -----------------------------------------------------
-- Table `baseball`.`half`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myDB`.`half`
(
    id              BIGINT PRIMARY KEY AUTO_INCREMENT,
    last_bat_player INT,
    total_plate     INT DEFAULT 0,
    outs            INT DEFAULT 0,
    hit             INT DEFAULT 0,
    point           INT DEFAULT 0
);

-- -----------------------------------------------------
-- Table `baseball`.`plate`
-- 타자 별 기록 (타자 교체 시기)
-- 현재 타자 정보를 안가지고 있어도 되나? round에서 가지고 있어서?
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myDB`.`plate`
(
    id             BIGINT PRIMARY KEY AUTO_INCREMENT,
    half_id        BIGINT REFERENCES half (id),
    strike         INT DEFAULT 0,
    ball           INT DEFAULT 0,
    first_baseman  VARCHAR(45) NULL,
    second_baseman VARCHAR(45) NULL,
    third_baseman  VARCHAR(45) NULL
);

-- -----------------------------------------------------
-- Table `baseball`.`round`
-- 선수의 투구별 기록
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myDB`.`round`
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    plate_id    BIGINT REFERENCES plate (id),
    player_name VARCHAR(45) NOT NULL,
    strike      INT DEFAULT 0,
    ball        INT DEFAULT 0,
    hit_or_out  ENUM ('안타', '아웃')
);


