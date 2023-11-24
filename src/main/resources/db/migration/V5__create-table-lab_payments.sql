CREATE TABLE lab_payments (
    lab_id INTEGER NOT NULL,
    payment_id INTEGER NOT NULL,
    CONSTRAINT fk_lab FOREIGN KEY (lab_id) REFERENCES labs(id),
    CONSTRAINT fk_payment FOREIGN KEY (payment_id) REFERENCES payment(id),
    PRIMARY KEY (lab_id, payment_id)
);
