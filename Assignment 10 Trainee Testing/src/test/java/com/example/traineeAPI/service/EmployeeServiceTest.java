package com.example.traineeAPI.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

import com.example.traineeAPI.entities.Trainee;
import com.example.traineeAPI.repositories.ITraineeRepository;
import com.example.traineeAPI.services.TraineeServiceImpl;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    ITraineeRepository traineeRepo;

    @InjectMocks
    TraineeServiceImpl traineeService;

    @Test
    void testFindTraineeById() {
        Trainee t = new Trainee();

        Mockito.when(traineeRepo.findById(1)).thenReturn(Optional.of(t));

        Trainee result = traineeService.getTraineeById(1);

        assertNotNull(result);
        Mockito.verify(traineeRepo).findById(1);
    }

    @Test
    void testFindTraineeByIdNotFound() {
        Mockito.when(traineeRepo.findById(2)).thenReturn(Optional.empty());

        Trainee result = traineeService.getTraineeById(2);

        assertNull(result);
    }

    @Test
    void testGetAllTrainees() {
        List<Trainee> list = Arrays.asList(new Trainee(), new Trainee());

        Mockito.when(traineeRepo.findAll()).thenReturn(list);

        List<Trainee> result = traineeService.getAllTrainees();

        assertEquals(2, result.size());
    }

    @Test
    void testInsertTrainee() {
        Trainee t = new Trainee();
        t.setTraineeName("Aman");

        Mockito.when(traineeRepo.save(t)).thenReturn(t);

        Trainee result = traineeService.addTrainee(t);

        assertEquals("Aman", result.getTraineeName());
    }

    @Test
    void testUpdateTrainee() {
        Trainee existing = new Trainee();
        Trainee updated = new Trainee();
        updated.setTraineeName("New");

        Mockito.when(traineeRepo.findById(1)).thenReturn(Optional.of(existing));
        Mockito.when(traineeRepo.save(existing)).thenReturn(existing);

        Trainee result = traineeService.updateTrainee(1, updated);

        assertEquals("New", result.getTraineeName());
    }
}
