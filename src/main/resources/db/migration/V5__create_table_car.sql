CREATE TABLE IF NOT EXISTS cars
(
    id          SERIAL PRIMARY KEY,
    code        VARCHAR(20) UNIQUE NOT NULL,
    brand       VARCHAR(50),
    model       VARCHAR(50),
    customer_id INT,
    created_at  TIMESTAMP DEFAULT now()
);