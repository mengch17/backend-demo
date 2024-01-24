package com.meng.backenddemo.controller;
import com.meng.backenddemo.response.SolrResponse;
import com.meng.backenddemo.service.SolrService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class SolrSearchController {

    public static final Logger log = LogManager.getLogger(SolrSearchController.class);

    @Autowired
    SolrService solrService;

//    public SolrResponse search
}
