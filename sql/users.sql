CREATE TABLE users(
	id INT PRIMARY KEY AUTO_INCREMENT,
	email VARCHAR(100) NOT NULL,
	password VARCHAR(100) NOT NULL,
	name VARCHAR(100) NOT NULL DEFAULT ''
);


INSERT INTO users (email, password, name) VALUES('yuichi.sudo@ssie.jp', MD5('yuichi.sudo@ssie.jp'), '須藤雄一');
INSERT INTO users (email, password, name) VALUES('ono@ssie.jp', MD5('ono@ssie.jp'), '小野茂');
INSERT INTO users (email, password, name) VALUES('tou@ssie.jp', MD5('tou@ssie.jp'), '藤正幸');
