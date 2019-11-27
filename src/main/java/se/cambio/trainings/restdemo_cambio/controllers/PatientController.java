package se.cambio.trainings.restdemo_cambio.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import se.cambio.trainings.restdemo_cambio.controllers.dto.Patient;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

	List<Patient> patientRepo;

	public PatientController() {
		patientRepo = new ArrayList<Patient>();
		patientRepo.addAll(Arrays.asList(new Patient("Nimal", 30), new Patient("Kamal", 26)));
	}

	@ApiOperation("Get all patients")
	@GetMapping("/getall")
	public List<Patient> getAllPatients() {

		return this.patientRepo;
	}

	@GetMapping("/get/{name}")
	public ResponseEntity<Patient> getPatientByName(@PathVariable("name") final String name) {

		Patient state = this.patientRepo.stream().filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
				.findFirst().orElse(null);

		if (state != null) {
			return new ResponseEntity<>(state, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@PostMapping("/create")
	public Patient createPatient(@RequestBody Patient patient) {

		if (!patient.getName().isEmpty()) {
			patient.setId(UUID.randomUUID().toString());
			this.patientRepo.add(patient);
		}
		return patient;
	}

	@PutMapping(value = "update/{patientname}")
	public Patient updatePatient(@PathVariable("patientname") final String patientName, @RequestBody Patient patient) {

		Patient patientToUpdate = patientRepo.stream().filter(p -> p.getName().equalsIgnoreCase(patientName))
				.findFirst().orElse(null);
		if (patientToUpdate != null) {
			patientToUpdate.setAge(patient.getAge());
		}
		return patient;
	}

	@DeleteMapping(value = "/delete/{patientname}")
	public String deletePatient(@PathVariable("patientname") final String patientName) {

		patientRepo.removeIf(p -> p.getName().equals(patientName));
		return patientName;
	}
}
