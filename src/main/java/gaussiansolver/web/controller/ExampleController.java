package gaussiansolver.web.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import gaussiansolver.data.InData;
import gaussiansolver.data.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import gaussiansolver.dbClients.MySQLClient_2;

import gaussiansolver.parallelGaussian.ParallelGaussianEliminationNet_Rest;


@RestController
public class ExampleController {

    @Autowired
    Result result;

    @Autowired
    ParallelGaussianEliminationNet_Rest pg;

    @Autowired
    MySQLClient_2 dbClient;


    //@RequestMapping(value = "/", method = RequestMethod.GET)
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> getRequests() {
    //public String getRequests() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");

        StringBuffer sb = new StringBuffer();

        List<Object> resultList = null;
        resultList = dbClient.getAll();
        for (Object obj : resultList){
            sb.append(obj);
            //sb.append("\"");
            sb.append("\n");
        }

        //System.out.println(sb.toString());

        return ResponseEntity.ok(sb.toString());
        //return sb.toString();
    }


    @RequestMapping(value = "/", method = RequestMethod.POST, consumes="application/json", produces="application/json")
    @ResponseBody
    //public String getJson(@RequestBody InData inData) {
    public ResponseEntity<String> postRequest(@RequestBody InData inData) {
    //public Response postRequest(@RequestBody InData inData) {
        ObjectMapper mapper = new ObjectMapper();


        // Gaussian Calculation
        String[] args = new String[1];
        args[0] = "2";

        pg.execGaussianElimination_2(args, inData, result);


        // Java to Json
        String jsonStringInData = "";
        try {
            jsonStringInData = mapper.writeValueAsString(inData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String jsonStringResult = "";
        try {
            jsonStringResult = mapper.writeValueAsString(result.result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        dbClient.insert(jsonStringInData, jsonStringResult);

        return ResponseEntity.ok(jsonStringResult.toString());

    }


}
