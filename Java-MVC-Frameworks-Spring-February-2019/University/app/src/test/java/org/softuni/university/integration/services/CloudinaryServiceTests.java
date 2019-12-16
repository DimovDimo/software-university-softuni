package org.softuni.university.integration.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.university.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CloudinaryServiceTests {
    @Autowired
    private CloudinaryService service;

    @MockBean
    private MultipartFile multipartFile;

    @Test(expected = Exception.class)
    public void uploadImage_whenNull_throw() throws Exception {
        var result = service.uploadImage(multipartFile);
    }
}
