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

-- 삼성 --
INSERT INTO player (team_id, name, batting_average, is_pitcher) VALUE (4, '김동엽', 0.897, false);
INSERT INTO player (team_id, name, batting_average, is_pitcher) VALUE (4, '김상수', 0.292, false);
INSERT INTO player (team_id, name, batting_average, is_pitcher) VALUE (4, '살라디노', 0.281, false);
INSERT INTO player (team_id, name, batting_average, is_pitcher) VALUE (4, '박해민', 0.273, false);
INSERT INTO player (team_id, name, batting_average, is_pitcher) VALUE (4, '이성규', 0.271, false);
INSERT INTO player (team_id, name, batting_average, is_pitcher) VALUE (4, '이원석', 0.267, false);
INSERT INTO player (team_id, name, batting_average, is_pitcher) VALUE (4, '강민호', 0.262, false);
INSERT INTO player (team_id, name, batting_average, is_pitcher) VALUE (4, '구자욱', 0.257, false);
INSERT INTO player (team_id, name, batting_average, is_pitcher) VALUE (4, '김헌', 0.256, true);

-- 기아 --
INSERT INTO player (team_id, name, batting_average, is_pitcher) VALUE (5, '안치홍', 0.815, true);
INSERT INTO player (team_id, name, batting_average, is_pitcher) VALUE (5, '터커', 0.311, false);
INSERT INTO player (team_id, name, batting_average, is_pitcher) VALUE (5, '황윤호', 0.301, false);
INSERT INTO player (team_id, name, batting_average, is_pitcher) VALUE (5, '최형우', 0.300, false);
INSERT INTO player (team_id, name, batting_average, is_pitcher) VALUE (5, '한준수', 0.300, false);
INSERT INTO player (team_id, name, batting_average, is_pitcher) VALUE (5, '김주찬', 0.300, false);
INSERT INTO player (team_id, name, batting_average, is_pitcher) VALUE (5, '김선빈', 0.292, false);
INSERT INTO player (team_id, name, batting_average, is_pitcher) VALUE (5, '유민상', 0.291, false);
INSERT INTO player (team_id, name, batting_average, is_pitcher) VALUE (5, '이정', 0.286, true);

-- 롯데 --
INSERT INTO player (team_id, name, batting_average, is_pitcher) VALUE (6, '민병헌', 0.804, false);
INSERT INTO player (team_id, name, batting_average, is_pitcher) VALUE (6, '전준우', 0.301, false);
INSERT INTO player (team_id, name, batting_average, is_pitcher) VALUE (6, '손아섭', 0.295, false);
INSERT INTO player (team_id, name, batting_average, is_pitcher) VALUE (6, '김동한', 0.285, false);
INSERT INTO player (team_id, name, batting_average, is_pitcher) VALUE (6, '정준혁', 0.273, false);
INSERT INTO player (team_id, name, batting_average, is_pitcher) VALUE (6, '신본기', 0.256, false);
INSERT INTO player (team_id, name, batting_average, is_pitcher) VALUE (6, '허일', 0.255, false);
INSERT INTO player (team_id, name, batting_average, is_pitcher) VALUE (6, '고승민', 0.253, false);
INSERT INTO player (team_id, name, batting_average, is_pitcher) VALUE (6, '윌슨', 0.252, true);