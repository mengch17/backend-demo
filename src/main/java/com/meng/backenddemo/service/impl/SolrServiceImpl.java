package com.meng.backenddemo.service.impl;

import com.meng.backenddemo.entity.UserSolrEntry;
import com.meng.backenddemo.requests.SearchRequest;
import com.meng.backenddemo.service.SolrService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.params.SolrParams;
import org.springframework.beans.factory.annotation.Value;
import org.apache.solr.client.solrj.response.QueryResponse;


import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SolrServiceImpl implements SolrService {

    private static final Logger log = LogManager.getLogger(SolrServiceImpl.class);

    private final SolrClient client;

    public SolrServiceImpl(@Value("${solr.host}") String solrHost,
                           @Value("${solr.core}") String solrCore) {
        String urlString = solrHost + "/" + solrCore;
        this.client = new HttpSolrClient.Builder(urlString).build();
        log.info("Solr client initialized with URL: " + urlString);
    }

    /**
     * Insert collection to Solr
     * @param users
     * @throws SolrServerException
     * @throws IOException
     */
    @Override
    public void insertAllProducts(List<UserSolrEntry> users) throws SolrServerException, IOException {
        if (users.isEmpty()) {
            log.info("No products to insert into Solr.");
            return;
        }
        client.addBeans(users);
        client.commit();
        log.info("Inserted all products to Solr.");
    }

    /**
     * Search by SkuId
     * @param skuId
     * @return
     * @throws SolrServerException
     * @throws IOException
     */
    public String searchBySkuId(String skuId) throws SolrServerException, IOException {
        SolrParams query = new SolrQuery("sku_id:" + skuId);
        log.info("Executing solr query: " + query);
        QueryResponse response = client.query(query);
        return response.toString();
    }

    /**
     * Search with json request
     * @param searchRequest
     * @return
     * @throws Exception
     */
    public QueryResponse searchJson(SearchRequest searchRequest) throws Exception {
        SolrQuery query = new SolrQuery();
        StringBuilder queryBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : searchRequest.getSearchCriteria().entrySet()) {
            if (queryBuilder.length() > 0) {
                queryBuilder.append(" AND ");
            }
            queryBuilder.append(entry.getKey()).append(":").append(entry.getValue());
        }
        query.setQuery(queryBuilder.toString());

        log.info("Executing solr query: " + query);
        QueryResponse response = client.query(query);
        return response;
    }
}
