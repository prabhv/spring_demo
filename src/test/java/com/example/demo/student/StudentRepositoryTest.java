package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void checkIfFindStudentByEmailExists() {
        //given
        String email = "happy@email.com";
        Student student = new Student("Happy", LocalDate.of(2006, 7, 29), email);

        underTest.save(student);
        //when
        Optional<Student> studentByEmail = underTest.findStudentByEmail(email);
        boolean studentPresent = studentByEmail.isPresent();
        //then
        assertThat(studentPresent).isTrue();

    }

    @Test
    void checkIfFindStudentByEmailDoesNotExists() {
        //given
        String email = "happy@email.com";
        //when
        Optional<Student> studentByEmail = underTest.findStudentByEmail(email);
        boolean studentPresent = studentByEmail.isPresent();
        //then
        assertThat(studentPresent).isFalse();

    }
}