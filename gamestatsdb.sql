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
  `team_count` INT(11) NOT NULL DEFAULT '0',
  `player_count` INT(11) NOT NULL DEFAULT '0',
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
  `ended_code` TINYINT(4) NOT NULL,
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
  `registration_datetime` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `last_login_datetime` TIMESTAMP NULL,
  PRIMARY KEY (`id`))
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
  PRIMARY KEY (`id`))
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
  PRIMARY KEY (`id`),
  INDEX `fk_tournament_team_tournament1_idx` (`tournament_id` ASC),
  CONSTRAINT `fk_tournament_team_tournament1`
    FOREIGN KEY (`tournament_id`)
    REFERENCES `tournament` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tournament_match_to_game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tournament_match_to_game` ;

CREATE TABLE IF NOT EXISTS `tournament_match_to_game` (
  `tournament_match_id` INT NOT NULL,
  `game_id` INT(11) NOT NULL,
  PRIMARY KEY (`tournament_match_id`, `game_id`),
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
-- Table `game_team_to_tournament_team`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game_team_to_tournament_team` ;

CREATE TABLE IF NOT EXISTS `game_team_to_tournament_team` (
  `team_id` INT(11) NOT NULL,
  `tournament_team_id` INT NOT NULL,
  PRIMARY KEY (`team_id`, `tournament_team_id`),
  INDEX `fk_team_has_tournament_team_tournament_team1_idx` (`tournament_team_id` ASC),
  INDEX `fk_team_has_tournament_team_team1_idx` (`team_id` ASC),
  CONSTRAINT `fk_team_has_tournament_team_team1`
    FOREIGN KEY (`team_id`)
    REFERENCES `game_team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_team_has_tournament_team_tournament_team1`
    FOREIGN KEY (`tournament_team_id`)
    REFERENCES `tournament_team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


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

SET SQL_MODE = '';
DROP USER IF EXISTS gamestatsuser;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'gamestatsuser' IDENTIFIED BY 'gamestatsuser';

GRANT SELECT, INSERT, TRIGGER ON TABLE * TO 'gamestatsuser';

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

