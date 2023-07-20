INSERT INTO article(id, title, content) VALUES (1, 'AAA', '111');
INSERT INTO article(id, title, content) VALUES (2, 'BBB', '222');
INSERT INTO article(id, title, content) VALUES (3, 'CCC', '333');

-- article 더미 데이터
INSERT INTO article(id, title, content) VALUES (4, 'ddd', 'f');
INSERT INTO article(id, title, content) VALUES (5, 'eee', 'f');
INSERT INTO article(id, title, content) VALUES (6, 'fff', 'f');

-- comment 더미 데이터
-- 4번 게시글의 댓글들
INSERT INTO comment(id, article_id, nickname, body) VALUES (1, 4, 'park', 'you1');
INSERT INTO comment(id, article_id, nickname, body) VALUES (2, 4, 'kim', 'you2');
INSERT INTO comment(id, article_id, nickname, body) VALUES (3, 4, 'choi', 'you3');

-- 5번 게시글의 댓글들
INSERT INTO comment(id, article_id, nickname, body) VALUES (4, 5, 'park', 'you4');
INSERT INTO comment(id, article_id, nickname, body) VALUES (5, 5, 'kim', 'you5');
INSERT INTO comment(id, article_id, nickname, body) VALUES (6, 5, 'choi', 'you6');

-- 6번 게시글의 댓글들
INSERT INTO comment(id, article_id, nickname, body) VALUES (7, 6, 'park', 'you7');
INSERT INTO comment(id, article_id, nickname, body) VALUES (8, 6, 'kim', 'you8');
INSERT INTO comment(id, article_id, nickname, body) VALUES (9, 6, 'choi', 'you9');