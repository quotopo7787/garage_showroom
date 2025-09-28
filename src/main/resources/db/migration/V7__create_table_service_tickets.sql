CREATE TABLE IF NOT EXISTS public.service_tickets (
                                                      id               BIGSERIAL PRIMARY KEY,
                                                      created_at       TIMESTAMP(6),
    created_by       VARCHAR(255),
    updated_at       TIMESTAMP(6),
    updated_by       VARCHAR(255),

    code             VARCHAR(30) UNIQUE NOT NULL,          -- VD: ST2023001
    user_id          INT NOT NULL REFERENCES public.users(id) ON DELETE RESTRICT,
    car_id           INT NOT NULL REFERENCES public.cars(id)  ON DELETE RESTRICT,
    received_date    DATE NOT NULL,
    completed_date   DATE,
    status           VARCHAR(20) NOT NULL DEFAULT 'PROCESSING',  -- PROCESSING / COMPLETED / CANCELLED
    notes            TEXT,
    total_amount     NUMERIC(18,2) NOT NULL DEFAULT 0
    );
CREATE TABLE IF NOT EXISTS public.service_ticket_items (
                                                           id                 BIGSERIAL PRIMARY KEY,
                                                           created_at         TIMESTAMP(6),
    created_by         VARCHAR(255),
    updated_at         TIMESTAMP(6),
    updated_by         VARCHAR(255),

    service_ticket_id  BIGINT NOT NULL REFERENCES public.service_tickets(id) ON DELETE CASCADE,
    description        VARCHAR(255) NOT NULL,                 -- VD: Thay nhớt, Bảo dưỡng phanh
    price              NUMERIC(18,2) NOT NULL DEFAULT 0,
    quantity           INT NOT NULL DEFAULT 1,
    line_total         NUMERIC(18,2) GENERATED ALWAYS AS (price * quantity) STORED
    );