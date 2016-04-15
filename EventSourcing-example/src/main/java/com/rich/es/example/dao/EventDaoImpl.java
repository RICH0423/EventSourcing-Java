package com.rich.es.example.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rich.es.example.dao.model.Event;

@Repository
public class EventDaoImpl implements EventDao{
    
    @Autowired
    private DataSource dataSource;
    
    private JdbcTemplate template;

    @Override
    public List<Event> getAll() {
        template = new JdbcTemplate(dataSource);
        String query = "select id, aggregatetype, dataversion from Event";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Event> empList = new ArrayList<>();
 
        List<Map<String, Object>> empRows = jdbcTemplate.queryForList(query);
         
        for(Map<String,Object> empRow : empRows){
            Event event = new Event();
            event.setId(Integer.parseInt(String.valueOf(empRow.get("id"))));
            event.setAggregatetype((String.valueOf(empRow.get("aggregatetype"))));
            event.setDataversion(Integer.parseInt(String.valueOf(empRow.get("dataversion"))));
            empList.add(event);
        }
        
        return empList;
    }

}
