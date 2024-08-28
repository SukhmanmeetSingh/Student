package ca.sheridancollege.sukhmanmeetsinghfinal;

import ca.sheridancollege.sukhmanmeetsinghfinal.beans.Student;
import ca.sheridancollege.sukhmanmeetsinghfinal.database.DatabaseAccess;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
class SukhmanmeetSinghFinalApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DatabaseAccess databaseAccess;

    @Test
    public void testGetAllStudents() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetStudents() {
        List<Student> students = databaseAccess.getStudents();
        assertNotNull(students);
    }
}
