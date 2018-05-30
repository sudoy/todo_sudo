CREATE TABLE todos(
	id INT PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(100) NOT NULL,
	detail TEXT NOT NULL,
	importance INT NOT NULL DEFAULT 3,
	limit_date DATE
);

INSERT INTO todos (title, detail, importance, limit_date) VALUES('テストテスト1', 'SQLの確認テストの採点と報告書を作成する。', '3', '2015-06-15');
INSERT INTO todos (title, detail, importance, limit_date) VALUES('テストテスト2', '報告書を作成する。', '1', '2015-06-20');
INSERT INTO todos (title, detail, importance, limit_date) VALUES('テストテスト3', 'hogehoge', '1', NULL);

