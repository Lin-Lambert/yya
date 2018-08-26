package com.lambert.search.service;

import com.lambert.manager.pojo.Product;
import com.lambert.search.pojo.SolrResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class SearchService {
    @Autowired
    private HttpSolrServer httpSolrServer;

    public SolrResult search(String queryString, String cid, String price, String page, String sort)
            throws SolrServerException {

        // 设置查询条件
        SolrQuery solrQuery = new SolrQuery();
        if (StringUtils.isNoneBlank(queryString)) {
            solrQuery.setQuery(queryString);
        } else {
            solrQuery.setQuery("*:*");
        }

        if (StringUtils.isNoneBlank(cid)) {
            // 设置过滤条件
            solrQuery.setFilterQueries("cid:" + cid);
        }

        // 设置当前页
        if (!StringUtils.isNoneBlank(page)) {
            page = "1";
        }
        solrQuery.setStart((Integer.parseInt(page)-1)*20);
        solrQuery.setRows(20);

        // 设置价格范围 price: 0-9
        if (StringUtils.isNoneBlank(price)) {
            String[] split = price.split("-");
            if (split != null && split.length == 2) {
                solrQuery.setFilterQueries("price:[" + split[0] + " TO " + split[1] + " ] ");
            }
        }

        if ( sort != null && sort.length() != 0 && !sort.trim().toString().equals("")) {
            if ((Integer.parseInt(sort) % 2) == 0) {
                solrQuery.setSort("price", ORDER.desc);
            }
            if ((Integer.parseInt(sort) % 2) == 1) {
                solrQuery.setSort("price", ORDER.asc);
            }
        }

        // 设置高亮显示
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("title");
        solrQuery.setHighlightSimplePre("<font color=\"red\">");
        solrQuery.setHighlightSimplePost("</font>");

        // 执行搜索
        QueryResponse response = this.httpSolrServer.query(solrQuery);

        // 获取高亮
        Map<String, Map<String, List<String>>> map = response.getHighlighting();

        // 获取结果集
        SolrDocumentList solrDocumentList = response.getResults();
        // 总记录数
        long total = solrDocumentList.getNumFound();

        // 总页数
        Integer pageCount = (int)((total + 20 - 1) / 20);

        List<Product> productList = new ArrayList<Product>();

        for (SolrDocument solrDocument : solrDocumentList) {
            Product product = new Product();

            product.setId(Long.parseLong(solrDocument.get("id").toString()));
            String imagestr = solrDocument.get("image").toString();

            String[] split = imagestr.split(",");
            if (split.length > 0) {
                product.setImage(split[0]);
            }

            product.setPrice(Long.parseLong(solrDocument.get("price").toString()));

            // 标题的关键词是高亮显示
            List<String> list = map.get(solrDocument.get("id").toString()).get("title");
            if (list != null && list.size() > 0) {// 有高亮信息
                product.setTitle(list.get(0));
            } else {
                product.setTitle(solrDocument.get("title").toString());
            }
            productList.add(product);
        }
        SolrResult solrResult = new SolrResult(Integer.parseInt(page), pageCount, total, productList);

        return solrResult;
    }

}
