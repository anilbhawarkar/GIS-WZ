package com.anilscript.GetAPIwz.service;

import com.anilscript.GetAPIwz.controller.WorkDetailsController;
import com.anilscript.GetAPIwz.model.PoleData;
import com.anilscript.GetAPIwz.model.RapdrpLocationModel;
import com.anilscript.GetAPIwz.model.WorkDetails;
import com.anilscript.GetAPIwz.repository.PoleDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PoleDataService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private PoleDataRepository poleDataRepository;

    @Autowired
    private WorkDetailsController workDetailsController;

    private static final String EXTERNAL_API_URL = "https://mpezgis.in/assets/api/json_emb_data_wz.php";  // Replace with actual API URL

    private static final String FDR_ID = "429250";
    private static final String USERNAME = "wz_emb";
    private static final String PASSWORD = "emb@123";

    public void fetchAndSavePoleData() {

        //get DC code form workdetails table from fdr_id
//        String dcCode = "";
//        dcCode = poleDataRepository.findAllBydcCode(FDR_ID);
//        System.out.println(dcCode);


        // Prepare form data
        String formData = "fdr_id=" + FDR_ID + "&username=" + USERNAME + "&password=" + PASSWORD;

        System.out.println("formData = " + formData);
        String dcCode = workDetailsController.getDcCodeFromFdrId(FDR_ID);

        WebClient webClient = webClientBuilder.build();

        // Make HTTP POST request
        Mono<String> responseMono = webClient.post()
                .uri(EXTERNAL_API_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(formData)
                .retrieve()
                .bodyToMono(String.class);

        responseMono.subscribe(
                response -> {
                    try {
                        // Debugging: Print response
                        System.out.println("Response from API: " + response);

                        if (response == null || response.trim().isEmpty()) {
                            System.out.println("Empty response body");
                            return;
                        }

                        // Parse JSON response
                        ObjectMapper objectMapper = new ObjectMapper();
                        Map<String, Object> responseData = objectMapper.readValue(response, Map.class);

                        if (responseData.containsKey("emb_data")) {
                            List<Map<String, String>> embDataList = (List<Map<String, String>>) responseData.get("emb_data");

                            for (Map<String, String> data : embDataList) {
                                PoleData poleData = new PoleData();
                                poleData.setFeederId(data.get("FEEDER_ID"));
                                poleData.setDevPoleId(data.get("DEV_POLE_ID"));
                                poleData.setFdrName(data.get("FDR_name"));
                                poleData.setPoleLat(data.get("POLE_LAT"));
                                poleData.setPoleLong(data.get("POLE_LONG"));
                                poleData.setSchNo(data.get("SCH_NO"));
                                poleData.setDcCode(dcCode);

                                // Save to DB
                                poleDataRepository.save(poleData);
                            }
                        } else {
                            System.out.println("No 'emb_data' found in response");
                        }
                    } catch (JsonParseException e) {
                        System.out.println("Invalid JSON response: " + response);
                    } catch (Exception e) {
                        System.out.println("Exception while parsing response: " + e.getMessage());
                        e.printStackTrace();
                    }
                },
                error -> {
                    System.out.println("Request failed: " + error.getMessage());
                    error.printStackTrace();
                }
        );
    }

    public List<PoleData> getEmbData(RapdrpLocationModel model) {
        List<PoleData> poleDataList = poleDataRepository.findAll();
        System.out.println(poleDataList.size());
        List<PoleData> filteredPoleList = new ArrayList<>();
        for (PoleData poleData : poleDataList) {
//            System.out.println("______");
//            System.out.println(poleData.toString());
//            boolean dcMatch;
//            dcMatch = poleData.getDcCode().equals(model.code_of_distribution_center);
//            System.out.println("dc match="+dcMatch);
            if (poleData.getDcCode().equals(model.code_of_distribution_center))
                filteredPoleList.add(poleData);
        }
        System.out.println("size of filtered list="+filteredPoleList.size());
        return filteredPoleList;
    }

    public void fetchAndSavePoleDataForAllFdr() {
        List<WorkDetails> filterWorkList = workDetailsController.getWorkDetailsForEmbData();
        System.out.println(filterWorkList.size());

        for (WorkDetails workDetail : filterWorkList) {
            // Prepare form data
            String feeder_id = workDetail.getFeederId();
            String formData = "fdr_id=" + feeder_id + "&username=" + USERNAME + "&password=" + PASSWORD;

            System.out.println("formData = " + formData);
            String dcCode = workDetailsController.getDcCodeFromFdrId(feeder_id);

            WebClient webClient = webClientBuilder.build();

            // Make HTTP POST request
            Mono<String> responseMono = webClient.post()
                    .uri(EXTERNAL_API_URL)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .accept(MediaType.APPLICATION_JSON)
                    .bodyValue(formData)
                    .retrieve()
                    .bodyToMono(String.class);

            responseMono.subscribe(
                    response -> {
                        try {
                            // Debugging: Print response
                            System.out.println("Response from API: " + response);

                            if (response == null || response.trim().isEmpty()) {
                                System.out.println("Empty response body");
                                return;
                            }

                            // Parse JSON response
                            ObjectMapper objectMapper = new ObjectMapper();
                            Map<String, Object> responseData = objectMapper.readValue(response, Map.class);

                            if (responseData.containsKey("emb_data")) {
                                List<Map<String, String>> embDataList = (List<Map<String, String>>) responseData.get("emb_data");

                                for (Map<String, String> data : embDataList) {
                                    PoleData poleData = new PoleData();
                                    poleData.setFeederId(data.get("FEEDER_ID"));
                                    poleData.setDevPoleId(data.get("DEV_POLE_ID"));
                                    poleData.setFdrName(data.get("FDR_name"));
                                    poleData.setPoleLat(data.get("POLE_LAT"));
                                    poleData.setPoleLong(data.get("POLE_LONG"));
                                    poleData.setSchNo(data.get("SCH_NO"));
                                    poleData.setDcCode(dcCode);

                                    // Save to DB
                                    poleDataRepository.save(poleData);
                                }
                            } else {
                                System.out.println("No 'emb_data' found in response");
                            }
                        } catch (JsonParseException e) {
                            System.out.println("Invalid JSON response: " + response);
                        } catch (Exception e) {
                            System.out.println("Exception while parsing response: " + e.getMessage());
                            e.printStackTrace();
                        }
                    },
                    error -> {
                        System.out.println("Request failed: " + error.getMessage());
                        error.printStackTrace();
                    }
            );

        }

//    public List<PoleData> getEmbDataDc(RapdrpLocationModel model) {
//        String dc_code = model.code_of_distribution_center;
//        List<PoleData> poleDataList = poleDataRepository.findAllByDcCode(dc_code);
//        return poleDataList;
//    }
    }

    public List<PoleData> getEmbDataFromDc(RapdrpLocationModel model) {
        String dcCode = model.code_of_distribution_center;
        return poleDataRepository.findAllByDcCode(dcCode);

    }
}




/* import com.anilscript.GetAPIwz.beans.PoleData;
import com.anilscript.GetAPIwz.repository.PoleDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class PoleDataService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PoleDataRepository poleDataRepository;

    private static final String EXTERNAL_API_URL = "http://mpezgis.in/assets/api/json_emb_data_wz.php"; // Replace with actual API URL

    String fdr_id = "49166";
    String username = "wz_emb" ;
    String password =  "emb@123";

    public void fetchAndSavePoleData() {

//        System.out.println("Feeder id "+fdr_id);
//        System.out.println("user id "+username);
//        System.out.println("password "+password);


        // Prepare form data
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("fdr_id", fdr_id);
        formData.add("username", username);
        formData.add("password", password);

        System.out.println(formData);
        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

//    // Add Authentication (Basic Auth as example)
//    String username = "your_username";
//    String password = "your_password";
//    String auth = username + ":" + password;
//    String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
//    headers.add("Authorization", "Basic " + encodedAuth);

        // Make HTTP request
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);
        ResponseEntity<String> response = restTemplate.exchange(EXTERNAL_API_URL, HttpMethod.POST, requestEntity, String.class);

        // Debugging: Print response and status code
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response from API: " + response.getBody());

        // Check if response is successful
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();

            // Check if the body is empty
            if (responseBody == null || responseBody.trim().isEmpty()) {
                System.out.println("Empty response body");
                return;
            }

            try {
                // Parse response to Map
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> responseData = objectMapper.readValue(responseBody, Map.class);

                if (responseData.containsKey("emb_data")) {
                    List<Map<String, String>> embDataList = (List<Map<String, String>>) responseData.get("emb_data");

                    for (Map<String, String> data : embDataList) {
                        PoleData poleData = new PoleData();
                        poleData.setFeederId(data.get("FEEDER_ID"));
                        poleData.setDevPoleId(data.get("DEV_POLE_ID"));
                        poleData.setFdrName(data.get("FDR_name"));
                        poleData.setPoleLat(data.get("POLE_LAT"));
                        poleData.setPoleLong(data.get("POLE_LONG"));
                        poleData.setSchNo(data.get("SCH_NO"));
                        poleDataRepository.save(poleData);
                    }
                } else {
                    System.out.println("No 'emb_data' found in response");
                }
            } catch (JsonParseException e) {
                System.out.println("Invalid JSON response: " + responseBody);
            } catch (Exception e) {
                System.out.println("Exception while parsing response: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Request failed with status code: " + response.getStatusCode());
            System.out.println("Response body: " + response.getBody());
        }

    }
}

    /* @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PoleDataRepository poleDataRepository;

    private static final String EXTERNAL_API_URL = "http://mpezgis.in/assets/api/json_emb_data_wz.php"; // Replace with actual API URL

    public void fetchAndSavePoleData(String fdr_id, String username, String password) throws JsonProcessingException {
        // Prepare form data
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("FEEDER_ID", fdr_id);
        formData.add("username", username);
        formData.add("password", password);

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);  // Sending form data
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));  // Force JSON response

        // Make HTTP request
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);
        ResponseEntity<String> response = restTemplate.exchange(EXTERNAL_API_URL, HttpMethod.POST, requestEntity, String.class);

        // Log response for debugging
        System.out.println("Response from API: " + response.getBody());

        // Check response
        if (response.getStatusCode().is2xxSuccessful()) {
            // Convert response to a map
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> responseData = objectMapper.readValue(response.getBody(), Map.class);

            if (responseData.containsKey("emb_data")) {
                List<Map<String, String>> embDataList = (List<Map<String, String>>) responseData.get("emb_data");

                for (Map<String, String> data : embDataList) {
                    PoleData poleData = new PoleData();
                    poleData.setFeederId(data.get("FEEDER_ID"));
                    poleData.setDevPoleId(data.get("DEV_POLE_ID"));
                    poleData.setFdrName(data.get("FDR_name"));
                    poleData.setPoleLat(data.get("POLE_LAT"));
                    poleData.setPoleLong(data.get("POLE_LONG"));
                    poleData.setSchNo(data.get("SCH_NO"));
                    System.out.println(data);
                    //poleDataRepository.save(poleData);
                }
            }
        } else {
            throw new RuntimeException("Failed to fetch data. Status: " + response.getStatusCode());
        }
    } */


