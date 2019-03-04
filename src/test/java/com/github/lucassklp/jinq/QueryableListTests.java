package com.github.lucassklp.jinq;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

//The test is using Arrange Act Assert methodology.
public class QueryableListTests {

    private QueryableList<User> users;

    public QueryableListTests(){
        users = new QueryableList<>();

        users.add(new User(10, "Tom", "tom@example.com", 40.2F));
        users.add(new User(26, "Clark", "clark@example.com", 62.7F));
        users.add(new User(52, "Marie", "marie@example.com", 54.7F));
        users.add(new User(37, "Peter", "peter@example.com", 87.7F));
        users.add(new User(10, "Cleber", "cleber@example.com", 47.7F));
        users.add(new User(10, "John", "john@example.com", 50.7F));
    }

    @Test
    public void map_over_the_age(){
        //Arrange
        QueryableList<Integer> expectedResult = new QueryableList<>();
        expectedResult.add(10);
        expectedResult.add(26);
        expectedResult.add(52);
        expectedResult.add(37);
        expectedResult.add(10);
        expectedResult.add(10);

        //Act
        QueryableList<Integer> result = users.map(x -> x.getAge());

        //Assert
        assertArrayEquals(result.toArray(), expectedResult.toArray());
    }

    @Test
    public void test_max_integer() throws Exception {
        //Act
        User result = users.max(x -> x.getAge());

        //Assert
        assertEquals(result, users.get(2));
    }

    @Test
    public void test_min_integer() throws Exception {
        //Act
        User result = users.min(x -> x.getAge());

        //Assert
        assertEquals(result, users.get(0));
    }

    @Test
    public void test_max_string() throws Exception {
        //Act
        User result = users.max(x -> x.getName());

        //Assert
        assertEquals(result, users.get(0));
    }

    @Test
    public void first_element() throws Exception {
        //Act
        User result = users.first();

        //Assert
        assertEquals(result, users.get(0));
    }



}
