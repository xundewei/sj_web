package com.demo.FIO.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 使用Jackson生成json格式字符串
 * 
 * @author archie2010 since 2011-4-26下午05:59:46
 */
public class JacksonTest {

    private static JsonGenerator jsonGenerator = null;
    private static ObjectMapper objectMapper = null;
    private static User user = null;

    /**
     * 转化实体为json字符串
     * @throws IOException
     */
    public static void writeEntity2Json() throws IOException{
        System.out.println("使用JsonGenerator转化实体为json串-------------");
        //writeObject可以转换java对象，eg:JavaBean/Map/List/Array等
        jsonGenerator.writeObject(user);
        System.out.println();
        System.out.println("使用ObjectMapper-----------");
        //writeValue具有和writeObject相同的功能
        objectMapper.writeValue(System.out, user);
    }
    /**
     * 转化Map为json字符串
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static void writeMap2Json() throws JsonGenerationException, JsonMappingException, IOException{
        System.out.println("转化Map为字符串--------");
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("uname", user.getUname());
        map.put("upwd", user.getUpwd());
        map.put("USER", user);
        objectMapper.writeValue(System.out, map);
    }
    /**
     * 转化List为json字符串
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonGenerationException 
     */
    public static void writeList2Json() throws IOException{
        List<User> userList=new ArrayList<User>();
        userList.add(user);
        
        User u=new User();
        u.setUid(10);
        u.setUname("archie");
        u.setUpwd("123");
        userList.add(u);
        objectMapper.writeValue(System.out, userList);
        
         
        
        objectMapper.writeValue(System.out, userList);
    }
    public static void main(String[] args) {
        user = new User();
        user.setUid(5);
        user.setUname("tom");
        user.setUpwd("123");
        user.setNumber(3.44);
        objectMapper = new ObjectMapper();
        try {
            jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);
            //writeEntity2Json();
            //writeMap2Json();
            writeList2Json();
        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}