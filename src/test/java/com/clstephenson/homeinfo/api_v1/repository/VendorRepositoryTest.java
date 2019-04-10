package com.clstephenson.homeinfo.api_v1.repository;

import com.clstephenson.homeinfo.api_v1.IntegrationTest;
import com.clstephenson.homeinfo.api_v1.model.User;
import com.clstephenson.homeinfo.api_v1.model.Vendor;
import com.clstephenson.homeinfo.api_v1.testutils.TestDataHelper;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class VendorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private VendorRepository vendorRepository;

    @Test
    @Category(IntegrationTest.class)
    public void whenFindByUserId_thenReturnVendors() {
        // given
        User user = entityManager.persist(TestDataHelper.getTestUser());
        Vendor vendor = TestDataHelper.getTestVendor(user);
        entityManager.persistAndFlush(vendor);

        // when
        List<Vendor> found = (List<Vendor>) vendorRepository.findByUserId(user.getId());

        // then
        assertThat(found.size()).isGreaterThan(0);
    }

}