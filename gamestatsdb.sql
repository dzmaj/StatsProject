-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema gamestatsdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `gamestatsdb` ;

-- -----------------------------------------------------
-- Schema gamestatsdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gamestatsdb` DEFAULT CHARACTER SET utf8 ;
USE `gamestatsdb` ;

-- -----------------------------------------------------
-- Table `difficulty`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `difficulty` ;

CREATE TABLE IF NOT EXISTS `difficulty` (
  `id` TINYINT(4) NOT NULL,
  `name` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gametype`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gametype` ;

CREATE TABLE IF NOT EXISTS `gametype` (
  `id` TINYINT(4) NOT NULL,
  `name` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game` ;

CREATE TABLE IF NOT EXISTS `game` (
  `id` INT(11) NOT NULL,
  `room_type` TINYINT(4) NULL,
  `team_count` INT(11) NULL DEFAULT '0',
  `player_count` INT(11) NULL DEFAULT '0',
  `game_name` VARCHAR(32) NULL,
  `map_name` VARCHAR(64) NULL,
  `gametype_id` TINYINT(4) NULL,
  `difficulty_id` TINYINT(4) NULL,
  `time_limit` INT(11) NULL,
  `planning_time_limit` INT(11) NULL,
  `cooperative` TINYINT(1) NOT NULL,
  `allow_teams` TINYINT(1) NOT NULL,
  `allow_unit_trading` TINYINT(1) NOT NULL,
  `allow_veterans` TINYINT(1) NOT NULL,
  `allow_alliances` TINYINT(1) NOT NULL,
  `overhead_map` TINYINT(1) NOT NULL,
  `deathmatch` TINYINT(1) NOT NULL,
  `vtfl` TINYINT(1) NOT NULL,
  `anti_clump` TINYINT(1) NOT NULL,
  `end_datetime` TIMESTAMP NULL,
  `start_datetime` TIMESTAMP NULL,
  `ended_code` TINYINT(4) NULL,
  `duration` INT(11) NOT NULL,
  `recording_url` VARCHAR(256) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `scoring` (`gametype_id` ASC),
  INDEX `difficulty` (`difficulty_id` ASC),
  CONSTRAINT `metaserver_games_ibfk_1`
    FOREIGN KEY (`gametype_id`)
    REFERENCES `gametype` (`id`),
  CONSTRAINT `metaserver_games_ibfk_2`
    FOREIGN KEY (`difficulty_id`)
    REFERENCES `difficulty` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `game_team`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game_team` ;

CREATE TABLE IF NOT EXISTS `game_team` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `game_id` INT(11) NOT NULL,
  `place` TINYINT(4) NOT NULL,
  `place_tie` TINYINT(1) NOT NULL,
  `spectators` TINYINT(1) NOT NULL,
  `eliminated` TINYINT(1) NOT NULL,
  `team_name` VARCHAR(32) NULL,
  PRIMARY KEY (`id`),
  INDEX `metaserver_games_id` (`game_id` ASC),
  CONSTRAINT `metaserver_games_teams_ibfk_1`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `site_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `site_user` ;

CREATE TABLE IF NOT EXISTS `site_user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `enabled` TINYINT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(500) NOT NULL,
  `email` VARCHAR(500) NULL,
  `bio` VARCHAR(5000) NULL,
  `profile_pic_url` VARCHAR(5000) NULL,
  `role` VARCHAR(45) NULL,
  `creation_timestamp` DATETIME NULL,
  `update_timestamp` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `metaserver_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `metaserver_user` ;

CREATE TABLE IF NOT EXISTS `metaserver_user` (
  `id` INT(11) NOT NULL,
  `nick_name` VARCHAR(32) NULL,
  `team_name` VARCHAR(32) NULL,
  `primary_color` INT(11) NULL,
  `secondary_color` INT(11) NULL,
  `coat_of_arms_bitmap_index` SMALLINT(6) NULL,
  `registration_datetime` DATETIME NULL,
  `last_login_datetime` DATETIME NULL,
  `site_user_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_metaserver_user_site_user1_idx` (`site_user_id` ASC),
  CONSTRAINT `fk_metaserver_user_site_user1`
    FOREIGN KEY (`site_user_id`)
    REFERENCES `site_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `game_player`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game_player` ;

CREATE TABLE IF NOT EXISTS `game_player` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `team_id` INT(11) NOT NULL,
  `user_id` INT(11) NULL DEFAULT NULL,
  `nick_name` VARCHAR(32) NULL,
  `team_name` VARCHAR(32) NULL,
  `primary_color` INT(11) NULL,
  `secondary_color` INT(11) NULL,
  `coat_of_arms_bitmap_index` INT(11) NULL,
  `game_version` INT(11) NULL,
  `build_number` INT(11) NULL,
  `ip_address` VARCHAR(45) NULL,
  `host` TINYINT(1) NOT NULL,
  `captain` TINYINT(1) NOT NULL,
  `dropped` TINYINT(1) NOT NULL,
  `units_killed` INT(11) NOT NULL,
  `units_lost` INT(11) NOT NULL,
  `damage_given` INT(11) NOT NULL,
  `damage_taken` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `metaserver_games_teams_id` (`team_id` ASC),
  INDEX `user_id` (`user_id` ASC),
  CONSTRAINT `metaserver_games_teams_players_ibfk_1`
    FOREIGN KEY (`team_id`)
    REFERENCES `game_team` (`id`),
  CONSTRAINT `metaserver_games_teams_players_ibfk_2`
    FOREIGN KEY (`user_id`)
    REFERENCES `metaserver_user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tournament`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tournament` ;

CREATE TABLE IF NOT EXISTS `tournament` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NULL,
  `description` VARCHAR(5000) NULL,
  `creation_timestamp` DATETIME NULL,
  `update_timestamp` DATETIME NULL,
  `site_user_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tournament_site_user1_idx` (`site_user_id` ASC),
  CONSTRAINT `fk_tournament_site_user1`
    FOREIGN KEY (`site_user_id`)
    REFERENCES `site_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tournament_match`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tournament_match` ;

CREATE TABLE IF NOT EXISTS `tournament_match` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NULL,
  `description` VARCHAR(5000) NULL,
  `tournament_id` INT NOT NULL,
  `scheduled_time` DATETIME NULL,
  `update_timestamp` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tournament_match_tournament1_idx` (`tournament_id` ASC),
  CONSTRAINT `fk_tournament_match_tournament1`
    FOREIGN KEY (`tournament_id`)
    REFERENCES `tournament` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tournament_team`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tournament_team` ;

CREATE TABLE IF NOT EXISTS `tournament_team` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NULL,
  `description` VARCHAR(5000) NULL,
  `tournament_id` INT NOT NULL,
  `pic_url` VARCHAR(5000) NULL,
  `creation_timestamp` DATETIME NULL,
  `update_timestamp` DATETIME NULL,
  `site_user_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tournament_team_tournament1_idx` (`tournament_id` ASC),
  INDEX `fk_tournament_team_site_user1_idx` (`site_user_id` ASC),
  CONSTRAINT `fk_tournament_team_tournament1`
    FOREIGN KEY (`tournament_id`)
    REFERENCES `tournament` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tournament_team_site_user1`
    FOREIGN KEY (`site_user_id`)
    REFERENCES `site_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tournament_game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tournament_game` ;

CREATE TABLE IF NOT EXISTS `tournament_game` (
  `tournament_match_id` INT NOT NULL,
  `game_id` INT(11) NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `note` VARCHAR(500) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tournament_match_has_game_game1_idx` (`game_id` ASC),
  INDEX `fk_tournament_match_has_game_tournament_match1_idx` (`tournament_match_id` ASC),
  CONSTRAINT `fk_tournament_match_has_game_tournament_match1`
    FOREIGN KEY (`tournament_match_id`)
    REFERENCES `tournament_match` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tournament_match_has_game_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `metaserver_user_to_tournament_team`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `metaserver_user_to_tournament_team` ;

CREATE TABLE IF NOT EXISTS `metaserver_user_to_tournament_team` (
  `metaserver_user_id` INT(11) NOT NULL,
  `tournament_team_id` INT NOT NULL,
  PRIMARY KEY (`metaserver_user_id`, `tournament_team_id`),
  INDEX `fk_metaserver_user_has_tournament_team_tournament_team1_idx` (`tournament_team_id` ASC),
  INDEX `fk_metaserver_user_has_tournament_team_metaserver_user1_idx` (`metaserver_user_id` ASC),
  CONSTRAINT `fk_metaserver_user_has_tournament_team_metaserver_user1`
    FOREIGN KEY (`metaserver_user_id`)
    REFERENCES `metaserver_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_metaserver_user_has_tournament_team_tournament_team1`
    FOREIGN KEY (`tournament_team_id`)
    REFERENCES `tournament_team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tournament_team_has_tournament_match`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tournament_team_has_tournament_match` ;

CREATE TABLE IF NOT EXISTS `tournament_team_has_tournament_match` (
  `tournament_team_id` INT NOT NULL,
  `tournament_match_id` INT NOT NULL,
  PRIMARY KEY (`tournament_team_id`, `tournament_match_id`),
  INDEX `fk_tournament_team_has_tournament_match_tournament_match1_idx` (`tournament_match_id` ASC),
  INDEX `fk_tournament_team_has_tournament_match_tournament_team1_idx` (`tournament_team_id` ASC),
  CONSTRAINT `fk_tournament_team_has_tournament_match_tournament_team1`
    FOREIGN KEY (`tournament_team_id`)
    REFERENCES `tournament_team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tournament_team_has_tournament_match_tournament_match1`
    FOREIGN KEY (`tournament_match_id`)
    REFERENCES `tournament_match` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tournament_game_score`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tournament_game_score` ;

CREATE TABLE IF NOT EXISTS `tournament_game_score` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `score` INT NULL,
  `tournament_team_id` INT NOT NULL,
  `game_team_id` INT(11) NOT NULL,
  `tournament_game_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tournament_game_score_tournament_team1_idx` (`tournament_team_id` ASC),
  INDEX `fk_tournament_game_score_game_team1_idx` (`game_team_id` ASC),
  INDEX `fk_tournament_game_score_tournament_game1_idx` (`tournament_game_id` ASC),
  CONSTRAINT `fk_tournament_game_score_tournament_team1`
    FOREIGN KEY (`tournament_team_id`)
    REFERENCES `tournament_team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tournament_game_score_game_team1`
    FOREIGN KEY (`game_team_id`)
    REFERENCES `game_team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tournament_game_score_tournament_game1`
    FOREIGN KEY (`tournament_game_id`)
    REFERENCES `tournament_game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS gamestatsuser;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'gamestatsuser' IDENTIFIED BY 'gamestatsuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'gamestatsuser';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `difficulty`
-- -----------------------------------------------------
START TRANSACTION;
USE `gamestatsdb`;
INSERT INTO `difficulty` (`id`, `name`) VALUES (0, 'Timid');
INSERT INTO `difficulty` (`id`, `name`) VALUES (1, 'Simple');
INSERT INTO `difficulty` (`id`, `name`) VALUES (2, 'Normal');
INSERT INTO `difficulty` (`id`, `name`) VALUES (3, 'Heroic');
INSERT INTO `difficulty` (`id`, `name`) VALUES (4, 'Legendary');

COMMIT;


-- -----------------------------------------------------
-- Data for table `gametype`
-- -----------------------------------------------------
START TRANSACTION;
USE `gamestatsdb`;
INSERT INTO `gametype` (`id`, `name`) VALUES (0, 'Body Count');
INSERT INTO `gametype` (`id`, `name`) VALUES (1, 'Steal the Bacon');
INSERT INTO `gametype` (`id`, `name`) VALUES (2, 'Last Man on the Hill');
INSERT INTO `gametype` (`id`, `name`) VALUES (3, 'Scavenger Hunt');
INSERT INTO `gametype` (`id`, `name`) VALUES (4, 'Flag Rally');
INSERT INTO `gametype` (`id`, `name`) VALUES (5, 'Capture the Flag');
INSERT INTO `gametype` (`id`, `name`) VALUES (6, 'Balls on Parade');
INSERT INTO `gametype` (`id`, `name`) VALUES (7, 'Territories');
INSERT INTO `gametype` (`id`, `name`) VALUES (8, 'Captures');
INSERT INTO `gametype` (`id`, `name`) VALUES (9, 'King of the Hill');
INSERT INTO `gametype` (`id`, `name`) VALUES (10, 'Stampede');
INSERT INTO `gametype` (`id`, `name`) VALUES (11, 'Assassin');
INSERT INTO `gametype` (`id`, `name`) VALUES (12, 'Hunting');
INSERT INTO `gametype` (`id`, `name`) VALUES (13, 'Co-op');
INSERT INTO `gametype` (`id`, `name`) VALUES (14, 'King of the Hill (TFL)');
INSERT INTO `gametype` (`id`, `name`) VALUES (15, 'King of the Map');

COMMIT;


-- -----------------------------------------------------
-- Data for table `game`
-- -----------------------------------------------------
START TRANSACTION;
USE `gamestatsdb`;
INSERT INTO `game` (`id`, `room_type`, `team_count`, `player_count`, `game_name`, `map_name`, `gametype_id`, `difficulty_id`, `time_limit`, `planning_time_limit`, `cooperative`, `allow_teams`, `allow_unit_trading`, `allow_veterans`, `allow_alliances`, `overhead_map`, `deathmatch`, `vtfl`, `anti_clump`, `end_datetime`, `start_datetime`, `ended_code`, `duration`, `recording_url`) VALUES (1, NULL, 4, 4, 'NULL', 'I\'ll Fall On Your Grave', 2, 2, 10800, 1350, 0, 0, 1, 0, 0, 1, 0, 0, 0, '2013-06-05 12:45:44', '2013-06-05 12:38:13', NULL, 13530, NULL);
INSERT INTO `game` (`id`, `room_type`, `team_count`, `player_count`, `game_name`, `map_name`, `gametype_id`, `difficulty_id`, `time_limit`, `planning_time_limit`, `cooperative`, `allow_teams`, `allow_unit_trading`, `allow_veterans`, `allow_alliances`, `overhead_map`, `deathmatch`, `vtfl`, `anti_clump`, `end_datetime`, `start_datetime`, `ended_code`, `duration`, `recording_url`) VALUES (8, NULL, 6, 13, 'NULL', 'Gimble in the Wabe', 1, 2, 14400, 1350, 0, 0, 1, 0, 0, 1, 0, 0, 0, '2013-06-05 13:27:07', '2013-06-05 13:18:07', NULL, 16200, NULL);
INSERT INTO `game` (`id`, `room_type`, `team_count`, `player_count`, `game_name`, `map_name`, `gametype_id`, `difficulty_id`, `time_limit`, `planning_time_limit`, `cooperative`, `allow_teams`, `allow_unit_trading`, `allow_veterans`, `allow_alliances`, `overhead_map`, `deathmatch`, `vtfl`, `anti_clump`, `end_datetime`, `start_datetime`, `ended_code`, `duration`, `recording_url`) VALUES (326507, NULL, 2, 16, 'NULL', 'Barbarian Valley T.E. - Light', 4, 4, 21600, 5400, 0, 1, 1, 0, 0, 1, 0, 0, 0, '2020-11-21 15:04:08', '2020-11-21 14:54:52', NULL, 16680, NULL);
INSERT INTO `game` (`id`, `room_type`, `team_count`, `player_count`, `game_name`, `map_name`, `gametype_id`, `difficulty_id`, `time_limit`, `planning_time_limit`, `cooperative`, `allow_teams`, `allow_unit_trading`, `allow_veterans`, `allow_alliances`, `overhead_map`, `deathmatch`, `vtfl`, `anti_clump`, `end_datetime`, `start_datetime`, `ended_code`, `duration`, `recording_url`) VALUES (326626, NULL, 3, 16, 'NULL', 'Grilling Grounds (light)', 2, 2, 18000, 7200, 0, 1, 1, 0, 0, 1, 0, 0, 0, '2020-11-22 12:39:39', '2020-11-22 12:25:23', NULL, 25680, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `game_team`
-- -----------------------------------------------------
START TRANSACTION;
USE `gamestatsdb`;
INSERT INTO `game_team` (`id`, `game_id`, `place`, `place_tie`, `spectators`, `eliminated`, `team_name`) VALUES (1, 1, 1, 0, 0, 0, 'Sormiron');
INSERT INTO `game_team` (`id`, `game_id`, `place`, `place_tie`, `spectators`, `eliminated`, `team_name`) VALUES (2, 1, 2, 0, 0, 0, '|Bc⁄J⁄I|Iilk |Bc⁄J⁄I|Ian |B◊§t◊');
INSERT INTO `game_team` (`id`, `game_id`, `place`, `place_tie`, `spectators`, `eliminated`, `team_name`) VALUES (3, 1, 3, 0, 0, 0, 'HeadHunter');
INSERT INTO `game_team` (`id`, `game_id`, `place`, `place_tie`, `spectators`, `eliminated`, `team_name`) VALUES (4, 1, 3, 0, 0, 0, 'NewMutator');
INSERT INTO `game_team` (`id`, `game_id`, `place`, `place_tie`, `spectators`, `eliminated`, `team_name`) VALUES (5, 326626, 1, 0, 0, 0, 'Doomsday squad');
INSERT INTO `game_team` (`id`, `game_id`, `place`, `place_tie`, `spectators`, `eliminated`, `team_name`) VALUES (6, 326626, 2, 0, 0, 0, 'Planet Express');
INSERT INTO `game_team` (`id`, `game_id`, `place`, `place_tie`, `spectators`, `eliminated`, `team_name`) VALUES (7, 326626, -1, 0, 1, 0, 'NULL');
INSERT INTO `game_team` (`id`, `game_id`, `place`, `place_tie`, `spectators`, `eliminated`, `team_name`) VALUES (8, 8, 1, 0, 0, 0, 'All Night Long...');
INSERT INTO `game_team` (`id`, `game_id`, `place`, `place_tie`, `spectators`, `eliminated`, `team_name`) VALUES (9, 8, 2, 0, 0, 0, 'Flesh Carvers');
INSERT INTO `game_team` (`id`, `game_id`, `place`, `place_tie`, `spectators`, `eliminated`, `team_name`) VALUES (10, 8, 3, 0, 0, 0, '|ippl that kill you:');
INSERT INTO `game_team` (`id`, `game_id`, `place`, `place_tie`, `spectators`, `eliminated`, `team_name`) VALUES (11, 8, 4, 0, 0, 0, 'Belligerent Pandas');
INSERT INTO `game_team` (`id`, `game_id`, `place`, `place_tie`, `spectators`, `eliminated`, `team_name`) VALUES (12, 8, 5, 0, 0, 0, '|B —Team of Noobs—');
INSERT INTO `game_team` (`id`, `game_id`, `place`, `place_tie`, `spectators`, `eliminated`, `team_name`) VALUES (13, 8, 5, 0, 0, 0, 'Super Peachie ducks');
INSERT INTO `game_team` (`id`, `game_id`, `place`, `place_tie`, `spectators`, `eliminated`, `team_name`) VALUES (14, 326507, 1, 0, 0, 0, 'Doomsday squad');
INSERT INTO `game_team` (`id`, `game_id`, `place`, `place_tie`, `spectators`, `eliminated`, `team_name`) VALUES (15, 326507, 2, 0, 0, 0, '|iP-Familiarness');

COMMIT;


-- -----------------------------------------------------
-- Data for table `site_user`
-- -----------------------------------------------------
START TRANSACTION;
USE `gamestatsdb`;
INSERT INTO `site_user` (`id`, `enabled`, `username`, `password`, `email`, `bio`, `profile_pic_url`, `role`, `creation_timestamp`, `update_timestamp`) VALUES (1, 1, 'admin', 'admin', 'admin@email.com', 'this is a biography', 'http://picsum.photos/200', 'admin', '2020-12-30 14:54:52', '2020-12-30 14:54:52');

COMMIT;


-- -----------------------------------------------------
-- Data for table `metaserver_user`
-- -----------------------------------------------------
START TRANSACTION;
USE `gamestatsdb`;
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (60, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (64, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (71, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (74, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (75, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (83, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (154, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (172, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (214, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (218, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (262, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (423, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (438, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (439, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (517, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (657, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (1077, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (1078, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (1407, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (2015, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (70629, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (70643, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (70660, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (70698, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (70718, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (70748, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (70786, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (70940, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (71071, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (71158, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (71184, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (71185, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (71258, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (71428, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (71441, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (71536, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (71656, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `metaserver_user` (`id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `registration_datetime`, `last_login_datetime`, `site_user_id`) VALUES (71739, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `game_player`
-- -----------------------------------------------------
START TRANSACTION;
USE `gamestatsdb`;
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (1, 1, 438, 'Sormiron', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, 14, 13, 40, 43);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (2, 2, NULL, '|Bc⁄J⁄I|Iilk |Bc⁄J⁄I|Ian |B◊§t◊', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, 0, 11, 17, 38, 51);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (3, 3, NULL, 'HeadHunter', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, 16, 14, 37, 46);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (4, 4, 83, 'NewMutator', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, 15, 18, 42, 54);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (5, 5, 218, 'Shad', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, 5, 9, 31, 28);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (6, 5, 70629, '|iV A S A Z E L ®', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 11, 8, 33, 24);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (7, 5, 70786, 'Professional Killer', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 9, 7, 50, 25);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (8, 5, 64, 'Funk', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 9, 3, 25, 18);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (9, 5, 60, 'hmp', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 6, 9, 30, 40);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (10, 5, 70940, 'Gekko', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 4, 3, 34, 21);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (11, 5, 154, 'Midget Killer Crun', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 4, 2, 14, 10);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (12, 6, 70718, '|iProfessor PlanEx', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, 0, 10, 3, 22);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (13, 6, 71185, '|iZapp |p PlanEx', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 10, 4, 32, 29);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (14, 6, 71184, 'Gheng Bender', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 9, 2, 20, 48);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (15, 6, 71536, 'Hypnotoad PlantEx', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 8, 11, 36, 40);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (16, 6, 71071, '|iHermes |pPlanEx', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 6, 7, 27, 30);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (17, 6, 70660, 'Zakberg', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 4, 11, 28, 38);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (18, 6, 70698, 'Kif Kroker', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 1, 3, 13, 15);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (19, 7, 71158, 'karma', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, 0, 0, 0, 0);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (20, 7, 657, 'bradblocké', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 1, 0, 0, 0, 0, 0, 0);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (21, 8, NULL, 'WTF', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, 1, 2, 4, 7);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (22, 8, 439, 'Isolder', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 10, 10, 27, 33);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (23, 9, 172, 'SamTheButcher', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, 9, 10, 32, 33);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (24, 9, 1077, 'Idles', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 8, 1, 25, 5);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (25, 10, 75, 'adrenaline', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, 4, 4, 26, 17);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (26, 10, 423, 'mike', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 12, 4, 29, 19);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (27, 11, 438, 'Sormiron', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, 0, 9, 4, 28);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (28, 11, 1078, 'zagon', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 2, 2, 7, 8);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (29, 11, NULL, '|Bc⁄J⁄I|Iilk |Bc⁄J⁄I|Ian |B◊§t◊', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 1, 0, 0, 1, 6, 4, 20);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (30, 12, 83, 'NewMutator', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, 6, 5, 20, 20);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (31, 12, NULL, 'HeadHunter', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 11, 6, 47, 34);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (32, 13, NULL, 'renwood', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, 1, 10, -2, 31);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (33, 13, 214, 'Pallidice', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 3, 8, 14, 34);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (34, 14, 218, 'Shad', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, 5, 17, 14, 44);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (35, 14, 71, 'Giant Killer General', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 14, 14, 26, 25);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (36, 14, 60, 'hmp', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 11, 9, 26, 18);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (37, 14, 70786, 'Professional Killer', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 10, 11, 23, 22);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (38, 14, 71656, '|iSei', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 8, 0, 14, 4);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (39, 14, 262, 'spoon', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 3, 0, 12, 3);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (40, 14, 71441, 'Empy -!-', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 3, 1, 8, 7);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (41, 14, 1407, '|iWalter Wight', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 2, 1, 9, 5);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (42, 15, 74, '|bPårı$-73 G2.MYTH', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, 19, 25, 4, 63);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (43, 15, 2015, 'pussy limps', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 1, 12, 15, 22, 30);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (44, 15, 71739, 'zoobee|idoo', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 7, 5, 26, 13);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (45, 15, 71428, 'Reekfish', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 2, 5, 6, 14);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (46, 15, 517, '|iKillerKing T-Flex', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 1, 0, 0, 2, 3, 5, 12);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (47, 15, 70748, 'TheCandyDude', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 2, 4, 5, 12);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (48, 15, 71258, '|ispy', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 6, 6, 17);
INSERT INTO `game_player` (`id`, `team_id`, `user_id`, `nick_name`, `team_name`, `primary_color`, `secondary_color`, `coat_of_arms_bitmap_index`, `game_version`, `build_number`, `ip_address`, `host`, `captain`, `dropped`, `units_killed`, `units_lost`, `damage_given`, `damage_taken`) VALUES (49, 15, 70643, 'Father Xmas', 'NULL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 2, 3, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `tournament`
-- -----------------------------------------------------
START TRANSACTION;
USE `gamestatsdb`;
INSERT INTO `tournament` (`id`, `name`, `description`, `creation_timestamp`, `update_timestamp`, `site_user_id`) VALUES (1, 'test tournament', 'test', '2020-11-21 14:54:52', '2020-11-21 14:54:52', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `tournament_match`
-- -----------------------------------------------------
START TRANSACTION;
USE `gamestatsdb`;
INSERT INTO `tournament_match` (`id`, `name`, `description`, `tournament_id`, `scheduled_time`, `update_timestamp`) VALUES (1, 'test match', 'test match description', 1, '2020-11-21 14:54:52', '2020-11-21 14:54:52');

COMMIT;


-- -----------------------------------------------------
-- Data for table `tournament_team`
-- -----------------------------------------------------
START TRANSACTION;
USE `gamestatsdb`;
INSERT INTO `tournament_team` (`id`, `name`, `description`, `tournament_id`, `pic_url`, `creation_timestamp`, `update_timestamp`, `site_user_id`) VALUES (1, 'test team 1', 'test description', 1, 'http://picsum.photos/200', '2020-11-21 14:54:52', '2020-11-21 14:54:52', 1);
INSERT INTO `tournament_team` (`id`, `name`, `description`, `tournament_id`, `pic_url`, `creation_timestamp`, `update_timestamp`, `site_user_id`) VALUES (2, 'test team 2', 'test description 2', 1, 'http://picsum.photos/200', '2020-11-21 14:54:52', '2020-11-21 14:54:52', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `tournament_game`
-- -----------------------------------------------------
START TRANSACTION;
USE `gamestatsdb`;
INSERT INTO `tournament_game` (`tournament_match_id`, `game_id`, `id`, `note`) VALUES (1, 326507, 1, 'test note for tournament game');

COMMIT;


-- -----------------------------------------------------
-- Data for table `metaserver_user_to_tournament_team`
-- -----------------------------------------------------
START TRANSACTION;
USE `gamestatsdb`;
INSERT INTO `metaserver_user_to_tournament_team` (`metaserver_user_id`, `tournament_team_id`) VALUES (218, 1);
INSERT INTO `metaserver_user_to_tournament_team` (`metaserver_user_id`, `tournament_team_id`) VALUES (71, 1);
INSERT INTO `metaserver_user_to_tournament_team` (`metaserver_user_id`, `tournament_team_id`) VALUES (60, 1);
INSERT INTO `metaserver_user_to_tournament_team` (`metaserver_user_id`, `tournament_team_id`) VALUES (70786, 1);
INSERT INTO `metaserver_user_to_tournament_team` (`metaserver_user_id`, `tournament_team_id`) VALUES (71656, 1);
INSERT INTO `metaserver_user_to_tournament_team` (`metaserver_user_id`, `tournament_team_id`) VALUES (262, 1);
INSERT INTO `metaserver_user_to_tournament_team` (`metaserver_user_id`, `tournament_team_id`) VALUES (71441, 1);
INSERT INTO `metaserver_user_to_tournament_team` (`metaserver_user_id`, `tournament_team_id`) VALUES (1407, 1);
INSERT INTO `metaserver_user_to_tournament_team` (`metaserver_user_id`, `tournament_team_id`) VALUES (74, 2);
INSERT INTO `metaserver_user_to_tournament_team` (`metaserver_user_id`, `tournament_team_id`) VALUES (2015, 2);
INSERT INTO `metaserver_user_to_tournament_team` (`metaserver_user_id`, `tournament_team_id`) VALUES (71739, 2);
INSERT INTO `metaserver_user_to_tournament_team` (`metaserver_user_id`, `tournament_team_id`) VALUES (71428, 2);
INSERT INTO `metaserver_user_to_tournament_team` (`metaserver_user_id`, `tournament_team_id`) VALUES (517, 2);
INSERT INTO `metaserver_user_to_tournament_team` (`metaserver_user_id`, `tournament_team_id`) VALUES (70748, 2);
INSERT INTO `metaserver_user_to_tournament_team` (`metaserver_user_id`, `tournament_team_id`) VALUES (71258, 2);
INSERT INTO `metaserver_user_to_tournament_team` (`metaserver_user_id`, `tournament_team_id`) VALUES (70643, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `tournament_team_has_tournament_match`
-- -----------------------------------------------------
START TRANSACTION;
USE `gamestatsdb`;
INSERT INTO `tournament_team_has_tournament_match` (`tournament_team_id`, `tournament_match_id`) VALUES (1, 1);
INSERT INTO `tournament_team_has_tournament_match` (`tournament_team_id`, `tournament_match_id`) VALUES (2, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `tournament_game_score`
-- -----------------------------------------------------
START TRANSACTION;
USE `gamestatsdb`;
INSERT INTO `tournament_game_score` (`id`, `score`, `tournament_team_id`, `game_team_id`, `tournament_game_id`) VALUES (1, 1, 1, 14, 1);
INSERT INTO `tournament_game_score` (`id`, `score`, `tournament_team_id`, `game_team_id`, `tournament_game_id`) VALUES (2, 0, 2, 15, 1);

COMMIT;

