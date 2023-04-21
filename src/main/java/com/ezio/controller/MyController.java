package com.ezio.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ezio.model.MyData;
import com.ezio.repository.MyRepository;

@Controller
public class MyController {
  @Autowired
  private MyRepository myRepository;

  @PostMapping("/save-data")
  public void saveData(@RequestParam("name") String name,
                       @RequestParam("email") String email,
                       @RequestParam("image") MultipartFile imageFile) throws IOException {
    MyData myData = new MyData();
    myData.setName(name);
    myData.setEmail(email);
    myData.setImage(imageFile.getBytes());
    myRepository.save(myData);
  }
}
