CREATE TABLE IF NOT EXISTS public.invoice (
                                              id BIGSERIAL PRIMARY KEY,
                                              invoice_code VARCHAR(50) UNIQUE NOT NULL,
    invoice_type VARCHAR(20) NOT NULL, -- SERVICE / CAR
    customer_id BIGINT NOT NULL REFERENCES public.users(id), -- khách hàng
    employee_id BIGINT REFERENCES public.users(id),          -- nhân viên bán hàng (cho hóa đơn xe)
    car_id BIGINT,                                           -- nếu là hóa đơn bán xe (FK sang bảng car)
    total_amount NUMERIC(18,2) DEFAULT 0,
    status VARCHAR(30) DEFAULT 'PENDING', -- PENDING, PAID, CANCELLED
    notes TEXT,
    created_at TIMESTAMP DEFAULT now()
    );

CREATE TABLE IF NOT EXISTS public.invoice_item (
                                                   id BIGSERIAL PRIMARY KEY,
                                                   invoice_id BIGINT NOT NULL REFERENCES public.invoice(id) ON DELETE CASCADE,
    product_name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    unit_price NUMERIC(18,2) NOT NULL DEFAULT 0,
    line_total NUMERIC(18,2) GENERATED ALWAYS AS (quantity * unit_price) STORED
    );
