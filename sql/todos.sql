CREATE TABLE todos(
	id INT PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(100) NOT NULL,
	detail TEXT NOT NULL,
	importance INT NOT NULL DEFAULT 3,
	limit_date DATE
);

INSERT INTO todos (title, detail, importance, limit_date) VALUES('�e�X�g�e�X�g1', 'SQL�̊m�F�e�X�g�̍̓_�ƕ񍐏����쐬����B', '3', '2015-06-15');
INSERT INTO todos (title, detail, importance, limit_date) VALUES('�e�X�g�e�X�g2', '�񍐏����쐬����B', '1', '2015-06-20');
INSERT INTO todos (title, detail, importance, limit_date) VALUES('�e�X�g�e�X�g3', 'hogehoge', '1', NULL);

