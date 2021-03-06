package com.clstephenson.homeinfo.api.service;

import com.clstephenson.homeinfo.model.Property;
import com.clstephenson.homeinfo.model.StoredFile;
import com.clstephenson.homeinfo.model.User;
import com.clstephenson.homeinfo.api.repository.StoredFileRepository;
import com.clstephenson.homeinfo.api.testutils.TestDataHelper;
import edu.emory.mathcs.backport.java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
public class StoredFileServiceImplTest {

    final long id = 1;
    @Autowired
    private StoredFileService service;
    @MockBean
    private StoredFileRepository repository;
    @MockBean
    private FileStorageService fileStorageService;
    private StoredFile storedFile;
    private List<StoredFile> storedFileList;

    @Before
    public void setUp() {
        User user = TestDataHelper.getTestUser();
        user.setId(id);
        Property property = TestDataHelper.getTestProperty(user);
        property.setId(id);
        storedFile = TestDataHelper.getTestFile(property);

        storedFileList = Collections.singletonList(storedFile);

        when(repository.findAllByPropertyId(id)).thenReturn(storedFileList);
        when(repository.findAll()).thenReturn(storedFileList);
        when(repository.findById(id)).thenReturn(Optional.of(storedFile));
        when(repository.save(storedFile)).thenReturn(storedFile);
        when(repository.existsById(id)).thenReturn(true);
        doNothing().when(repository).deleteById(anyLong());
    }

    @Test
    public void whenGetAll_thenReturnListWithOneStoredFile() {
        List<StoredFile> found = service.getAll();

        assertThat(found).size().isEqualTo(1);
    }

    @Test
    public void whenGetByPropertyId_thenReturnListWithOneStoredFile() {
        List<StoredFile> found = service.findByPropertyId(id);

        assertThat(found).size().isEqualTo(1);
    }

    @Test
    public void whenFindById_thenReturnStoredFile() {
        StoredFile found = service.findById(id).get();

        assertThat(found).isEqualTo(storedFile);
    }

//    @Test
//    public void whenSaved_thenReturnSavedStoredFile() {
//        StoredFile saved = service.save(storedFile);
//
//        assertThat(saved).isEqualToIgnoringGivenFields(storedFile, "id");
//    }
//
//    @Test
//    public void whenDelete_thenVerifyRepositoryMethodCalled() {
//        service.deleteById(id);
//
//        verify(repository).deleteById(anyLong());
//    }

    @Test
    public void whenCheckIfExists_thenReturnTrue() {
        assertThat(service.existsById(id)).isTrue();
    }

    @TestConfiguration
    static class StoredFileServiceImplTestContextConfiguration {
        @Bean
        public StoredFileService storedFileService() {
            return new StoredFileServiceImpl();
        }
    }
}