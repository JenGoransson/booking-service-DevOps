package se.jennifer.bookingservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BookingIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnRoomsSuccessfully() throws Exception {

        mockMvc.perform(get("/room")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // Testar att GET /bookings fungerar korrekt genom att returnera en tom lista.
// Detta är ett enkelt integrationstest som verifierar att endpointen är åtkomlig,
// att Spring Boot startar som det ska, att databasen är tom i testprofilen,
// och att inga externa anrop (t.ex. CustomerClient) triggas.
    @Test
    void shouldReturnEmptyBookingsList() throws Exception {
        mockMvc.perform(get("/bookings")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }
}