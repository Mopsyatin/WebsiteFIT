DROP TABLE IF EXISTS categories;
CREATE TABLE categories (
                            id BIGSERIAL PRIMARY KEY,
                            name VARCHAR(100) UNIQUE NOT NULL
);


DROP TABLE IF EXISTS product_categories;
CREATE TABLE product_categories (
                                    product_id BIGINT NOT NULL REFERENCES products(id) ON DELETE CASCADE,
                                    category_id BIGINT NOT NULL REFERENCES categories(id) ON DELETE CASCADE,
                                    PRIMARY KEY(product_id, category_id)
);