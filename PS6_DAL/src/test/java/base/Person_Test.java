package base;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;

public class Person_Test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void AddPerson() {
		PersonDomainModel per1 = new PersonDomainModel();
		per1.setBirthday(new Date(0));
		per1.setCity("Townsend");
		per1.setFirstName("Bert");
		per1.setLastName("Gibbons");
		per1.setPostalCode(19734);
		per1.setStreet("214 Labrador Lane");
		
		//AddPerson and GetPerson Tests
		PersonDAL.addPerson(per1);
		UUID per1ID = per1.getPersonID();
		
		PersonDomainModel per2 = PersonDAL.getPerson(per1ID);
		assertTrue(per2.getCity().equals(per1.getCity()));
		assertTrue(per2.getFirstName().equals(per1.getFirstName()));
		assertTrue(per2.getLastName().equals(per1.getLastName()));
		assertTrue(per2.getPostalCode().equals(per1.getPostalCode()));
		assertTrue(per2.getStreet().equals(per1.getStreet()));
		
		//GetPersons Test
		ArrayList<PersonDomainModel> people = PersonDAL.getPersons();
		
		PersonDomainModel per3 = new PersonDomainModel();
		per3.setBirthday(new Date(0));
		per3.setCity("Hockessin");
		per3.setFirstName("Michael");
		per3.setLastName("Ly");
		per3.setPostalCode(19707);
		per3.setStreet("28 Clark Ridge");
		
		UUID per3ID = per3.getPersonID();
		PersonDAL.addPerson(per3);
		people = PersonDAL.getPersons();
		
		//UpdatePerson Test
		per1.setCity("New York");
		per1.setStreet("221 Wolf Street");
		PersonDAL.updatePerson(per1);
		
		PersonDomainModel per4 = PersonDAL.getPerson(per1ID);
		assertTrue(per4.getCity().equals("New York"));
		assertTrue(per4.getStreet().equals("221 Wolf Street"));
		
		//DeletePerson Test
		PersonDAL.deletePerson(per1ID);
		PersonDAL.deletePerson(per3ID);
		assertNull(PersonDAL.getPerson(per1ID));
		assertNull(PersonDAL.getPerson(per3ID));
		
		
	}

}
