-- -----------------------------------------------------
-- Table `baseball`.`team`
-- -----------------------------------------------------
INSERT INTO team (name) VALUE ('한화');
INSERT INTO team (name) VALUE ('두산');
INSERT INTO team (name) VALUE ('LG');
INSERT INTO team (name) VALUE ('삼성');
INSERT INTO team (name) VALUE ('기아');
INSERT INTO team (name) VALUE ('롯데');

-- -----------------------------------------------------
-- Table `baseball`.`player`
-- -----------------------------------------------------
INSERT INTO player (team_id, name, batting_average, is_pitcher) VALUE (1, '손광민', 0.714, false);
