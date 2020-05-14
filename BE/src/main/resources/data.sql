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
-- USER TEST --
-- -----------------------------------------------------
INSERT INTO user (id, team_id, user_id, email) VALUE (1, 2, 'ever', 'evwe@gmail.com');
INSERT INTO user (id, team_id, user_id, email) VALUE (2, 1, 'solar', 'slar@gmail.com');
INSERT INTO user (id, team_id, user_id, email) VALUE (3, 4, 'jason', 'jswe@gmail.com');

-- -----------------------------------------------------
-- MATCHES TEST --
-- -----------------------------------------------------
INSERT INTO matches (id, a_user_id, b_user_id, home_team, away_team) VALUE (1, 1, 2, '한화', '두산');
INSERT INTO matches (id, a_user_id, b_user_id, home_team, away_team) VALUE (2, 3, null, 'LG', '삼성');
INSERT INTO matches (id, a_user_id, b_user_id, home_team, away_team) VALUE (3, null, null, '기아', '롯데');

-- -----------------------------------------------------
-- GAME TEST --
-- -----------------------------------------------------
INSERT INTO game (id, matches_id) VALUE (1, 1);

-- -----------------------------------------------------
-- INNING TEST --
-- -----------------------------------------------------

INSERT INTO inning (id, game_id, first_half_id, second_half_id, half) VALUE (1, 1, 1, null, true);

-- -----------------------------------------------------
-- HALF TEST --
-- -----------------------------------------------------
INSERT INTO half (id, last_bat_player, total_plate, outs, hit, point) VALUE (1, 1, 0, 1, 1, 1);

-- -----------------------------------------------------
-- PLATE TEST --
-- -----------------------------------------------------
INSERT INTO plate (id, half_id, strike, ball, first_baseman, second_baseman,
                   third_baseman) VALUE (1, 1, 0, 0, '손광민', null, null);
INSERT INTO plate (id, half_id, strike, ball, first_baseman, second_baseman,
                   third_baseman) VALUE (2, 3, 1, 0, '손광민', null, null);

-- -----------------------------------------------------
-- ROUND TEST --
-- -----------------------------------------------------
INSERT INTO round (id, plate_id, player_name, strike, ball, hit_or_out) VALUE (1, 1, '박건우', 0, 0, '안타');
INSERT INTO round (id, plate_id, player_name, strike, ball, hit_or_out) VALUE (2, 1, '이홍련', 1, 0, null);
INSERT INTO round (id, plate_id, player_name, strike, ball, hit_or_out) VALUE (3, 1, '이홍련', 1, 0, null);
INSERT INTO round (id, plate_id, player_name, strike, ball, hit_or_out) VALUE (4, 1, '이홍련', 1, 0, null);
INSERT INTO round (id, plate_id, player_name, strike, ball, hit_or_out) VALUE (5, 1, '이홍련', 0, 0, '아웃');

-- -----------------------------------------------------
-- Table `baseball`.`player`
-- -----------------------------------------------------

-- 한화 --
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (1, 1, '손광민', 0.714, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (2, 1, '김태균', 0.400, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (3, 1, '정진호', 0.167, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (4, 1, '하주석', 0.167, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (5, 1, '이성열', 0.305, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (6, 1, '호잉', 0.284, true);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (7, 1, '양성우', 0168., false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (8, 1, '이창열', 0.154, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (9, 1, '최윤석', 0.250, false);
-- 두산 --
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (1, 2, '박건우', 0.319, true);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (2, 2, '이홍련', 0.310, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (3, 2, '오재일', 0.293, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (4, 2, '허경민', 0.288, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (5, 2, '김재환', 0.283, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (6, 2, '박세혁', 0.279, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (7, 2, '이유찬', 0.231, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (8, 2, '김경호', 0.192, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (9, 2, '오재원', 0.164, false);
-- LG --
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (1, 3, '채은성', 0.315, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (2, 3, '이천웅', 0.308, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (3, 3, '김현수', 0.304, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (4, 3, '페게로', 0.286, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (5, 3, '이형종', 0.274, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (6, 3, '박용택', 0.522, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (7, 3, '조셉', 0.333, true);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (8, 3, '유강남', 0.111, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (9, 3, '김용의', 0.164, false);
-- 삼성 --
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (1, 4, '김동엽', 0.897, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (2, 4, '김상수', 0.292, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (3, 4, '살라디노', 0.281, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (4, 4, '박해민', 0.273, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (5, 4, '이성규', 0.271, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (6, 4, '이원석', 0.267, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (7, 4, '강민호', 0.262, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (8, 4, '구자욱', 0.257, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (9, 4, '김헌', 0.256, true);
-- 기아 --
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (1, 5, '안치홍', 0.815, true);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (2, 5, '터커', 0.311, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (3, 5, '황윤호', 0.301, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (4, 5, '최형우', 0.300, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (5, 5, '한준수', 0.300, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (6, 5, '김주찬', 0.300, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (7, 5, '김선빈', 0.292, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (8, 5, '유민상', 0.291, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (9, 5, '이정', 0.286, true);
-- 롯데 --
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (1, 6, '민병헌', 0.804, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (2, 6, '전준우', 0.301, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (3, 6, '손아섭', 0.295, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (4, 6, '김동한', 0.285, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (5, 6, '정준혁', 0.273, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (6, 6, '신본기', 0.256, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (7, 6, '허일', 0.255, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (8, 6, '고승민', 0.253, false);
INSERT INTO player (line_up, team_id, name, batting_average, is_pitcher) VALUE (9, 6, '윌슨', 0.252, true);

-- ---------------------------------------------------------
-- record 세팅
-- ---------------------------------------------------------
INSERT INTO record (player_id, pitch, mounts, hit, strike, ball, outs) VALUE (1, 0, 3, 0, 9, 2, 3);
INSERT INTO record (player_id, pitch, mounts, hit, strike, ball, outs) VALUE (2, 0, 3, 0, 3, 2, 1);
INSERT INTO record (player_id, pitch, mounts, hit, strike, ball, outs) VALUE (3, 0, 3, 2, 8, 5, 2);
INSERT INTO record (player_id, pitch, mounts, hit, strike, ball, outs) VALUE (4, 0, 3, 1, 2, 5, 0);
INSERT INTO record (player_id, pitch, mounts, hit, strike, ball, outs) VALUE (5, 0, 2, 0, 6, 1, 2);
INSERT INTO record (player_id, pitch, mounts, hit, strike, ball, outs) VALUE (6, 10, 2, 1, 2, 4, 2);
INSERT INTO record (player_id, pitch, mounts, hit, strike, ball, outs) VALUE (7, 0, 2, 2, 3, 2, 0);
INSERT INTO record (player_id, pitch, mounts, hit, strike, ball, outs) VALUE (8, 0, 2, 0, 3, 9, 1);
INSERT INTO record (player_id, pitch, mounts, hit, strike, ball, outs) VALUE (9, 0, 2, 1, 3, 2, 1);
INSERT INTO record (player_id, pitch, mounts, hit, strike, ball, outs) VALUE (10, 20, 2, 1, 3, 2, 1);
INSERT INTO record (player_id, pitch, mounts, hit, strike, ball, outs) VALUE (11, 0, 3, 0, 3, 2, 1);
INSERT INTO record (player_id, pitch, mounts, hit, strike, ball, outs) VALUE (12, 0, 3, 1, 2, 5, 0);
INSERT INTO record (player_id, pitch, mounts, hit, strike, ball, outs) VALUE (13, 0, 3, 0, 9, 2, 3);
INSERT INTO record (player_id, pitch, mounts, hit, strike, ball, outs) VALUE (14, 0, 2, 0, 3, 9, 1);
INSERT INTO record (player_id, pitch, mounts, hit, strike, ball, outs) VALUE (15, 0, 2, 0, 6, 1, 2);
INSERT INTO record (player_id, pitch, mounts, hit, strike, ball, outs) VALUE (16, 0, 3, 2, 8, 5, 2);
INSERT INTO record (player_id, pitch, mounts, hit, strike, ball, outs) VALUE (17, 0, 2, 2, 3, 2, 0);
INSERT INTO record (player_id, pitch, mounts, hit, strike, ball, outs) VALUE (18, 0, 2, 2, 3, 2, 0);
