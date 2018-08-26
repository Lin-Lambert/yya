package com.lambert.search.service.mq;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Autowired;


public class ProductMQ {

    @Autowired
    private HttpSolrServer httpSolrServer;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public void excuete(String msg) {

        try {
            JsonNode jsonNode = MAPPER.readTree(msg);

            String productId = jsonNode.get("productId").asText();

            if (StringUtils.equals("delete", jsonNode.get("type").asText())) {

                httpSolrServer.deleteById(productId);

                httpSolrServer.commit();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
