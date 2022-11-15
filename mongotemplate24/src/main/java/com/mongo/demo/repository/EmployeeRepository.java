package com.mongo.demo.repository;

import com.mongo.demo.model.Employee;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;

@Repository
public class EmployeeRepository {
    @Autowired
    MongoTemplate  mongoTemplate;
    public Employee insert(Employee emp) {
        Query query = new Query(Criteria.where("employeeId").is(emp.getEmployeeId()));
        Employee foundEmployee = mongoTemplate.findOne(query,Employee.class);
        if(foundEmployee == null) {
            return mongoTemplate.insert(emp,"employee");
        }
        return null;
    }
    public long update(Employee emp,String id) {
        Query query = new Query(Criteria.where("id").is(id));
        Employee foundEmployee = mongoTemplate.findOne(query,Employee.class);

if(foundEmployee !=null) {
    Update update =  new Update();
    update.set("firstName",emp.getFirstName());

    return mongoTemplate.updateFirst(query,update,"employee").getModifiedCount();
}
        return 0;
    }




    public List<Employee> find() {
        List<Employee> foundAllEmployee = mongoTemplate.findAll(Employee.class);
        if(foundAllEmployee == null) {
            return null;
        }
        return mongoTemplate.findAll(Employee.class);
    }
    public long delete(Employee emp) {
        Query query = new Query(Criteria.where("employeeId").is(emp.getEmployeeId()));
        return mongoTemplate.remove(emp).getDeletedCount();
    }

    public List<Employee> findBySalary(int salary) {
            Query query = new Query(Criteria.where("salary").gte(salary));
        return mongoTemplate.find(query,Employee.class);
    }
    public List<Employee> getByfirstName(String firstName) {
        Query query = new Query(Criteria.where("firstName").regex("^"+firstName));
        return mongoTemplate.find(query,Employee.class);
    }
    public Document getCount(String firstName) {
        Aggregation aggregation = Aggregation.newAggregation(group().sum("firstName").as("count"));
        return mongoTemplate.aggregate(aggregation,"employee", Employee.class).getRawResults();
    }

    public Document countSalary() {


        Aggregation aggregation = Aggregation.newAggregation(group("firstName").sum("salary").as("countSalary"));
        return mongoTemplate.aggregate(aggregation,"employee",Employee.class).getRawResults();
    }
}
