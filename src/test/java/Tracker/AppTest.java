package Tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    private App app;

    @BeforeEach
    void setUp() {
        app = new App();
    }

    @Test
    void testAddTrainingSession() throws Exception {
        String dateString = "2023-01-01";
        int duration = 60;
        app.addTrainingSession(dateString, duration);
        assertEquals(1, app.getTrainingSessions().size());
        assertEquals(duration, app.getTotalTrainingTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateString);
        assertEquals(date, app.getYoungest().getDate());
        assertEquals(date, app.getOldest().getDate());
    }

    @Test
    void testViewTotalTrainingTime() {
        app.addTrainingSession("2023-01-01", 60);
        app.addTrainingSession("2023-01-02", 30);
        assertEquals(90, app.getTotalTrainingTime());
    }

    @Test
    void testCheckEligibility() {
        app.addTrainingSession("2023-01-01", 60);
        app.addTrainingSession("2023-07-02", 30);
        assertTrue(app.checkEligibility());
    }
}