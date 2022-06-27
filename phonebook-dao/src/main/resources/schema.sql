CREATE TABLE IF NOT EXISTS PHONE_NUMBER (
	NUMBER_ID BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	TYPE VARCHAR(15) NOT NULL,
	NUMBER VARCHAR(15) NOT NULL,
	CARD_ID BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS BUSINESS_CARD (
	CARD_ID BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	FIRSTNAME VARCHAR(30) NOT NULL,
	LASTNAME VARCHAR(30) NOT NULL
);