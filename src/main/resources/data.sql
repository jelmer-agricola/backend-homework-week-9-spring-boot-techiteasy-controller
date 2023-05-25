

INSERT INTO televisions (type, brand, name, price, available_size, refresh_rate, screen_type, screen_Quality, smart_tv, wifi, voice_control, hdr, bluetooth, ambi_light, original_stock, sold)
VALUES ('LED', 'Samsung', 'Samsung 55-Inch 4K Smart TV', 799.99, 55.0, 120.0, 'Flat', 'Ultra HD', true, true, true, true, false, true, 50, 10);

INSERT INTO televisions (type, brand, name, price, available_size, refresh_rate, screen_type, screen_Quality, smart_tv, wifi, voice_control, hdr, bluetooth, ambi_light, original_stock, sold)
VALUES ('LED', 'LG', 'LG 65-Inch 4K Smart TV', 899.99, 65.0, 120.0, 'Flat', 'Ultra HD', true, true, true, true, false, false, 100, 20);

INSERT INTO televisions (type, brand, name, price, available_size, refresh_rate, screen_type, screen_Quality, smart_tv, wifi, voice_control, hdr, bluetooth, ambi_light, original_stock, sold)
VALUES ('OLED', 'Sony', 'Sony 55-Inch 4K Smart TV', 1299.99, 55.0, 120.0, 'Curved', 'Ultra HD', true, true, true, true, true, true, 50, 5);

INSERT INTO televisions (type, brand, name, price, available_size, refresh_rate, screen_type, screen_Quality, smart_tv, wifi, voice_control, hdr, bluetooth, ambi_light, original_stock, sold)
VALUES ('QLED', 'Samsung', 'Samsung 75-Inch 8K Smart TV', 2499.99, 75.0, 240.0, 'Flat', 'Ultra HD', true, true, true, true, true, false, 200, 30);


INSERT INTO remotecontroller (compatible_with, battery_type, name, brand, price, original_stock)
VALUES ('TV', 'AA', 'Universal Remote 2', 'ABC', 24.99, 30);

INSERT INTO remotecontroller (compatible_with, battery_type, name, brand, price, original_stock)
VALUES ('Smart TV', 'AAA', 'Smart TV Remote', 'XYZ', 29.99, 20);

INSERT INTO remotecontroller (compatible_with, battery_type, name, brand, price, original_stock)
VALUES ('Gaming Console', 'AA', 'Gaming Console Controller', 'DEF', 39.99, 15);

INSERT INTO cimodules ( name, type, price)
VALUES ( 'Product A', 'Type A', 9.99);

INSERT INTO cimodules ( name, type, price)
VALUES ( 'Product B', 'Type B', 19.99);

INSERT INTO cimodules ( name, type, price)
VALUES ( 'Product C', 'Type C', 29.99);


INSERT INTO wallbracket (size, adjustable, name, price)
VALUES ('Small', true, 'Product A', 9.99);

INSERT INTO wallbracket ( size, adjustable, name, price)
VALUES ('Medium', false, 'Product B', 19.99);

INSERT INTO wallbracket ( size, adjustable, name, price)
VALUES ( 'Large', true, 'Product C', 29.99);

INSERT INTO wallbracket ( size, adjustable, name, price)
VALUES ('Extra Large', false, 'Product D', 39.99);

UPDATE televisions
SET rc_id = 1
WHERE id = 1;