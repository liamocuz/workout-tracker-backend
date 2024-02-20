-- Table to store user authentication information
-- email and password will be used
CREATE TABLE IF NOT EXISTS user_auth (
    email VARCHAR(255) NOT NULL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN DEFAULT true,
    user_id BIGINT REFERENCES user_info(id) ON DELETE CASCADE
);

-- Table to store user role information
CREATE TABLE IF NOT EXISTS user_role (
    email VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    -- If a user_auth row is deleted, this user_role will also be deleted
    CONSTRAINT fk_user_role_to_user_auth FOREIGN KEY (email) REFERENCES user_auth(email) ON DELETE CASCADE
);
CREATE UNIQUE INDEX idx_user_role_unique_email_and_role ON user_role (email, role);
