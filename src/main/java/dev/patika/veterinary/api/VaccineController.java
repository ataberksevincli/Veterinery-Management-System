package dev.patika.veterinary.api;

import dev.patika.veterinary.business.abstracts.IAnimalService;
import dev.patika.veterinary.business.abstracts.IVaccineService;
import dev.patika.veterinary.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinary.core.result.Result;
import dev.patika.veterinary.core.result.ResultData;
import dev.patika.veterinary.core.utilies.ResultHelper;
import dev.patika.veterinary.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.veterinary.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.veterinary.dto.response.CursorResponse;
import dev.patika.veterinary.dto.response.vaccine.VaccineDtoWithoutAnimal;
import dev.patika.veterinary.dto.response.vaccine.VaccineResponse;
import dev.patika.veterinary.entity.Vaccine;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/vaccines")
public class VaccineController {

    private final IVaccineService vaccineService;
    private final IModelMapperService modelMapper;
    private final IAnimalService animalService;

    @Autowired
    public VaccineController(IVaccineService vaccineService, IModelMapperService modelMapper, IAnimalService animalService) {
        this.vaccineService = vaccineService;
        this.modelMapper = modelMapper;
        this.animalService = animalService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> save(@Valid @RequestBody VaccineSaveRequest vaccineSaveRequest){
        Vaccine saveVaccine = this.modelMapper.forRequest().map(vaccineSaveRequest,Vaccine.class);
        return ResultHelper.created(this.modelMapper.forResponse().map(this.vaccineService.save(saveVaccine),VaccineResponse.class));
    }

    /*@PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> save(@Valid @RequestBody VaccineSaveRequest vaccineSaveRequest) {

        Animal animal = animalService.get(vaccineSaveRequest.getAnimalId());
        vaccineSaveRequest.setAnimalId(null);

        Vaccine saveVaccine = modelMapper.forRequest().map(vaccineSaveRequest, Vaccine.class);
        saveVaccine.setAnimal(animal);

        Vaccine savedVaccine = vaccineService.save(saveVaccine);
        return ResultHelper.created(modelMapper.forResponse().map(savedVaccine, VaccineResponse.class));
    }*/

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> get(@PathVariable("id") int id){
        Vaccine vaccine = this.vaccineService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(vaccine,VaccineResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<VaccineResponse>> cursor(
            @RequestParam(name = "page",required = false,defaultValue = "100") int page,
            @RequestParam(name = "pageSize",required = false,defaultValue = "100") int pageSize
    ){
        Page<Vaccine> vaccinePage = this.vaccineService.cursor(page, pageSize);
        Page<VaccineResponse> vaccineResponsePage = vaccinePage
                .map(vaccine -> this.modelMapper.forResponse().map(vaccine,VaccineResponse.class));
        return ResultHelper.cursor(vaccineResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> update(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest){
        Vaccine updateVaccine = this.modelMapper.forRequest().map(vaccineUpdateRequest,Vaccine.class);

        //Animal animal = this.animalService.get(vaccineUpdateRequest.getAnimal().getId());
        //updateVaccine.setAnimal(animal);

        this.vaccineService.update(updateVaccine);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateVaccine,VaccineResponse.class));
    }

    /*@GetMapping("/animal/{animalId}")
    public ResponseEntity<List<Vaccine>> findByAnimalId(@PathVariable Integer animalId) {
        List<Vaccine> vaccines = vaccineService.findByAnimalId(animalId);
        return ResponseEntity.ok(vaccines);
    }*/

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<VaccineDtoWithoutAnimal>> findByAnimalId(@PathVariable Integer animalId) {
        List<VaccineDtoWithoutAnimal> vaccineDtoWithoutAnimals = vaccineService.findByAnimalId(animalId);
        return ResponseEntity.ok(vaccineDtoWithoutAnimals);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.vaccineService.delete(id);
        return ResultHelper.ok();
    }

    @GetMapping("/search/by-protection-date-range")
    public ResponseEntity<List<VaccineResponse>> findVaccinesByProtectionDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<VaccineResponse> responses = vaccineService.findVaccinesByProtectionDateRange(startDate, endDate);
        return ResponseEntity.ok(responses);
    }
}
