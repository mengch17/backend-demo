package com.meng.backenddemo.entity;

import jakarta.persistence.Id;
import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

import java.util.Date;

@Data
public class UserSolrEntry {

    @Id()
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userUuid;

    @Field("first_name")
    private String firstName;

    @Field("last_name")
    private String lastName;

    @Field("birthday")
    private Date birthday;

    @Field("gender")
    private String gender;

    @Field("phone")
    private String phone;

    @Field("user_rank")
    private int userRank;
}
