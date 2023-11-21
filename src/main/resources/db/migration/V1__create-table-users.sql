CREATE TABLE users (

    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    email TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    role TEXT NOT NULL,
    name TEXT NOT NULL,
    is_active INTEGER NOT NULL ,
    cpf_cnpj TEXT UNIQUE NOT NULL,
    phone TEXT NOT NULL

);