package com.patryk.Task1;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


@Repository
public class RequestRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    CUS cus;

    public int inProgress(){
        return jdbcTemplate.queryForObject("SELECT COUNT(id) FROM requests", new Object[] {} , Integer.class );
    }
    public void save(List<Request> requests) {
            requests.forEach(request -> jdbcTemplate.update("INSERT INTO requests (characters, min, max, quantity) VALUES(?, ?, ?, ?)",
                    request.getCharacters(), request.getMin(), request.getMax(), request.getQuantity()
            ));

    }
    public void create() throws IOException {
        FileWriter fileWriter = new FileWriter("Results");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        List<Request> jobs = jdbcTemplate.query("SELECT id, characters, min, max, quantity FROM requests", BeanPropertyRowMapper.newInstance(Request.class));
        String b = "";
         for(int i = 0; i < jobs.size(); i++){
             printWriter.println("REQUEST " + (i+1));

             b = cus.createUniqueString(jobs.get(i));
             printWriter.println(b);
             delete(jobs.get(i).getId());

         }
         printWriter.close();
    }




    public void delete(int id){
        jdbcTemplate.update("DELETE FROM requests WHERE id = ?", id);
    }
    public String getFile() throws IOException {
        String content = Files.readString(Path.of("D:\\Programowanie\\Task1\\Results"));
        String result = content.replaceAll(" ", "\r\n");
        return content;
    }







}
