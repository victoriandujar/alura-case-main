CREATE TABLE registration (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    registration_date TIMESTAMP NOT NULL,

    CONSTRAINT uk_user_course UNIQUE (user_id, course_id),

    CONSTRAINT fk_registration_to_user FOREIGN KEY (user_id) REFERENCES User(id),
    CONSTRAINT fk_registration_to_course FOREIGN KEY (course_id) REFERENCES course(id)
);