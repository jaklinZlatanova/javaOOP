package stuntClimb;


import org.junit.Test;

import static org.junit.Assert.*;

public class ClimbingTests {


    @Test(expected = NullPointerException.class)
    public void test_Create_Climbing_Exception_When_NullName() {
        new Climbing(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void test_Create_Climbing_Exception_When_Empty_Name() {
        new Climbing("             ", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Create_Climbing_Exception_When_InvalidCapacity() {
        new Climbing("valid", -10);
    }

    @Test
    public void test_Create_Climbing_Exception_With_ZeroCapacity() {
        new Climbing("valid", 0);
    }

    @Test
    public void test_Create_Climbing_With_ValidName_And_PositiveCapacity() {
        new Climbing("valid", 10);
    }

    @Test
    public void test_GetName() {
        Climbing valid = new Climbing("valid", 10);

        assertEquals("valid", valid.getName());
    }

    @Test
    public void test_GetCapacity() {
        Climbing valid = new Climbing("valid", 10);

        assertEquals(10, valid.getCapacity());
    }

    @Test
    public void test_Get_Count_IsZero_OnEmptyClimbing() {
        Climbing valid = new Climbing("valid", 10);

        assertEquals(0, valid.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Cannot_Add_Climber_With_SameName() {
        Climbing valid = new Climbing("valid", 2);
        RockClimber climber = new RockClimber("name", 10);

        valid.addRockClimber(climber);
        valid.addRockClimber(climber);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Cannot_Add_Climber_With_FullCapacity() {
        Climbing valid = new Climbing("valid", 2);
        RockClimber climber1 = new RockClimber("name1", 10);
        RockClimber climber2 = new RockClimber("name2", 10);
        RockClimber climber3 = new RockClimber("name3", 10);


        valid.addRockClimber(climber1);
        valid.addRockClimber(climber2);
        valid.addRockClimber(climber3);

    }

    @Test
    public void test_Add_Climber_IncreasesCount() {
        Climbing valid = new Climbing("valid", 10);
        RockClimber climber1 = new RockClimber("name1", 10);
        RockClimber climber2 = new RockClimber("name2", 10);
        RockClimber climber3 = new RockClimber("name3", 10);


        valid.addRockClimber(climber1);
        valid.addRockClimber(climber2);
        valid.addRockClimber(climber3);

        assertEquals(3, valid.getCount());
    }

    @Test
    public void test_Remove_Climber_Return_False_For_MissingName() {
        Climbing valid = new Climbing("valid", 2);
        RockClimber climber1 = new RockClimber("name1", 10);

        valid.addRockClimber(climber1);

        boolean result = valid.removeRockClimber("name2");

        assertFalse(result);
        assertEquals(1, valid.getCount());
    }

    @Test
    public void test_Remove_CLimber_On_Empty_Excavation_Will_Not_Throw() {
        Climbing valid = new Climbing("valid", 10);

        boolean result = valid.removeRockClimber("Some-name");

        assertFalse(result);
    }

    @Test
    public void test_Remove_Climber_DecreasesCount() {
        Climbing valid = new Climbing("valid", 10);
        RockClimber climber1 = new RockClimber("name1", 10);
        RockClimber climber2 = new RockClimber("name2", 10);
        RockClimber climber3 = new RockClimber("name3", 10);

        valid.addRockClimber(climber1);
        valid.addRockClimber(climber2);
        valid.addRockClimber(climber3);

        boolean result = valid.removeRockClimber(climber1.getName());

        assertTrue(result);
        assertEquals(2, valid.getCount());
    }
}
