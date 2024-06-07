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

-- Pre-made workouts created by the user
CREATE TABLE IF NOT EXISTS workout (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT DEFAULT '',
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE,
    is_archived BOOLEAN DEFAULT false,
    user_id BIGINT REFERENCES user_info(id) ON DELETE CASCADE,
    UNIQUE (user_id, name)
);
CREATE INDEX IF NOT EXISTS workout_user_id_idx
    ON workout(user_id);
CREATE INDEX IF NOT EXISTS workout_is_archived_idx
    ON workout(is_archived);
CREATE INDEX IF NOT EXISTS workout_created_at_idx
    ON workout(created_at);

-- Information about a workout that occurred
-- If it was used with a pre-made, then workout_id will point to it
CREATE TABLE IF NOT EXISTS workout_instance (
    id BIGSERIAL PRIMARY KEY,
    start_time TIMESTAMP WITH TIME ZONE NOT NULL,
    end_time TIMESTAMP WITH TIME ZONE NOT NULL,
    feeling VARCHAR(255),
    notes TEXT DEFAULT '',
    template_id BIGINT REFERENCES workout(id),
    user_id BIGINT NOT NULL REFERENCES user_info(id) ON DELETE CASCADE
);
CREATE INDEX IF NOT EXISTS workout_instance_user_id_idx
    ON workout_instance(user_id);
CREATE INDEX IF NOT EXISTS workout_instance_start_time_idx
    ON workout_instance(start_time);
CREATE INDEX IF NOT EXISTS workout_instance_template_id_idx
    ON workout_instance(template_id);

-- Defines a strength exercise
CREATE TABLE IF NOT EXISTS strength_exercise (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    -- These can be default values that the user will set
    -- At each workout start, the user will choose from the list of exercise templates
    -- and then be prompted if these values are correct. If not, they will update for current exercise
    weight REAL NOT NULL CHECK ( weight > 0 ),
    sets INT NOT NULL CHECK ( sets > 0 ),
    reps INT NOT NULL CHECK ( reps > 0 ),
    muscle_group VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE,
    is_archived BOOLEAN DEFAULT false,
    user_id BIGINT NOT NULL REFERENCES user_info(id) ON DELETE CASCADE,
    UNIQUE (user_id, name)
);
CREATE INDEX IF NOT EXISTS strength_exercise_user_id_idx
    ON strength_exercise(user_id);
CREATE INDEX IF NOT EXISTS strength_exercise_is_archived_idx
    ON strength_exercise(is_archived);
CREATE INDEX IF NOT EXISTS strength_exercise_created_at_idx
    ON strength_exercise(created_at);

-- Defines the saved values for a strength exercise of a workout template
CREATE TABLE IF NOT EXISTS workout_strength_exercise_value (
    id BIGSERIAL PRIMARY KEY,
    weight REAL NOT NULL CHECK ( weight > 0 ),
    sets INT NOT NULL CHECK ( sets > 0 ),
    reps INT NOT NULL CHECK ( reps > 0 ),
    strength_exercise_id BIGINT NOT NULL REFERENCES strength_exercise(id) ON DELETE CASCADE
);
CREATE INDEX IF NOT EXISTS workout_strength_exercise_value_seid_idx
    ON workout_strength_exercise_value(strength_exercise_id);

CREATE TABLE IF NOT EXISTS strength_exercise_instance (
    id BIGSERIAL PRIMARY KEY,
    start_time TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    -- This weight info will be copied into a new row in the instance table to make sure it
    -- won't be updated by any changes to the template id
    weight REAL NOT NULL CHECK ( weight > 0 ),
    sets INT NOT NULL CHECK ( sets > 0 ),
    reps INT NOT NULL CHECK ( reps > 0 ),
    exercise_id BIGINT NOT NULL REFERENCES strength_exercise(id),
    user_id BIGINT NOT NULL REFERENCES user_info(id)
);
CREATE INDEX IF NOT EXISTS strength_exercise_instance_exercise_id_idx
    ON strength_exercise_instance(exercise_id);
CREATE INDEX IF NOT EXISTS strength_exercise_instance_user_id_idx
    ON strength_exercise_instance(user_id);
CREATE INDEX IF NOT EXISTS strength_exercise_instance_created_at_idx
    ON strength_exercise_instance(start_time);

-- Map strength exercise templates to workout templates to create the pre-made workouts
-- This uses the values table to get the pre-populated values
CREATE TABLE IF NOT EXISTS workout_join_strength_exercise_value (
    workout_id BIGINT NOT NULL REFERENCES workout(id) ON DELETE CASCADE,
    strength_exercise_value_id BIGINT NOT NULL REFERENCES workout_strength_exercise_value(id),
    PRIMARY KEY (workout_id, strength_exercise_value_id)
);

-- Map the strength exercise instances to the workout instance
-- This is a proper join table
CREATE TABLE IF NOT EXISTS workout_instance_join_strength_exercise_instance (
    workout_instance_id BIGINT NOT NULL REFERENCES workout_instance(id) ON DELETE CASCADE,
    strength_exercise_instance_id BIGINT NOT NULL REFERENCES strength_exercise_instance(id),
    PRIMARY KEY (workout_instance_id, strength_exercise_instance_id)
);

