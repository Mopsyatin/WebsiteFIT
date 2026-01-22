CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       full_name VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE carts (
                       id BIGSERIAL PRIMARY KEY,
                       user_id BIGINT NOT NULL UNIQUE,
                       created_at TIMESTAMP NOT NULL DEFAULT now(),

                       CONSTRAINT fk_cart_user
                           FOREIGN KEY (user_id)
                               REFERENCES users(id)
                               ON DELETE CASCADE
);

CREATE TABLE cart_items (
                            id BIGSERIAL PRIMARY KEY,
                            cart_id BIGINT NOT NULL,
                            product_id BIGINT NOT NULL,
                            quantity INT NOT NULL DEFAULT 1 CHECK (quantity > 0),

                            CONSTRAINT fk_cart_item_cart
                                FOREIGN KEY (cart_id)
                                    REFERENCES carts(id)
                                    ON DELETE CASCADE,

                            CONSTRAINT fk_cart_item_product
                                FOREIGN KEY (product_id)
                                    REFERENCES products(id),

                            CONSTRAINT uq_cart_product UNIQUE (cart_id, product_id)
);
