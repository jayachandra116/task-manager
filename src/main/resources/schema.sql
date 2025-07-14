CREATE TABLE IF NOT EXISTS tasks (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(50),
    priority VARCHAR(50),
    due_date DATE DEFAULT NULL,
    created_at DATETIME,
    updated_At DATETIME
);
