-- Information about users
CREATE TABLE IF NOT EXISTS user_info (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    verified BOOLEAN DEFAULT false,
    created_at TIMESTAMP DEFAULT NOW(),
    last_workout DATE,
    workout_streak INT DEFAULT 0,
    exercise_templates INT DEFAULT 0,
    metric_units BOOLEAN DEFAULT false
);

----------------------
 ----- Workout ------
----------------------

-- Defines a workout such as its name and description
-- Created by a user
-- Should ONLY be deleted when a user is deleted, otherwise set is_archived to false
-- This will cause pretty much all else to be deleted too
CREATE TABLE IF NOT EXISTS workout_template (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_time TIMESTAMP NOT NULL,
    last_modified_time TIMESTAMP,
    is_archived BOOLEAN DEFAULT false,
    user_id BIGINT REFERENCES user_info(id) ON DELETE CASCADE,
    UNIQUE (name, user_id)
);

-- Shows that a workout occurred
CREATE TABLE IF NOT EXISTS workout_instance (
    id BIGSERIAL PRIMARY KEY,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    duration INT NOT NULL,
    feeling VARCHAR(255),
    notes TEXT,
    workout_template_id BIGINT REFERENCES workout_template(id) ON DELETE CASCADE
);

-----------------------
-- Strength Exercise --
-----------------------

-- Defines the names of the different types of strength exercises
-- Will show for all users
-- Want it to be set up pre-populated for users to choose from
-- This table should only be changed by an admin
CREATE TABLE IF NOT EXISTS strength_exercise (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    muscle_group VARCHAR(255) NOT NULL,
    user_id BIGINT REFERENCES user_info(id) ON DELETE CASCADE,
    UNIQUE(name, user_id),
    UNIQUE (name, muscle_group)
);

-- Define the weights used in a strength exercise
-- Use @NaturalId
CREATE TABLE IF NOT EXISTS strength_exercise_weight (
    id BIGSERIAL PRIMARY KEY,
    weight REAL NOT NULL CHECK ( weight > 0 ),
    sets INT NOT NULL CHECK ( sets > 0 ),
    reps INT NOT NULL CHECK ( reps > 0 ),
    UNIQUE (weight, sets, reps)
);

-- Define a strength workout
-- These should not be deletable, only archivable
CREATE TABLE IF NOT EXISTS strength_exercise_template (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    is_archived BOOLEAN DEFAULT false,
    weight_id BIGINT REFERENCES strength_exercise_weight(id),
    exercise_id BIGINT REFERENCES strength_exercise(id) ON DELETE CASCADE,
    user_id BIGINT NOT NULL REFERENCES user_info(id) ON DELETE CASCADE
);

-- Map strength_exercise_template to workout_template
CREATE TABLE IF NOT EXISTS strength_exercise_template_join_workout_template (
    strength_exercise_template_id BIGINT REFERENCES strength_exercise_template(id) ON DELETE CASCADE,
    workout_template_id BIGINT REFERENCES workout_template(id) ON DELETE CASCADE,
    PRIMARY KEY (strength_exercise_template_id, workout_template_id)
);

-- Define that this exercise occurred on this date
-- This is used mainly to keep track of progress, so we want to store
-- when an exercise happened and what weight was used
CREATE TABLE IF NOT EXISTS strength_exercise_instance (
    id BIGINT PRIMARY KEY,
    template_id BIGINT REFERENCES strength_exercise_template(id) ON DELETE CASCADE,
    time TIMESTAMP DEFAULT NOW(),
    weight_id BIGINT REFERENCES strength_exercise_weight(id)
);

-- Map strength_exercise_instance to workout_instance
CREATE TABLE IF NOT EXISTS strength_exercise_instance_join_workout_instance (
    strength_exercise_instance_id BIGINT REFERENCES strength_exercise_instance(id) ON DELETE CASCADE,
    workout_instance_id BIGINT REFERENCES workout_instance(id) ON DELETE CASCADE,
    PRIMARY KEY (strength_exercise_instance_id, workout_instance_id)
);









