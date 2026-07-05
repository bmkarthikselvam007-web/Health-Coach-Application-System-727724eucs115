package com.examly.springapp;

import java.io.File;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SpringappApplication.class)
@AutoConfigureMockMvc
class HealthCoachTests {

    @Autowired
    private MockMvc mockMvc;

    // Test adding a health coach through the controller
    @Test
    void test_Add_Coach() throws Exception {
        String coachJson = "{\"name\": \"John Doe\", \"specialization\": \"Nutrition\", \"certification\": \"MSc in Nutrition\", \"experience\": 5, \"phoneNumber\": \"9876543210\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/addCoach")
                .contentType(MediaType.APPLICATION_JSON)
                .content(coachJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    // Test adding a health coach with invalid (negative) experience
    @Test
    void test_Add_Coach_With_Invalid_Experience() throws Exception {
        String coachJson = "{\"name\": \"Jane Doe\", \"specialization\": \"Fitness\", \"certification\": \"PhD in Sports Science\", \"experience\": -1, \"phoneNumber\": \"9876543211\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/addCoach")
                .contentType(MediaType.APPLICATION_JSON)
                .content(coachJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()) // Expecting a bad request for invalid experience
                .andExpect(jsonPath("$").value("Experience cannot be negative.")); // Adjust the message based on your exception handling
    }

    // Test fetching all coaches from the controller
    @Test
    void test_Get_AllCoaches() throws Exception {
        mockMvc.perform(get("/getAllCoaches")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andReturn();
    }

    // Verify the controller folder exists
    @Test
    public void test_Controller_Directory_Exists() {
        String directoryPath = "src/main/java/com/examly/springapp/controller";
        File directory = new File(directoryPath);
        assertTrue(directory.exists() && directory.isDirectory());
    }

    // Verify the HealthCoachController class exists
    @Test
    public void test_HealthCoachController_File_Exists() {
        String filePath = "src/main/java/com/examly/springapp/controller/HealthCoachController.java";
        File file = new File(filePath);
        assertTrue(file.exists() && file.isFile());
    }

    // Verify the model folder exists
    @Test
    public void test_Model_Directory_Exists() {
        String directoryPath = "src/main/java/com/examly/springapp/model";
        File directory = new File(directoryPath);
        assertTrue(directory.exists() && directory.isDirectory());
    }

    // Verify the HealthCoach model file exists
    @Test
    public void test_HealthCoach_File_Exists() {
        String filePath = "src/main/java/com/examly/springapp/model/HealthCoach.java";
        File file = new File(filePath);
        assertTrue(file.exists() && file.isFile());
    }

    // Verify the repository folder exists
    @Test
    public void test_Repository_Folder_Exists() {
        String directoryPath = "src/main/java/com/examly/springapp/repository";
        File directory = new File(directoryPath);
        assertTrue(directory.exists() && directory.isDirectory());
    }

    // Verify the service folder exists
    @Test
    public void test_Service_Folder_Exists() {
        String directoryPath = "src/main/java/com/examly/springapp/service";
        File directory = new File(directoryPath);
        assertTrue(directory.exists() && directory.isDirectory());
    }

    // Verify the HealthCoachService class exists
    @Test
    public void test_HealthCoachService_Class_Exists() {
        checkClassExists("com.examly.springapp.service.HealthCoachService");
    }

    // Verify the HealthCoach model class exists
    @Test
    public void test_HealthCoachModel_Class_Exists() {
        checkClassExists("com.examly.springapp.model.HealthCoach");
    }

    // Check that the HealthCoach model has a 'name' field
    @Test
    public void test_HealthCoach_Model_Has_name_Field() {
        checkFieldExists("com.examly.springapp.model.HealthCoach", "name");
    }

    // Check that the HealthCoach model has a 'specialization' field
    @Test
    public void test_HealthCoach_Model_Has_specialization_Field() {
        checkFieldExists("com.examly.springapp.model.HealthCoach", "specialization");
    }

    // Check that the HealthCoach model has a 'certification' field
    @Test
    public void test_HealthCoach_Model_Has_certification_Field() {
        checkFieldExists("com.examly.springapp.model.HealthCoach", "certification");
    }

    // Check that the HealthCoach model has a 'phoneNumber' field
    @Test
    public void test_HealthCoach_Model_Has_phoneNumber_Field() {
        checkFieldExists("com.examly.springapp.model.HealthCoach", "phoneNumber");
    }

    // Check that the HealthCoachRepo implements JpaRepository
    @Test
    public void test_HealthCoachRepo_Extends_JpaRepository() {
        checkClassImplementsInterface("com.examly.springapp.repository.HealthCoachRepo",
                "org.springframework.data.jpa.repository.JpaRepository");
    }

    // Verify that CORS configuration class exists
    @Test
    public void test_CorsConfiguration_Class_Exists() {
        checkClassExists("com.examly.springapp.configuration.CorsConfiguration");
    }

    // Verify that CORS configuration has the Configuration annotation
    @Test
    public void test_CorsConfiguration_Has_Configuration_Annotation() {
        checkClassHasAnnotation("com.examly.springapp.configuration.CorsConfiguration",
                "org.springframework.context.annotation.Configuration");
    }

    // Verify that InvalidExperienceException class exists
    @Test
    public void test_InvalidExperienceException_Class_Exists() {
        checkClassExists("com.examly.springapp.exception.InvalidExperienceException");
    }

    // Verify that InvalidExperienceException extends RuntimeException
    @Test
    public void test_InvalidExperienceException_Extends_RuntimeException() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.exception.InvalidExperienceException");
            assertTrue(RuntimeException.class.isAssignableFrom(clazz),
                    "InvalidExperienceException should extend RuntimeException");
        } catch (ClassNotFoundException e) {
            fail("InvalidExperienceException class does not exist.");
        }
    }

    // Helper method to check if a class exists
    private void checkClassExists(String className) {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            fail("Class " + className + " does not exist.");
        }
    }

    // Helper method to check if a field exists in a class
    private void checkFieldExists(String className, String fieldName) {
        try {
            Class<?> clazz = Class.forName(className);
            clazz.getDeclaredField(fieldName);  // Will throw NoSuchFieldException if the field doesn't exist
        } catch (ClassNotFoundException e) {
            fail("Class " + className + " does not exist.");
        } catch (NoSuchFieldException e) {
            fail("Field " + fieldName + " does not exist in class " + className);
        }
    }

    // Helper method to check if a class implements an interface
    private void checkClassImplementsInterface(String className, String interfaceName) {
        try {
            Class<?> clazz = Class.forName(className);
            Class<?> iface = Class.forName(interfaceName);
            assertTrue(iface.isAssignableFrom(clazz), className + " does not implement " + interfaceName);
        } catch (ClassNotFoundException e) {
            fail("Class or interface not found.");
        }
    }

    // Helper method to check if a class has a specific annotation
    private void checkClassHasAnnotation(String className, String annotationName) {
        try {
            Class<?> clazz = Class.forName(className);
            if (!clazz.isAnnotationPresent(Class.forName(annotationName).asSubclass(java.lang.annotation.Annotation.class))) {
                fail("Class " + className + " does not have the " + annotationName + " annotation.");
            }
        } catch (ClassNotFoundException e) {
            fail("Class or annotation not found.");
        }
    }
}