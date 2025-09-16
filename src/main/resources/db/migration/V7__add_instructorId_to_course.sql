ALTER TABLE course ADD COLUMN instructor_id BIGINT NOT NULL;

ALTER TABLE course ADD CONSTRAINT fk_course_to_user_instructor
    FOREIGN KEY (instructor_id) REFERENCES User(id);