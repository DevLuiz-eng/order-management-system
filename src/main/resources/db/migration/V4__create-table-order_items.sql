CREATE TABLE order_items (

    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    quantity INTEGER NOT NULL CHECK(quantity > 0),
    product_id BIGINT NOT NULL,

    CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES products (id)
)