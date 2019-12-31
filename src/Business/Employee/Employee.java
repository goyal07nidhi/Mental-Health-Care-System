/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Employee;

/**
 *
 * @author Nidhi Goyal
 */
public class Employee {
    
    private final String name;
    private int id;
    private static int count = 1;
    
    public Employee(String name) {
        this.name = name;
        id = count;
        count++;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return name;
    }

}
