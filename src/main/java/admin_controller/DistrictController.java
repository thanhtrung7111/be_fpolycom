package admin_controller;

import dto.district.AdminDistrictRequestDTO;
import dto.district.AdminDistrictResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.data_return.DataReturnService;
import service.district.DistrictService;

@RestController
@RequestMapping(value = "/admin")
public class DistrictController {

    @Autowired
    DistrictService districtService;

    @Autowired
    DataReturnService dataReturnService;

    @GetMapping(value = "/district/all")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(dataReturnService.success(districtService.getAllData()));
    }

    @PostMapping(value="/district/create")
    public ResponseEntity<Object> getNew(@RequestBody AdminDistrictRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(districtService.postData(request)));
    }
    @PostMapping(value="/district/update")
    public ResponseEntity<Object> getUpdate(@RequestBody AdminDistrictRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(districtService.updateData(request)));
    }
    @PostMapping(value="/district/delete")
    public ResponseEntity<Object> getDelete(@RequestBody AdminDistrictRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(districtService.deleteData(request)));
    }


}
