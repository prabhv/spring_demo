package com.example.demo.student;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    private AutoCloseable autoCloseable;
    private StudentService underTest;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new StudentService(studentRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetStudents() {
        //when
        underTest.getStudents();

        //verify
        verify(studentRepository).findAll();
    }

    @Test
    @Disabled
    void addNewStudent() {
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