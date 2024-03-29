package com.rakuten.rakutenproductservice.swagger.controller;
/**
 * Swagger controller
 * 
 * For static way of exposing swagger
 * 
 * Commented now
 * 
 * 
 * @author adarshsumma
 *
 */


/*import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
@RestController */
public class SwaggerController {
	/**
   @RequestMapping(method = RequestMethod.GET, path = "/v2/api-docs", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource apiDocs() {
       return new ClassPathResource("swagger.json");
    }
   @RequestMapping(method = RequestMethod.GET, path = "/configuration/ui", produces = MediaType.APPLICATION_JSON_VALUE)
   public Object uiConfig() {
       return ImmutableList.of(ImmutableMap.of(
               "docExpansion", "none",
               "apisSorter", "alpha",
               "defaultModelRendering", "schema",
               "jsonEditor", Boolean.FALSE,
               "showRequestHeaders", Boolean.TRUE));
   }
   @RequestMapping(method = RequestMethod.GET, path = "/configuration/security", produces = MediaType.APPLICATION_JSON_VALUE)
   public Object securityConfig() {
       return ImmutableList.of(ImmutableMap.of(
               "apiKeyVehicle", "header",
               "scopeSeparator", ",",
               "apiKeyName", "api_key"));
   }
   @RequestMapping(method = RequestMethod.GET, path = "/swagger-resources", produces = MediaType.APPLICATION_JSON_VALUE)
   public Object resources() {
       return ImmutableList.of(ImmutableMap.of(
               "name", "default",
               "location", "/v2/api-docs", // should match the endpoint exposing Swagger JSON
               "swaggerVersion", "2.0"));
   }
   */
}