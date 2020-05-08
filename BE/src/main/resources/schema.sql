DROP TABLE IF EXISTS `myDB`.`team`;
DROP TABLE IF EXISTS `myDB`.`player`;
DROP TABLE IF EXISTS `myDB`.`record`;
DROP TABLE IF EXISTS `myDB`.`game`;
DROP TABLE IF EXISTS `myDB`.`inning`;
DROP TABLE IF EXISTS `myDB`.`half`;
DROP TABLE IF EXISTS `myDB`.`plate`;
DROP TABLE IF EXISTS `myDB`.`round`;
DROP TABLE IF EXISTS `myDB`.`match_table`;


-- -----------------------------------------------------
-- Table `baseball`.`team`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myDB`.`team`
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45)
);

-- -----------------------------------------------------
-- Table `baseball`.`player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myDB`.`player`
(
    number          INT PRIMARY KEY AUTO_INCREMENT,
    team_id       VARCHAR(45) REFERENCES team (id),
    name            VARCHAR(45),
    batting_average DOUBLE,
    is_pitcher      BOOLEAN
);

-- -----------------------------------------------------
-- Table `baseball`.`record`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myDB`.`record`
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    player_number     VARCHAR(32) REFERENCES player (number),
    mount           INT,
    hit             INT,
    strike          INT,
    ball            INT,
    `out`           INT,
    batting_average DOUBLE
);

-- -----------------------------------------------------
-- Table `baseball`.`game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myDB`.`game`
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    home_team VARCHAR(45) REFERENCES team (id),
    away_team VARCHAR(45) REFERENCES team (id),
    user_team VARCHAR(45)
);

-- -----------------------------------------------------
-- Table `baseball`.`inning`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myDB`.`inning`
(
    id             INT PRIMARY KEY AUTO_INCREMENT,
    game_id        INT REFERENCES game (id),
    first_half_id  INT REFERENCES half (id),
    second_half_id INT REFERENCES half (id),
    half           ENUM ('초', '말') DEFAULT '초'
);

-- -----------------------------------------------------
-- Table `baseball`.`half`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myDB`.`half`
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    last_bat_player INT,
    total_plate     INT DEFAULT 0,
    `out`           INT DEFAULT 0,
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
    id             INT PRIMARY KEY AUTO_INCREMENT,
    half_id        INT REFERENCES half (id),
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
    id          INT PRIMARY KEY AUTO_INCREMENT,
    plate_id    INT REFERENCES plate (id),
    player_name VARCHAR(45) NOT NULL,
    strike      INT DEFAULT 0,
    ball        INT DEFAULT 0,
    `out`       INT DEFAULT 0,
    hit_or_out  ENUM ('안타', '아웃')
);

-- -----------------------------------------------------
-- Table `baseball`.`MATCH_TABLE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myDB`.`match_table`
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    home_team   VARCHAR(45) NULL,
    away_team   VARCHAR(45) NULL,
    is_selected BOOLEAN DEFAULT FALSE
);
