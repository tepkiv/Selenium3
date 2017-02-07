package tests.contact;

import com.data.ContactData;
import com.pages.ContactHelper;
import com.TestBase;
import com.utils.ModifiedSortedList;
import org.testng.annotations.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ContactModificationTest extends TestBase {


    @Test(dataProvider = "randomValidContactGenerator")
    public void testContactModification(ContactData contact) {
        ContactHelper contactHelper = app.getContactHelper();
        // save old state
        ModifiedSortedList<ContactData> oldList = contactHelper.getContacts();
        System.out.println(oldList.size());
        // actions
        Random rnd = new Random();
        int index = rnd.nextInt(oldList.size() - 1);

        contactHelper.modifyContact(index, contact);
        contactHelper.waitUntilPageLoads();
        // save new state
        ModifiedSortedList<ContactData> newList = contactHelper.getContacts();
        System.out.println(newList.size());

        oldList.remove(index);
        oldList.add(contact);

        assertThat(newList, equalTo(oldList));
    }
}

