CREATE TABLE labs (

    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    andar TEXT UNIQUE NOT NULL,
    lab TEXT NOT NULL,
    user_id INTEGER UNIQUE NOT NULL,
    description TEXT NOT NULL,
    is_active INTEGER NOT NULL,
    CONSTRAINT fk_role FOREIGN KEY (user_id) REFERENCES users(id)
);