package com.example.demo.student;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    private AutoCloseable autoCloseable;
    private StudentService underTest;

    @BeforeEach
    void setUp() {
        underTest = new StudentService(studentRepository);
    }

    @Test
    void canGetStudents() {
        //when
        underTest.getStudents();

        //verify
        verify(studentRepository).findAll();
    }

    @Test
    void canAddNewStudent() {
        //given
        String email = "happy@email.com";
        Student student = new Student("Happy", LocalDate.of(2006, 7, 29), email);

        //when
        underTest.addNewStudent(student);

        //then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        verify(studentRepository).save(studentArgumentCaptor.capture());
        Student capturedStudent = studentArgumentCaptor.getValue();

        assertThat(capturedStudent).isEqualTo(student);

    }

    @Test
    void throwExcWhenCannotAddNewStudent() {
        //given
        String email = "happy@email.com";
        Student student = new Student("Happy", LocalDate.of(2006, 7, 29), email);

        given(studentRepository.findStudentByEmail(student.getEmail())).willReturn(Optional.of(student));
        //when
        //then
        assertThatThrownBy(() -> underTest.addNewStudent(student))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Email is taken");

    }

    @Test
    @Disabled
    void deletStudent() {
    }

    @Test
    @Disabled
    void updateStudent() {
    }
}