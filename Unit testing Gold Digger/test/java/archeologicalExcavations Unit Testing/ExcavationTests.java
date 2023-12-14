package archeologicalExcavations;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExcavationTests {

    @Test(expected = NullPointerException.class)
    public void test_Create_Excavation_Exception_When_NullName(){
        new Excavation(null,10);
    }
    @Test(expected = NullPointerException.class)
    public void test_Create_Excavation_Exception_When_Empty_Name(){
        new Excavation("             ",10);
    }
    @Test(expected = IllegalArgumentException.class)
    public void test_Create_Excavation_Exception_When_InvalidCapacity(){
        new Excavation("valid",-10);
    }
    @Test
    public void test_Create_Excavation_Exception_With_ZeroCapacity(){
        new Excavation("valid",0);
    }
    @Test
    public void test_Create_Excavation_With_ValidName_And_PositiveCapacity(){
        new Excavation("valid",10);
    }

    @Test
    public void test_GetName(){
        Excavation valid = new Excavation("valid",10);

        assertEquals("valid",valid.getName());
    }
    @Test
    public void test_GetCapacity(){
        Excavation valid = new Excavation("valid",10);

        assertEquals(10,valid.getCapacity());
    }
    @Test
    public void test_Get_Count_IsZero_OnEmptyExcavation(){
        Excavation valid = new Excavation("valid",10);

        assertEquals(0,valid.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void test_Cannot_Add_Arch_With_SameName(){
        Excavation valid = new Excavation("valid",2);
        Archaeologist archaeologist = new Archaeologist("name",10);

        valid.addArchaeologist(archaeologist);
        valid.addArchaeologist(archaeologist);
    }
   @Test(expected = IllegalArgumentException.class)
    public void test_Cannot_Add_Arch_With_FullCapacity(){
        Excavation valid = new Excavation("valid",2);
        Archaeologist archaeologist1 = new Archaeologist("name1",10);
        Archaeologist archaeologist2 = new Archaeologist("name2",10);
        Archaeologist archaeologist3 = new Archaeologist("name3",10);

        valid.addArchaeologist(archaeologist1);
        valid.addArchaeologist(archaeologist2);
        valid.addArchaeologist(archaeologist3);
    }
    @Test
    public void test_Add_Arch_IncreasesCount(){
        Excavation valid = new Excavation("valid",10);
        Archaeologist archaeologist1 = new Archaeologist("name1",10);
        Archaeologist archaeologist2 = new Archaeologist("name2",10);
        Archaeologist archaeologist3 = new Archaeologist("name3",10);

        valid.addArchaeologist(archaeologist1);
        valid.addArchaeologist(archaeologist2);
        valid.addArchaeologist(archaeologist3);

        assertEquals(3,valid.getCount());
    }
    @Test
    public void test_Remove_Arch_Return_False_For_MissingName(){
        Excavation valid = new Excavation("valid",2);
        Archaeologist archaeologist1 = new Archaeologist("name1",10);

        valid.addArchaeologist(archaeologist1);

        boolean result = valid.removeArchaeologist("name2");

        assertFalse(result);
        assertEquals(1,valid.getCount());
    }
    @Test
    public void test_Remove_Arch_DecreasesCount(){
        Excavation valid = new Excavation("valid",10);
        Archaeologist archaeologist1 = new Archaeologist("name1",10);
        Archaeologist archaeologist2 = new Archaeologist("name2",10);
        Archaeologist archaeologist3 = new Archaeologist("name3",10);

        valid.addArchaeologist(archaeologist1);
        valid.addArchaeologist(archaeologist2);
        valid.addArchaeologist(archaeologist3);

        boolean result = valid.removeArchaeologist(archaeologist1.getName());

        assertTrue(result);
        assertEquals(2, valid.getCount());
    }
    @Test
    public void test_Remove_Arch_On_Empty_Excavation_Will_Not_Throw(){
        Excavation valid = new Excavation("valid",10);

        boolean result = valid.removeArchaeologist("Some-name");

        assertFalse(result);
    }



}
