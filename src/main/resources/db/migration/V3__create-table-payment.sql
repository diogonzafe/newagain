CREATE TABLE payment (
    id SERIAL PRIMARY KEY,
    boleto VARCHAR(255) UNIQUE NOT NULL,
    user_id INTEGER UNIQUE NOT NULL,
    payment_date VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    CONSTRAINT fk_role FOREIGN KEY (user_id) REFERENCES users(id)
);
