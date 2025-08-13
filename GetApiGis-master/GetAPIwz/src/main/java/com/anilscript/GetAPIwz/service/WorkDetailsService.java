package com.anilscript.GetAPIwz.service;//package com.anilscript.GetAPIwz.service;
//
//import com.anilscript.GetAPIwz.model.WorkDetails;
//import com.anilscript.GetAPIwz.repository.WorkDetailsRepository;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class WorkDetailsService {
//
//    @Autowired
//    private WorkDetailsRepository workDetailsRepository;
//
//    @Autowired
//    private WebClient.Builder webClientBuilder;
//
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    /**
//     * Fetches the list of dc_codes from the database.
//     */
//    public List<String> fetchDcCodes() {
//        return workDetailsRepository.findAllDcCodes();
//    }
//
//    /**
//     * Fetches and saves work details for multiple DC codes using WebClient.
//     */
//    public void fetchAndSaveWorkDetails() {
//        List<String> dcCodes = fetchDcCodes();
//
//        for (String dcCode : dcCodes) {
//            String url = "http://mpezgis.in/DWZ/api/proposed_wk_dtls.php?dc_code=" + dcCode;
//
//            // Debug URL
//            System.out.println("Fetching data from: " + url);
//
//            WebClient webClient = webClientBuilder.build();
//
//            // Make the asynchronous call
//            Mono<String> responseMono = webClient.get()
//                    .uri(url)
//                    .retrieve()
//                    .bodyToMono(String.class);
//
//            responseMono.subscribe(
//                    response -> processResponse(response),
//                    error -> {
//                        System.out.println("Error fetching data for DC Code: " + dcCode + ", Error: " + error.getMessage());
//                        error.printStackTrace();
//                    }
//            );
//        }
//    }
//
//    /**
//     * Fetches and saves work details for a single DC code using WebClient.
//     */
//    public void fetchAndSaveWorkDetails(String dcCode) {
//        String url = "http://mpezgis.in/DWZ/api/proposed_wk_dtls.php?dc_code=" + dcCode;
//
//        WebClient webClient = webClientBuilder.build();
//
//        Mono<String> responseMono = webClient.get()
//                .uri(url)
//                .retrieve()
//                .bodyToMono(String.class);
//
//        responseMono.subscribe(
//                response -> processResponse(response),
//                error -> {
//                    System.out.println("Error fetching data for DC Code: " + dcCode + ", Error: " + error.getMessage());
//                    error.printStackTrace();
//                }
//        );
//    }
//
//    /**
//     * Processes the JSON response and saves it into the database.
//     */
//    private void processResponse(String response) {
//        try {
//            System.out.println("API Response: " + response);
//
//            if (response == null || response.trim().isEmpty()) {
//                System.out.println("Empty response body.");
//                return;
//            }
//
//            JsonNode jsonResponse = objectMapper.readTree(response);
//            JsonNode workDetailsNode = jsonResponse.path("WORK_DETAILS");
//
//            if (workDetailsNode.isArray()) {
//                for (JsonNode workDetail : workDetailsNode) {
//                    WorkDetails workDetails = new WorkDetails();
//                    workDetails.setFeederId(workDetail.path("FEEDER_ID").asText());
//                    workDetails.setCircleCode(workDetail.path("CIRCLE_CODE").asText());
//                    workDetails.setDivisionCode(workDetail.path("DIVISION_CODE").asText());
//                    workDetails.setDcCode(workDetail.path("DC_CODE").asText());
//                    workDetails.setFdrName(workDetail.path("FDR_NAME").asText());
//                    workDetails.setSsId(workDetail.path("SS_ID").asText());
//                    workDetails.setSchemeCode(workDetail.path("SCHEME_CODE").asText());
//                    workDetails.setWorkStatus(workDetail.path("WORK_STATUS").asText());
//                    workDetails.setCreationDate(workDetail.path("CREATION_DT").isNull() ? null : new Date());
//
//                    // Save the work details to the database
//                    workDetailsRepository.save(workDetails);
//                }
//                System.out.println("Data saved successfully!");
//            } else {
//                System.out.println("No work details found in the response.");
//            }
//
//        } catch (Exception e) {
//            System.out.println("Error processing response: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//}




import com.anilscript.GetAPIwz.model.PoleData;
import com.anilscript.GetAPIwz.model.RapdrpLocationModel;
import com.anilscript.GetAPIwz.model.WorkDetails;
import com.anilscript.GetAPIwz.repository.WorkDetailsRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class WorkDetailsService {
    @Autowired
    private WorkDetailsRepository workDetailsRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<String> fetchDcCodes() {
        // Implement logic to fetch dc_codes from the database
        return workDetailsRepository.findAllDcCodes(); // Implement this method in the repository
    }

        public void fetchAndSaveWorkDetails() {

            List<String> dcCodes = fetchDcCodes();

            for (String dcCode : dcCodes) {

                String url = "http://mpezgis.in/DWZ/api/proposed_wk_dtls.php?dc_code=" + dcCode;

                System.out.println(url);



                try {
                    // Fetch the response as a String
                    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

                    // Log the raw response for debugging purposes
                    System.out.println("Response body: " + response.getBody());

                    // Manually parse the response body (which is JSON) as a JsonNode
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonResponse = objectMapper.readTree(response.getBody());

                    // Get the 'WORK_DETAILS' node from the response
                    JsonNode workDetailsNode = jsonResponse.path("WORK_DETAILS");

                    // Iterate through the work details and save them to the database
                    if (workDetailsNode.isArray()) {
                        for (JsonNode workDetail : workDetailsNode) {
                            WorkDetails workDetails = new WorkDetails();
                            workDetails.setFeederId(workDetail.path("FEEDER_ID").asText());
                            workDetails.setCircleCode(workDetail.path("CIRCLE_CODE").asText());
                            workDetails.setDivisionCode(workDetail.path("DIVISION_CODE").asText());
                            workDetails.setDcCode(workDetail.path("DC_CODE").asText());
                            workDetails.setFdrName(workDetail.path("FDR_NAME").asText());
                            workDetails.setSsId(workDetail.path("SS_ID").asText());
                            workDetails.setSchemeCode(workDetail.path("SCHEME_CODE").asText());
                            workDetails.setWorkStatus(workDetail.path("WORK_STATUS").asText());
                            workDetails.setCreationDate(workDetail.path("CREATION_DT").isNull() ? null : new Date());

                            // Save the work details to the database
                            workDetailsRepository.save(workDetails);
                        }
                    }
                } catch (Exception e) {
                    // Log the error in case of any issues
                    System.out.println("Error: " + e.getMessage());
                    e.printStackTrace();
                }
            }


        }

    public List<WorkDetails> getWorkDetails(RapdrpLocationModel model) {
        System.out.println("In Service getWorkDetails");
        System.out.println(model);
        List<WorkDetails> workDetailsList=workDetailsRepository.findAll();
        return workDetailsList;
    }


    public String getDcCodeFromFdrId(String fdrId) {
//        System.out.println("Feeder ID =" + fdrId);
        String dc = workDetailsRepository.getDcCodeFromFdrId(fdrId);
        System.out.println(dc);
        return dc;
    }

//    public List<WorkDetails> getWorkDetailsForEmbData() {
//         List<WorkDetails> workDetailsList = workDetailsRepository.findAll();
//         List<WorkDetails> filterWorkDetails = new ArrayList<>();
//         for (WorkDetails workDetail:workDetailsList){
////             System.out.println(workDetail.toString());
//             String workdetails = workDetail.getWorkStatus();
////             System.out.println(workdetails);
////            break;
//             if (workdetails == null)
//             {
//                 filterWorkDetails.add(workDetail);
//                 System.out.println(filterWorkDetails.toString());
//             }
//
//         }
//        System.out.println("workDetailsList size= "+workDetailsList.size());
//        System.out.println("filterWorkDetails size= "+filterWorkDetails.size());
//
//        return null;
//    }

    public List<WorkDetails> getWorkDetailsForEmbData() {
        List<WorkDetails> workDetailsList = workDetailsRepository.findAll();
        List<WorkDetails> filterWorkDetails = new ArrayList<>();

        for (WorkDetails workDetail : workDetailsList) {
            String workStatus = workDetail.getWorkStatus();

            if (workStatus.isEmpty()) {
                filterWorkDetails.add(workDetail);
            }
        }

        System.out.println("workDetailsList size = " + workDetailsList.size());
        System.out.println("filterWorkDetails size = " + filterWorkDetails.size());

        return filterWorkDetails;
    }

}

//public List<PoleData> getEmbData(RapdrpLocationModel model) {
//    List<PoleData> poleDataList =  poleDataRepository.findAll();
//    System.out.println(poleDataList.size());
//    List<PoleData> filteredPoleList = new ArrayList<>();
//    for(PoleData poleData : poleDataList) {
////            System.out.println("______");
////            System.out.println(poleData.toString());
////            boolean dcMatch;
////            dcMatch = poleData.getDcCode().equals(model.code_of_distribution_center);
////            System.out.println("dc match="+dcMatch);
//        if(poleData.getDcCode().equals(model.code_of_distribution_center))
//            filteredPoleList.add(poleData);
//    }
////        System.out.println("size of filtered list="+filteredPoleList.size());
//    return filteredPoleList;
//}


/* working with single dc code
 @Autowired
    private WorkDetailsRepository workDetailsRepository;

    @Autowired
    private RestTemplate restTemplate;


        public void fetchAndSaveWorkDetails(String dcCode) {
        String url = "http://mpezgis.in/DWZ/api/proposed_wk_dtls.php?dc_code=" + dcCode;

        try {
            // Fetch the response as a String
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            // Log the raw response for debugging purposes
            System.out.println("Response body: " + response.getBody());

            // Manually parse the response body (which is JSON) as a JsonNode
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(response.getBody());

            // Get the 'WORK_DETAILS' node from the response
            JsonNode workDetailsNode = jsonResponse.path("WORK_DETAILS");

            // Iterate through the work details and save them to the database
            if (workDetailsNode.isArray()) {
                for (JsonNode workDetail : workDetailsNode) {
                    WorkDetails workDetails = new WorkDetails();
                    workDetails.setFeederId(workDetail.path("FEEDER_ID").asText());
                    workDetails.setCircleCode(workDetail.path("CIRCLE_CODE").asText());
                    workDetails.setDivisionCode(workDetail.path("DIVISION_CODE").asText());
                    workDetails.setDcCode(workDetail.path("DC_CODE").asText());
                    workDetails.setFdrName(workDetail.path("FDR_NAME").asText());
                    workDetails.setSsId(workDetail.path("SS_ID").asText());
                    workDetails.setSchemeCode(workDetail.path("SCHEME_CODE").asText());
                    workDetails.setWorkStatus(workDetail.path("WORK_STATUS").asText());
                    workDetails.setCreationDate(workDetail.path("CREATION_DT").isNull() ? null : new Date());

                    // Save the work details to the database
                    workDetailsRepository.save(workDetails);
                }
            }
        } catch (Exception e) {
            // Log the error in case of any issues
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
 */