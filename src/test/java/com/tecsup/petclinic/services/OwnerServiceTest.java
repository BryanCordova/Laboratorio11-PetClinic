package com.tecsup.petclinic.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.fail;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;

@SpringBootTest
public class OwnerServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(OwnerServiceTest.class);
	@Autowired
	private OwnerService ownerService;
	@Test
	public void testDeleteOwner() {

		String first_name = "Ñahui";
		String last_name = "Bryan";
		String address = "Avenida San Carlos SJL";
		String city = "Lima";
		String telephone = "987654321";

		Owner own = new Owner(first_name, last_name, address, city, telephone);
		own = ownerService.create(own);
		logger.info("" + own);

		try {
			ownerService.delete(own.getId());
		} catch (OwnerNotFoundException e) {
			assertThat(e.getMessage(), false);
		}

		try {
			ownerService.findById(own.getId());
			assertThat(true, is(false));
		} catch (OwnerNotFoundException e) {
			assertThat(true, is(true));
		}
	}
	@Test
	public void testFindOwnerById() {

		long ID = 1;
		String NAME = "Bryan";
		Owner owner = null;
		try {
			owner = ownerService.findById(ID);

		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}
		logger.info("" + owner);
		assertThat(owner.getFirst_name(), is(NAME));
	}
	@Test
	public void testUpdateOwnerById() {
		String FIRST_NAME = "Alarcon";
		String LAST_NAME = "Jhanina";
		String ADDRESS = "Avenida Santa Anita";
		String CITY = "Lima";
		String TELEPHONE = "987321654";
		long created_id = -1;
		// UPDATE
		Owner owner = new Owner(FIRST_NAME, LAST_NAME, ADDRESS, CITY, TELEPHONE);

		logger.info(">" + owner);
		Owner ownerCreated = ownerService.create(owner);
		logger.info(">>" + ownerCreated);
		created_id = ownerCreated.getId();
		// Execute update
		Owner upgradeOwner = ownerService.update(ownerCreated);
		logger.info(">>>>" + upgradeOwner);
		// ACTUAL EXPECTED
		assertThat(created_id, notNullValue());
	}
}
