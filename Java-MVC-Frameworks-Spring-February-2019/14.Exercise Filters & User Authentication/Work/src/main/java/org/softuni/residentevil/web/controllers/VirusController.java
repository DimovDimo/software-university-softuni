package org.softuni.residentevil.web.controllers;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.softuni.residentevil.domain.entities.Capital;
import org.softuni.residentevil.domain.model.binding.VirusAddBindingModel;
import org.softuni.residentevil.domain.model.binding.VirusBindingModel;
import org.softuni.residentevil.domain.model.binding.VirusEditBindingModel;
import org.softuni.residentevil.domain.model.service.VirusServiceModel;
import org.softuni.residentevil.domain.model.view.CapitalListViewModel;
import org.softuni.residentevil.domain.model.view.VirusListViewModel;
import org.softuni.residentevil.service.CapitalService;
import org.softuni.residentevil.service.VirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/viruses")
public class VirusController extends BaseController {

    private final CapitalService capitalService;
    private final VirusService virusService;
    private final ModelMapper modelMapper;

    @Autowired
    public VirusController(CapitalService capitalService, VirusService virusService, ModelMapper modelMapper) {
        this.capitalService = capitalService;
        this.virusService = virusService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public ModelAndView add(ModelAndView modelAndView, @ModelAttribute(name = "bindingModel") VirusAddBindingModel bindingModel) {

        modelAndView.addObject("bindingModel", bindingModel);
        List<CapitalListViewModel> capitals = this.getCapitals();
        modelAndView.addObject("capitals", capitals);

        return super.view("add-virus", modelAndView);
    }

    @PostMapping("/add")
    public ModelAndView addConfirm(
            @Valid
            @ModelAttribute(name = "bindingModel") VirusAddBindingModel bindingModel,
            BindingResult bindingResult,
            ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("bindingModel", bindingModel);
            modelAndView.addObject("capitals", this.getCapitals());
            return super.view("add-virus", modelAndView);
        }

        VirusServiceModel virusServiceModel = this.modelMapper.map(bindingModel, VirusServiceModel.class);
        this.allCapitals(virusServiceModel, bindingModel);
        this.virusService.addVirus(virusServiceModel);
//        try{
//            VirusServiceModel virusServiceModel = this.modelMapper.map(bindingModel, VirusServiceModel.class);
//            this.allCapitals(virusServiceModel, bindingModel);
//            this.virusService.addVirus(virusServiceModel);
//        } catch (Exception e){}

        return super.redirect("/");
    }

    @GetMapping("/show")
    public ModelAndView show(ModelAndView modelAndView) {
//        List<VirusListViewModel> viruses = getAllViruses();
//        modelAndView.addObject("viruses", viruses);

        return super.view("show", modelAndView);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(
            @PathVariable(name = "id") String id,
            ModelAndView modelAndView,
            @ModelAttribute(name = "bindingModel") VirusEditBindingModel bindingModel) {

        VirusServiceModel virusServiceModel = this.virusService.findById(id);
        if (error(virusServiceModel)) return super.view("error");
        modelAndView.addObject("capitals", this.getCapitals());

        return super.view("edit", modelAndView);
    }

    @PostMapping("/edit/{id}")
    public ModelAndView edinConfirm(
            @PathVariable(name = "id") String id,
            ModelAndView modelAndView,
            @Valid
            @ModelAttribute(name = "bindingModel") VirusEditBindingModel bindingModel,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("bindingModel", bindingModel);
            modelAndView.addObject("capitals", this.getCapitals());
            return super.view("edit", modelAndView);
        }

        VirusServiceModel virusServiceModel = this.modelMapper.map(bindingModel, VirusServiceModel.class);
        this.allCapitals(virusServiceModel, bindingModel);
        this.virusService.editVirus(virusServiceModel);
        //try{
        //    VirusServiceModel virusServiceModel = this.modelMapper.map(bindingModel, VirusServiceModel.class);
        //    this.allCapitals(virusServiceModel, bindingModel);
        //    this.virusService.editVirus(virusServiceModel);
        //} catch (Exception e){}

        return super.redirect("/viruses/show");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete( @PathVariable(name = "id") String id, ModelAndView modelAndView){

        this.virusService.deleteVirusById(id);

        return super.redirect("/viruses/show");
    }

    @GetMapping(value = "/all-viruses", produces = "application/json")
    @ResponseBody
    public Object viruses(){

        return this.virusService.findAllViruses()
                .stream()
                .map(v->this.modelMapper.map(v, VirusListViewModel.class))
                .collect(Collectors.toList());
    }


    @GetMapping(value = "/all-capitals", produces = "application/json")
    @ResponseBody
    public Object capitals(){
        return this.capitalService.findAllCapitals()
                .stream().map(c -> this.modelMapper.map(c, CapitalListViewModel.class))
                .collect(Collectors.toList());
    }

    private List<CapitalListViewModel> getCapitals() {
        return this.capitalService.findAllCapitals()
                .stream()
                .map(c -> this.modelMapper.map(c, CapitalListViewModel.class))
                .collect(Collectors.toList());
    }

    private boolean error(VirusServiceModel virusServiceModel) {
        if (virusServiceModel == null) {
            return true;
        }
        return false;
    }

    private List<VirusListViewModel> getAllViruses() {
        return this.virusService.getAllViruses()
                .stream()
                .map(v -> this.modelMapper.map(v, VirusListViewModel.class))
                .collect(Collectors.toList());
    }

    private void allCapitals(VirusServiceModel virusServiceModel, VirusBindingModel bindingModel) {
        List<Capital> capitalList = new ArrayList<>();
        for (String capitalId : bindingModel.getCapitalList()) {
            Capital entity;
            try {
                entity = this.capitalService.findById(capitalId);
            } catch (NotFoundException e) {
                continue;
            }
            capitalList.add(entity);
        }
        virusServiceModel.setCapitalList(capitalList);
    }
}

