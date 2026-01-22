DROP TABLE IF EXISTS products;

CREATE TABLE products (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          price NUMERIC(10,2) NOT NULL CHECK (price >= 0),
                          image_url VARCHAR(500),
                          stock INT DEFAULT 0 CHECK (stock >= 0)
);