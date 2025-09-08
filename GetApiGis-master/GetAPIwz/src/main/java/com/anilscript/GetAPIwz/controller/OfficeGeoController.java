package com.anilscript.GetAPIwz.controller;

import com.anilscript.GetAPIwz.exception.InvalidInputException;
import com.anilscript.GetAPIwz.exception.ResourceNotFoundException;
import com.anilscript.GetAPIwz.model.OfficeGeoMaster;
import com.anilscript.GetAPIwz.service.OfficeGeoService;
import com.anilscript.GetAPIwz.util.FileStorageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/offices")
public class OfficeGeoController {


//    private static final Logger logger = GlobalResources.getLogger(BillController.class);
    @Autowired
    OfficeGeoService officeGeoService;

    @Autowired
    FileStorageUtil fileStorageUtil;

    private static final Logger logger = LoggerFactory.getLogger(OfficeGeoController.class);

    // GET all offices
    @GetMapping
    public ResponseEntity<List<OfficeGeoMaster>> getAllOffices() {
        final String methodName = "getAllOffices";
        logger.info(methodName + "called");

        List<OfficeGeoMaster> officeList = officeGeoService.getAllOffices();

        if(officeList == null || officeList.isEmpty())
        {
            throw new ResourceNotFoundException("No offices found in database");
        }
        return ResponseEntity.ok(officeList);
    }

    @GetMapping("/circle")
    public ResponseEntity<List<OfficeGeoMaster>> getOfficeByCircle(@RequestParam String circle)
    {
        final String methodName = "getOfficeByCircle()";
        logger.info(methodName + "called for circle: "+circle);

        //input valiation
        if(circle == null || circle.trim().isEmpty())
        {
            throw new InvalidInputException("circle can not be empty");
        }

        List<OfficeGeoMaster> officeList = officeGeoService.getOfficeByCircleCode(circle);
        System.out.println("list size"+officeList.size());

        if(officeList == null || officeList.isEmpty())
        {
            throw new ResourceNotFoundException("No Offices found for the circle :"+circle);
        }
        return ResponseEntity.ok(officeList);
    }
    // GET office by ID
    @GetMapping("/{id}")
    public ResponseEntity<OfficeGeoMaster> getOfficeById(@PathVariable Long id)
    {
        final String methodName = "getOfficeById()";
        logger.info(methodName + "called for office id: "+id);

        if (id == null || id == 0)
        {
            throw new InvalidInputException("office id can not be empty or zero");
        }

        return officeGeoService.getOfficeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST create office (without image)
    @PostMapping
    public ResponseEntity<OfficeGeoMaster> createOffice(@RequestBody OfficeGeoMaster office)
    {
        final String methodName = "createOffice()";
        logger.info(methodName+"called for office details : "+office.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(officeGeoService.createOffice(office));
    }

//    // POST create with image
//    @PostMapping("/with-image")
//    public ResponseEntity<OfficeGeoMaster> createOfficeWithImage(
//            @RequestPart("office") OfficeGeoMaster office,
//            @RequestPart("image") MultipartFile image) throws IOException
//    {
//        final String methodName = "createOfficeWithImage()";
//        logger.info(methodName+"called");
//        office.setBuildingImage(image.getBytes());
//        return ResponseEntity.status(HttpStatus.CREATED).body(officeGeoService.createOffice(office));
//    }
@PostMapping("/with-image")
public ResponseEntity<OfficeGeoMaster> createOfficeWithImage(
        @RequestPart("office") OfficeGeoMaster office,
        @RequestPart("image") MultipartFile image) throws IOException {

    final String methodName = "createOfficeWithImage()";
    logger.info(methodName + " called");

    // Save the file to disk
    String imagePath = fileStorageUtil.saveFile(image);

    // Save only path in database
    office.setBuildingImagePath(imagePath);

    return ResponseEntity.status(HttpStatus.CREATED).body(officeGeoService.createOffice(office));
}


    // PUT update office
    @PutMapping("/{id}")
    public ResponseEntity<OfficeGeoMaster> updateOffice(@PathVariable Long id,
                                                        @RequestBody OfficeGeoMaster office)
    {
        final String methodName = "updateOffice()";
        logger.info(methodName+"called for reqest to update with id: "+ id);
        return ResponseEntity.ok(officeGeoService.updateOffice(id, office));
    }

    // DELETE office
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffice(@PathVariable Long id)
    {
        final String methodName = "deleteOffice()";
        logger.info("called to delete office for id : "+id);
        officeGeoService.deleteOffice(id);
        return ResponseEntity.noContent().build();
    }

//    // GET office image
//    @GetMapping("/{id}/image")
//    public ResponseEntity<byte[]> getOfficeImage(@PathVariable Long id)
//    {
//        final String methodName = "getOfficeImage()";
//        logger.info(methodName+"called for id: "+id);
//        return officeGeoService.getOfficeById(id)
//                .map(office -> ResponseEntity.ok()
//                        .header("Content-Type", "image/jpeg")
//                        .body(office.getBuildingImagePath())
//                .orElse(ResponseEntity.notFound().build());
//    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getOfficeImage(@PathVariable Long id) {
        final String methodName = "getOfficeImage()";
        logger.info(methodName + " called for id: " + id);

        return (ResponseEntity<byte[]>) officeGeoService.getOfficeById(id)
                .map(office -> {
                    try {
                        // 1. Get image path from DB
                        String imagePath = office.getBuildingImagePath();

                        if (imagePath == null || imagePath.isEmpty()) {
                            logger.warn("No image path found for office ID: " + id);
                            return ResponseEntity.notFound().build();
                        }

                        // 2. Read file bytes from disk
                        Path path = Paths.get(imagePath).normalize();
                        byte[] imageBytes = Files.readAllBytes(path);

                        // 3. Determine content type
                        String contentType = Files.probeContentType(path);
                        if (contentType == null) {
                            contentType = "application/octet-stream"; // fallback
                        }

                        // 4. Return image
                        return ResponseEntity.ok()
                                .header(HttpHeaders.CONTENT_TYPE, contentType)
                                .header(HttpHeaders.CONTENT_DISPOSITION,
                                        "inline; filename=\"" + path.getFileName().toString() + "\"")
                                .body(imageBytes);

                    } catch (IOException e) {
                        logger.error("Error reading image file for office ID: " + id, e);
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
