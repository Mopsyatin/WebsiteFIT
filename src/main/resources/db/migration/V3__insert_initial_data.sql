INSERT INTO categories (name) VALUES
                                  ('Clean'),
                                  ('Romantic'),
                                  ('Premium'),
                                  ('Calm'),
                                  ('Elegant'),
                                  ('Modern'),
                                  ('Festive'),
                                  ('Classic');


INSERT INTO products (name, description, price, image_url, stock) VALUES
                       ('Blush Rose Bouquet', 'Soft blush roses, hand-tied and ribbon-finished. Clean, romantic and classic.', 39.00, '/img/products/bouquet-blush-roses.jpg',20),
                       ('Ivory Garden Bouquet', 'Ivory roses with baby''s breath and eucalyptus. A calm premium look for any occasion.', 46.00, '/img/products/bouquet-ivory-garden.jpg',20),
                       ('Pearl Blush Wrap', 'Pastel roses with delicate baby''s breath and pearl detailing. Elegant and gift-ready.', 44.00, '/img/products/bouquet-pearl-blush.jpg',20),
                       ('Champagne Whites', 'Soft white blooms with subtle cream tones. Modern, textured, and quietly luxurious.', 45.00, '/img/products/bouquet-champagne-whites.jpg',20),
                       ('Red Rose Classic', 'Deep red roses with baby''s breath. Bold, timeless, and made for romance.', 49.00, '/img/products/bouquet-red-romance.jpg',20),
                       ('Birthday Crown Bouquet', 'A statement birthday bouquet with pink and cream roses. Designed to feel special.', 59.00, '/img/products/bouquet-birthday-crown.jpg',20);



WITH mappings(product_name, category_name) AS (
    VALUES
        ('Blush Rose Bouquet', 'Clean'),
        ('Blush Rose Bouquet', 'Romantic'),
        ('Red Rose Classic', 'Romantic'),
        ('Red Rose Classic', 'Classic'),
        ('Ivory Garden Bouquet', 'Premium'),
        ('Ivory Garden Bouquet', 'Calm'),
        ('Pearl Blush Wrap', 'Elegant'),
        ('Champagne Whites', 'Premium'),
        ('Champagne Whites', 'Modern'),
        ('Birthday Crown Bouquet', 'Festive'),
        ('Blush Rose Bouquet', 'Classic')

)
INSERT INTO product_categories(product_id, category_id)
SELECT p.id, c.id
FROM mappings m
         JOIN products p ON p.name = m.product_name
         JOIN categories c ON c.name = m.category_name;

