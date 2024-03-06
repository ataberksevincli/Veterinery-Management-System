package dev.patika.veterinary.api;

import dev.patika.veterinary.business.abstracts.IAnimalService;
import dev.patika.veterinary.business.abstracts.ICustomerService;
import dev.patika.veterinary.business.concretes.AnimalManager;
import dev.patika.veterinary.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinary.core.result.Result;
import dev.patika.veterinary.core.result.ResultData;
import dev.patika.veterinary.core.utilies.Msg;
import dev.patika.veterinary.core.utilies.ResultHelper;
import dev.patika.veterinary.dao.AnimalRepository;
import dev.patika.veterinary.dto.request.animal.AnimalSaveRequest;
import dev.patika.veterinary.dto.request.animal.AnimalUpdateRequest;
import dev.patika.veterinary.dto.response.CursorResponse;
import dev.patika.veterinary.dto.response.animal.AnimalResponse;
import dev.patika.veterinary.entity.Animal;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/animals")
public class AnimalController {
    private final IAnimalService animalService;
    private final AnimalManager animalManager;
    private final IModelMapperService modelMapper;
    private final ICustomerService customerService;
    private AnimalRepository animalRepository;


    @Autowired
    public AnimalController(IAnimalService animalService, AnimalManager animalManager, IModelMapperService modelMapper, ICustomerService customerService, AnimalRepository animalRepository) {
        this.animalService = animalService;
        this.animalManager = animalManager;
        this.modelMapper = modelMapper;
        this.customerService = customerService;
        this.animalRepository = animalRepository;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest){
        Animal saveAnimal = this.modelMapper.forRequest().map(animalSaveRequest,Animal.class);
        return ResultHelper.created(this.modelMapper.forResponse().map(this.animalService.save(saveAnimal),AnimalResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> get(@PathVariable("id") int id){
        Animal animal = this.animalService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(animal,AnimalResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AnimalResponse>> cursor(
            @RequestParam(name = "page",required = false,defaultValue = "0") int page,
            @RequestParam(name = "pageSize",required = false,defaultValue = "10") int pageSize
    ){
        Page<Animal> animalPage = this.animalService.cursor(page, pageSize);
        Page<AnimalResponse> animalResponsePage = animalPage
                .map(animal -> this.modelMapper.forResponse().map(animal,AnimalResponse.class));
        return ResultHelper.cursor(animalResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest){
        Animal updateAnimal = this.modelMapper.forRequest().map(animalUpdateRequest,Animal.class);
        this.animalService.update(updateAnimal);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateAnimal,AnimalResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.animalService.delete(id);
        return ResultHelper.ok();
    }

    // CUSTOMER NAME FILTER
    @GetMapping("/search/by-customer-name")
    public ResponseEntity<List<AnimalResponse>> findAnimalsByCustomerName(@RequestParam String name) {
        List<AnimalResponse> responses = animalService.findAnimalsByCustomerName(name);
        return ResponseEntity.ok(responses);
    }
    // ANIMAL NAME FILTER
    @GetMapping("/search/by-animal-name")
    public ResponseEntity<List<AnimalResponse>> findAnimalsByName(@RequestParam String name) {
        List<AnimalResponse> responses = animalService.findAnimalsByName(name);
        return ResponseEntity.ok(responses);
    }
    // FILTER WITH CUSTOMER ID
    @GetMapping("/by-customer/{customerId}")
    public ResponseEntity<List<AnimalResponse>> findAllAnimalsByCustomerId(@PathVariable int customerId) {
        List<AnimalResponse> responses = animalService.findAllAnimalsByCustomerId(customerId);
        return ResponseEntity.ok(responses);
    }
}
