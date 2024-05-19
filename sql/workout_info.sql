CREATE TABLE IF NOT EXISTS user_info (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    is_verified BOOLEAN DEFAULT false,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    last_workout TIMESTAMP WITH TIME ZONE,
    workout_streak INT DEFAULT 0,
    metric_units BOOLEAN DEFAULT false
);
CREATE INDEX IF NOT EXISTS user_info_email_idx
    ON user_info(email);

-- Pre-made workout templates created by the user
CREATE TABLE IF NOT EXISTS workout_template (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE,
    is_archived BOOLEAN DEFAULT false,
    user_id BIGINT REFERENCES user_info(id) ON DELETE CASCADE,
    UNIQUE (user_id, name)
);
CREATE INDEX IF NOT EXISTS workout_template_user_id_idx
    ON workout_template(user_id);
CREATE INDEX IF NOT EXISTS workout_template_is_archived_idx
    ON workout_template(is_archived);
CREATE INDEX IF NOT EXISTS workout_template_created_at_idx
    ON workout_template(created_at);

-- Information about a workout that occurred
-- If it was used with a pre-made, then workout_template_id will point to it
CREATE TABLE IF NOT EXISTS workout_instance (
    id BIGSERIAL PRIMARY KEY,
    start_time TIMESTAMP WITH TIME ZONE NOT NULL,
    end_time TIMESTAMP WITH TIME ZONE NOT NULL,
    feeling VARCHAR(255),
    notes TEXT,
    template_id BIGINT REFERENCES workout_template(id),
    user_id BIGINT NOT NULL REFERENCES user_info(id)
);
CREATE INDEX IF NOT EXISTS workout_instance_start_time_idx
    ON workout_instance(start_time);
CREATE INDEX IF NOT EXISTS workout_instance_workout_template_id_idx
    ON workout_instance(template_id);
CREATE INDEX IF NOT EXISTS workout_instance_workout_user_id_idx
    ON workout_instance(user_id);

-- Defines a type of exercise
-- Should only be updated by an ADMIN. Users cannot modify these
CREATE TABLE IF NOT EXISTS strength_exercise_type (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    muscle_group VARCHAR(255) NOT NULL,
    UNIQUE (name, muscle_group)
);

-- Defines a strength exercise
CREATE TABLE IF NOT EXISTS strength_exercise_template (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    -- These can be default values that the user will set
    -- At each workout start, the user will choose from the list of exercise templates
    -- and then be prompted if these values are correct. If not, they will update for current exercise
    weight REAL NOT NULL CHECK ( weight > 0 ),
    sets INT NOT NULL CHECK ( sets > 0 ),
    reps INT NOT NULL CHECK ( reps > 0 ),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE,
    is_archived BOOLEAN DEFAULT false,
    exercise_type_id BIGINT NOT NULL REFERENCES strength_exercise_type(id),
    user_id BIGINT NOT NULL REFERENCES user_info(id) ON DELETE CASCADE
);
CREATE INDEX IF NOT EXISTS strength_exercise_template_user_id_idx
    ON strength_exercise_template(user_id);
CREATE INDEX IF NOT EXISTS strength_exercise_template_is_archived_idx
    ON strength_exercise_template(is_archived);
CREATE INDEX IF NOT EXISTS strength_exercise_template_created_at_idx
    ON strength_exercise_template(created_at);

CREATE TABLE IF NOT EXISTS strength_exercise_instance (
    id BIGINT PRIMARY KEY,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    -- This weight info will be copied into a new row in the instance table to make sure it
    -- won't be updated by any changes to the template id
    weight REAL NOT NULL CHECK ( weight > 0 ),
    sets INT NOT NULL CHECK ( sets > 0 ),
    reps INT NOT NULL CHECK ( reps > 0 ),
    template_id BIGINT NOT NULL REFERENCES strength_exercise_template(id),
    user_id BIGINT NOT NULL REFERENCES user_info(id)
);
CREATE INDEX IF NOT EXISTS strength_exercise_instance_template_id_idx
    ON strength_exercise_instance(template_id);
CREATE INDEX IF NOT EXISTS strength_exercise_instance_user_id_idx
    ON strength_exercise_instance(user_id);
CREATE INDEX IF NOT EXISTS strength_exercise_instance_created_at_idx
    ON strength_exercise_instance(created_at);

-- Map exercise templates to workout templates to create the pre-made workouts
CREATE TABLE IF NOT EXISTS workout_template_join_strength_exercise_template (
    workout_template_id BIGINT NOT NULL REFERENCES workout_template(id) ON DELETE CASCADE,
    strength_exercise_template_id BIGINT NOT NULL REFERENCES strength_exercise_template(id),
    -- Set the values for the pre-made exercise
    weight REAL NOT NULL CHECK ( weight > 0 ),
    sets INT NOT NULL CHECK ( sets > 0 ),
    reps INT NOT NULL CHECK ( reps > 0 ),
    PRIMARY KEY (workout_template_id, strength_exercise_template_id)
);

-- Map the strength exercise instances to the workout instance
CREATE TABLE IF NOT EXISTS workout_instance_join_strength_exercise_instance (
    workout_instance_id BIGINT NOT NULL REFERENCES workout_instance(id) ON DELETE CASCADE,
    strength_exercise_instance_id BIGINT NOT NULL REFERENCES strength_exercise_instance(id),
    PRIMARY KEY (workout_instance_id, strength_exercise_instance_id)
);

