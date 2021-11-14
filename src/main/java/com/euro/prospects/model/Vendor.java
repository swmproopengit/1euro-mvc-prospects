package com.euro.prospects.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class Vendor {
    private String id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String grade;
    private String resume;
    private String picture;
    private String barnnerpicture;
    private Date date;
}
