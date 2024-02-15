CREATE DATABASE Ireflix_pro;

USE Ireflix_pro;

CREATE TABLE IF NOT EXISTS nationality (
	`id` int UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` varchar(35) NOT NULL DEFAULT '',
	UNIQUE (`name`),
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS genres (
	`id` int UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` varchar(20) NOT NULL DEFAULT '',
	UNIQUE (`name`),
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS actors (
	`id` int UNSIGNED NOT NULL AUTO_INCREMENT,
	`nationality_id` int UNSIGNED NOT NULL DEFAULT '0',
	`name` varchar(35) NOT NULL DEFAULT '',
	`surname` varchar(35) NOT NULL DEFAULT '',
	UNIQUE (`surname`),
	PRIMARY KEY (`id`),
	FOREIGN KEY (nationality_id) REFERENCES nationality(id)
);

CREATE TABLE IF NOT EXISTS directors (
	`id` int UNSIGNED NOT NULL AUTO_INCREMENT,
	`nationality_id` int UNSIGNED NOT NULL DEFAULT '0',
	`name` varchar(35) NOT NULL DEFAULT '',
	`surname` varchar(35) NOT NULL DEFAULT '',
	UNIQUE (`surname`),
	PRIMARY KEY (`id`),
	FOREIGN KEY (nationality_id) REFERENCES nationality(id)
);