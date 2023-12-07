package bank;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class BankTests {

    private static final String NAME = "testBank";
    private static final int CAPACITY = 10;
    private Bank bank;
    private Client client;

    @Before
    public void SetUp() {
        this.bank = new Bank(NAME, CAPACITY);
        this.client = new Client("test_client");
    }

    @Test(expected = NullPointerException.class)
    public void test_Creating_Bank_WithNullName_ShouldThrow() {
        new Bank(null, CAPACITY);
    }

    @Test(expected = NullPointerException.class)
    public void test_Creating_Bank_EmptyName_ShouldThrow() {
        new Bank("   ", CAPACITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Creating_Bank_WithNegativeCapacity_ShouldThrow() {
        new Bank(NAME, -1);
    }

    @Test
    public void test_Creating_Bank_WithValidArguments() {
        Bank bank = new Bank(NAME, CAPACITY);

        Assert.assertEquals(NAME, bank.getName());
        Assert.assertEquals(CAPACITY, bank.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Should_Fail_When_Capacity_Reached() {
        Bank bank = new Bank(NAME, 0);
        bank.addClient(new Client("test_client"));
    }

    @Test
    public void test_Should_Correctly_Increase_ClientCount() {
        bank.addClient(client);
        Assert.assertEquals(1, bank.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Remove_Client_Should_Fail_When_Missing() {
        bank.removeClient(client.getName());
    }

    @Test
    public void test_Remove_Client_Should_Decrease_ClientsCount() {
        bank.addClient(client);
        bank.removeClient(client.getName());
        Assert.assertEquals(0, bank.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_LoanWithdrawal_ShouldFail_When_Missing() {
        bank.removeClient(client.getName());
    }

    @Test
    public void test_loanWithdrawal_Should_Set_Client_NotApproved() {
        bank.addClient(client);
        bank.loanWithdrawal(client.getName());
        Assert.assertFalse(client.isApprovedForLoan());
    }


}