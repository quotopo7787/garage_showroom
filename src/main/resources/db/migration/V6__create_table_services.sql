CREATE TABLE IF NOT EXISTS public.services (
    -- BaseEntity
                                               id              BIGSERIAL PRIMARY KEY,
                                               created_at      TIMESTAMP(6),
    created_by      VARCHAR(255),
    updated_at      TIMESTAMP(6),
    updated_by      VARCHAR(255),

    -- Domain fields
    code            VARCHAR(30) UNIQUE,                  -- mã DV (tùy chọn)
    name            VARCHAR(150) NOT NULL,               -- tên dịch vụ
    hourly_price    NUMERIC(12,2) NOT NULL DEFAULT 0,    -- giá theo giờ
    status          VARCHAR(20) NOT NULL DEFAULT 'ACTIVE'-- ACTIVE / INACTIVE
);