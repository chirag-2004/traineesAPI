package com.example.traineeAPI.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.traineeAPI.entities.Trainee;
import com.example.traineeAPI.repositories.ITraineeRepository;
import com.example.traineeAPI.services.TraineeServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
	
	@Mock  
	ITraineeRepository traineeRepo;
	
	@Autowired
	@InjectMocks
	TraineeServiceImpl traineeService;
	
	@Disabled 
	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	// -------------------- getTraineeById --------------------
	
	@Test
	void testFindTrineeById() {
		
		// 1. Stubbing or simulation using when and thenReturn
		Trainee emp = new Trainee();
		Mockito.when(traineeRepo.findById(7)).thenReturn(Optional.of(emp));
		Trainee actualObject = traineeService.getTraineeById(7);
		assertNotNull(actualObject);
		// 2. Verification
		Mockito.verify(traineeRepo, Mockito.times(1)).findById(7);
		
	}
	@Test
	void testFindTraineeByIdForNull() {
		Mockito.when(traineeRepo.findById(10)).thenReturn(Optional.empty());

		Trainee result = traineeService.getTraineeById(10);

		assertNull(result);
		Mockito.verify(traineeRepo, Mockito.times(1)).findById(10);
	}
	
	// -------------------- getAllTrainees --------------------
	
	@Test
	void testGetAllTrainees() {
		List<Trainee> list = new ArrayList<>();
		list.add(new Trainee());
		list.add(new Trainee());

		Mockito.when(traineeRepo.findAll()).thenReturn(list);

		List<Trainee> result = traineeService.getAllTrainees();

		assertEquals(2, result.size());
		Mockito.verify(traineeRepo, Mockito.times(1)).findAll();
	}
	
	// -------------------- findByTraineeName --------------------
	
	@Test
	void testFindByTraineeName() {
		List<Trainee> list = new ArrayList<>();
		list.add(new Trainee());

		Mockito.when(traineeRepo.findByTraineeName("aman")).thenReturn(list);

		List<Trainee> result = traineeService.getTraineeByName("aman");

		assertNotNull(result);
		assertEquals(1, result.size());
		Mockito.verify(traineeRepo, Mockito.times(1)).findByTraineeName("aman");
	}
	
	// -------------------- insertTrainee --------------------
	
	@Test
	void testInsertTrainee() {
		Trainee trainee = new Trainee();
		trainee.setTraineeName("Aman");

		Mockito.when(traineeRepo.save(trainee)).thenReturn(trainee);

		Trainee result = traineeService.addTrainee(trainee);

		assertNotNull(result);
		assertEquals("Aman", result.getTraineeName());
		Mockito.verify(traineeRepo, Mockito.times(1)).save(trainee);
	}
	
	// -------------------- updateTrainee --------------------
	
	@Test
	void testUpdateTraineeSuccess() {
		Trainee existing = new Trainee();
		existing.setTraineeName("Old");

		Trainee updated = new Trainee();
		updated.setTraineeName("New");
		updated.setTraineeDomain("IT");
		updated.setTraineeLocation("Delhi");

		Mockito.when(traineeRepo.findById(1)).thenReturn(Optional.of(existing));
		Mockito.when(traineeRepo.save(existing)).thenReturn(existing);

		Trainee result = traineeService.updateTrainee(1, updated);

		assertNotNull(result);
		assertEquals("New", result.getTraineeName());
		assertEquals("IT", result.getTraineeDomain());
		assertEquals("Delhi", result.getTraineeLocation());

		Mockito.verify(traineeRepo).findById(1);
		Mockito.verify(traineeRepo).save(existing);
	}
	@Test
	void testUpdateTraineeNotFound() {
		Trainee updated = new Trainee();

		Mockito.when(traineeRepo.findById(1)).thenReturn(Optional.empty());

		Trainee result = traineeService.updateTrainee(1, updated);

		assertNull(result);
		Mockito.verify(traineeRepo).findById(1);
	}
	
	// -------------------- deleteTrainee --------------------


	
	@Disabled
	@Test
	void testFindTraineeByIdForException() {
		Mockito.when(traineeRepo.findById(10)).thenReturn(Optional.empty());
		assertThrows(RuntimeException.class, () -> traineeService.getTraineeById(10));
		Mockito.verify(traineeRepo, Mockito.times(1)).findById(10);
	}
	
//	@Disabled
	@Test
	void testFindTraineeByIdForNoException() {
		Mockito.when(traineeRepo.findById(7)).thenReturn(Optional.empty());
		assertDoesNotThrow(() -> traineeService.getTraineeById(7));
		Mockito.verify(traineeRepo, Mockito.times(1)).findById(7);
	}
}