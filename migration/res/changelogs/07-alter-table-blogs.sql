ALTER TABLE blogs
ADD user_id int,
ADD FOREIGN KEY (user_id) REFERENCES users(id);
