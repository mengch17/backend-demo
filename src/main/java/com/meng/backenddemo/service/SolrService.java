package com.meng.backenddemo.service;

import com.meng.backenddemo.entity.UserSolrEntry;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

public interface SolrService {
    void insertAllProducts(List<UserSolrEntry> products) throws IOException, SolrServerException;
}
