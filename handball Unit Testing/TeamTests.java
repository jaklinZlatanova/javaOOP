package handball;

import org.junit.Assert;
import org.junit.Test;

public class TeamTests {

    @Test(expected = NullPointerException.class)
    public void test_Constructor_ShouldThrow_When_InvalidName() {
        Team team = new Team("", 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Constructor_ShouldThrow_When_PositionNegative() {
        Team team = new Team("", -5);
    }

    @Test
    public void test_Constructor_ShouldCreate_Correct_Team() {
        Team team = new Team("JAVA", 5);

        Assert.assertEquals("JAVA", team.getName());
        Assert.assertEquals(5, team.getPosition());
        Assert.assertEquals(0, team.getCount());

    }

    @Test(expected = NullPointerException.class)
    public void test_SetName_ShouldThrow_When_NameWhiteSpace() {
        Team team = new Team("   ", 5);
    }

    @Test(expected = NullPointerException.class)
    public void test_SetName_ShouldThrow_When_NameNull() {
        Team team = new Team(null, 5);
    }

    @Test
    public void test_GetCount_ShouldReturn_CorrectCount() {
        Team team = new Team("JAVA", 5);
        team.add(new HandballPlayer("Jaklinka"));
        team.add(new HandballPlayer("Mariiche"));
        team.add(new HandballPlayer("Cinayche"));

        Assert.assertEquals(3, team.getCount());
    }

    @Test
    public void test_GetCount_EmptyTeam_ShouldReturn_0() {
        Team team = new Team("JAVA", 5);

        Assert.assertEquals(0, team.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Add_OnFullTeam_ShouldThrow() {
        Team team = new Team("JAVA", 2);

        team.add(new HandballPlayer("Jaklinka"));
        team.add(new HandballPlayer("Mariiche"));
        team.add(new HandballPlayer("Cinayche"));


    }

    @Test
    public void test_Add_When_Team_IsNotFull() {
        Team team = new Team("JAVA", 3);

        team.add(new HandballPlayer("Jaklinka"));
        team.add(new HandballPlayer("Mariiche"));
        team.add(new HandballPlayer("Cinayche"));

        Assert.assertEquals(3, team.getCount());
    }

    @Test
    public void test_Remove_ShouldRemove_When_Player_Exists() {
        Team team = new Team("JAVA", 3);

        team.add(new HandballPlayer("Jaklinka"));
        team.add(new HandballPlayer("Mariiche"));
        team.add(new HandballPlayer("Cinayche"));

        team.remove("Jaklinka");
        Assert.assertEquals(2, team.getCount());
    }
    @Test(expected = NullPointerException.class)
    public void test_Remove_ShouldThrow_When_PlayerNull() {
        Team team = new Team("JAVA", 3);

        team.add(new HandballPlayer("Jaklinka"));
        team.add(new HandballPlayer("Mariiche"));
        team.add(new HandballPlayer("Cinayche"));

        team.remove(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void test_Remove_ShouldThrow_When_Player_NonExistent() {
        Team team = new Team("JAVA", 3);

        team.add(new HandballPlayer("Jaklinka"));
        team.add(new HandballPlayer("Mariiche"));
        team.add(new HandballPlayer("Cinayche"));

        team.remove("Juju");


    }
    @Test(expected = IllegalArgumentException.class)
    public void test_PlayerForAnotherTeam_ShouldThrow_When_Player_NonExistent() {
        Team team = new Team("JAVA", 3);

        team.add(new HandballPlayer("Jaklinka"));
        team.add(new HandballPlayer("Mariiche"));
        team.add(new HandballPlayer("Cinayche"));

        team.playerForAnotherTeam("Juju");
    }
    @Test
    public void test_PlayerForAnotherTeam_Should_SetActive_ToFalse() {
        Team team = new Team("JAVA", 3);

        team.add(new HandballPlayer("Jaklinka"));
        team.add(new HandballPlayer("Mariiche"));
        team.add(new HandballPlayer("Cinayche"));

       HandballPlayer player= team.playerForAnotherTeam("Cinayche");
        Assert.assertFalse(player.isActive());

    } @Test
    public void test_GetStatistics_Should_ReturnCorrectly() {
        Team team = new Team("JAVA", 3);

        team.add(new HandballPlayer("Jaklinka"));
        team.add(new HandballPlayer("Mariiche"));
        team.add(new HandballPlayer("Cinayche"));

        String expectedResult = "The player Jaklinka, Mariiche, Cinayche is in the team JAVA.";
        String actualResult = team.getStatistics();

        Assert.assertEquals(expectedResult,actualResult);

    }
    @Test
    public void test_GetStatistics_ShouldReturnCorrectly_WhenEmpty() {
        Team team = new Team("JAVA", 3);

        String expectedResult = "The player  is in the team JAVA.";
        String actualResult = team.getStatistics();

        Assert.assertEquals(expectedResult,actualResult);

    }

}
