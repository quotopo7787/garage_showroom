ALTER TABLE public.users
    ADD COLUMN IF NOT EXISTS user_code      VARCHAR(32),
    ADD COLUMN IF NOT EXISTS full_name      VARCHAR(150),
    ADD COLUMN IF NOT EXISTS email          VARCHAR(255),
    ADD COLUMN IF NOT EXISTS phone          VARCHAR(30),
    ADD COLUMN IF NOT EXISTS gender         VARCHAR(10),     -- MALE/FEMALE/OTHER
    ADD COLUMN IF NOT EXISTS address        TEXT,
    ADD COLUMN IF NOT EXISTS avatar_url     TEXT,
    ADD COLUMN IF NOT EXISTS updated_password_at TIMESTAMP(6);

-- Ràng buộc giới tính (nếu cần)
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM pg_constraint WHERE conname = 'users_gender_check'
    ) THEN
ALTER TABLE public.users
    ADD CONSTRAINT users_gender_check
        CHECK (gender IS NULL OR gender IN ('MALE','FEMALE','OTHER'));
END IF;
END$$;

-- Uniqueness (dùng partial index để cho phép NULL)
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM pg_indexes WHERE schemaname='public' AND indexname='ux_users_user_code'
    ) THEN
CREATE UNIQUE INDEX ux_users_user_code
    ON public.users (user_code)
    WHERE user_code IS NOT NULL;
END IF;

    IF NOT EXISTS (
        SELECT 1 FROM pg_indexes WHERE schemaname='public' AND indexname='ux_users_phone'
    ) THEN
CREATE UNIQUE INDEX ux_users_phone
    ON public.users (phone)
    WHERE phone IS NOT NULL;
END IF;

    IF NOT EXISTS (
        SELECT 1 FROM pg_indexes WHERE schemaname='public' AND indexname='ux_users_email_nocase'
    ) THEN
CREATE UNIQUE INDEX ux_users_email_nocase
    ON public.users (LOWER(email))
    WHERE email IS NOT NULL;
END IF;
END$$;