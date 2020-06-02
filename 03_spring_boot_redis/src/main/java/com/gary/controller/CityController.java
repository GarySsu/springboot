package com.gary.controller;

import com.gary.model.CityEntity;
import com.gary.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/city/{id}", method = RequestMethod.GET)
    public CityEntity findByid(@PathVariable("id") Integer id){
        return  cityService.findById(id);
    }

    @RequestMapping(value = "/city", method = RequestMethod.POST)
    public String insert(@RequestBody CityEntity cityEntity){
        cityService.insert(cityEntity);
        return "success";
    }

    @RequestMapping(value = "/city/{id}", method = RequestMethod.PUT)
    public CityEntity update(@RequestBody CityEntity cityEntity,@PathVariable("id") Integer id){
        return cityService.updateById(Integer.valueOf(id),cityEntity);
    }

    @RequestMapping(value = "/city/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") Integer id){
        cityService.deleteById(id);
    }

}
