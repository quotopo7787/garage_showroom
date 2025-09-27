-- Insert admin user (password = admin123, bcrypt hash)
INSERT INTO public.users(username, password, enabled, role_id)
VALUES (
           'admin',
           '{bcrypt}$2a$10$9Q6a48ObeZpNHjVjKihfE.RqQtJB3SnXzCf3zEXF6nU6vvlvq6Xma',
           TRUE,
           (SELECT id FROM public.roles WHERE name = 'ADMIN')
       )
    ON CONFLICT (username) DO NOTHING;