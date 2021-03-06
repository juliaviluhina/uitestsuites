package work_with_native_todoMVC;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import work_with_native_todoMVC.categories.Buggy;

import static pages.Preconditions.*;

import static pages.Preconditions.Task.aTask;
import static pages.ToDoMVCPage.*;

public class TodosOperationsAtAllFilterTest extends TodosBaseTest {

    @Category(Buggy.class)
    @Test
    public void testAddWithBug(){

        givenAtAll();

        add("t1", "t2");
        assertTasks("t1", "t2");
        assertItemsLeft(0);
    }

    @Test
    public void testAdd(){

        givenAtAll();

        add("t1", "t2");
        assertTasks("t1", "t2");
        assertItemsLeft(2);
    }

    @Test
    public void testEdit(){

        givenAtAll(Status.ACTIVE, "t1", "t2", "t3");

        edit("t2","t2 edited");
        assertTasks("t1", "t2 edited", "t3");
        assertItemsLeft(3);
    }

    @Test
    public void testDeleteByEditing(){

        givenAtAll(Status.ACTIVE, "t1", "t2");

        edit("t2", "");
        assertTasks("t1");
        assertItemsLeft(1);
    }

    @Test
    public void testDelete(){

        givenAtAll( aTask("t1"),
                aTask("t2", Status.COMPLETED),
                aTask("t3"));

        delete("t2");
        assertTasks("t1", "t3");
        assertItemsLeft(2);
    }

    @Test
    public void testCompleteAll() {

        givenAtAll(Status.ACTIVE, "t1", "t2");

        toggleAll();
        assertTasks("t1", "t2");
        assertItemsLeft(0);

    }

    @Test
    public void testReopenAll(){

        givenAtAll(Status.COMPLETED, "t1", "t2");

        toggleAll();
        assertTasks("t1", "t2");
        assertItemsLeft(2);
    }

    @Test
    public void testComplete(){

        givenAtAll( aTask("t1"),
                aTask("t2", Status.COMPLETED),
                aTask("t3"));

        toggle("t3");
        assertTasks("t1","t2", "t3");
        assertItemsLeft(1);
    }

    @Test
    public void testReopen(){

        givenAtAll( aTask("t1"),
                aTask("t2", Status.COMPLETED),
                aTask("t3"));

        toggle("t2");
        assertTasks("t1","t2", "t3");
        assertItemsLeft(3);
    }

    @Test
    public void testClearCompleted(){

        givenAtAll( aTask("t1"),
                aTask("t2", Status.COMPLETED),
                aTask("t3"));

        clearCompleted();
        assertTasks("t1", "t3");
        assertItemsLeft(2);

    }


}
