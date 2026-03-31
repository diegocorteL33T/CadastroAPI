-- Migrations to add rank column to the users table
ALTER TABLE tb_users
ADD COLUMN rank VARCHAR(255);