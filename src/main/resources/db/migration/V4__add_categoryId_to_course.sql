ALTER TABLE course
    ADD COLUMN category_id BIGINT NOT NULL;

ALTER TABLE course
    ADD CONSTRAINT fk_course_category
        FOREIGN KEY (category_id) REFERENCES category(id);
