-- Migration: V1__init_schema.sql
-- Description: Khởi tạo bảng roles và users với quan hệ 1-nhiều (user -> role)

CREATE TABLE IF NOT EXISTS public.roles (
                                            id BIGSERIAL PRIMARY KEY,
                                            name VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP(6),
    created_by VARCHAR(255),
    updated_at TIMESTAMP(6),
    updated_by VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS public.users (
                                            id SERIAL PRIMARY KEY,
                                            username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN DEFAULT TRUE,
    role_id INT,
    CONSTRAINT fk_users_role FOREIGN KEY (role_id) REFERENCES public.roles(id)
    );

-- Insert role mặc định
INSERT INTO public.roles(name) VALUES ('ADMIN'), ('CUSTOMER')
    ON CONFLICT (name) DO NOTHING;

-- Insert admin user (password = admin123, bcrypt hash)
INSERT INTO public.users(username, password, enabled, role_id)
VALUES (
           'admin',
           '{bcrypt}$2a$10$9Q6a48ObeZpNHjVjKihfE.RqQtJB3SnXzCf3zEXF6nU6vvlvq6Xma',
           TRUE,
           (SELECT id FROM public.roles WHERE name = 'ADMIN')
       )
    ON CONFLICT (username) DO NOTHING;